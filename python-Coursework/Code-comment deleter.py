# 删除该目录下的a.py文件中的注释内容

with open("a.py", 'r', encoding='utf-8') as fo_r:
    print("原文件内容：\n" + fo_r.read() + "\n")

with open("a.py", 'r', encoding='utf-8') as fo:
    lines = fo.readlines()

with open("a.py", 'w', encoding='utf-8') as fo_w:
    for line in lines:
        if len(line) != 1:
            s = ""
            if line[0] == "#":
                s = s + "\n"
                fo_w.write(s)
                continue
            for i in line:
                if i == "#":
                    s = s + "\n"
                    break
                s = s + i
            fo_w.write(s)
        else:
            fo_w.write(line)

with open("a.py", 'r', encoding='utf-8') as fo_r:
    print("修改后内容：\n" + fo_r.read())
