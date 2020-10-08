# 代码参考 [facenet-pytorch库的简单使用](https://www.cnblogs.com/muyisun/p/13338098.html)
import os

from PIL import Image, ImageFont, ImageDraw
from facenet_pytorch import MTCNN, InceptionResnetV1
import torch
from torch.utils.data import DataLoader
from torchvision import datasets
import pandas as pd


def collate_fn(x):
    return x[0]


# 使用 windows 操作系统时，设置 DataLoader 的 num_workers 为 0，其它操作系统为 4
workers = 0 if os.name == 'nt' else 4
# 当设备支持 cuda 加速时，使用 cuda 进行机器学习，否则使用 cpu
device = torch.device('cuda:0' if torch.cuda.is_available() else 'cpu')
# 打印出当前机器学习使用的设备
print('Program is running on device: {}'.format(device))
# 使用 facenet_pytorch 预设的训练参数和权重
# 由于提供的预训练模型在 160x160px 的图片上进行，故使用该分辨率的图片将获得最佳效果
mtcnn = MTCNN(
    image_size=160,
    margin=0,
    min_face_size=20,
    thresholds=[0.6, 0.7, 0.7],
    factor=0.709,
    post_process=True,
    device=device
)
# 使用 InceptionResnetV1 提供的预训练模型
# 由于提供的两个数据集中，VGGFace2 数据集训练结果较好(LFW accuracy = 0.9965)
# 故在此处使用 VGGFace2 数据集进行训练
resnet = InceptionResnetV1(pretrained='vggface2').eval().to(device)
# 将每个人的照片分别放在独立的文件夹下，并以其姓名命名文件夹，格式例如：
'''
--database
    |--origin
        |--Ann_Veneman
            |--Ann_Veneman_0001.jpg
            |--Ann_Veneman_0002.jpg
            |--...
        |--Catherine_Zeta-Jones
            |--Catherine_Zeta-Jones_0001.jpg
            |--Catherine_Zeta-Jones_0002.jpg
            |--...
        |--...
'''
# 加载图片数据集
dataset = datasets.ImageFolder('./database/origin')
dataset.idx_to_class = {i: c for c, i in dataset.class_to_idx.items()}
loader = DataLoader(dataset, collate_fn=collate_fn, num_workers=workers)
# aligned 存储 mtcnn 截取的人脸，names 存储对应的姓名
aligned = []
names = []
i = "1"
for x, y in loader:
    # dataset.idx_to_class[y] 为文件夹名，也即人脸对应的姓名
    base_path = './database/aligned/{}'.format(dataset.idx_to_class[y])
    # 目录不存在时，创建文件夹并置计数器 i 为 1
    if not os.path.exists(base_path):
        i = "1"
        os.mkdir(base_path)
    # 将识别出的人脸保存到 ./database/aligned/name 目录下
    # 保存名保持统一，格式例如 name_0001_aligned
    save_name = dataset.idx_to_class[y]+"_"+i.zfill(4)+"_aligned"
    # x_aligned 为截取到的人脸图像，prob 为检测到人脸的概率
    x_aligned, prob = mtcnn(x, return_prob=True,
                            save_path=base_path+'/{}.jpg'.format(save_name))
    # 计数器 i 自增
    i = str(int(i)+1)
    # 将结果打印到屏幕上
    # 并将结果添加到 aligned 和 names 数组中
    if x_aligned is not None:
        print('{0} detected with probability: {1}'.format(save_name, prob))
        aligned.append(x_aligned)
        names.append(dataset.idx_to_class[y])
# 提取所有人脸的特征向量
aligned = torch.stack(aligned).to(device)
embeddings = resnet(aligned).detach().cpu()
# 计算混淆矩阵并打印到屏幕上
dists = [[(e1 - e2).norm().item() for e2 in embeddings] for e1 in embeddings]
print(pd.DataFrame(dists, columns=names, index=names))
# 保存存储有人脸特征向量的数据集到当前目录
torch.save(embeddings, 'embeddings.pt')
torch.save(names, 'names.pt')

# 对新的照片进行人脸识别
# 设置 keep_all 为 True，即 mtcnn 返回所有检测到的人脸
mtcnn = MTCNN(
    image_size=160,
    margin=0,
    min_face_size=20,
    thresholds=[0.6, 0.7, 0.7],
    factor=0.709,
    post_process=True,
    keep_all=True,
    device=device
)
# 载入数据集
embeddings = torch.load('embeddings.pt').to(device)
names = torch.load('names.pt')


# 提取图片中的所有人脸，基于 facenet 模型，根据数据集标注人脸识别结果到图片中
def detect_frame(img, img_name):
    font_style = ImageFont.truetype("simhei.ttf", 12, encoding="utf-8")
    color_style = (0, 85, 255)
    faces = mtcnn(img)
    boxes, _ = mtcnn.detect(img)
    frame_draw = img.copy()
    draw = ImageDraw.Draw(frame_draw)
    print('Image {0} recognize {1} faces.'.format(img_name, len(boxes)))
    for i, box in enumerate(boxes):
        draw.rectangle(box.tolist(), outline=color_style)
        face_embedding = resnet(faces[i].unsqueeze(0).to(device))
        distance = [(face_embedding - embeddings[i]).norm().item() for i in range(embeddings.size()[0])]
        index = distance.index(min(distance))
        name = names[index]
        draw.text((int(box[0]), int(box[1])), str(name), fill=color_style, font=font_style)
    return frame_draw


# 加载图片数据集
dataset = datasets.ImageFolder('./testing/origin')
dataset.idx_to_class = {i: c for c, i in dataset.class_to_idx.items()}
loader = DataLoader(dataset, collate_fn=collate_fn, num_workers=workers)
i = "1"
for x, y in loader:
    base_path = './testing/result/{}'.format(dataset.idx_to_class[y])
    if not os.path.exists(base_path):
        i = "1"
        os.mkdir(base_path)
    save_name = dataset.idx_to_class[y]+"_"+i.zfill(4)+"_result"
    frame_result = detect_frame(x, save_name)
    frame_result.save(base_path+'/{}.jpg'.format(save_name), quality=100)
    i = str(int(i)+1)
