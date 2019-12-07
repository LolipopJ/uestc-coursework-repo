import java.io.*;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class TestMyfile {
    public static void main(String[] args) throws IOException {
        Myfile file = new Myfile("C:\\test");
        Student[] students = new Student[2];
        students[0] = new Student("XiaoMing", "male", 18, "2018091202000", "computer");
        students[1] = new Student("XiaoHong", "female", 20, "2018091202001", "design");
        file.writeFile(students[0]);
        file.writeFile(students[1]);
    }
}
