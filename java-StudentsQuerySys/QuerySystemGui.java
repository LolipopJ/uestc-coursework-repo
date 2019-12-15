import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
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
    final private static String MALE = "男";
    final private static String FEMALE = "女";

    // 初始化对象数组
    private static Student[] students = new Student[MAXSIZE];
    private static Teacher[] teachers = new Teacher[MAXSIZE];
    private static Course[] courses = new Course[MAXSIZE];
    private static Schedule[] schedules = new Schedule[MAXSIZE];
    private static Electivecourse[] electiveCourses = new Electivecourse[MAXSIZE];

    // JFrame框架
    private static JFrame frame = new JFrame(sysTitle);

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

    // 输入面板按钮
    private static JButton confirmBtn1 = new JButton("查询");
    private static JButton confirmBtn2 = new JButton("添加");
    private static JButton confirmBtn3 = new JButton("添加");
    private static JButton confirmBtn4 = new JButton("添加");
    private static JButton confirmBtn5 = new JButton("添加");
    private static JButton confirmBtn6 = new JButton("添加");
    private static JButton resetBtn1 = new JButton("重置");
    private static JButton resetBtn2 = new JButton("重置");
    private static JButton resetBtn3 = new JButton("重置");
    private static JButton resetBtn4 = new JButton("重置");
    private static JButton resetBtn5 = new JButton("重置");
    private static JButton resetBtn6 = new JButton("重置");

    // 提示信息窗口
    private static Dialog dialog = new Dialog(frame, "提示信息", true);
    private static JLabel dialogLable = new JLabel();
    private static JButton dialogButton = new JButton("确认");

    // 主函数
    public static void main (String[] args) throws IOException, ClassNotFoundException {
        new QuerySystemGui();
    }

    private QuerySystemGui() throws IOException, ClassNotFoundException {
        // 设置框架属性
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 400));
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);

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

    // 初始化选项面板
    private static void initOptionPanel() {
        // 选项面板按钮监听
        queryInfoBtn.addActionListener(new queryInfoBtnListener());
        studentBtn.addActionListener(new studentBtnListener());
        teacherBtn.addActionListener(new teacherBtnListener());
        courseBtn.addActionListener(new courseBtnListener());
        scheduleBtn.addActionListener(new scheduleBtnListener());
        electiveCourseBtn.addActionListener(new electiveCourseBtnListener());

        // 选项面板初始化
        optionPanel.add(queryInfoBtn);
        optionPanel.add(studentBtn);
        optionPanel.add(teacherBtn);
        optionPanel.add(courseBtn);
        optionPanel.add(scheduleBtn);
        optionPanel.add(electiveCourseBtn);
    }

    // 初始化输入面板
    private static void initInputPanel() {
        // 设置输入面板属性
        Dimension inputPanelDimension = new Dimension(180, 0);
        queryInfoInputPanel.setPreferredSize(inputPanelDimension);
        insertStudentPanel.setPreferredSize(inputPanelDimension);
        insertTeacherPanel.setPreferredSize(inputPanelDimension);
        insertCoursePanel.setPreferredSize(inputPanelDimension);
        insertSchedulePanel.setPreferredSize(inputPanelDimension);
        insertElectiveCoursePanel.setPreferredSize(inputPanelDimension);

        // 设置输入面板内容物属性
        Dimension comboBoxDimension = new Dimension(110, 20);
        studentSexComboBox.setPreferredSize(comboBoxDimension);
        teacherSexComboBox.setPreferredSize(comboBoxDimension);
        courseIdComboBox.setPreferredSize(comboBoxDimension);
        teacherIdComboBox.setPreferredSize(comboBoxDimension);
        studentIdComboBox.setPreferredSize(comboBoxDimension);
        classIdComboBox.setPreferredSize(comboBoxDimension);

        // 输入面板按钮监听
        confirmBtn1.addActionListener(new confirmBtn1Listener());
        confirmBtn2.addActionListener(new confirmBtn2Listener());
        confirmBtn3.addActionListener(new confirmBtn3Listener());
        confirmBtn4.addActionListener(new confirmBtn4Listener());
        confirmBtn5.addActionListener(new confirmBtn5Listener());
        confirmBtn6.addActionListener(new confirmBtn6Listener());

        resetBtn1.addActionListener(new resetBtn1Listener());
        resetBtn2.addActionListener(new resetBtn2Listener());
        resetBtn3.addActionListener(new resetBtn3Listener());
        resetBtn4.addActionListener(new resetBtn4Listener());
        resetBtn5.addActionListener(new resetBtn5Listener());
        resetBtn6.addActionListener(new resetBtn6Listener());

        // 当下拉选单未读取到数据时禁用按钮

        // 添加输入面板下拉框数据
        studentSexComboBox.addItem(MALE);
        studentSexComboBox.addItem(FEMALE);
        teacherSexComboBox.addItem(MALE);
        teacherSexComboBox.addItem(FEMALE);

        // 初始化查询界面输入面板
        queryInfoInputPanel.add(studentIdLable_query);
        queryInfoInputPanel.add(studentIdTextField_query);
        queryInfoInputPanel.add(confirmBtn1);
        queryInfoInputPanel.add(resetBtn1);

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
        insertStudentPanel.add(confirmBtn2);
        insertStudentPanel.add(resetBtn2);

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
        insertTeacherPanel.add(confirmBtn3);
        insertTeacherPanel.add(resetBtn3);

        // 初始化课程信息界面输入面板
        insertCoursePanel.add(courseIdLable);
        insertCoursePanel.add(courseIdTextField);
        insertCoursePanel.add(courseNameLable);
        insertCoursePanel.add(courseNameTextField);
        insertCoursePanel.add(courseHourLable);
        insertCoursePanel.add(courseHourTextField);
        insertCoursePanel.add(confirmBtn4);
        insertCoursePanel.add(resetBtn4);

        // 初始化排课信息界面输入面板
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

        // 初始化选课信息界面输入面板
        insertElectiveCoursePanel.add(electiveCourseIdLable);
        insertElectiveCoursePanel.add(electiveCourseIdTextField);
        insertElectiveCoursePanel.add(studentIdLable_comboBox);
        insertElectiveCoursePanel.add(studentIdComboBox);
        insertElectiveCoursePanel.add(classIdLable_comboBox);
        insertElectiveCoursePanel.add(classIdComboBox);
        insertElectiveCoursePanel.add(confirmBtn6);
        insertElectiveCoursePanel.add(resetBtn6);
    }

    // 初始化表格
    private static void initTablePanel() throws IOException, ClassNotFoundException {
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

        // 初始化表格内容
        queryInfoTable = new JTable(queryInfoRowData, queryColumnName);
        studentTable = new JTable(studentRowData, studentColumnName);
        teacherTable = new JTable(teacherRowData, teacherColumnName);
        courseTable = new JTable(courseRowData, courseColumnName);
        scheduleTable = new JTable(scheduleRowData, scheduleColumnName);
        electiveCourseTable = new JTable(electiveCourseRowData, electiveCourseColumnName);

        // 初始化表格面板
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
    }

    // 从文件中获取表格信息
    private static Vector<Vector<java.io.Serializable>> getRowData() {
        // 返回未存放数据的Vector
        return new Vector<>();
    }

    private static Vector<Vector<java.io.Serializable>> getRowData(Student[] students) throws IOException, ClassNotFoundException {
        file.readFile(students);
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

    private static Vector<Vector<java.io.Serializable>> getRowData(Teacher[] teachers) throws IOException, ClassNotFoundException {
        file.readFile(teachers);
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

    private static Vector<Vector<java.io.Serializable>> getRowData(Course[] courses) throws IOException, ClassNotFoundException {
        file.readFile(courses);
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

    private static Vector<Vector<java.io.Serializable>> getRowData(Schedule[] schedules) throws IOException, ClassNotFoundException {
        file.readFile(schedules);
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

    private static Vector<Vector<java.io.Serializable>> getRowData(Electivecourse[] electiveCourses) throws IOException, ClassNotFoundException {
        file.readFile(electiveCourses);
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

    // 初始化提示信息窗口
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

    // 显示带有指定内容的提示信息窗口
    private static void dialog(String words) {
        dialog.setSize(new Dimension(200, 100));
        dialog.setLocationRelativeTo(null);

        dialogLable.setText(words);
        dialog.setVisible(true);
    }

    // 选项按钮监听器
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
            }
        }
    }

    // 确定按钮监听器
    private static class confirmBtn1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String studentId = studentIdTextField_query.getText();

            int count = 0;
            String classroom = null;
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
                            break;
                        }
                    }
                }
            }
            queryInfoTable.setModel(new DefaultTableModel(queryInfoRowData, queryColumnName));
        }
    }

    private static class confirmBtn2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 检查输入值
            int studentAge = Integer.parseInt(studentAgeTextField.getText());
            if (checkAge(studentAge)) {
                dialog("学生年龄输入有误，请检查！");
                return;
            }
            String studentSex = Objects.requireNonNull(studentSexComboBox.getSelectedItem()).toString();
            if (studentSex == null) {
                dialog("学生性别不能为空，请检查！");
                return;
            }

            String studentId = studentIdTextField_insert.getText();
            String studentName = studentNameTextField.getText();
            String major = majorTextField.getText();

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
        }
    }

    private static class confirmBtn3Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int teacherAge = Integer.parseInt(teacherAgeTextField.getText());
            if (checkAge(teacherAge)) {
                dialog("教师年龄输入有误，请检查！");
                return;
            }
            String teacherSex = Objects.requireNonNull(teacherSexComboBox.getSelectedItem()).toString();
            if (teacherSex == null) {
                dialog("教师性别不能为空，请检查！");
                return;
            }

            String teacherId = teacherIdTextField.getText();
            String teacherName = teacherNameTextField.getText();
            String title = titleTextField.getText();

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
        }
    }

    private static class confirmBtn4Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String courseId = courseIdTextField.getText();
            String courseName = courseNameTextField.getText();
            int courseHour = Integer.parseInt(courseHourTextField.getText());

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
        }
    }

    private static class confirmBtn5Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String classId = classIdTextField.getText();
            String courseId = Objects.requireNonNull(courseIdComboBox.getSelectedItem()).toString();
            String teacherId = Objects.requireNonNull(teacherIdComboBox.getSelectedItem()).toString();
            String classRoom = classRoomTextField.getText();

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
        }
    }

    private static class confirmBtn6Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String electiveCourseId = electiveCourseIdTextField.getText();
            String studentId = Objects.requireNonNull(studentIdComboBox.getSelectedItem()).toString();
            String classId = Objects.requireNonNull(classIdComboBox.getSelectedItem()).toString();

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
        }
    }

    // 重置按钮监听器
    private static class resetBtn1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 清除输入学号文本框并选中文本框
            studentIdTextField_query.setText("");
            studentIdTextField_query.requestFocus();
        }
    }

    private static class resetBtn2Listener implements ActionListener {
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

    private static class resetBtn3Listener implements ActionListener {
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

    private static class resetBtn4Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            courseIdTextField.setText("");
            courseNameTextField.setText("");
            courseHourTextField.setText("");

            courseIdTextField.requestFocus();
        }
    }

    private static class resetBtn5Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            classIdTextField.setText("");
            courseIdComboBox.setSelectedIndex(0);
            teacherIdComboBox.setSelectedIndex(0);
            classRoomTextField.setText("");

            classIdTextField.requestFocus();
        }
    }

    private static class resetBtn6Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            electiveCourseIdTextField.setText("");
            studentIdComboBox.setSelectedIndex(0);
            classIdComboBox.setSelectedIndex(0);

            electiveCourseIdTextField.requestFocus();
        }
    }

    // 输入检验
    private static boolean checkAge(int age) {
        // 当输入的年龄大于120或小于0时，输入数据无效
        return (age < 0 || age > 120);
    }
}
