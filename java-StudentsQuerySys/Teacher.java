/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class Teacher extends Person {
    private String tid;
    private String title;

    Teacher(String name, String sex, int age, String tid, String title) {
        super(name, sex, age);
        this.tid = tid;
        this.title = title;
    }

    String getTid() {
        return this.tid;
    }

    String getTitle() {
        return this.title;
    }

    void setTid(String tid) {
        this.tid = tid;
    }

    void setTitle(String title) {
        this.title = title;
    }

    @Override
    void display() {
        System.out.print("name: "+this.name+"\nsex: "+this.sex+"\nage: "+this.age+"\ntid: "+this.tid+"\ntitle: "+this.title+"\n");
    }
}
