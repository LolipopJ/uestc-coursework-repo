package coursework.abstractperson;

/**
 * @author Lolipop
 * @lastUpdate 2019/10/25
 */
public class Person {
    public static void main (String[] args) {
        Student stu = new Student("0001", "AI");
        stu.setData("XiaoMing", '男', 19);
        System.out.print(stu.getData()+"\n");

        Teacher tea = new Teacher("001", "FA");
        tea.setData("DaMei", '女', 35);
        System.out.print(tea.getData()+"\n");
    }
}

abstract class BasePerson {
    String name;
    char sex;
    int age;

    /**
     * set person data
     * @param name: set person name
     * @param sex: set person sex
     * @param age: set person age
     */
    abstract void setData (String name, char sex, int age);

    /**
     * get person data
     * @return person data
     */
    abstract String getData();
}

class Student extends BasePerson {
    private String sID;
    private String speciality;

    Student (String sid, String sp) {
        this.sID = sid;
        this.speciality = sp;
    }

    @Override
    void setData(String name, char sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    String getData() {
        return "Student: "+this.name+" sID="+this.sID+"\nAge: "+this.age+"\nSex: "+this.sex+"\nSpeciality: "+this.speciality;
    }
}

class Teacher extends BasePerson {
    private String tID;
    private String department;

    Teacher (String tid, String de) {
        this.tID = tid;
        this.department = de;
    }

    @Override
    void setData(String name, char sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    String getData() {
        return "Teacher: "+this.name+" tID="+this.tID+"\nAge: "+this.age+"\nSex: "+this.sex+"\nDepartment: "+this.department;
    }
}
