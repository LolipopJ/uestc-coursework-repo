import java.io.*;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/8
 */
public class TestMyfile {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 在这里修改测试文件保存目录
        Myfile file = new Myfile("H:\\Lolipop");

        // 测试例
        Student[] students = new Student[2];
        students[0] = new Student("XiaoMing", "male", 18, "2018091202000", "computer");
        students[1] = new Student("XiaoHong", "female", 20, "2018091202001", "design");
        file.writeFile(students[0]);
        file.writeFile(students[1]);
        file.writeFile(students[0]);
    }
}
