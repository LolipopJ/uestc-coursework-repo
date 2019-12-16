import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * @author Lolipop
 * @lastUpdate 2019/12/16
 */
public class QuerySystemGui {
    // 文件默认存储目录
    private static Myfile file = new Myfile(System.getProperty("user.dir"));

    // 设置最大存储数MAXSIZE
    final private static int MAXSIZE = file.setMAXSIZE(100);

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
    final private static String MALE = "男";
    final private static String FEMALE = "女";
    final private static String DIALOG_EMPTY = "信息不能为空，请检查！";
    final private static String DIALOG_WRONG_INPUT = "输入有误，请检查！";

    // 初始化对象数组
    private static Student[] students = new Student[MAXSIZE];
    private static Teacher[] teachers = new Teacher[MAXSIZE];
    private static Course[] courses = new Course[MAXSIZE];
    private static Schedule[] schedules = new Schedule[MAXSIZE];
    private static Electivecourse[] electiveCourses = new Electivecourse[MAXSIZE];

    // JFrame框架
    private static JFrame frame;

    // 主面板，采用边框布局
    private static JPanel mainPanel = new JPanel(new BorderLayout(5,0));

    // 选项面板，采用流式布局
    private static JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

    // 选项面板按钮
    private static JButton queryInfoBtn = new JButton(QUERY_INFO_TITLE);
    private static JButton studentBtn = new JButton(STUDENT_TITLE);
    private static JButton teacherBtn = new JButton(TEACHER_TITLE);
    private static JButton courseBtn = new JButton(COURSE_TITLE);
    private static JButton scheduleBtn = new JButton(SCHEDULE_TITLE);
    private static JButton electiveCourseBtn = new JButton(ELECTIVE_COURSE_TITLE);

    // 表格面板，采用边框布局
    private static JPanel queryInfoPanel = new JPanel(new BorderLayout());
    private static JPanel studentPanel = new JPanel(new BorderLayout());
    private static JPanel teacherPanel = new JPanel(new BorderLayout());
    private static JPanel coursePanel = new JPanel(new BorderLayout());
    private static JPanel schedulePanel = new JPanel(new BorderLayout());
    private static JPanel electiveCoursePanel = new JPanel(new BorderLayout());

    // 表格面板存放的表格
    private static JTable queryInfoTable;
    private static JTable studentTable;
    private static JTable teacherTable;
    private static JTable courseTable;
    private static JTable scheduleTable;
    private static JTable electiveCourseTable;

    private static Vector<String> queryColumnName = new Vector<>();
    private static Vector<String> studentColumnName = new Vector<>();
    private static Vector<String> teacherColumnName = new Vector<>();
    private static Vector<String> courseColumnName = new Vector<>();
    private static Vector<String> scheduleColumnName = new Vector<>();
    private static Vector<String> electiveCourseColumnName = new Vector<>();

    private static Vector<Vector<Serializable>> queryInfoRowData;
    private static Vector<Vector<Serializable>> studentRowData;
    private static Vector<Vector<Serializable>> teacherRowData;
    private static Vector<Vector<Serializable>> courseRowData;
    private static Vector<Vector<Serializable>> scheduleRowData;
    private static Vector<Vector<Serializable>> electiveCourseRowData;

    // 输入面板，采用流式布局
    private static JPanel queryInfoInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
    private static JPanel insertStudentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
    private static JPanel insertTeacherPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
    private static JPanel insertCoursePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
    private static JPanel insertSchedulePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
    private static JPanel insertElectiveCoursePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));

    // 输入面板标签
    private static JLabel studentNameLable = new JLabel(NAME);
    private static JLabel studentSexLable = new JLabel(SEX);
    private static JLabel studentAgeLable = new JLabel(AGE);
    private static JLabel teacherNameLable = new JLabel(NAME);
    private static JLabel teacherSexLable = new JLabel(SEX);
    private static JLabel teacherAgeLable = new JLabel(AGE);
    private static JLabel studentIdLable_insert = new JLabel(STUDENT_ID);
    private static JLabel studentIdLable_query = new JLabel(STUDENT_ID);
    private static JLabel studentIdLable_comboBox = new JLabel(STUDENT_ID);
    private static JLabel majorLable = new JLabel(MAJOR);
    private static JLabel teacherIdLable = new JLabel(TEACHER_ID);
    private static JLabel teacherIdLable_comboBox = new JLabel(TEACHER_ID);
    private static JLabel titleLable = new JLabel(TITLE);
    private static JLabel courseIdLable = new JLabel(COURSE_ID);
    private static JLabel courseIdLable_comboBox = new JLabel(COURSE_ID);
    private static JLabel courseNameLable = new JLabel(COURSE_NAME);
    private static JLabel courseHourLable = new JLabel(COURSE_HOUR);
    private static JLabel classIdLable = new JLabel(CLASS_ID);
    private static JLabel classIdLable_comboBox = new JLabel(CLASS_ID);
    private static JLabel classRoomLable = new JLabel(CLASS_ROOM);
    private static JLabel electiveCourseIdLable = new JLabel(ELECTIVE_COURSE_ID);

    // 输入面板文本输入框
    private static JTextField studentNameTextField = new JTextField(10);
    private static JTextField studentAgeTextField = new JTextField(10);
    private static JTextField teacherNameTextField = new JTextField(10);
    private static JTextField teacherAgeTextField = new JTextField(10);
    private static JTextField studentIdTextField_insert = new JTextField(10);
    private static JTextField studentIdTextField_query = new JTextField(10);
    private static JTextField majorTextField = new JTextField(10);
    private static JTextField teacherIdTextField = new JTextField(10);
    private static JTextField titleTextField = new JTextField(10);
    private static JTextField courseIdTextField = new JTextField(10);
    private static JTextField courseNameTextField = new JTextField(10);
    private static JTextField courseHourTextField = new JTextField(10);
    private static JTextField classIdTextField = new JTextField(10);
    private static JTextField classRoomTextField = new JTextField(10);
    private static JTextField electiveCourseIdTextField = new JTextField(10);

    // 输入面板下拉框
    private static JComboBox<String> studentSexComboBox = new JComboBox<>();
    private static JComboBox<String> teacherSexComboBox = new JComboBox<>();
    private static JComboBox<String> courseIdComboBox = new JComboBox<>();
    private static JComboBox<String> teacherIdComboBox = new JComboBox<>();
    private static JComboBox<String> studentIdComboBox = new JComboBox<>();
    private static JComboBox<String> classIdComboBox = new JComboBox<>();

    private static ArrayList<String> courseIdList = new ArrayList<>();
    private static ArrayList<String> teacherIdList = new ArrayList<>();
    private static ArrayList<String> studentIdList = new ArrayList<>();
    private static ArrayList<String> classIdList = new ArrayList<>();

    // 输入面板按钮
    private static JButton queryInfoConfirm = new JButton("查询");
    private static JButton insertStudentConfirm = new JButton("添加");
    private static JButton insertTeacherConfirm = new JButton("添加");
    private static JButton insertCourseConfirm = new JButton("添加");
    private static JButton insertScheduleConfirm = new JButton("添加");
    private static JButton insertElectiveCourseConfirm = new JButton("添加");
    private static JButton queryInfoReset = new JButton("重置");
    private static JButton insertStudentReset = new JButton("重置");
    private static JButton insertTeacherReset = new JButton("重置");
    private static JButton insertCourseReset = new JButton("重置");
    private static JButton insertScheduleReset = new JButton("重置");
    private static JButton insertElectiveCourseReset = new JButton("重置");

    private static boolean insertScheduleBtn = false;
    private static boolean insertElectiveCourseBtn = false;

    // 提示信息窗口
    private static Dialog dialog = new Dialog(frame, "提示信息", true);
    private static JLabel dialogLable = new JLabel();
    private static JButton dialogButton = new JButton("确认");

    /*
     * Main函数
     */
    public static void main (String[] args) throws IOException, ClassNotFoundException {
        new QuerySystemGui();
    }

    /*
     * 初始化框架
     * @description 调用函数初始化JFrame框架
     */
    private QuerySystemGui() throws IOException, ClassNotFoundException {
        // 设置框架属性
        frame = new JFrame(sysTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 400));
        frame.setMinimumSize(new Dimension(800, 400));
        frame.setLocationRelativeTo(null);
        // frame.setExtendedState(Frame.MAXIMIZED_BOTH);

        // 文件数据初始化
        readFile();

        // 选项面板初始化
        initOptionPanel();

        // 表格面板初始化
        initTablePanel();

        // 输入面板初始化
        initInputPanel();

        // 提示信息框初始化
        initDialogPanel();

        // 主面板初始化，启动时默认在查询选课信息界面
        mainPanel.add(optionPanel, BorderLayout.NORTH);
        mainPanel.add(queryInfoPanel, BorderLayout.CENTER);
        mainPanel.add(queryInfoInputPanel, BorderLayout.WEST);
        NOW_CASE = QUERY_INFO_CASE;

        frame.setContentPane(mainPanel);

        // 设置可见
        frame.setVisible(true);
    }

    /*
     * 读取文件
     * @description 反序列化读取文件，将获取的内容存储到对象数组中
     */
    private static void readFile() throws IOException, ClassNotFoundException {
        file.readFile(students);
        file.readFile(teachers);
        file.readFile(courses);
        file.readFile(schedules);
        file.readFile(electiveCourses);
    }

    /*
     * 初始化选项面板
     */
    private static void initOptionPanel() {
        // 监听选项面板按钮
        queryInfoBtn.addActionListener(new queryInfoBtnListener());
        studentBtn.addActionListener(new studentBtnListener());
        teacherBtn.addActionListener(new teacherBtnListener());
        courseBtn.addActionListener(new courseBtnListener());
        scheduleBtn.addActionListener(new scheduleBtnListener());
        electiveCourseBtn.addActionListener(new electiveCourseBtnListener());

        // 初始化选项面板
        optionPanel.add(queryInfoBtn);
        optionPanel.add(studentBtn);
        optionPanel.add(teacherBtn);
        optionPanel.add(courseBtn);
        optionPanel.add(scheduleBtn);
        optionPanel.add(electiveCourseBtn);
    }

    /*
     * 初始化输入面板
     */
    private static void initInputPanel() {
        // 设置输入面板大小
        Dimension inputPanelDimension = new Dimension(180, 0);
        queryInfoInputPanel.setPreferredSize(inputPanelDimension);
        insertStudentPanel.setPreferredSize(inputPanelDimension);
        insertTeacherPanel.setPreferredSize(inputPanelDimension);
        insertCoursePanel.setPreferredSize(inputPanelDimension);
        insertSchedulePanel.setPreferredSize(inputPanelDimension);
        insertElectiveCoursePanel.setPreferredSize(inputPanelDimension);

        // 设置输入面板下拉选单大小
        Dimension comboBoxDimension = new Dimension(110, 20);
        studentSexComboBox.setPreferredSize(comboBoxDimension);
        teacherSexComboBox.setPreferredSize(comboBoxDimension);
        courseIdComboBox.setPreferredSize(comboBoxDimension);
        teacherIdComboBox.setPreferredSize(comboBoxDimension);
        studentIdComboBox.setPreferredSize(comboBoxDimension);
        classIdComboBox.setPreferredSize(comboBoxDimension);

        // 监听输入面板按钮
        queryInfoConfirm.addActionListener(new queryInfoConfirmListener());
        insertStudentConfirm.addActionListener(new insertStudentConfirmListener());
        insertTeacherConfirm.addActionListener(new insertTeacherConfirmListener());
        insertCourseConfirm.addActionListener(new insertCourseConfirmListener());
        insertScheduleConfirm.addActionListener(new insertScheduleConfirmListener());
        insertElectiveCourseConfirm.addActionListener(new insertElectiveCourseConfirmListener());

        queryInfoReset.addActionListener(new queryInfoResetListener());
        insertStudentReset.addActionListener(new insertStudentResetListener());
        insertTeacherReset.addActionListener(new insertTeacherResetListener());
        insertCourseReset.addActionListener(new insertCourseResetListener());
        insertScheduleReset.addActionListener(new insertScheduleResetListener());
        insertElectiveCourseReset.addActionListener(new insertElectiveCourseResetListener());

        // 添加输入面板下拉选单数据
        studentSexComboBox.addItem(MALE);
        studentSexComboBox.addItem(FEMALE);
        teacherSexComboBox.addItem(MALE);
        teacherSexComboBox.addItem(FEMALE);
        getComboBox();

        // 如果排课信息或选课信息下拉选单不存在数据则隐藏其按钮
        insertScheduleBtn = checkInsertScheduleComboBox();
        insertElectiveCourseBtn = checkInsertElectiveCourseComboBox();

        // 年龄、课时信息只允许输入数字
        studentAgeTextField.setDocument(new integerTextField());
        teacherAgeTextField.setDocument(new integerTextField());
        courseHourTextField.setDocument(new integerTextField());

        // 初始化查询界面输入面板
        queryInfoInputPanel.add(studentIdLable_query);
        queryInfoInputPanel.add(studentIdTextField_query);
        queryInfoInputPanel.add(queryInfoConfirm);
        queryInfoInputPanel.add(queryInfoReset);

        // 初始化学生信息界面输入面板
        insertStudentPanel.add(studentIdLable_insert);
        insertStudentPanel.add(studentIdTextField_insert);
        insertStudentPanel.add(studentNameLable);
        insertStudentPanel.add(studentNameTextField);
        insertStudentPanel.add(studentSexLable);
        insertStudentPanel.add(studentSexComboBox);
        insertStudentPanel.add(studentAgeLable);
        insertStudentPanel.add(studentAgeTextField);
        insertStudentPanel.add(majorLable);
        insertStudentPanel.add(majorTextField);
        insertStudentPanel.add(insertStudentConfirm);
        insertStudentPanel.add(insertStudentReset);

        // 初始化教师信息界面输入面板
        insertTeacherPanel.add(teacherIdLable);
        insertTeacherPanel.add(teacherIdTextField);
        insertTeacherPanel.add(teacherNameLable);
        insertTeacherPanel.add(teacherNameTextField);
        insertTeacherPanel.add(teacherSexLable);
        insertTeacherPanel.add(teacherSexComboBox);
        insertTeacherPanel.add(teacherAgeLable);
        insertTeacherPanel.add(teacherAgeTextField);
        insertTeacherPanel.add(titleLable);
        insertTeacherPanel.add(titleTextField);
        insertTeacherPanel.add(insertTeacherConfirm);
        insertTeacherPanel.add(insertTeacherReset);

        // 初始化课程信息界面输入面板
        insertCoursePanel.add(courseIdLable);
        insertCoursePanel.add(courseIdTextField);
        insertCoursePanel.add(courseNameLable);
        insertCoursePanel.add(courseNameTextField);
        insertCoursePanel.add(courseHourLable);
        insertCoursePanel.add(courseHourTextField);
        insertCoursePanel.add(insertCourseConfirm);
        insertCoursePanel.add(insertCourseReset);

        // 初始化排课信息界面输入面板
        insertSchedulePanel.add(classIdLable);
        insertSchedulePanel.add(classIdTextField);
        insertSchedulePanel.add(courseIdLable_comboBox);
        insertSchedulePanel.add(courseIdComboBox);
        insertSchedulePanel.add(teacherIdLable_comboBox);
        insertSchedulePanel.add(teacherIdComboBox);
        insertSchedulePanel.add(classRoomLable);
        insertSchedulePanel.add(classRoomTextField);
        insertSchedulePanel.add(insertScheduleConfirm);
        insertSchedulePanel.add(insertScheduleReset);

        // 初始化选课信息界面输入面板
        insertElectiveCoursePanel.add(electiveCourseIdLable);
        insertElectiveCoursePanel.add(electiveCourseIdTextField);
        insertElectiveCoursePanel.add(studentIdLable_comboBox);
        insertElectiveCoursePanel.add(studentIdComboBox);
        insertElectiveCoursePanel.add(classIdLable_comboBox);
        insertElectiveCoursePanel.add(classIdComboBox);
        insertElectiveCoursePanel.add(insertElectiveCourseConfirm);
        insertElectiveCoursePanel.add(insertElectiveCourseReset);
    }

    /*
     * 初始化表格面板
     */
    private static void initTablePanel() {
        // 初始化表格列标题
        queryColumnName.add(ID);
        queryColumnName.add(COURSE_NAME);
        queryColumnName.add(NAME);
        queryColumnName.add(CLASS_ROOM);
        studentColumnName.add(ID);
        studentColumnName.add(STUDENT_ID);
        studentColumnName.add(NAME);
        studentColumnName.add(SEX);
        studentColumnName.add(AGE);
        studentColumnName.add(MAJOR);
        teacherColumnName.add(ID);
        teacherColumnName.add(TEACHER_ID);
        teacherColumnName.add(NAME);
        teacherColumnName.add(SEX);
        teacherColumnName.add(AGE);
        teacherColumnName.add(TITLE);
        courseColumnName.add(ID);
        courseColumnName.add(COURSE_ID);
        courseColumnName.add(COURSE_NAME);
        courseColumnName.add(COURSE_HOUR);
        scheduleColumnName.add(ID);
        scheduleColumnName.add(CLASS_ID);
        scheduleColumnName.add(COURSE_ID);
        scheduleColumnName.add(TEACHER_ID);
        scheduleColumnName.add(CLASS_ROOM);
        electiveCourseColumnName.add(ID);
        electiveCourseColumnName.add(ELECTIVE_COURSE_ID);
        electiveCourseColumnName.add(STUDENT_ID);
        electiveCourseColumnName.add(CLASS_ID);

        // 初始化表格行数据
        queryInfoRowData = getRowData();
        studentRowData = getRowData(students);
        teacherRowData = getRowData(teachers);
        courseRowData = getRowData(courses);
        scheduleRowData = getRowData(schedules);
        electiveCourseRowData = getRowData(electiveCourses);

        // 初始化表格值
        queryInfoTable = new JTable(queryInfoRowData, queryColumnName);
        studentTable = new JTable(studentRowData, studentColumnName);
        teacherTable = new JTable(teacherRowData, teacherColumnName);
        courseTable = new JTable(courseRowData, courseColumnName);
        scheduleTable = new JTable(scheduleRowData, scheduleColumnName);
        electiveCourseTable = new JTable(electiveCourseRowData, electiveCourseColumnName);

        // 设置表格表头格式
        // 表头字体
        Font tableHeaderFont = new Font("SimHei", Font.BOLD, 16);
        queryInfoTable.getTableHeader().setFont(tableHeaderFont);
        studentTable.getTableHeader().setFont(tableHeaderFont);
        teacherTable.getTableHeader().setFont(tableHeaderFont);
        courseTable.getTableHeader().setFont(tableHeaderFont);
        scheduleTable.getTableHeader().setFont(tableHeaderFont);
        electiveCourseTable.getTableHeader().setFont(tableHeaderFont);

        // 设置表格内容格式
        // 内容字体
        Font rowDataFont = new Font("SimHei", Font.PLAIN, 14);
        queryInfoTable.setFont(rowDataFont);
        studentTable.setFont(rowDataFont);
        teacherTable.setFont(rowDataFont);
        courseTable.setFont(rowDataFont);
        scheduleTable.setFont(rowDataFont);
        electiveCourseTable.setFont(rowDataFont);

        // 内容行高
        queryInfoTable.setRowHeight(22);
        studentTable.setRowHeight(22);
        teacherTable.setRowHeight(22);
        courseTable.setRowHeight(22);
        scheduleTable.setRowHeight(22);
        electiveCourseTable.setRowHeight(22);

        // 内容居中
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        queryInfoTable.setDefaultRenderer(Object.class, cr);
        studentTable.setDefaultRenderer(Object.class, cr);
        teacherTable.setDefaultRenderer(Object.class, cr);
        courseTable.setDefaultRenderer(Object.class, cr);
        scheduleTable.setDefaultRenderer(Object.class, cr);
        electiveCourseTable.setDefaultRenderer(Object.class, cr);

        // 初始化表格面板并添加滚动条
        queryInfoPanel.add(queryInfoTable, BorderLayout.CENTER);
        queryInfoPanel.add(new JScrollPane(queryInfoTable));
        studentPanel.add(studentTable, BorderLayout.CENTER);
        studentPanel.add(new JScrollPane(studentTable));
        teacherPanel.add(teacherTable, BorderLayout.CENTER);
        teacherPanel.add(new JScrollPane(teacherTable));
        coursePanel.add(courseTable, BorderLayout.CENTER);
        coursePanel.add(new JScrollPane(courseTable));
        schedulePanel.add(scheduleTable, BorderLayout.CENTER);
        schedulePanel.add(new JScrollPane(scheduleTable));
        electiveCoursePanel.add(electiveCourseTable, BorderLayout.CENTER);
        electiveCoursePanel.add(new JScrollPane(electiveCourseTable));
    }

    /*
     * 获取表格信息
     * @description 读取文件中获取的对象数组的内容并返回存放表格数据的对象数组
     */
    private static Vector<Vector<java.io.Serializable>> getRowData() {
        // 返回未存放数据的Vector
        return new Vector<>();
    }

    private static Vector<Vector<java.io.Serializable>> getRowData(Student[] students) {
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

    private static Vector<Vector<java.io.Serializable>> getRowData(Teacher[] teachers) {
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

    private static Vector<Vector<java.io.Serializable>> getRowData(Course[] courses) {
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

    private static Vector<Vector<java.io.Serializable>> getRowData(Schedule[] schedules) {
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

    private static Vector<Vector<java.io.Serializable>> getRowData(Electivecourse[] electiveCourses) {
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

    /*
     * 初始化提示信息窗口
     * @description 生成一个具有提示信息的新窗口
     */
    private static void initDialogPanel() {
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER));

        dialogButton.addActionListener(e-> dialog.setVisible(false));
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dialog.setVisible(false);
            }
        });

        dialog.add(dialogLable);
        dialog.add(dialogButton);
    }

    /*
     * 显示提示信息窗口
     * @description 修改提示信息并使提示窗口可见
     */
    private static void dialog(String words) {
        dialog.setSize(new Dimension(200, 100));
        dialog.setLocationRelativeTo(null);

        dialogLable.setText(words);
        dialog.setVisible(true);
    }

    /*
     * 选项按钮监听器
     * @description 点击时切换工作面板
     */
    private static class queryInfoBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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
        }
    }

    private static class studentBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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
        }
    }

    private static class teacherBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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
        }
    }

    private static class courseBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
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
        }
    }

    private static class scheduleBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (NOW_CASE != SCHEDULE_CASE) {
                mainPanel.removeAll();
                mainPanel.add(optionPanel, BorderLayout.NORTH);
                mainPanel.add(schedulePanel, BorderLayout.CENTER);
                mainPanel.add(insertSchedulePanel, BorderLayout.WEST);
                mainPanel.updateUI();
                mainPanel.repaint();

                NOW_CASE = STUDENT_CASE;
                frame.setTitle(sysTitle+" - "+SCHEDULE_TITLE);

                // 如果排课信息输入面板按钮被隐藏，则重新进行判断
                if (!insertScheduleBtn) {
                    insertScheduleBtn = checkInsertScheduleComboBox();
                }
            }
        }
    }

    private static class electiveCourseBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (NOW_CASE != ELECTIVE_COURSE_CASE) {
                mainPanel.removeAll();
                mainPanel.add(optionPanel, BorderLayout.NORTH);
                mainPanel.add(electiveCoursePanel, BorderLayout.CENTER);
                mainPanel.add(insertElectiveCoursePanel, BorderLayout.WEST);
                mainPanel.updateUI();
                mainPanel.repaint();

                NOW_CASE = ELECTIVE_COURSE_CASE;
                frame.setTitle(sysTitle+" - "+ELECTIVE_COURSE_TITLE);

                if (!insertElectiveCourseBtn) {
                    insertElectiveCourseBtn = checkInsertElectiveCourseComboBox();
                }
            }
        }
    }

    /*
     * 输入面板确定按钮监听器
     * @description 点击时检索信息或将数据写入文件，并刷新表单
     */
    private static class queryInfoConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String studentId = studentIdTextField_query.getText();

            // 检查输入是否为空
            if (studentId.equals("")) {
                dialog(STUDENT_ID+DIALOG_EMPTY);
                return;
            }

            boolean hasElectiveCourse = false;
            int count = 0;
            String classroom;
            String courseName = null;
            String teacherName = null;

            // 根据学号检索选课类，获得班级号
            for (Electivecourse electivecourse : electiveCourses) {
                if (electivecourse != null && studentId.equals(electivecourse.getSid())) {
                    String classId = electivecourse.getClassid();

                    // 根据班级号检索排课类，获得课程号、教师号和上课教室
                    for (Schedule schedule : schedules) {
                        if (schedule != null && classId.equals(schedule.getClassid())) {
                            String courseId = schedule.getCid();
                            String teacherId = schedule.getTid();
                            classroom = schedule.getClassroom();

                            // 根据课程号、教师号分别获取课程名称和教师名称
                            for (Course course : courses) {
                                if (course != null && courseId.equals(course.getCid())) {
                                    courseName = course.getCname();
                                    break;
                                }
                            }

                            for (Teacher teacher : teachers) {
                                if (teacher != null && teacherId.equals(teacher.getTid())) {
                                    teacherName = teacher.getName();
                                    break;
                                }
                            }

                            // 查询的结果
                            count++;
                            Vector<java.io.Serializable> line = new Vector<>();
                            line.add(count);
                            line.add(courseName);
                            line.add(teacherName);
                            line.add(classroom);
                            queryInfoRowData.add(line);

                            // 设置布尔值为真
                            hasElectiveCourse = true;
                            break;
                        }
                    }
                }
            }

            // 如果为查询到信息，则显示dialog
            if (!hasElectiveCourse) {
                dialog("未查询到"+studentId+"的选课信息");
            }
            else queryInfoTable.setModel(new DefaultTableModel(queryInfoRowData, queryColumnName));

            // 修改框架名
            frame.setTitle(sysTitle+" - "+QUERY_INFO_TITLE+" - "+studentId);

            // 点击查询按钮后选中输入的学号
            studentIdTextField_query.requestFocus();
            studentIdTextField_query.selectAll();
        }
    }

    private static class insertStudentConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (studentAgeTextField.getText().equals("")) {
                dialog(DIALOG_EMPTY);
                return;
            }

            int studentAge = Integer.parseInt(studentAgeTextField.getText());

            // 检查输入值
            if (checkAge(studentAge)) {
                dialog("学生"+AGE+DIALOG_WRONG_INPUT);
                studentAgeTextField.requestFocus();
                studentAgeTextField.selectAll();
                return;
            }

            String studentSex = Objects.requireNonNull(studentSexComboBox.getSelectedItem()).toString();
            String studentId = studentIdTextField_insert.getText();
            String studentName = studentNameTextField.getText();
            String major = majorTextField.getText();

            if (studentSex.equals("") || studentId.equals("")
                    || studentName.equals("") || major.equals("")) {
                dialog(DIALOG_EMPTY);
                return;
            }

            // 将输入内容写入文件并重新读取文件到对象数组students中
            try {
                file.writeFile(new Student(studentName, studentSex, studentAge, studentId, major));
                file.readFile(students);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            // 刷新表格
            Vector<java.io.Serializable> line = new Vector<>();
            line.add(studentTable.getRowCount()+1);
            line.add(studentId);
            line.add(studentName);
            line.add(studentSex);
            line.add(studentAge);
            line.add(major);
            studentRowData.add(line);
            studentTable.setModel(new DefaultTableModel(studentRowData, studentColumnName));

            // 重置信息
            studentIdTextField_insert.setText("");
            studentNameTextField.setText("");

            studentIdTextField_insert.requestFocus();

            // 刷新下拉选单
            studentIdComboBox.addItem(studentId);
        }
    }

    private static class insertTeacherConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (teacherAgeTextField.getText().equals("")) {
                dialog(DIALOG_EMPTY);
                return;
            }

            int teacherAge = Integer.parseInt(teacherAgeTextField.getText());

            if (checkAge(teacherAge)) {
                dialog("教师"+AGE+DIALOG_WRONG_INPUT);
                teacherAgeTextField.requestFocus();
                teacherAgeTextField.selectAll();
                return;
            }

            String teacherSex = Objects.requireNonNull(teacherSexComboBox.getSelectedItem()).toString();
            String teacherId = teacherIdTextField.getText();
            String teacherName = teacherNameTextField.getText();
            String title = titleTextField.getText();

            if (teacherSex.equals("") || teacherId.equals("") || teacherName.equals("") || title.equals("")) {
                dialog(DIALOG_EMPTY);
                return;
            }

            try {
                file.writeFile(new Teacher(teacherName, teacherSex, teacherAge, teacherId, title));
                file.readFile(teachers);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            Vector<java.io.Serializable> line = new Vector<>();
            line.add(teacherTable.getRowCount()+1);
            line.add(teacherId);
            line.add(teacherName);
            line.add(teacherSex);
            line.add(teacherAge);
            line.add(title);
            teacherRowData.add(line);
            teacherTable.setModel(new DefaultTableModel(teacherRowData, teacherColumnName));

            teacherIdTextField.setText("");
            teacherNameTextField.setText("");
            teacherAgeTextField.setText("");

            teacherIdTextField.requestFocus();

            teacherIdComboBox.addItem(teacherId);
        }
    }

    private static class insertCourseConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (courseHourTextField.getText().equals("")) {
                dialog(DIALOG_EMPTY);
                return;
            }

            int courseHour = Integer.parseInt(courseHourTextField.getText());

            if (checkCourseHour(courseHour)) {
                dialog(COURSE_HOUR+DIALOG_WRONG_INPUT);
                courseHourTextField.requestFocus();
                courseHourTextField.selectAll();
                return;
            }

            String courseId = courseIdTextField.getText();
            String courseName = courseNameTextField.getText();

            if (courseId.equals("") || courseName.equals("")) {
                dialog(DIALOG_EMPTY);
                return;
            }

            try {
                file.writeFile(new Course(courseName, courseId, courseHour));
                file.readFile(courses);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            Vector<java.io.Serializable> line = new Vector<>();
            line.add(courseTable.getRowCount()+1);
            line.add(courseId);
            line.add(courseName);
            line.add(courseHour);
            courseRowData.add(line);
            courseTable.setModel(new DefaultTableModel(courseRowData, courseColumnName));

            courseIdTextField.setText("");
            courseNameTextField.setText("");
            courseHourTextField.setText(" ");

            courseIdTextField.requestFocus();

            courseIdComboBox.addItem(courseId);
        }
    }

    private static class insertScheduleConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String classId = classIdTextField.getText();
            String courseId = Objects.requireNonNull(courseIdComboBox.getSelectedItem()).toString();
            String teacherId = Objects.requireNonNull(teacherIdComboBox.getSelectedItem()).toString();
            String classRoom = classRoomTextField.getText();

            if (classId.equals("") || courseId.equals("") || teacherId.equals("") || classRoom.equals("")) {
                dialog(DIALOG_EMPTY);
                return;
            }

            try {
                file.writeFile(new Schedule(classId, courseId, teacherId, classRoom));
                file.readFile(schedules);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            Vector<java.io.Serializable> line = new Vector<>();
            line.add(scheduleTable.getRowCount()+1);
            line.add(classId);
            line.add(courseId);
            line.add(teacherId);
            line.add(classRoom);
            scheduleRowData.add(line);
            scheduleTable.setModel(new DefaultTableModel(scheduleRowData, scheduleColumnName));

            classIdTextField.setText("");
            courseIdComboBox.setSelectedIndex(0);
            classRoomTextField.setText("");

            classIdTextField.requestFocus();

            classIdComboBox.addItem(classId);
        }
    }

    private static class insertElectiveCourseConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String electiveCourseId = electiveCourseIdTextField.getText();
            String studentId = Objects.requireNonNull(studentIdComboBox.getSelectedItem()).toString();
            String classId = Objects.requireNonNull(classIdComboBox.getSelectedItem()).toString();

            if (electiveCourseId.equals("") || studentId.equals("") || classId.equals("")) {
                dialog(DIALOG_EMPTY);
                return;
            }

            try {
                file.writeFile(new Electivecourse(classId, electiveCourseId, studentId));
                file.readFile(electiveCourses);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            Vector<java.io.Serializable> line = new Vector<>();
            line.add(electiveCourseTable.getRowCount()+1);
            line.add(electiveCourseId);
            line.add(studentId);
            line.add(classId);
            electiveCourseRowData.add(line);
            electiveCourseTable.setModel(new DefaultTableModel(electiveCourseRowData, electiveCourseColumnName));

            electiveCourseIdTextField.setText("");
            classIdComboBox.setSelectedIndex(0);

            electiveCourseIdTextField.requestFocus();
        }
    }

    /*
     * 输入面板重置按钮监听器
     * @description 点击时将输入面板清零
     */
    private static class queryInfoResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 清除输入学号文本框并选中文本框
            studentIdTextField_query.setText("");
            studentIdTextField_query.requestFocus();
        }
    }

    private static class insertStudentResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            studentIdTextField_insert.setText("");
            studentNameTextField.setText("");
            studentSexComboBox.setSelectedIndex(0);
            studentAgeTextField.setText("");
            majorTextField.setText("");

            studentIdTextField_insert.requestFocus();
        }
    }

    private static class insertTeacherResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            teacherIdTextField.setText("");
            teacherNameTextField.setText("");
            teacherSexComboBox.setSelectedIndex(0);
            teacherAgeTextField.setText("");
            titleTextField.setText("");

            teacherIdTextField.requestFocus();
        }
    }

    private static class insertCourseResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            courseIdTextField.setText("");
            courseNameTextField.setText("");
            courseHourTextField.setText("");

            courseIdTextField.requestFocus();
        }
    }

    private static class insertScheduleResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            classIdTextField.setText("");
            courseIdComboBox.setSelectedIndex(0);
            teacherIdComboBox.setSelectedIndex(0);
            classRoomTextField.setText("");

            classIdTextField.requestFocus();
        }
    }

    private static class insertElectiveCourseResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            electiveCourseIdTextField.setText("");
            studentIdComboBox.setSelectedIndex(0);
            classIdComboBox.setSelectedIndex(0);

            electiveCourseIdTextField.requestFocus();
        }
    }

    /*
     * 数字输入检验
     * @description 限制年龄、课时输入只能输入数字
     */
    private static class integerTextField extends PlainDocument {
        public integerTextField() {
            super();
        }

        public void insertString(int offset, String str, AttributeSet attr) throws javax.swing.text.BadLocationException {
            if (str == null) {
                return;
            }

            char[] s = str.toCharArray();
            int length = 0;
            // 过滤非数字
            for (int i = 0; i < s.length; i++) {
                if ((s[i] >= '0') && (s[i] <= '9')) {
                    s[length++] = s[i];
                }
                super.insertString(offset, new String(s, 0, length), attr);
            }
        }
    }

    /*
     * 数据输入检验
     * @description 检验输入的年龄、课时数据是否合理
     */
    private static boolean checkAge(int age) {
        // 当输入的年龄大于120或小于0时，输入数据无效
        return (age < 0 || age > 120);
    }

    private static boolean checkCourseHour(int courseHour) {
        // 当输入的课时大于240或小于0时，输入数据无效
        return (courseHour < 0 || courseHour > 240);
    }

    /*
     * 获取输入面板下拉选单
     * @description 将文件读取到的信息添加到下拉选单中
     */
    private static void getComboBox() {
        // 遍历信息添加到ArrayList中
        for (Course course : courses) {
            if (course != null)
                courseIdList.add(course.getCid());
            else break;
        }
        for (Teacher teacher : teachers) {
            if (teacher != null)
                teacherIdList.add(teacher.getTid());
            else break;
        }
        for (Student student : students) {
            if (student != null)
                studentIdList.add(student.getSid());
            else break;
        }
        for (Schedule schedule : schedules) {
            if (schedule != null)
                classIdList.add(schedule.getClassid());
            else break;
        }

        // 对信息进行排序
        Collections.sort(courseIdList);
        Collections.sort(teacherIdList);
        Collections.sort(studentIdList);
        Collections.sort(classIdList);

        // 将排序后的信息添加到下拉选单中
        for (String courseid : courseIdList) {
            courseIdComboBox.addItem(courseid);
        }
        for (String teacherid : teacherIdList) {
            teacherIdComboBox.addItem(teacherid);
        }
        for (String studentid : studentIdList) {
            studentIdComboBox.addItem(studentid);
        }
        for (String classid : classIdList) {
            classIdComboBox.addItem(classid);
        }
    }

    /*
     * 检验输入面板下拉选单
     * @description 如果不存在则设置对应面板按钮不可见
     */
    private static boolean checkInsertElectiveCourseComboBox() {
        if (studentIdComboBox.getSelectedItem() == null || classIdComboBox.getSelectedItem() == null) {
            insertElectiveCourseConfirm.setVisible(false);
            insertElectiveCourseReset.setVisible(false);
            return false;
        }
        else {
            insertElectiveCourseConfirm.setVisible(true);
            insertElectiveCourseReset.setVisible(true);
            return true;
        }
    }

    private static boolean checkInsertScheduleComboBox() {
        if (courseIdComboBox.getSelectedItem() == null || teacherIdComboBox.getSelectedItem() == null) {
            insertScheduleConfirm.setVisible(false);
            insertScheduleReset.setVisible(false);
            return false;
        }
        else {
            insertScheduleConfirm.setVisible(true);
            insertScheduleReset.setVisible(true);
            return true;
        }
    }
}