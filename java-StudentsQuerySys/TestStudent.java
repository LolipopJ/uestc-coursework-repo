/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class TestStudent {
    public static void main(String[] args) {
        Student testStudent = new Student("XiaoMing", "Male", 20, "2018091202000", "Computer");
        testStudent.display();
        System.out.println("-----------------");

        testStudent.setSid("XiaoHong");
        testStudent.display();
        System.out.println("-----------------");

        testStudent.setMajor("Design");
        testStudent.display();
        System.out.println("-----------------");

        String info = "sid: "+testStudent.getSid()+"\nmajor: "+testStudent.getMajor();
        System.out.print(info);
    }
}
