import java.io.Serializable;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class Schedule implements Serializable {
    private String classid;
    private String cid;
    private String tid;
    private String classroom;

    Schedule(String classid, String cid, String tid, String classroom) {
        this.classid = classid;
        this.cid = cid;
        this.tid = tid;
        this.classroom = classroom;
    }

    String getClassid() {
        return this.classid;
    }

    String getCid() {
        return this.cid;
    }

    String getTid() {
        return this.tid;
    }

    String getClassroom() {
        return this.classroom;
    }

    void setClassid(String classid) {
        this.classid = classid;
    }

    void setTid(String tid) {
        this.tid = tid;
    }

    void setCid(String cid) {
        this.cid = cid;
    }

    void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    void display() {
        System.out.print("classid: "+this.classid+"\ncid: "+this.cid+"\ntid: "+this.tid+"\nclassroom: "+this.classroom+"\n");
    }
}
