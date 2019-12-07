/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class TestElcourse {
    public static void main(String[] args) {
        Electivecourse testElcourse = new Electivecourse("2018091202", "1", "2018091202000");
        testElcourse.display();
        System.out.println("-----------------");

        testElcourse.setClassid("2018091609");
        testElcourse.setElid("2");
        testElcourse.setSid("2018091609000");
        testElcourse.display();
        System.out.println("-----------------");

        String info = "elid: "+testElcourse.getElid()+"\nsid: "+testElcourse.getSid()+"\nclassid: "+testElcourse.getClassid();
        System.out.print(info);
    }
}
