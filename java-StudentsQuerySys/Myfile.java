import java.io.*;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/8
 */
public class Myfile {
    // 设置opfile默认路径
    File opfile;

    // file存储的最多信息数，默认为30条
    int MAXSIZE = 30;

    Myfile(String filepath) {
        opfile = new File(filepath);
    }

    File getFile() {
        //返回存储数据文件的目录
        return opfile;
    }

    int setMAXSIZE(int size) {
        return this.MAXSIZE = size;
    }

    // StudentCase
    void writeFile(Student student) throws IOException, ClassNotFoundException {
        File file = new File(opfile, "studentData.dat");
        Student[] writeStudents;

        if (file.exists()) {
            // 若文件存在，则先从文件中读取对象到对象数组readStudents中
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Student[] readStudents = (Student[]) ois.readObject();
            ois.close();
            fis.close();

            // 将readStudents复制给writeStudents
            // 并在writeStudents的末尾加上新加对象student
            writeStudents = new Student[readStudents.length+1];
            System.arraycopy(readStudents, 0, writeStudents, 0, readStudents.length);
            writeStudents[readStudents.length] = student;
        } else {
            // 若文件不存在，则把writeStudents的第一项赋为对象student
            writeStudents = new Student[1];
            writeStudents[0] = student;
        }

        // 将对象数组writeStudents序列化并覆盖原文件
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(writeStudents);
        oos.flush();
        oos.close();
        fos.close();
    }

    void readFile(Student[] students) throws IOException, ClassNotFoundException {
        File file = new File(opfile, "studentData.dat");

        // 文件不存在时直接返回null
        if (!file.exists()) {
            return;
        }

        // 文件存在时，读取文件信息到对象数组中
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Student[] readStudents = (Student[]) ois.readObject();
        System.arraycopy(readStudents, 0, students, 0, readStudents.length);
        ois.close();
        fis.close();
    }

    // TeacherCase
    void writeFile(Teacher teacher) throws IOException, ClassNotFoundException {
        File file = new File(opfile, "teacherData.dat");
        Teacher[] writeTeachers;

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Teacher[] readTeachers = (Teacher[]) ois.readObject();
            ois.close();
            fis.close();

            writeTeachers = new Teacher[readTeachers.length+1];
            System.arraycopy(readTeachers, 0, writeTeachers, 0, readTeachers.length);
            writeTeachers[readTeachers.length] = teacher;
        } else {
            writeTeachers = new Teacher[1];
            writeTeachers[0] = teacher;
        }

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(writeTeachers);
        oos.flush();
        oos.close();
        fos.close();
    }

    void readFile(Teacher[] teachers) throws IOException, ClassNotFoundException {
        File file = new File(opfile, "teacherData.dat");

        if (!file.exists()) {
            return;
        }

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Teacher[] readTeachers = (Teacher[]) ois.readObject();
        System.arraycopy(readTeachers, 0, teachers, 0, readTeachers.length);
        ois.close();
        fis.close();
    }

    // CourseCase
    void writeFile(Course course) throws IOException, ClassNotFoundException {
        File file = new File(opfile, "courseData.dat");
        Course[] writeCourses;

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Course[] readCourses = (Course[]) ois.readObject();
            ois.close();
            fis.close();

            writeCourses = new Course[readCourses.length+1];
            System.arraycopy(readCourses, 0, writeCourses, 0, readCourses.length);
            writeCourses[readCourses.length] = course;
        } else {
            writeCourses = new Course[1];
            writeCourses[0] = course;
        }

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(writeCourses);
        oos.flush();
        oos.close();
        fos.close();
    }

    void readFile(Course[] courses) throws IOException, ClassNotFoundException {
        File file = new File(opfile, "courseData.dat");

        if (!file.exists()) {
            return;
        }

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Course[] readCourses = (Course[]) ois.readObject();
        System.arraycopy(readCourses, 0, courses, 0, readCourses.length);
        ois.close();
        fis.close();
    }

    // ScheduleCase
    void writeFile(Schedule schedule) throws IOException, ClassNotFoundException {
        File file = new File(opfile, "scheduleData.dat");
        Schedule[] writeSchedules;

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Schedule[] readSchedules = (Schedule[]) ois.readObject();
            ois.close();
            fis.close();

            writeSchedules = new Schedule[readSchedules.length+1];
            System.arraycopy(readSchedules, 0, writeSchedules, 0, readSchedules.length);
            writeSchedules[readSchedules.length] = schedule;
        } else {
            writeSchedules = new Schedule[1];
            writeSchedules[0] = schedule;
        }

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(writeSchedules);
        oos.flush();
        oos.close();
        fos.close();
    }

    void readFile(Schedule[] schedules) throws IOException, ClassNotFoundException {
        File file = new File(opfile, "scheduleData.dat");

        if (!file.exists()) {
            return;
        }

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Schedule[] readSchedules = (Schedule[]) ois.readObject();
        System.arraycopy(readSchedules, 0, schedules, 0, readSchedules.length);
        ois.close();
        fis.close();
    }

    // ElectivecourseCase
    void writeFile(Electivecourse electivecourse) throws IOException, ClassNotFoundException {
        File file = new File(opfile, "electivecourseData.dat");
        Electivecourse[] writeElectivecourses;

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Electivecourse[] readElectivecourses = (Electivecourse[]) ois.readObject();
            ois.close();
            fis.close();

            writeElectivecourses = new Electivecourse[readElectivecourses.length+1];
            System.arraycopy(readElectivecourses, 0, writeElectivecourses, 0, readElectivecourses.length);
            writeElectivecourses[readElectivecourses.length] = electivecourse;
        } else {
            writeElectivecourses = new Electivecourse[1];
            writeElectivecourses[0] = electivecourse;
        }

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(writeElectivecourses);
        oos.flush();
        oos.close();
        fos.close();
    }

    void readFile(Electivecourse[] electivecourses) throws IOException, ClassNotFoundException {
        File file = new File(opfile, "electivecourseData.dat");

        if (!file.exists()) {
            return;
        }

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Electivecourse[] readElectivecourses = (Electivecourse[]) ois.readObject();
        System.arraycopy(readElectivecourses, 0, electivecourses, 0, readElectivecourses.length);
        ois.close();
        fis.close();
    }
}
