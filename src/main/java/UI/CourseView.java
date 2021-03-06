package UI;

import Model.Class;
import Model.ClassInfo;
import Model.Grade;
import Model.Student;
import Presenter.CoursePresenter;
import Utils.LoginInfo;
import Utils.TabController;
import com.mysql.cj.util.StringUtils;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import dao.SqlOperation;
import dao.SqlOperationImpl;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CourseView extends BaseView implements ICourseView {

    private CoursePresenter presenter;
    private DefaultTableModel tableModel;
    private DefaultTableModel tableMode2;
    private DefaultTableModel tableMode3;
    private DefaultTableModel tableMode4;
    private DefaultTableModel tableMode5;
    private String[] className;
    private JPanel tab1 = new JPanel();
    private JPanel tab2 = new JPanel();
    private JPanel tab3 = new JPanel();
    private JPanel tab4 = new JPanel();
    private List<Grade> gradeList;
    private List<Class> classList;
    private DefaultTableCellRenderer cellRenderer;



    @Override
    protected void onCreate() {
        super.onCreate();
        //返回主界面
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                onDestory();
            }
        });
        cellRenderer = new DefaultTableCellRenderer();

        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        jFrame.setTitle("学生成绩管理");
        jFrame.getContentPane().setBackground(Color.WHITE);
        presenter = new CoursePresenter(this);
        presenter.queryClassById();
        initTab(tab1);
        initTab(tab2);
        tab2.setVisible(false);
        initTab(tab3);
        tab3.setVisible(false);
        initTab(tab4);
        tab4.setVisible(false);
        LeftUIInit();
        tab1RightUIInit();
        tab2RightUIInit();
        tab3RightUIInit();
        tab4RightUIInit();
    }

    private void tab4RightUIInit() {
        final String[] newClassName = className.clone();
        newClassName[0] = "课程";
        JLabel jLabel = new JLabel("查找最高分的学生信息",JLabel.CENTER);
        jLabel.setBounds(0,35,700,30);
        tab4.add(jLabel);

        final JComboBox<String> classTitles = new JComboBox<String>(newClassName);
        classTitles.setBackground(Color.WHITE);
        classTitles.setBounds(240,70,80,30);
        tab4.add(classTitles);
        JButton jButton = new JButton("查找");
        jButton.setBackground(Color.WHITE);
        jButton.setBounds(360,70,80,30);
        tab4.add(jButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (classTitles.getSelectedIndex()!=0){
                    presenter.getMaxStudentInfo(newClassName[classTitles.getSelectedIndex()]);
                }
            }
        });

        String[] titles1 = {"学号","姓名","班级","性别","专业","分数"};
        tableMode4 = new DefaultTableModel(titles1,1);
        JTable jTable = new JTable(tableMode4){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable.setDefaultRenderer(Object.class,cellRenderer);
        JScrollPane scrollpane=new JScrollPane(jTable);
        scrollpane.setBounds(50,120,610,80);
        tab4.add(scrollpane);

        JLabel jLabe2 = new JLabel("课程成绩信息统计",JLabel.CENTER);
        jLabe2.setBounds(0,220,700,30);
        tab4.add(jLabe2);

        final JComboBox<String> classTitles1 = new JComboBox<String>(newClassName);
        classTitles1.setBackground(Color.WHITE);
        classTitles1.setBounds(240,255,80,30);
        tab4.add(classTitles1);

        JButton jButton1 = new JButton("统计");
        jButton1.setBackground(Color.WHITE);
        jButton1.setBounds(360,255,80,30);
        tab4.add(jButton1);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (classTitles1.getSelectedIndex()!=0){
                    presenter.getClassInfo(newClassName[classTitles1.getSelectedIndex()]);
                }
            }
        });

        String[] titles2 = {"课程名称","任课老师","考试人数","及格人数","及格率","最高分","最低分","平均分"};
        tableMode5 = new DefaultTableModel(titles2,1);
        JTable jTable2 = new JTable(tableMode5){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable2.setDefaultRenderer(Object.class,cellRenderer);
        JScrollPane scrollpane1=new JScrollPane(jTable2);
        scrollpane1.setBounds(50,305,610,39);
        tab4.add(scrollpane1);

    }

    private void tab3RightUIInit() {
        final JTextField jTextField = new JTextField();
        jTextField.setBounds(160,35,300,30);
        tab3.add(jTextField);
        final JComboBox jcombo = new JComboBox();
        jcombo.addItem("姓名");
        jcombo.addItem("学号");
        jcombo.setBounds(50,35,80,30);
        jcombo.setBackground(Color.WHITE);
        tab3.add(jcombo);

        final JComboBox jComboBox = new JComboBox<>(className);
        jComboBox.setBounds(480,35,80,30);
        jComboBox.setBackground(Color.WHITE);
        tab3.add(jComboBox);

        JButton jButton = new JButton("查找");
        jButton.setBackground(Color.WHITE);
        jButton.setBounds(580,35,80,30);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectClass = className[jComboBox.getSelectedIndex()];
                String name = jTextField.getText();
                if (StringUtils.isEmptyOrWhitespaceOnly(name)){
                    JOptionPane.showMessageDialog(jFrame,"搜索信息不能为空","提示",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (jcombo.getSelectedIndex() == 0){
                    if (selectClass.equals("全部")){
                        System.out.println(LoginInfo.getInstance().getLoginName() +",您好");
                        presenter.queryGradeByName(tableMode3,name);
                    }else{
                        presenter.queryGradeByNameAndClass(tableMode3,name,selectClass);
                    }
                }else {
                    if (selectClass.equals("全部")){
                        presenter.queryGradeById(tableMode3,name);
                    }else{
                        presenter.queryGradeByIdAndClass(tableMode3,name,selectClass);
                    }
                }
            }
        });

        final JTextField delText = new JTextField();
        delText.setToolTipText("请输入要删除的编号");
        delText.setBounds(50,450,520,30);
        tab3.add(delText);

        JButton delButton = new JButton("删除");
        delButton.setBounds(580,450,80,30);
        delButton.setBackground(Color.WHITE);
        tab3.add(delButton);
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isEmptyOrWhitespaceOnly(delText.getText())){
                    JOptionPane.showMessageDialog(jFrame,"不能为空","提示",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int id = Integer.valueOf(delText.getText());
                boolean next = false;
                for (Grade grade:gradeList){
                    if (grade.getGradeId() == id){
                        next=true;
                        gradeList.remove(grade);
                        break;
                    }
                }
                if (next){
                    presenter.delGrade(delText.getText());
                    if (jcombo.getSelectedIndex() == 0){
                        if (jComboBox.getSelectedIndex() == 0){
                            presenter.queryGradeByName(tableMode3,jTextField.getText());
                        }else {
                            presenter.queryGradeByNameAndClass(tableMode3,jTextField.getText(),className[jComboBox.getSelectedIndex()-1]);
                        }
                    }else {
                        if (jComboBox.getSelectedIndex() == 0){
                            presenter.queryGradeById(tableMode3,jTextField.getText());
                        }else {
                            presenter.queryGradeByIdAndClass(tableMode3,jTextField.getText(),className[jComboBox.getSelectedIndex()-1]);
                        }
                    }

                }else {
                    JOptionPane.showMessageDialog(jFrame,"没有对应的记录","提示",JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        tab3.add(jButton);
        String[] names = { "学号", "姓名", "班级", "课程名称", "课程成绩","编号"};
        tableMode3 = new DefaultTableModel(names,0);
        JTable table = new JTable(tableMode3){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        table.setDefaultRenderer(Object.class,cellRenderer);
        tableMode3.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getColumn() == 4){
                    int i = e.getFirstRow();
                    presenter.updateGrade(gradeList.get(i).getId(),gradeList.get(i).getClassId(),"class_grade",
                            Float.valueOf(tableMode3.getValueAt(i,4).toString()));
                }
            }
        });
        JScrollPane scrollpane=new JScrollPane(table);
        scrollpane.setBounds(50,100,615,330);
        tab3.add(scrollpane);
    }

    private void tab2RightUIInit() {
        String[] newName = className.clone();
        newName[0] = "选择课程";
        final JTextField jTextField = new JTextField();
        jTextField.setBounds(85,35,220,30);
        tab2.add(jTextField);

        final JLabel xuehao = new JLabel("学号");
        xuehao.setBounds(50,35,80,30);
        xuehao.setBackground(Color.WHITE);
        tab2.add(xuehao);


        JLabel score = new JLabel("分数");
        score.setBounds(330,35,40,30);
        score.setBackground(Color.WHITE);
        tab2.add(score);

        final JTextField scoreText = new JTextField();
        scoreText.setBounds(370,35,80,30);
        tab2.add(scoreText);

        final JComboBox jComboBox = new JComboBox<>(newName);
        jComboBox.setBounds(480,35,80,30);
        jComboBox.setBackground(Color.WHITE);
        tab2.add(jComboBox);

        JButton jButton = new JButton("插入");
        jButton.setBackground(Color.WHITE);
        jButton.setBounds(580,35,80,30);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectClass = className[jComboBox.getSelectedIndex()];
                String name = jTextField.getText();
                if (StringUtils.isEmptyOrWhitespaceOnly(name)){
                    JOptionPane.showMessageDialog(jFrame,"搜索信息不能为空","提示",JOptionPane.WARNING_MESSAGE);
                }else if (jComboBox.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(jFrame,"请选择一门课程","提示",JOptionPane.WARNING_MESSAGE);
                } else {
                    presenter.insertGrade(jTextField.getText(),scoreText.getText(),classList.get(jComboBox.getSelectedIndex()-1));
                    Student student = SqlOperationImpl.getInstance().getStudentInfoById(jTextField.getText());
                    if (student!=null){
                        presenter.queryGradeByName(tableMode2,student.getName());
                    }
                }
            }
        });

        tab2.add(jButton);
        String[] names = { "学号", "姓名", "班级", "课程名称" ,"课程成绩"};
        tableMode2 = new DefaultTableModel(names,0);
        JTable table = new JTable(tableMode2){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setDefaultRenderer(Object.class,cellRenderer);
        JScrollPane scrollpane=new JScrollPane(table);
        scrollpane.setBounds(50,100,615,450);
        tab2.add(scrollpane);
    }

    private void initTab(JPanel tab1) {
        tab1.setBounds(100,0,700,600);
        tab1.setLayout(null);
        tab1.setBackground(Color.WHITE);
        jFrame.add(tab1);
        TabController.addPanel(tab1);
    }

    private void tab1RightUIInit() {
        final JTextField jTextField = new JTextField();
        jTextField.setBounds(160,35,300,30);
        tab1.add(jTextField);
        final JComboBox jcombo = new JComboBox();
        jcombo.addItem("姓名");
        jcombo.addItem("学号");
        jcombo.setBounds(50,35,80,30);
        jcombo.setBackground(Color.WHITE);
        tab1.add(jcombo);

        final JComboBox jComboBox = new JComboBox<>(className);
        jComboBox.setBounds(480,35,80,30);
        jComboBox.setBackground(Color.WHITE);
        tab1.add(jComboBox);

        JButton jButton = new JButton("查询");
        jButton.setBackground(Color.WHITE);
        jButton.setBounds(580,35,80,30);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectClass = className[jComboBox.getSelectedIndex()];
                String name = jTextField.getText();
                if (StringUtils.isEmptyOrWhitespaceOnly(name)){
                    JOptionPane.showMessageDialog(jFrame,"搜索信息不能为空","提示",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (jcombo.getSelectedIndex() == 0){
                    if (selectClass.equals("全部")){
                        presenter.queryGradeByName(tableModel,name);
                    }else {
                        presenter.queryGradeByNameAndClass(tableModel,name,selectClass);
                    }
                }else {
                    if (selectClass.equals("全部")){
                        presenter.queryGradeById(tableModel,name);
                    }else {
                        presenter.queryGradeByIdAndClass(tableModel,name,selectClass);
                    }
                }

            }
        });


        tab1.add(jButton);

        String[] names = { "学号", "姓名", "班级", "课程名称", "课程成绩" };
        tableModel = new DefaultTableModel(names,0);
        JTable table = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setDefaultRenderer(Object.class,cellRenderer);
        JScrollPane scrollpane=new JScrollPane(table);
        scrollpane.setBounds(50,100,615,450);
        tab1.add(scrollpane);
    }

    private void LeftUIInit() {
        //左边框
        JPanel left = new JPanel();
        left.setLayout(null);
        left.setBounds(0,0,120,600);
        jFrame.add(left);

        ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\任务监控和查询.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));
        final JLabel search = new JLabel(imageIcon);
        search.setText("查找成绩");
        search.setBounds(23,25,60,60);
        left.add(search);
        addClickListener(search,tab1);

        JLabel jLabel = new JLabel("查询成绩",JLabel.CENTER);
        jLabel.setBounds(0,90,105,20);
        left.add(jLabel);

        ImageIcon imageIcon1 = new ImageIcon("src\\main\\resources\\工单管理.png");
        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));
        final JLabel insert = new JLabel(imageIcon1);
        insert.setBounds(23,175,60,60);
        left.add(insert);

        addClickListener(insert,tab2);
        JLabel jLabe2 = new JLabel("录入成绩",JLabel.CENTER);
        jLabe2.setBounds(23,240,60,20);
        left.add(jLabe2);

        ImageIcon imageIcon2 = new ImageIcon("src\\main\\resources\\检测管理.png");
        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(60,60,Image.SCALE_AREA_AVERAGING));

        final JLabel controller = new JLabel(imageIcon2);
        controller.setBounds(23,325,60,60);
        left.add(controller);
        addClickListener(controller,tab3);

        JLabel jLabe3 = new JLabel("修改成绩",JLabel.CENTER);
        jLabe3.setBounds(23,390,60,20);
        left.add(jLabe3);

        ImageIcon imageIcon3 = new ImageIcon("src\\main\\resources\\任务监控001.png");
        imageIcon3.setImage(imageIcon3.getImage().getScaledInstance(60,60,Image.SCALE_AREA_AVERAGING));

        final JLabel update = new JLabel(imageIcon3);
        update.setText("统计成绩");
        update.setBounds(23,465,60,60);
        left.add(update);

        addClickListener(update,tab4);

        JLabel jLabe4 = new JLabel("统计成绩",JLabel.CENTER);
        jLabe4.setBounds(23,530,60,20);
        left.add(jLabe4);


    }

    private void addClickListener(final JLabel search, final JPanel tab1) {
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //鼠标点击事件
                super.mouseClicked(e);
                TabController.showPanel(tab1);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //鼠标进入事件
                super.mouseEntered(e);
                //鼠标变成手的样式
                search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
    }


    @Override
    public void getGradeByName(DefaultTableModel model,List<Grade> gradeList) {
        model.setRowCount(0);
        this.gradeList = gradeList;
        if (gradeList.size() == 0){
            JOptionPane.showMessageDialog(jFrame,"没有相应的用户记录","提示",JOptionPane.WARNING_MESSAGE);
        }
        for (Grade grade : gradeList){
            Vector<String> vector = new Vector<>();
            vector.add(grade.getId());
            vector.add(grade.getStuName());
            vector.add(grade.getBanji());
            vector.add(grade.getClassName());
            vector.add(String.valueOf(grade.getGrade()));
            vector.add(String.valueOf(grade.getGradeId()));
            model.addRow(vector);
    }
    }

    @Override
    public void getClassName(List<Class> classList) {
        this.classList = classList;
        int size = classList.size();
        className = new String[size+1];
        className[0] = "全部";
        for (int i=0;i<size;i++){
            className[i+1] = classList.get(i).getName();
        }
    }

    @Override
    public void getGradeByNameAndClass(DefaultTableModel model,Grade grade) {
        model.setRowCount(0);
        if (gradeList!=null){
            gradeList.clear();
        }else {
            gradeList = new ArrayList<>();
        }
        if (grade == null){
            JOptionPane.showMessageDialog(jFrame,"没有相应的用户记录","提示",JOptionPane.WARNING_MESSAGE);
            return;
        }
        gradeList.add(grade);
        Vector<String> vector = new Vector<>();
        vector.add(grade.getId());
        vector.add(grade.getStuName());
        vector.add(grade.getBanji());
        vector.add(grade.getClassName());
        vector.add(String.valueOf(grade.getGrade()));
        vector.add(String.valueOf(grade.getGradeId()));
        model.addRow(vector);
    }

    @Override
    public void delCourseResult(Boolean delGrade) {
        JOptionPane.showMessageDialog(jFrame,"删除成功","提示",JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void insertResult(boolean b, String s) {
        if (b){
            JOptionPane.showMessageDialog(jFrame,s,"提示",JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(jFrame,s,"提示",JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void resultOfGrade(boolean b, List<Student> studentList,String[] grades) {
        //String[] titles1 = {"学号","姓名","班级","性别","专业","分数"};
        tableMode4.setRowCount(0);
        if (b){
            int i=0;
            for (Student student:studentList){
                Vector<String> data = new Vector<>();
                data.add(student.getId());
                data.add(student.getName());
                data.add(student.getClassName());
                data.add(student.getSex());
                data.add(student.getMajor());
                data.add(grades[i]);
                tableMode4.addRow(data);
                i++;
            }
        }else {
            JOptionPane.showMessageDialog(jFrame,"还没有该门成绩的信息","提示",JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void resultOfClassInfo(ClassInfo classInfo) {
        tableMode5.setRowCount(0);
        //String[] titles2 = {"课程名称","任课老师","考试人数","及格人数","及格率","最高分","最低分","平均分"};
        if (classInfo != null){
            Vector<String> data = new Vector<>();
            data.add(classInfo.getName());
            data.add(classInfo.getTeacher());
            data.add(String.valueOf(classInfo.getTotalMember()));
            data.add(String.valueOf(classInfo.getPassMember()));
            data.add(String.valueOf(classInfo.getPassRate() * 100) + "%");
            data.add(String.valueOf(classInfo.getMaxGrade()));
            data.add(String.valueOf(classInfo.getMinGrade()));
            data.add(String.valueOf(classInfo.getAverageGrade()));
            tableMode5.addRow(data);
        }else {
            JOptionPane.showMessageDialog(jFrame,"出现未知错误","提示",JOptionPane.WARNING_MESSAGE);
        }
    }
}
