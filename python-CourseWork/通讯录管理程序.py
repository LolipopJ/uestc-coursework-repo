def insert():
    name = input('请输入新建的联系人姓名: ')
    phone = input('请输入电话号码: ')
    telbook[str(name)] = str(phone)
    print("Records insert successfully!\nHere are all records:")
    print(telbook)


def delete():
    name = input('请输入要删除的联系人姓名: ')
    del telbook[name]
    print('Delete successfully!\nHere are new records:')
    print(telbook)


def change():
    name = input('请输入要修改号码的联系人姓名: ')
    for key in sorted(telbook.keys()):
        if str(name) == key:
            phone = input('请输入新的电话号码: ')
            telbook[str(key)] = str(phone)
            print('Change successfully!\nHere are new records:')
            print(telbook)
            return
    print('该联系人不存在，请查证！')


def show_all():
    print('Here are all records:')
    print(telbook)


def search():
    name = input('请输入联系人姓名: ')
    for key in sorted(telbook.keys()):
        if str(name) == key:
            print('联系人 '+key+' 的电话号码为: '+telbook[key])
            return
    print('该联系人不存在，请查证！')


telbook = {}
print('Here is Telbook System, choose a option to start!')
while 1:
    choice = input('1、新建联系人\n2、删除联系人\n3、修改联系人电话号码信息\n4、查询联系人电话信息\n5、显示已有所有联系人及电话号码信息\n0、退出系统\nChoose: ')
    if int(choice) == 1:
        insert()
    elif int(choice) == 2:
        delete()
    elif int(choice) == 3:
        change()
    elif int(choice) == 4:
        search()
    elif int(choice) == 5:
        show_all()
    elif int(choice) == 0:
        exit(1)
    else:
        print('请输入正确选项序号!')
    print('\n')
