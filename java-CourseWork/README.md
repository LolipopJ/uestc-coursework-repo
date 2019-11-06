# Java编程课程作业

Last updated: 2019/11/6

## 索引

1. 编写9 X 9的乘法口诀表的程序

    NineNine.java

2. 计算一个整数的各位数字之和。例如，整数 `20160907` ，则计算并显示2+0+1+6+0+9+0+7的值

    NumberSum.java

3. 编写类 `TestArray` ，只有一个main方法，该方法中，创建一个int类型的一维数组sim，从键盘输入任意的数据，并实现数组sim 元素从小到大排序，输出排序后的数组值

    TestArray.java

4. 编写类 `MyDate` 具有属性年月日，要求一个构造函数初始化属性年月日，提供重置日期、增加日期（考虑闰年闰月）、输出日期等成员函数

    MyDate.java

5. 编写类 `ArraySort` ，该类有一个int类型一维数组sim的成员变量，一个setOrder()的成员方法，一个带有一个参数的构造方法对sim数组初始化，方法setOrder没有参数和返回值，实现成员变量sim中的元素升序排序。另外类 `TestArray` ，只有一个main方法，该方法中，从键盘输入任意的数据但int类型的一维数组，从键盘输入任意的数据，并在创建一个ArraySort对象时，构造函数使用该数组做参数初始化sim成员变量， 并调用setOrder实现元素从小到大排序，并输出排序结果

    classarray/TestArray.java

6. 创建一个类 `Point` ,有成员变量x,y,它们都是int类型,该类有四个成员方法SetX(int),setY(int),getPoint()和movePoint(int,int).setx(int)和setY(int)方法是设置成员变量x和y的值，getPoint（）则是获得由x，y构成的坐标点，movePoint（int，int）带两个int参数，用来修改x，y构成的坐标点。`point` 类有一个构造方法，不带参数，为x，y设置原点值。另一个类为 `TestPoint` ，有 main方法用来对 `point` 类的实例进行测试。要求为其实例设置（0,0）坐标点，在移动到（10,20）坐标点上，并输出实例调用相应方法的结果

    TestPoint.java

7. 编写程序，找出1\~n以内的所有素数。要求使用数组元素的下标从1\~n以内表示这些数值，数组元素的值作为素数的标志。其中用数组元素的值为0来表示该元素的下标的数值是素数，用1来表示该元素的下标的数值不是素数，并输出这些素数

    PrimeNumber.java

8. 有类 `Person` 和 `Student` ，它们之间存在继承关系，Person有成员变量name,sex,age，类型分别为String,char,int，构造方法Person（String,char,int）用来对成员变量进行初始化，成员方法setData（String,char,int）设置成员变量name,sex,age的值，getData()是不带参数且返回值是name,sex和age的值构成的字符串的成员方法；`Student` 是 `Person` 的子类，在 `Student` 中有int类型的sID和classNo用来表示学生的学号和班级号，它有带有5个参数的成员方法setData()和不带参数的方法getData()，setData()设置成员变量的值，getData()是返回五个成员变量值构成的字符串

    Person.java

9. 抽象类 `Person` 定义如下：

        abstract class Person{
            String name；
            char sex；
            int age；
            abstract void setData(String name,char sex,int age);
            abstract String getData();
        }

    类 `Student` 和类 `Teacher` 均是抽象类 `Person` 的子类，类 `Student` 有成员变量name,sex,age,sID,speciality，其中sID表示学生学号，speciality表示学生专业；类 `Teacher` 有成员变量name,sex,age,tID,department，其中tID表示教师的编号，department表示教师所在部门，请编写类 `Student` 和类 `Teacher` 所需基本功能

    abstractperson/Person.java

10. 创建一个 `Print接口` ，在其中定义一个打印方法print，再创建两个类分别实现这个接口

    interfacetest/PrintTest.java

11. 创建一个 `Person接口` ，它有方法setData()和getData()对属性name,sex,birthday赋值和获得这些属性组成的字符串信息；创建类 `Student` 实现 `Person接口` ，并重写setData()成员方法，设置学生属性的成员变量sID、speciality设置值，重写getData()获得学生成员变量值所组成的字符信息

    interfacetest/Person.java

12. 有几何形状边数为n及可计算面积area的 `Shape` 类，其子类 `Triangle` 类及 `Rectangle` 类实现几何形状三角形和矩形面积area计算，利用前三个形状类实现柱体 `Pillar` 类的体积计算，并在 `PillarTest` 类中实现对某一柱体的体积计算

    PillarTest.java

13. 编写程序，创建学生成绩中所涉及的类： `Student` 类、 `Teacher` 类、 `Course` 类，并由 `Grade` 类将 `Student` 类、 `Teacher` 类和 `Course` 类关联起来，由 `GradeTest` 类对以上四个类进行测试

    grade/GradeTest.java

14. 编写一个含有ArithmeticException、IndexOutOfBoundsException和NullPointerException 异常处理程序

    throwable/TayTest.java

15. 编写程序实现从键盘输入数据，保存到指定文件里

    savetofile/SaveToFile.java

16. 编写程序，在面板上显示三个按钮，按钮上分别显示是：`set red`，`set green`，`set blue`，按下按钮时修改窗口颜色

    gui/SetColor.java

17. 编写类似windows“记事本”的界面程序

    gui/NoteBook.java
