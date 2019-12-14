import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/14
 */
public class QuerySystemGui {
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

    // 初始化表格动态数组
    private Vector<String> columnName;

    public static void main (String[] args) throws IOException, ClassNotFoundException {
        readFile();
        new QuerySystemGui();
    }

    private QuerySystemGui() {
        // 框架
        JFrame frame = new JFrame("Student Query System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout(5,0));

        // 面板
        // 选项面板，采用流式布局
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        // 表格面板，采用边框布局
        JPanel queryInfoPanel = new JPanel();
        JPanel studentPanel = new JPanel();
        JPanel teacherPanel = new JPanel();
        JPanel coursePanel = new JPanel();
        JPanel schedulePanel = new JPanel();
        JPanel electiveCoursePanel = new JPanel();
        queryInfoPanel.setLayout(new BorderLayout());
        studentPanel.setLayout(new BorderLayout());
        teacherPanel.setLayout(new BorderLayout());
        coursePanel.setLayout(new BorderLayout());
        schedulePanel.setLayout(new BorderLayout());
        electiveCoursePanel.setLayout(new BorderLayout());
        // 输入面板，采用流式布局
        JPanel queryInfoInputPanel = new JPanel();
        JPanel insertStudentPanel = new JPanel();
        JPanel insertTeacherPanel = new JPanel();
        JPanel insertCoursePanel = new JPanel();
        JPanel insertSchedulePanel = new JPanel();
        JPanel insertElectiveCoursePanel = new JPanel();
        queryInfoInputPanel.setLayout(new FlowLayout());
        insertStudentPanel.setLayout(new FlowLayout());
        insertTeacherPanel.setLayout(new FlowLayout());
        insertCoursePanel.setLayout(new FlowLayout());
        insertSchedulePanel.setLayout(new FlowLayout());
        insertElectiveCoursePanel.setLayout(new FlowLayout());

        // 功能按钮
        JButton queryInfoBtn = new JButton("查询学生选课信息");
        JButton insertStudentBtn = new JButton("学生信息");
        JButton insertTeacherBtn = new JButton("教师信息");
        JButton insertCourseBtn = new JButton("课程信息");
        JButton insertScheduleBtn = new JButton("排课信息");
        JButton insertElectiveCourseBtn = new JButton("选课信息");

        // 表格面板初始化
        JTable queryInfoTable;
        JTable studentTable;
        JTable teacherTable;
        JTable courseTable;
        JTable scheduleTable;
        JTable electiveCourseTable;

        Vector<Vector<java.io.Serializable>> rowData = new Vector<>();
        columnName = new Vector<>();
        columnName.add("序号");
        columnName.add("课程名");
        columnName.add("教师名");
        columnName.add("教室");
        queryInfoTable = new JTable(rowData, columnName);

        columnName = new Vector<>();
        columnName.add("序号");
        columnName.add("学号");
        columnName.add("姓名");
        columnName.add("性别");
        columnName.add("年龄");
        columnName.add("专业");
        studentTable = new JTable(getRowData(students), columnName);

        columnName = new Vector<>();
        columnName.add("序号");
        columnName.add("教师号");
        columnName.add("教师名");
        columnName.add("性别");
        columnName.add("年龄");
        columnName.add("职称");
        teacherTable = new JTable(getRowData(teachers), columnName);

        columnName = new Vector<>();
        columnName.add("序号");
        columnName.add("课程号");
        columnName.add("课程名");
        columnName.add("课时");
        courseTable = new JTable(getRowData(courses), columnName);

        columnName = new Vector<>();
        columnName.add("序号");
        columnName.add("班号");
        columnName.add("课程号");
        columnName.add("教师号");
        columnName.add("教室");
        scheduleTable = new JTable(getRowData(schedules), columnName);

        columnName = new Vector<>();
        columnName.add("序号");
        columnName.add("选课号");
        columnName.add("学号");
        columnName.add("班号");
        electiveCourseTable = new JTable(getRowData(electivecourses), columnName);

        // 添加布局
        queryInfoPanel.add(queryInfoTable.getTableHeader(), BorderLayout.NORTH);
        queryInfoPanel.add(queryInfoTable, BorderLayout.CENTER);
        studentPanel.add(studentTable.getTableHeader(), BorderLayout.NORTH);
        studentPanel.add(studentTable, BorderLayout.CENTER);
        teacherPanel.add(teacherTable.getTableHeader(), BorderLayout.NORTH);
        teacherPanel.add(teacherTable, BorderLayout.CENTER);
        coursePanel.add(courseTable.getTableHeader(), BorderLayout.NORTH);
        coursePanel.add(courseTable, BorderLayout.CENTER);
        schedulePanel.add(scheduleTable.getTableHeader(), BorderLayout.NORTH);
        schedulePanel.add(scheduleTable, BorderLayout.CENTER);
        electiveCoursePanel.add(electiveCourseTable.getTableHeader(), BorderLayout.NORTH);
        electiveCoursePanel.add(electiveCourseTable, BorderLayout.CENTER);

        optionPanel.add(queryInfoBtn);
        optionPanel.add(insertStudentBtn);
        optionPanel.add(insertTeacherBtn);
        optionPanel.add(insertCourseBtn);
        optionPanel.add(insertScheduleBtn);
        optionPanel.add(insertElectiveCourseBtn);

        frame.add(optionPanel, BorderLayout.NORTH);
        frame.add(queryInfoPanel, BorderLayout.CENTER);

        // 设置可见
        frame.setVisible(true);
    }

    // 启动系统时读取所有文件信息
    private static void readFile() throws IOException, ClassNotFoundException {
        file.readFile(students);
        file.readFile(teachers);
        file.readFile(courses);
        file.readFile(schedules);
        file.readFile(electivecourses);
    }

    private static Vector<Vector<java.io.Serializable>> getRowData (Student[] students) {
        int count = 0;
        Vector<Vector<java.io.Serializable>> rowData = new Vector<>();
        for (Student student : students) {
            if (student != null) {
                Vector<java.io.Serializable> line = new Vector<>();
                line.add(count+1);
                line.add(student.getSid());
                line.add(student.getName());
                line.add(student.getSex());
                line.add(student.getAge());
                line.add(student.getMajor());
                rowData.add(line);
                count++;
            }
            else break;
        }
        return rowData;
    }

    private static Vector<Vector<java.io.Serializable>> getRowData (Teacher[] teachers) {
        int count = 0;
        Vector<Vector<java.io.Serializable>> rowData = new Vector<>();
        for (Teacher teacher : teachers) {
            if (teacher != null) {
                Vector<java.io.Serializable> line = new Vector<>();
                line.add(count+1);
                line.add(teacher.getTid());
                line.add(teacher.getName());
                line.add(teacher.getSex());
                line.add(teacher.getAge());
                line.add(teacher.getTitle());
                rowData.add(line);
                count++;
            }
            else break;
        }
        return rowData;
    }

    private static Vector<Vector<java.io.Serializable>> getRowData (Course[] courses) {
        int count = 0;
        Vector<Vector<java.io.Serializable>> rowData = new Vector<>();
        for (Course course : courses) {
            if (course != null) {
                Vector<java.io.Serializable> line = new Vector<>();
                line.add(count+1);
                line.add(course.getCid());
                line.add(course.getCname());
                line.add(course.getChour());
                rowData.add(line);
                count++;
            }
            else break;
        }
        return rowData;
    }

    private static Vector<Vector<java.io.Serializable>> getRowData (Schedule[] schedules) {
        int count = 0;
        Vector<Vector<java.io.Serializable>> rowData = new Vector<>();
        for (Schedule schedule : schedules) {
            if (schedule != null) {
                Vector<java.io.Serializable> line = new Vector<>();
                line.add(count+1);
                line.add(schedule.getClassid());
                line.add(schedule.getCid());
                line.add(schedule.getTid());
                line.add(schedule.getClassroom());
                rowData.add(line);
                count++;
            }
            else break;
        }
        return rowData;
    }

    private static Vector<Vector<java.io.Serializable>> getRowData (Electivecourse[] electivecourses) {
        int count = 0;
        Vector<Vector<java.io.Serializable>> rowData = new Vector<>();
        for (Electivecourse electivecourse : electivecourses) {
            if (electivecourse != null) {
                Vector<java.io.Serializable> line = new Vector<>();
                line.add(count+1);
                line.add(electivecourse.getElid());
                line.add(electivecourse.getSid());
                line.add(electivecourse.getClassid());
                rowData.add(line);
                count++;
            }
            else break;
        }
        return rowData;
    }
}
