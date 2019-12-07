/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class Student extends Person{
    private String sid;
    private String major;

    Student(String name, String sex, int age, String sid, String major) {
        super(name, sex, age);
        this.sid = sid;
        this.major = major;
    }

    String getSid() {
        return this.sid;
    }

    String getMajor() {
        return this.major;
    }

    void setSid(String sid) {
        this.sid = sid;
    }

    void setMajor(String major) {
        this.major = major;
    }

    @Override
    void display() {
        System.out.print("name: "+this.name+"\nsex: "+this.sex+"\nage: "+this.age+"\nsid: "+this.sid+"\nmajor: "+this.major+"\n");
    }
}
