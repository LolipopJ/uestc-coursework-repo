package coursework.interfacetest;

interface Person {
    /**
     * 对属性name,sex,birthday赋值;
     * @param name  设置姓名
     * @param sex 设置性别
     * @param birthday 设置生日
     */
    void setData(String name, char sex, String birthday);

    /**
     * 获得这些属性组成的字符串信息。
     * @return name,sex,birthday属性组成的字符串信息。
     */
    String getData();
}

/**
 * @author Lolipop
 * @lastUpdate 2019/10/28
 */
public class PersonTest {
    public static void main (String[] args) {
        InfStudent student = new InfStudent();
        student.setData("Dragon", '男', "2000.07.03", 10001, "Eat");
        student.print();
    }
}

class InfStudent implements Person,Print{
    private String name;
    private char sex;
    private String birthday;
    private int sID;
    private String speciality;

    InfStudent () {
        name = "unset";
        sex = '男';
        birthday = "2000.01.01";
        sID = 10000;
        speciality = "unset";
    }

    @Override
    public void setData(String readName, char readSex, String readBirthday) {
        this.name = readName;
        this.sex = readSex;
        this.birthday = readBirthday;
    }

    public void setData(String readName, char readSex, String readBirthday, int readSID, String readSpeciality) {
        this.name = readName;
        this.sex = readSex;
        this.birthday = readBirthday;
        this.sID = readSID;
        this.speciality = readSpeciality;
    }

    @Override
    public String getData() {
        return "Student: "+this.name+"\nsex: "+this.sex+"\nbirthday: "+this.birthday+"\nsID: "+this.sID+"\nspeciality: "+this.speciality;
    }

    @Override
    public void print() {
        String info = this.getData();
        System.out.println("Information:\n"+info);
    }
}
