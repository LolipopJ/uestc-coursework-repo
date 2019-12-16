# 学生选课查询系统

## 实验内容

1. 定义一个名为[Person](./Person.java)的类，含有两个String类型的成员变量name和sex，一个int类型的成员变量age，它们用protect修饰符，分别实现getXXX()访问方法和setXXX()修改方法；实现构造方法Person(String name,String sex,int age)；实现成员方法display()显示输出类的成员变量的信息。编写独立的测试文件[TestPerson](./TestPerson.java).

2. 定义学生类[Student](./Student.java)和教师类[Teacher](./Teacher.java)，**继承自Person类**。新增成员变量，用private修饰。编写独立的测试文件[TestStudent](./TestStudent.java)和[TestTeacher](./TestTeacher.java).

3. 定义课程类[Course](./Course.java)，排课类[Schedule](./Schedule.java)和选课类[Electivecourse](./Electivecourse.java)，新增成员变量，用private修饰。编写独立的测试文件[TestCourse](./TestCourse.java)，[TestSchedule](./TestSchedule.java)和[TestElcourse](./TestElcourse.java).

4. 定义一个名为[Myfile](./Myfile.java)的文件类，含有一个file类型的成员变量opfile，分别实现getfile()访问方法返回opfile、readfile(对象数组)方法和Writefile(对象)方法；实现构造方法Myfile(文件名)，用于打开已有文件，如果不存在，则新建文件；readfile(对象数组)将文件的信息读到数组中，Writefile(对象)将对象写入文件中。针对前面的6种类，分别**重载**6个readfile和Writefile成员方法。编写独立的测试文件[TestMyfile](./TestMyfile.java).
  采用**序列化**和**反序列化**的方法实现对象（数组）的存储与读取。

5. 编程实现分别输入5条记录的教师、学生、课程、排课、选课等信息，并利用文件类的方法，写入文件中；利用编写的readfile()方法，将文件的信息分别读入对象数组中。编写程序任意输入学生的学号，查询显示该学生所选课程的名称、教师、上课地点。
   - 终端界面[QuerySystem](./QuerySystem.java)
   - 图形界面[QuerySystemGui](./QuerySystemGui.java)

### 更新日志

#### 2019/12/16

- 实现了QuerySystemGui实验需求的查询功能
- QuerySystemGui
  - 现在表格有了滚动栏
  - 为表格设置了字体样式
  - 输入面板添加了数据验证与相应提示
  - 为代码增加了注释
  - 以及更多小更新

#### 2019/12/15

- 基本实现了QuerySystemGui界面设计

#### 2019/12/14

- 搭建了基础的QuerySystemGui界面，并实现了一些功能
- 现在QuerySystem的默认存储数据文件路径为工作路径

#### 2019/12/12

- 修复了当Myfile写入文件时，若目标文件存在但没有存储数据时发生异常的bug

### 亟待完成

- 重写Myfile中的对象数组相关内容，用ArrayList<>替代
- 优化QuerySystemGui代码结构，实现JTable、JTextField等GUI界面元素的复用
- 优化QuerySystemGui逻辑结构，减少文件与变量的读写操作，提升性能
- 美化QuerySystemGui界面
- 实现QuerySystemGui输入面板输入数据后逻辑自增，增加操作效率
