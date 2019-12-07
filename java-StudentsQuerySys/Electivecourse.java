import java.io.Serializable;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class Electivecourse implements Serializable {
    private String elid;
    private String sid;
    private String classid;

    Electivecourse(String classid, String elid, String sid) {
        this.classid = classid;
        this.elid = elid;
        this.sid = sid;
    }

    String getClassid() {
        return this.classid;
    }

    String getElid() {
        return this.elid;
    }

    String getSid() {
        return this.sid;
    }

    void setClassid(String classid) {
        this.classid = classid;
    }

    void setElid(String elid) {
        this.elid = elid;
    }

    void setSid(String sid) {
        this.sid = sid;
    }

    void display() {
        System.out.print("elid: "+this.elid+"\nsid: "+this.sid+"\nclassid: "+this.classid+"\n");
    }
}
