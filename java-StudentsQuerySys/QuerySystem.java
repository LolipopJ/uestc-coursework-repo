import java.io.IOException;
import java.util.Scanner;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/14
 */
public class QuerySystem {
    private static Scanner scanner = new Scanner(System.in);

    // 文件默认存储在工作目录
    private static Myfile file = new Myfile(System.getProperty("user.dir"));

    // 设置最大存储数MAXSIZE
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
    final private static int QUIT_CASE = 0;
    final private static int QUERY_INFO_CASE = 1;
    final private static int INSERT_STUDENT_CASE = 2;
    final private static int INSERT_TEACHER_CASE = 3;
    final private static int INSERT_COURSE_CASE = 4;
    final private static int INSERT_SCHEDULE_CASE = 5;
    final private static int INSERT_ELECTIVECOURSE_CASE = 6;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int choice;
        boolean flag = true;
        readFile();

        while(flag) {
            choice = querySys();
            switch (choice) {
                case QUIT_CASE:
                    System.out.println("You quit successfully!");
                    flag = false;
                    break;
                case QUERY_INFO_CASE:
                    System.out.println("---Query student information---");
                    queryInfo();
                    break;
                case INSERT_STUDENT_CASE:
                    System.out.println("---Insert new student information---");
                    insertStudent();
                    break;
                case INSERT_TEACHER_CASE:
                    System.out.println("---Insert new teacher information---");
                    insertTeacher();
                    break;
                case INSERT_COURSE_CASE:
                    System.out.println("---Insert new course information---");
                    insertCourse();
                    break;
                case INSERT_SCHEDULE_CASE:
                    System.out.println("---Insert new schedule information---");
                    insertSchedule();
                    break;
                case INSERT_ELECTIVECOURSE_CASE:
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
        System.out.println("#"+QUERY_INFO_CASE+"  Query student information");
        System.out.println("#"+INSERT_STUDENT_CASE+"  Insert new student information");
        System.out.println("#"+INSERT_TEACHER_CASE+"  Insert new teacher information");
        System.out.println("#"+INSERT_COURSE_CASE+"  Insert new course information");
        System.out.println("#"+INSERT_SCHEDULE_CASE+"  Insert new schedule information");
        System.out.println("#"+INSERT_ELECTIVECOURSE_CASE+"  Insert new elective course information");
        System.out.println("#"+QUIT_CASE+"  Quit system");
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

        readFile(INSERT_STUDENT_CASE);
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

        readFile(INSERT_TEACHER_CASE);
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

        readFile(INSERT_COURSE_CASE);
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

        readFile(INSERT_SCHEDULE_CASE);
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

        readFile(INSERT_ELECTIVECOURSE_CASE);
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
            case INSERT_STUDENT_CASE:
                file.readFile(students);
                break;
            case INSERT_TEACHER_CASE:
                file.readFile(teachers);
                break;
            case INSERT_COURSE_CASE:
                file.readFile(courses);
                break;
            case INSERT_SCHEDULE_CASE:
                file.readFile(schedules);
                break;
            case INSERT_ELECTIVECOURSE_CASE:
                file.readFile(electivecourses);
                break;
            default:
                break;
        }
    }
}
