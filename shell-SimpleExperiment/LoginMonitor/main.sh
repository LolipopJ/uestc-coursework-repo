#!/bin/sh
# 运行格式为 usr_monitor username
# 其中 username 是用户指定的任意用户名
# 程序首先列出当前系统已登录的用户名单，再检查该用户是否已登录。
# 如果已登录，则显示对应信息；如果未登录，则等待该用户登录，直到指定用户登录到系统为止。

# 检查输入格式是否正确
if [ $# != 1 ]
then
    echo "Usage: usr_monitor username"
    exit 1
else
    username=$1
fi

# 查找目标用户是否登录
check_user()
{
    # 打印现在登录系统的用户名单
    who > users
    echo -e "\033[036mLogin users:\033[0m"
    echo `users`
    echo '------------------------------------------------------------------'

    # 检查用户是否登录
    check=`users | grep ${username}`
    rm users
    if [ -n "${check}" ]
    then
        echo -e "user \033[034m${username}\033[0m is \033[036mlog on\033[0m"
        return 0
    else
        echo -e -n "\033[031mwaiting\033[0m user \033[034m${username}\033[0m..."
        return 1
    fi
}

# Main
check_user
while [ $? -eq 1 ]
do
    sleep 5
    echo -e '\n------------------------------------------------------------------'
    check_user
done
