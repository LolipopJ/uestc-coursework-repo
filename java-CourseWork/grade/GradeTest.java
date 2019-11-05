package coursework.grade;

/**
 * @author Lolipop
 * @lastUpdate 2019/10/30
 */
public class GradeTest {
    public static void main (String[] args) {
        Grade gr = new Grade();
        gr.setData("Ai", 20191030, "Lolipop", "Java", 2019001, 85);
        gr.printGrade();
    }
}

class Student {
    private String sName;
    private int sId;

    Student () {
        this.sName = "unset";
        this.sId = 0;
    }

    void setData (String name, int id) {
        this.sName = name;
        this.sId = id;
    }

    String getData () {
        return "Student: "+this.sName+"\nStudent ID: "+this.sId+"\n";
    }
}

class Teacher {
    private String tName;

    Teacher () {
        this.tName = "unset";
    }

    void setData (String name) {
        this.tName = name;
    }

    String getData () {
        return "Student: "+this.tName+"\n";
    }
}

class Course {
    private String cName;
    private int cId;

    Course () {
        this.cName = "unset";
        this.cId = 0;
    }

    void setData (String name, int id) {
        this.cName = name;
        this.cId = id;
    }

    String getData () {
        return "Course: "+this.cName+"\nCourse ID: "+this.cId+"\n";
    }
}

class Grade {
    private Student st = new Student();
    private Teacher te = new Teacher();
    private Course co = new Course();
    private int grade;

    Grade () {
        this.grade = 0;
    }

    void setData (String sName, int sId, String tName, String cname, int cId, int g) {
        st.setData(sName, sId);
        te.setData(tName);
        co.setData(cname, cId);
        this.grade = g;
    }

    void printGrade () {
        System.out.print("Grade System\n"+st.getData()+te.getData()+co.getData()+"Course Grade: "+this.grade+"\n");
    }
}
