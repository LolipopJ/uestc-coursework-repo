#!/bin/sh
# 将用户从键盘输入的文本附加到一个指定的文件中。
# 如果该文件不存在，则新建立该文件；如果该文件已经存在，则把键盘输入的文本附加到该文件后面。
# 输入内容及行数不限定，以空行表示输入结束。运行结束时显示该文件内容。

# 判断输入格式是否正确
if [ $# != 1 ]
then
    echo "Usage: exam1 filename"
    exit 1
else
    filename=$1
fi

# 输入数据并添加到目标文件的末尾
echo "Input the words you want to save below, blank to quit:"
read INPUT
while test -n "${INPUT}"
do
    echo ${INPUT} >> ${filename}
    read INPUT
done
echo "Save successfully!"

# 打印文件内容
echo -e "filename: \033[036m${filename}\033[0m"
cat ${filename}
