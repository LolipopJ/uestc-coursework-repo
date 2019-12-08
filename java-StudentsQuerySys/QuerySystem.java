import java.io.IOException;
import java.util.Scanner;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/8
 */
public class QuerySystem {
    private static Scanner scanner = new Scanner(System.in);

    // 在这里修改数据存储目录
    private static Myfile file = new Myfile("H:\\Lolipop\\JAVA_STUDY_EXP");
    // 设置MAXSIZE
    private static int MAXSIZE = file.setMAXSIZE(30);

    // 初始化变量
    private static String name;
    private static String sex;
    private static int age;
    private static String sid;
    private static String major;
    private static String tid;
    private static String title;
    private static String cname;
    private static String cid;
    private static int chour;
    private static String classid;
    private static String classroom;
    private static String elid;

    // 初始化对象数组
    private static Student[] students = new Student[MAXSIZE];
    private static Teacher[] teachers = new Teacher[MAXSIZE];
    private static Course[] courses = new Course[MAXSIZE];
    private static Schedule[] schedules = new Schedule[MAXSIZE];
    private static Electivecourse[] electivecourses = new Electivecourse[MAXSIZE];

    // choice选项值
    final private static int quitCase = 0;
    final private static int queryInfoCase = 1;
    final private static int insertStudentCase = 2;
    final private static int insertTeacherCase = 3;
    final private static int insertCourseCase = 4;
    final private static int insertScheduleCase = 5;
    final private static int insertElectivecourseCase = 6;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int choice;
        boolean flag = true;
        readFile();

        while(flag) {
            choice = querySys();
            switch (choice) {
                case quitCase:
                    System.out.println("You quit successfully!");
                    flag = false;
                    break;
                case queryInfoCase:
                    System.out.println("---Query student information---");
                    queryInfo();
                    break;
                case insertStudentCase:
                    System.out.println("---Insert new student information---");
                    insertStudent();
                    break;
                case insertTeacherCase:
                    System.out.println("---Insert new teacher information---");
                    insertTeacher();
                    break;
                case insertCourseCase:
                    System.out.println("---Insert new course information---");
                    insertCourse();
                    break;
                case insertScheduleCase:
                    System.out.println("---Insert new schedule information---");
                    insertSchedule();
                    break;
                case insertElectivecourseCase:
                    System.out.println("---Insert new elective course information---");
                    insertElectivecourse();
                    break;
                default: System.out.println("Wrong code! Check your input."); break;
            }
        }
    }

    // 显示操作提示界面并获取选项值
    private static int querySys() {
        System.out.println("\n---------Query Information System---------");
        System.out.println("#"+queryInfoCase+"  Query student information");
        System.out.println("#"+insertStudentCase+"  Insert new student information");
        System.out.println("#"+insertTeacherCase+"  Insert new teacher information");
        System.out.println("#"+insertCourseCase+"  Insert new course information");
        System.out.println("#"+insertScheduleCase+"  Insert new schedule information");
        System.out.println("#"+insertElectivecourseCase+"  Insert new elective course information");
        System.out.println("#"+quitCase+"  Quit system");
        System.out.println("------------------------------------------");
        System.out.print("input choice: #");
        return scanner.nextInt();
    }

    private static void queryInfo() {
        System.out.println("input student id your want to query below:");
        sid = scanner.next();

        // 根据学号检索选课类，获得班级号
        for (Electivecourse electivecourse : electivecourses) {
            if (electivecourse != null && sid.equals(electivecourse.getSid())) {
                classid = electivecourse.getClassid();

                // 根据班级号检索排课类，获得课程号、教师号和上课教室
                for (Schedule schedule : schedules) {
                    if (schedule != null && classid.equals(schedule.getClassid())) {
                        cid = schedule.getCid();
                        tid = schedule.getTid();
                        classroom = schedule.getClassroom();

                        // 根据课程号、教师号分别获取课程名称和教师名称
                        for (Course course : courses) {
                            if (course != null && cid.equals(course.getCid())) {
                                cname = course.getCname();
                                break;
                            }
                        }

                        for (Teacher teacher : teachers) {
                            if (teacher != null && tid.equals(teacher.getTid())) {
                                name = teacher.getName();
                                break;
                            }
                        }

                        // 输出显示查询的结果
                        outputQueryInfo(cname, name, classroom);
                        break;
                    }
                }
            }
        }
    }

    private static void outputQueryInfo(String courseName, String teacherName, String classroom) {
        System.out.println("---Elected course details---");
        System.out.println("Course name: "+courseName);
        System.out.println("Teacher name: "+teacherName);
        System.out.println("Classroom: "+classroom);
        System.out.println("----------------------------");
    }

    private static void insertStudent() throws IOException, ClassNotFoundException {
        System.out.print("input student name: ");
        name = scanner.next();
        System.out.print("input student sex(male or female): ");
        sex = scanner.next();
        System.out.print("input student age: ");
        age = scanner.nextInt();
        System.out.print("input student id: ");
        sid = scanner.next();
        System.out.print("input student major: ");
        major = scanner.next();

        Student student = new Student(name, sex, age, sid, major);
        file.writeFile(student);

        readFile(insertStudentCase);
    }

    private static void insertTeacher() throws IOException, ClassNotFoundException {
        System.out.print("input teacher name: ");
        name = scanner.next();
        System.out.print("input teacher sex(male or female): ");
        sex = scanner.next();
        System.out.print("input teacher age: ");
        age = scanner.nextInt();
        System.out.print("input teacher id: ");
        tid = scanner.next();
        System.out.print("input teacher title: ");
        title = scanner.next();

        Teacher teacher = new Teacher(name, sex, age, tid, title);
        file.writeFile(teacher);

        readFile(insertTeacherCase);
    }

    private static void insertCourse() throws IOException, ClassNotFoundException {
        System.out.print("input course name: ");
        cname = scanner.next();
        System.out.print("input course id: ");
        cid = scanner.next();
        System.out.print("input course hour: ");
        chour = scanner.nextInt();

        Course course = new Course(cname, cid, chour);
        file.writeFile(course);

        readFile(insertCourseCase);
    }

    private static void insertSchedule() throws IOException, ClassNotFoundException {
        System.out.print("input class id: ");
        classid = scanner.next();
        System.out.print("input course id: ");
        cid = scanner.next();
        System.out.print("input teacher id: ");
        tid = scanner.next();
        System.out.print("input classroom: ");
        classroom = scanner.next();

        Schedule schedule = new Schedule(classid, cid, tid, classroom);
        file.writeFile(schedule);

        readFile(insertScheduleCase);
    }

    private static void insertElectivecourse() throws IOException, ClassNotFoundException {
        System.out.print("input class id: ");
        classid = scanner.next();
        System.out.print("input elective course id: ");
        elid = scanner.next();
        System.out.print("input student id: ");
        sid = scanner.next();

        Electivecourse electivecourse = new Electivecourse(classid, elid, sid);
        file.writeFile(electivecourse);

        readFile(insertElectivecourseCase);
    }

    // 启动系统时读取所有文件信息
    private static void readFile() throws IOException, ClassNotFoundException {
        file.readFile(students);
        file.readFile(teachers);
        file.readFile(courses);
        file.readFile(schedules);
        file.readFile(electivecourses);
    }

    // 添加信息后刷新并读取对应文件信息（增加性能）
    private static void readFile(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case insertStudentCase:
                file.readFile(students);
                break;
            case insertTeacherCase:
                file.readFile(teachers);
                break;
            case insertCourseCase:
                file.readFile(courses);
                break;
            case insertScheduleCase:
                file.readFile(schedules);
                break;
            case insertElectivecourseCase:
                file.readFile(electivecourses);
                break;
        }
    }
}
