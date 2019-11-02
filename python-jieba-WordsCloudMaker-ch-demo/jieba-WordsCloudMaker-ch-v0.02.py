from tkinter import *
import tkinter.filedialog
from tkinter import ttk
from datetime import *
from wordcloud import WordCloud, STOPWORDS
import jieba
import numpy as np
from PIL import Image


w = Tk()
w.title('中文词云Maker v0.02')
w.geometry('400x560')


#  读取txt类型的文件
def readtxt():
    filename = tkinter.filedialog.askopenfilename()
    if filename != '' and filename[-3:] == 'txt':
        rtxt_lb.config(text='已选择文件: '+filename)
        rtxt_btn.config(text='重新选择txt文件')
    else:
        rtxt_lb.config(text='请选择txt文件!')


#  读取图片类型的文件
def readimg():
    filename = tkinter.filedialog.askopenfilename()
    if filename != '' and filename[-3:] in ['jpg', 'png']:
        rimg_lb.config(text='已选择文件: '+filename)
        rimg_btn.config(text='重新选择图片文件')
    else:
        rimg_lb.config(text='请选择图片文件!')


def choosefont(*args):
    del font[1]
    if choice_list.get() == '微软雅黑':
        font.insert(1, '微软雅黑/msyh.ttc')
    elif choice_list.get() == '宋体':
        font.insert(1, 'simsun.ttc')
    elif choice_list.get() == '黑体':
        font.insert(1, 'simhei.ttf')
    else:
        font.insert(1, 'Arial/arial.ttf')


#  制作词云的主函数
def maker_do():
    #  读取label中存储的文件目录信息
    if rtxt_lb.cget('text')[:1] == '已' and rimg_lb.cget('text')[:1] == '已':
        txtfile = rtxt_lb.cget('text')[7:]
        imgfile = rimg_lb.cget('text')[7:]
        #  默认输出目录为图片所在的目录,并命名
        now_time = str(datetime.now().strftime('-%Y%m%d-%H%M%S'))
        list_imgfile = list(imgfile)
        list_imgfile.insert(-4, now_time)
        savefile = ''.join(list_imgfile)
        #  修改label指示
        course_lb.config(text='制作中...')
    else:
        course_lb.config(text='请选择正确类型的文件！')
        return
    #  制作词云
    #  1|读取词云选项
    sc = scale.get()
    if sc > 10 or sc <= 0:
        course_lb.config(text='清晰度范围为1~10！')
        return
    mw = max_words.get()
    mfs = max_font_size.get()
    rs = random_state.get()
    if (mfs - rs) < 0:
        course_lb.config(text='字体大小变动范围应小于最大字体大小！')
        return
    if mw <= 0 or mfs <= 0 or rs <= 0:
        course_lb.config(text='词云设置选项均应大于0！')
        return
    #  2|读取txt文件内容到text中
    text = open(txtfile, 'r').read()
    #  3|利用jieba进行中文分词并生成字符串wl
    wl = ''.join(jieba.cut(text))
    #  4|图片设置蒙版
    coloring = np.array(Image.open(imgfile))
    #  5|生成图云
    wc = WordCloud(scale=sc, background_color='White', max_words=mw, mask=coloring,
                   max_font_size=mfs, random_state=rs, font_path=''.join(font), stopwords=stop_words)
    wc.generate(wl)
    #  6|保存到图片所在目录中
    wc.to_file(savefile)
    course_lb.config(text='已存储为文件: '+savefile)


#  在主界面置入读取txt文件的标签和按钮
rtxt_lb = Label(w, text='')
rtxt_lb.pack(ipady=3, pady=5)
rtxt_btn = Button(w, text='选择txt文件', command=readtxt)
rtxt_btn.pack(ipady=3, pady=5)
#  在主界面置入读取图片文件的标签和按钮
rimg_lb = Label(w, text='')
rimg_lb.pack(ipady=3, pady=5)
rimg_btn = Button(w, text='选择图片文件', command=readimg)
rimg_btn.pack(ipady=3, pady=5)
#  字体选项
Label(w, text='词云字体:').pack(pady=5)
choice = StringVar()
choice_list = ttk.Combobox(w, textvariable=choice)
choice_list['values'] = ('微软雅黑', '宋体', '黑体', 'Arial')
choice_list.current(0)   # 默认选项为微软雅黑
font = ['C:/WINDOWS/Fonts/', '微软雅黑/msyh.ttc']   # 默认字体为微软雅黑(常规)
choice_list.bind("<<ComboboxSelected>>", choosefont)
choice_list.pack(ipady=3)
#  生成词云选项
Label(w, text='词云清晰度:').pack(pady=5)
scale = IntVar()   # 图片清晰度
input_scale = Entry(w, textvariable=scale)
input_scale.pack(ipady=3)
Label(w, text='词云最大词数:').pack(pady=5)
max_words = IntVar()   # 最大词数
input_max_words = Entry(w, textvariable=max_words)
input_max_words.pack(ipady=3)
Label(w, text='字体最大大小:').pack(pady=5)
max_font_size = IntVar()   # 字体最大大小
input_max_font_size = Entry(w, textvariable=max_font_size)
input_max_font_size.pack(ipady=3)
Label(w, text='字体大小变动值:').pack(pady=5)
random_state = IntVar()   # 字体大小变动范围((max_font_size - random_state) ~ max_font_size)
input_random_state = Entry(w, textvariable=random_state)
input_random_state.pack(ipady=3)
scale.set(4)   # 设置默认清晰度为4
max_words.set(1000)   # 设置默认最大词数为1000
max_font_size.set(50)   # 设置默认字体最大大小为50
random_state.set(42)   # 设置默认字体大小变动范围为42
#  添加词云暂停词
stop_words = set(STOPWORDS)
stoped_words = ['said', '你好', '撤回', '表情', '图片', 'QQ', '红包']
for key in stoped_words:
    stop_words.add(key)
#  开始按钮和进程提示
start_btn = Button(w, text='制作词云', command=maker_do)
start_btn.pack(ipady=3, pady=5)
course_lb = Label(w, text='')
course_lb.pack(ipady=3, pady=5)

w.mainloop()
