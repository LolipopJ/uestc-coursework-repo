package coursework;

/**
 * @author Lolipop
 * @lastUpdate 2019/11/6
 */
public class Person {
    String name;
    char sex;
    int age;

    Person (String testName, char testSex, int testAge) {
        this.name = testName;
        this.sex = testSex;
        this.age = testAge;
    }

    private void setData(String testName, char testSex, int testAge) {
        this.name = testName;
        this.sex = testSex;
        this.age = testAge;
    }

    protected String getData () {
        return "Person name: "+this.name+"\nPerson sex: "+this.sex+"\nPerson age: "+this.age+"\n";
    }

    public static void main (String[] args) {
        Person testPerson = new Person("XiaoMing", '男', 16);
        System.out.print("test 1:\n" + testPerson.getData());

        testPerson.setData("WangGang", '女', 12);
        System.out.print("test 2:\n" + testPerson.getData());

        Student testStudent = new Student("XiaoHong", '女', 18, 20191028, 1001);
        System.out.print("test 3:\n" + testStudent.getData());

        testStudent.setData("AWei", '男', 21, 20191022, 1002);
        System.out.print("test 4:\n" + testStudent.getData());
    }

}

class Student extends Person {
    private int sID;
    private int classNo;

    Student(String testName, char testSex, int testAge, int testsID, int testClassNo) {
        super(testName, testSex, testAge);
        this.sID = testsID;
        this.classNo = testClassNo;
    }

    void setData (String testName, char testSex, int testAge, int testsID, int testClassNo) {
        this.name = testName;
        this.sex = testSex;
        this.age = testAge;
        this.sID = testsID;
        this.classNo = testClassNo;
    }

    @Override
    protected String getData () {
        return "Student name: "+this.name+"\nStudent sex: "+this.sex+"\nStudent age: "+this.age+"\nStudent ID: "+this.sID+"\nStudent class: "+this.classNo+"\n";
    }
}
