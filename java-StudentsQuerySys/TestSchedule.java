/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class TestSchedule {
    public static void main(String[] args) {
        Schedule testSchedule = new Schedule("2018091202", "2018002", "10004", "xy02");
        testSchedule.display();
        System.out.println("-----------------");

        testSchedule.setClassid("2019091202");
        testSchedule.setClassroom("xy04");
        testSchedule.setCid("2018004");
        testSchedule.setTid("10005");
        testSchedule.display();
        System.out.println("-----------------");

        String info = "classid: "+testSchedule.getClassid()+"\ncid: "+testSchedule.getCid()+"\ntid: "+testSchedule.getTid()+"\nclassroom: "+testSchedule.getClassroom();
        System.out.print(info);
    }
}
