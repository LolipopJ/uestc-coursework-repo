import java.io.Serializable;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class Course implements Serializable {
    private String cname;
    private String cid;
    private int chour;

    Course(String cname, String cid, int chour) {
        this.cname = cname;
        this.cid = cid;
        this.chour = chour;
    }

    String getCname() {
        return this.cname;
    }

    String getCid() {
        return this.cid;
    }

    String getChour() {
        return Integer.toString(this.chour);
    }

    void setCname(String cname) {
        this.cname = cname;
    }

    void setCid(String cid) {
        this.cid = cid;
    }

    void setChour(int chour) {
        this.chour = chour;
    }

    void display() {
        System.out.print("cname: "+this.cname+"\ncid: "+this.cid+"\nchour: "+this.chour+"\n");
    }
}
