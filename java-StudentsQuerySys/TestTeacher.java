/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class TestTeacher {
    public static void main(String[] args) {
        Teacher testTeacher = new Teacher("XiaoMing", "Male", 20, "10001", "professor");
        testTeacher.display();
        System.out.println("-----------------");

        testTeacher.setTid("10005");
        testTeacher.display();
        System.out.println("-----------------");

        testTeacher.setTitle("associate professor");
        testTeacher.display();
        System.out.println("-----------------");

        String info = "tid: "+testTeacher.getTid()+"\ntitle: "+testTeacher.getTitle();
        System.out.print(info);
    }
}
