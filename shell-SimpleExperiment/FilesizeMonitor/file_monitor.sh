#!/bin/sh
# 动态检测指定文件的状态信息，当文件的大小发生改变时，给出提示信息，并继续进行检测

# 检查命令格式
if [ $# != 0 ]
then
    echo "Usage: file_monitor"
    exit 1
else
    clear
    echo -n "Input filename: "
fi

# 获取文件名
read filename

# 获取文件初始大小
filesize=`ls -l ${filename} | cut -d' ' -f5`
echo -e "file \033[034m${filename}\033[0m size is \033[036m${filesize}\033[0m"

# 监测文件大小变化
checksize()
{
    nowsize=`ls -l ${filename} | cut -d' ' -f5`
    if [ "${nowsize}" == "${filesize}" ]
    then
        echo -e "file \033[034m${filename}\033[0m size \033[036mnot changed\033[0m"
        return 0
    else
        echo -e "file \033[034m${filename}\033[0m size \033[031mchanged\033[0m"
        filesize="${nowsize}"
        echo -e "file \033[034m${filename}\033[0m size is \033[036m${filesize}\033[0m"
        return 1
    fi
}

# Main
# 声明整型变量计数
declare -i count_time=0
declare -i changed_time=0

# 当文件连续十次监测无改变或改变两次时监测结束
while [ ${count_time} -ne 10 -a ${changed_time} -ne 2 ]
do
    sleep 5
    checksize
    if [ $? -eq 0 ]
    then
        ((count_time++))
    else
        count_time=0
        ((changed_time++))
    fi
done

# 提示用户并清屏退出
if [ ${count_time} -eq 10 ]
then
    echo "aim file not changed for 50s!"
else
    echo "aim file changed twice!"
fi
clear
