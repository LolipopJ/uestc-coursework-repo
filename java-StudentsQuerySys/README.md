# 学生选课查询系统

Last updated: 2019/12/9

## 实验内容

1. 定义一个名为[Person](./Person.java)的类，含有两个String类型的成员变量name和sex，一个int类型的成员变量age，它们用protect修饰符，分别实现getXXX()访问方法和setXXX()修改方法；实现构造方法Person(String name,String sex,int age)；实现成员方法display()显示输出类的成员变量的信息。编写独立的测试文件[TestPerson](./TestPerson.java).

2. 定义学生类[Student](./Student.java)和教师类[Teacher](./Teacher.java)，**继承自Person类**。新增成员变量，用private修饰。编写独立的测试文件[TestStudent](./TestStudent.java)和[TestTeacher](./TestTeacher.java).

3. 定义课程类[Course](./Course.java)，排课类[Schedule](./Schedule.java)和选课类[Electivecourse](./Electivecourse.java)，新增成员变量，用private修饰。编写独立的测试文件[TestCourse](./TestCourse.java)，[TestSchedule](./TestSchedule.java)和[TestElcourse](./TestElcourse.java).

4. 定义一个名为[Myfile](./Myfile.java)的文件类，含有一个file类型的成员变量opfile，分别实现getfile()访问方法返回opfile、readfile(对象数组)方法和Writefile(对象)方法；实现构造方法Myfile(文件名)，用于打开已有文件，如果不存在，则新建文件；readfile(对象数组)将文件的信息读到数组中，Writefile(对象)将对象写入文件中。针对前面的6种类，分别**重载**6个readfile和Writefile成员方法。编写独立的测试文件[TestMyfile](./TestMyfile.java).
  采用**序列化**和**反序列化**的方法实现对象（数组）的存储与读取。

5. 编程实现分别输入5条记录的教师、学生、课程、排课、选课等信息，并利用文件类的方法，写入文件中；利用编写的readfile()方法，将文件的信息分别读入对象数组中。编写程序任意输入学生的学号，查询显示该学生所选课程的名称、教师、上课地点。
   - 终端界面[QuerySystem](./QuerySystem.java)
   - ~~图形界面[QuerySystemGui](./QuerySystemGui.java)~~（暂未实现）
