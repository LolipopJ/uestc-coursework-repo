import java.io.*;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/7
 */
public class Myfile {
    File opfile;

    Myfile(String filepath) {
        opfile = new File(filepath);
    }

    File getFile() {
        //返回存储数据文件的目录
        return opfile;
    }

    void writeFile(Student student) throws IOException {
        File file = new File(opfile, "studentData.txt");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(student);
        oos.flush();
        oos.close();
        fos.close();
    }

    void readFile(Student[] students) throws IOException, ClassNotFoundException {
        File file = new File(opfile, "studentData.txt");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        for (int i = 0; i<students.length; i++) {
            students[i] = (Student)ois.readObject();
        }
        ois.close();
        fis.close();
    }
}
