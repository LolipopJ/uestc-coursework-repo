import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/15
 */
public class QuerySystemGui {
    // 文件默认存储目录
    private static Myfile file = new Myfile(System.getProperty("user.dir"));

    // 设置最大存储数MAXSIZE
    final private static int MAXSIZE = file.setMAXSIZE(50);

    // 状态值
    private static int NOW_CASE;
    final private static int QUERY_INFO_CASE = 1;
    final private static int STUDENT_CASE = 2;
    final private static int TEACHER_CASE = 3;
    final private static int COURSE_CASE = 4;
    final private static int SCHEDULE_CASE = 5;
    final private static int ELECTIVE_COURSE_CASE = 6;

    // 常用String值
    final private static String sysTitle = "Student Query System";
    final private static String QUERY_INFO_TITLE = "查询学生选课信息";
    final private static String STUDENT_TITLE = "学生信息";
    final private static String TEACHER_TITLE = "教师信息";
    final private static String COURSE_TITLE = "课程信息";
    final private static String SCHEDULE_TITLE = "排课信息";
    final private static String ELECTIVE_COURSE_TITLE = "选课信息";
    final private static String ID = "序号";
    final private static String NAME = "姓名";
    final private static String SEX = "性别";
    final private static String AGE = "年龄";
    final private static String STUDENT_ID = "学号";
    final private static String MAJOR = "专业";
    final private static String TEACHER_ID = "教师号";
    final private static String TITLE = "职称";
    final private static String COURSE_ID = "课程号";
    final private static String COURSE_NAME = "课程名";
    final private static String COURSE_HOUR = "课时";
    final private static String CLASS_ID = "班号";
    final private static String CLASS_ROOM = "教室";
    final private static String ELECTIVE_COURSE_ID = "选课号";

    // 初始化对象数组
    private static Student[] students = new Student[MAXSIZE];
    private static Teacher[] teachers = new Teacher[MAXSIZE];
    private static Course[] courses = new Course[MAXSIZE];
    private static Schedule[] schedules = new Schedule[MAXSIZE];
    private static Electivecourse[] electiveCourses = new Electivecourse[MAXSIZE];

    public static void main (String[] args) throws IOException, ClassNotFoundException {
        new QuerySystemGui();
    }

    private QuerySystemGui() throws IOException, ClassNotFoundException {
        readFile();
        // 框架
        JFrame frame = new JFrame(sysTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 400));
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);

        // 主面板
        JPanel mainPanel = new JPanel(new BorderLayout(5,0));

        // 选项面板，采用流式布局
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        // 表格面板，采用边框布局
        JPanel queryInfoPanel = new JPanel(new BorderLayout());
        JPanel studentPanel = new JPanel(new BorderLayout());
        JPanel teacherPanel = new JPanel(new BorderLayout());
        JPanel coursePanel = new JPanel(new BorderLayout());
        JPanel schedulePanel = new JPanel(new BorderLayout());
        JPanel electiveCoursePanel = new JPanel(new BorderLayout());

        // 输入面板，采用流式布局
        JPanel queryInfoInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        JPanel insertStudentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        JPanel insertTeacherPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        JPanel insertCoursePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        JPanel insertSchedulePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        JPanel insertElectiveCoursePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));

        Dimension inputPanelDimension = new Dimension(180, 0);
        queryInfoInputPanel.setPreferredSize(inputPanelDimension);
        insertStudentPanel.setPreferredSize(inputPanelDimension);
        insertTeacherPanel.setPreferredSize(inputPanelDimension);
        insertCoursePanel.setPreferredSize(inputPanelDimension);
        insertSchedulePanel.setPreferredSize(inputPanelDimension);
        insertElectiveCoursePanel.setPreferredSize(inputPanelDimension);

        // 选项面板按钮
        JButton queryInfoBtn = new JButton(QUERY_INFO_TITLE);
        JButton studentBtn = new JButton(STUDENT_TITLE);
        JButton teacherBtn = new JButton(TEACHER_TITLE);
        JButton courseBtn = new JButton(COURSE_TITLE);
        JButton scheduleBtn = new JButton(SCHEDULE_TITLE);
        JButton electiveCourseBtn = new JButton(ELECTIVE_COURSE_TITLE);

        queryInfoBtn.addActionListener(e -> {
            if (NOW_CASE != QUERY_INFO_CASE) {
                mainPanel.removeAll();
                mainPanel.add(optionPanel, BorderLayout.NORTH);
                mainPanel.add(queryInfoPanel, BorderLayout.CENTER);
                mainPanel.add(queryInfoInputPanel, BorderLayout.WEST);
                mainPanel.updateUI();
                mainPanel.repaint();

                NOW_CASE = QUERY_INFO_CASE;
                frame.setTitle(sysTitle+" - "+QUERY_INFO_TITLE);
            }
        });

        studentBtn.addActionListener(e -> {
            if (NOW_CASE != STUDENT_CASE) {
                mainPanel.removeAll();
                mainPanel.add(optionPanel, BorderLayout.NORTH);
                mainPanel.add(studentPanel, BorderLayout.CENTER);
                mainPanel.add(insertStudentPanel, BorderLayout.WEST);
                mainPanel.updateUI();
                mainPanel.repaint();

                NOW_CASE = STUDENT_CASE;
                frame.setTitle(sysTitle+" - "+STUDENT_TITLE);
            }
        });

        teacherBtn.addActionListener(e -> {
            if (NOW_CASE != TEACHER_CASE) {
                mainPanel.removeAll();
                mainPanel.add(optionPanel, BorderLayout.NORTH);
                mainPanel.add(teacherPanel, BorderLayout.CENTER);
                mainPanel.add(insertTeacherPanel, BorderLayout.WEST);
                mainPanel.updateUI();
                mainPanel.repaint();

                NOW_CASE = TEACHER_CASE;
                frame.setTitle(sysTitle+" - "+TEACHER_TITLE);
            }
        });

        courseBtn.addActionListener(e -> {
            if (NOW_CASE != COURSE_CASE) {
                mainPanel.removeAll();
                mainPanel.add(optionPanel, BorderLayout.NORTH);
                mainPanel.add(coursePanel, BorderLayout.CENTER);
                mainPanel.add(insertCoursePanel, BorderLayout.WEST);
                mainPanel.updateUI();
                mainPanel.repaint();

                NOW_CASE = COURSE_CASE;
                frame.setTitle(sysTitle+" - "+COURSE_TITLE);
            }
        });

        scheduleBtn.addActionListener(e -> {
            if (NOW_CASE != SCHEDULE_CASE) {
                mainPanel.removeAll();
                mainPanel.add(optionPanel, BorderLayout.NORTH);
                mainPanel.add(schedulePanel, BorderLayout.CENTER);
                mainPanel.add(insertSchedulePanel, BorderLayout.WEST);
                mainPanel.updateUI();
                mainPanel.repaint();

                NOW_CASE = STUDENT_CASE;
                frame.setTitle(sysTitle+" - "+SCHEDULE_TITLE);
            }
        });

        electiveCourseBtn.addActionListener(e -> {
            if (NOW_CASE != ELECTIVE_COURSE_CASE) {
                mainPanel.removeAll();
                mainPanel.add(optionPanel, BorderLayout.NORTH);
                mainPanel.add(electiveCoursePanel, BorderLayout.CENTER);
                mainPanel.add(insertElectiveCoursePanel, BorderLayout.WEST);
                mainPanel.updateUI();
                mainPanel.repaint();

                NOW_CASE = ELECTIVE_COURSE_CASE;
                frame.setTitle(sysTitle+" - "+ELECTIVE_COURSE_TITLE);
            }
        });

        // 表格面板内容
        JTable queryInfoTable;
        JTable studentTable;
        JTable teacherTable;
        JTable courseTable;
        JTable scheduleTable;
        JTable electiveCourseTable;
        Vector<String> columnName;

        columnName = new Vector<>();
        columnName.add(ID);
        columnName.add(COURSE_NAME);
        columnName.add(NAME);
        columnName.add(CLASS_ROOM);
        queryInfoTable = new JTable(null, columnName);

        columnName = new Vector<>();
        columnName.add(ID);
        columnName.add(STUDENT_ID);
        columnName.add(NAME);
        columnName.add(SEX);
        columnName.add(AGE);
        columnName.add(MAJOR);
        studentTable = new JTable(getRowData(students), columnName);

        columnName = new Vector<>();
        columnName.add(ID);
        columnName.add(TEACHER_ID);
        columnName.add(NAME);
        columnName.add(SEX);
        columnName.add(AGE);
        columnName.add(TITLE);
        teacherTable = new JTable(getRowData(teachers), columnName);

        columnName = new Vector<>();
        columnName.add(ID);
        columnName.add(COURSE_ID);
        columnName.add(COURSE_NAME);
        columnName.add(COURSE_HOUR);
        courseTable = new JTable(getRowData(courses), columnName);

        columnName = new Vector<>();
        columnName.add(ID);
        columnName.add(CLASS_ID);
        columnName.add(COURSE_ID);
        columnName.add(TEACHER_ID);
        columnName.add(CLASS_ROOM);
        scheduleTable = new JTable(getRowData(schedules), columnName);

        columnName = new Vector<>();
        columnName.add(ID);
        columnName.add(ELECTIVE_COURSE_ID);
        columnName.add(STUDENT_ID);
        columnName.add(CLASS_ID);
        electiveCourseTable = new JTable(getRowData(electiveCourses), columnName);

        // 输入面板内容
        JLabel studentNameLable = new JLabel(NAME);
        JLabel studentSexLable = new JLabel(SEX);
        JLabel studentAgeLable = new JLabel(AGE);
        JLabel teacherNameLable = new JLabel(NAME);
        JLabel teacherSexLable = new JLabel(SEX);
        JLabel teacherAgeLable = new JLabel(AGE);
        JLabel studentIdLable_insert = new JLabel(STUDENT_ID);
        JLabel studentIdLable_query = new JLabel(STUDENT_ID);
        JLabel studentIdLable_comboBox = new JLabel(STUDENT_ID);
        JLabel majorLable = new JLabel(MAJOR);
        JLabel teacherIdLable = new JLabel(TEACHER_ID);
        JLabel teacherIdLable_comboBox = new JLabel(TEACHER_ID);
        JLabel titleLable = new JLabel(TITLE);
        JLabel courseIdLable = new JLabel(COURSE_ID);
        JLabel courseIdLable_comboBox = new JLabel(COURSE_ID);
        JLabel courseNameLable = new JLabel(COURSE_NAME);
        JLabel courseHourLable = new JLabel(COURSE_HOUR);
        JLabel classIdLable = new JLabel(CLASS_ID);
        JLabel classIdLable_comboBox = new JLabel(CLASS_ID);
        JLabel classRoomLable = new JLabel(CLASS_ROOM);
        JLabel electiveCourseIdLable = new JLabel(ELECTIVE_COURSE_ID);

        JTextField studentNameTextField = new JTextField(10);
        JTextField studentAgeTextField = new JTextField(10);
        JTextField teacherNameTextField = new JTextField(10);
        JTextField teacherAgeTextField = new JTextField(10);
        JTextField studentIdTextField_insert = new JTextField(10);
        JTextField studentIdTextField_query = new JTextField(10);
        JTextField majorTextField = new JTextField(10);
        JTextField teacherIdTextField = new JTextField(10);
        JTextField titleTextField = new JTextField(10);
        JTextField courseIdTextField = new JTextField(10);
        JTextField courseNameTextField = new JTextField(10);
        JTextField courseHourTextField = new JTextField(10);
        JTextField classIdTextField = new JTextField(10);
        JTextField classRoomTextField = new JTextField(10);
        JTextField electiveCourseIdTextField = new JTextField(10);

        JComboBox<String> studentSexTextField = new JComboBox<>();
        JComboBox<String> teacherSexTextField = new JComboBox<>();
        JComboBox<String> courseIdComboBox = new JComboBox<>();
        JComboBox<String> teacherIdComboBox = new JComboBox<>();
        JComboBox<String> studentIdComboBox = new JComboBox<>();
        JComboBox<String> classIdComboBox = new JComboBox<>();
        Dimension comboBoxDimension = new Dimension(110, 20);
        studentSexTextField.setPreferredSize(comboBoxDimension);
        teacherSexTextField.setPreferredSize(comboBoxDimension);
        courseIdComboBox.setPreferredSize(comboBoxDimension);
        teacherIdComboBox.setPreferredSize(comboBoxDimension);
        studentIdComboBox.setPreferredSize(comboBoxDimension);
        classIdComboBox.setPreferredSize(comboBoxDimension);

        JButton confirmBtn1 = new JButton("查询");
        JButton confirmBtn2 = new JButton("添加");
        JButton confirmBtn3 = new JButton("添加");
        JButton confirmBtn4 = new JButton("添加");
        JButton confirmBtn5 = new JButton("添加");
        JButton confirmBtn6 = new JButton("添加");
        JButton resetBtn1 = new JButton("重置");
        JButton resetBtn2 = new JButton("重置");
        JButton resetBtn3 = new JButton("重置");
        JButton resetBtn4 = new JButton("重置");
        JButton resetBtn5 = new JButton("重置");
        JButton resetBtn6 = new JButton("重置");

        // 面板初始化
        queryInfoInputPanel.add(studentIdLable_query);
        queryInfoInputPanel.add(studentIdTextField_query);
        queryInfoInputPanel.add(confirmBtn1);
        queryInfoInputPanel.add(resetBtn1);

        insertStudentPanel.add(studentIdLable_insert);
        insertStudentPanel.add(studentIdTextField_insert);
        insertStudentPanel.add(studentNameLable);
        insertStudentPanel.add(studentNameTextField);
        insertStudentPanel.add(studentSexLable);
        insertStudentPanel.add(studentSexTextField);
        insertStudentPanel.add(studentAgeLable);
        insertStudentPanel.add(studentAgeTextField);
        insertStudentPanel.add(majorLable);
        insertStudentPanel.add(majorTextField);
        insertStudentPanel.add(confirmBtn2);
        insertStudentPanel.add(resetBtn2);

        insertTeacherPanel.add(teacherIdLable);
        insertTeacherPanel.add(teacherIdTextField);
        insertTeacherPanel.add(teacherNameLable);
        insertTeacherPanel.add(teacherNameTextField);
        insertTeacherPanel.add(teacherSexLable);
        insertTeacherPanel.add(teacherSexTextField);
        insertTeacherPanel.add(teacherAgeLable);
        insertTeacherPanel.add(teacherAgeTextField);
        insertTeacherPanel.add(titleLable);
        insertTeacherPanel.add(titleTextField);
        insertTeacherPanel.add(confirmBtn3);
        insertTeacherPanel.add(resetBtn3);

        insertCoursePanel.add(courseIdLable);
        insertCoursePanel.add(courseIdTextField);
        insertCoursePanel.add(courseNameLable);
        insertCoursePanel.add(courseNameTextField);
        insertCoursePanel.add(courseHourLable);
        insertCoursePanel.add(courseHourTextField);
        insertCoursePanel.add(confirmBtn4);
        insertCoursePanel.add(resetBtn4);

        insertSchedulePanel.add(classIdLable);
        insertSchedulePanel.add(classIdTextField);
        insertSchedulePanel.add(courseIdLable_comboBox);
        insertSchedulePanel.add(courseIdComboBox);
        insertSchedulePanel.add(teacherIdLable_comboBox);
        insertSchedulePanel.add(teacherIdComboBox);
        insertSchedulePanel.add(classRoomLable);
        insertSchedulePanel.add(classRoomTextField);
        insertSchedulePanel.add(confirmBtn5);
        insertSchedulePanel.add(resetBtn5);

        insertElectiveCoursePanel.add(electiveCourseIdLable);
        insertElectiveCoursePanel.add(electiveCourseIdTextField);
        insertElectiveCoursePanel.add(studentIdLable_comboBox);
        insertElectiveCoursePanel.add(studentIdComboBox);
        insertElectiveCoursePanel.add(classIdLable_comboBox);
        insertElectiveCoursePanel.add(classIdComboBox);
        insertElectiveCoursePanel.add(confirmBtn6);
        insertElectiveCoursePanel.add(resetBtn6);

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
        optionPanel.add(studentBtn);
        optionPanel.add(teacherBtn);
        optionPanel.add(courseBtn);
        optionPanel.add(scheduleBtn);
        optionPanel.add(electiveCourseBtn);

        // 启动时默认在查询选课信息界面
        mainPanel.add(optionPanel, BorderLayout.NORTH);
        mainPanel.add(queryInfoPanel, BorderLayout.CENTER);
        mainPanel.add(queryInfoInputPanel, BorderLayout.WEST);
        NOW_CASE = QUERY_INFO_CASE;

        // 设置可见
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    // 启动系统时读取所有文件信息
    private static void readFile() throws IOException, ClassNotFoundException {
        file.readFile(students);
        file.readFile(teachers);
        file.readFile(courses);
        file.readFile(schedules);
        file.readFile(electiveCourses);
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

    private static Vector<Vector<java.io.Serializable>> getRowData (Electivecourse[] electiveCourses) {
        int count = 0;
        Vector<Vector<java.io.Serializable>> rowData = new Vector<>();
        for (Electivecourse electivecourse : electiveCourses) {
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
