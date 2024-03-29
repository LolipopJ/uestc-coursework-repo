# 二叉树的链式存储、序列化和反序列化

**一切代码测试于 C-FREE 5**

## 实验介绍

- 二叉树是由结点指针将多个结点关联起来的抽象数据结构，是存在于内存中的，不能进行持久化存储。

- 如果需要将一颗二叉树的结构持久化保存在磁盘文件中，需要将其转换为字符串并保存到文件中。

- **序列化**是对二叉树进行先序遍历产生一个字符序列，与一般的先序遍历不一样，需要记录空结点用'#'字符表示，并且假设序列中没有结点的值为'#'。

- **反序列化**是通过先序序列化的结果串str构建对应的二叉树，其过程是用i从头扫描str；采用先序方法，当i超界时返回NULL；否则当遇到'#'字符时返回NULL，当遇到其它字符时，创建一个结点，可以采用递归的方法构造该二叉树；也可以采用非递归方法构造该二叉树。

## 实验内容

1. 采用**二叉链式**存储创建二叉树B1

    ![func1](https://github.com/JasonSun2018/img-folder/blob/master/cpp-BinaryTree/func1.png)

2. 采用**先序序列化**显示输出序列，并存储到文件中

    ![func2](https://github.com/JasonSun2018/img-folder/blob/master/cpp-BinaryTree/func2.png)

    ![func2-result](https://github.com/JasonSun2018/img-folder/blob/master/cpp-BinaryTree/func2-result.png)

3. 从文件中读出序列，并**反序列化的递归方法**构造二叉树B2

    ![func3](https://github.com/JasonSun2018/img-folder/blob/master/cpp-BinaryTree/func3.png)

4. 从文件中读出序列，并**反序列化的非递归方法**构造二叉树B3

    ![func4](https://github.com/JasonSun2018/img-folder/blob/master/cpp-BinaryTree/func4.png)

5. 使用非递归方法输出二叉树**中序遍历**序列

    ![func5](https://github.com/JasonSun2018/img-folder/blob/master/cpp-BinaryTree/func5.png)

6. 使用非递归方法输出二叉树**后序遍历**序列

    ![func6](https://github.com/JasonSun2018/img-folder/blob/master/cpp-BinaryTree/func6.png)

7. 销毁释放二叉树B1，B2，B3

    ![func7](https://github.com/JasonSun2018/img-folder/blob/master/cpp-BinaryTree/func7.png)
