/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class TestCourse {
    public static void main(String[] args) {
        Course testCourse = new Course("Java", "2019001", 48);
        testCourse.display();
        System.out.println("-----------------");

        testCourse.setChour(96);
        testCourse.setCid("2019002");
        testCourse.setCname("Cpp");
        testCourse.display();
        System.out.println("-----------------");

        String info = "cname: "+testCourse.getCname()+"\ncid: "+testCourse.getCid()+"\nchour: "+testCourse.getChour();
        System.out.print(info);
    }
}
