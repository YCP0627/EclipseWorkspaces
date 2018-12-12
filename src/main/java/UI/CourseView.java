package UI;

import Model.Class;
import Model.Grade;
import Presenter.CoursePresenter;
import Utils.TabController;
import com.mysql.cj.util.StringUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

public class CourseView extends BaseView implements ICourseView {

    private CoursePresenter presenter;
    private DefaultTableModel tableModel;
    private String[] className;
    private JPanel tab1 = new JPanel();
    private JPanel tab2 = new JPanel();
    private JPanel tab3 = new JPanel();
    private JPanel tab4 = new JPanel();


    @Override
    protected void onCreate() {
        super.onCreate();
        jFrame.setTitle("学生成绩管理");
        jFrame.getContentPane().setBackground(Color.WHITE);
        presenter = new CoursePresenter(this);
        presenter.queryClassById();
        initTab(tab1);
        initTab(tab2);
        initTab(tab3);
        initTab(tab4);
        LeftUIInit();
        tab1RightUIInit();
        tab2RightUIInit();
    }

    private void tab2RightUIInit() {
        String[] tab2ClassNames = className.clone();
        tab2ClassNames[0] = "课程";
        final JTextField jTextField = new JTextField();
        jTextField.setBounds(160,35,300,30);
        tab2.add(jTextField);
        JComboBox jcombo = new JComboBox();
        jcombo.addItem("学号");
        jcombo.setBounds(50,35,80,30);
        jcombo.setBackground(Color.WHITE);
        tab2.add(jcombo);

        final JComboBox jComboBox = new JComboBox(tab2ClassNames);
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
                }
            }
        });

        tab2.add(jButton);

        String[] names = { "学号", "姓名", "班级", "课程成绩" };
        tableModel = new DefaultTableModel(names,1);
        JTable table = new JTable(tableModel);
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
        JComboBox jcombo = new JComboBox();
        jcombo.addItem("姓名");
        jcombo.addItem("学号");
        jcombo.setBounds(50,35,80,30);
        jcombo.setBackground(Color.WHITE);
        tab1.add(jcombo);

        final JComboBox jComboBox = new JComboBox(className);
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
                }
                if (selectClass.equals("全部")){
                    presenter.queryGradeByName(name);
                }else {
                    presenter.queryGradeByNameAndClass(name,selectClass);
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
        search.setBounds(30,25,60,60);
        left.add(search);
        addClickListener(search,tab1);

        JLabel jLabel = new JLabel("查询成绩",JLabel.CENTER);
        jLabel.setBounds(0,90,120,20);
        left.add(jLabel);

        ImageIcon imageIcon1 = new ImageIcon("src\\main\\resources\\工单管理.png");
        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));
        final JLabel insert = new JLabel(imageIcon1);
        insert.setBounds(30,175,60,60);
        left.add(insert);

        addClickListener(insert,tab2);
        JLabel jLabe2 = new JLabel("录入成绩",JLabel.CENTER);
        jLabe2.setBounds(30,240,60,20);
        left.add(jLabe2);

        ImageIcon imageIcon2 = new ImageIcon("src\\main\\resources\\检测管理.png");
        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(60,60,Image.SCALE_AREA_AVERAGING));

        final JLabel controller = new JLabel(imageIcon2);
        controller.setBounds(30,325,60,60);
        left.add(controller);
        addClickListener(controller,tab3);

        JLabel jLabe3 = new JLabel("修改成绩",JLabel.CENTER);
        jLabe3.setBounds(30,390,60,20);
        left.add(jLabe3);

        ImageIcon imageIcon3 = new ImageIcon("src\\main\\resources\\任务监控001.png");
        imageIcon3.setImage(imageIcon3.getImage().getScaledInstance(60,60,Image.SCALE_AREA_AVERAGING));

        final JLabel update = new JLabel(imageIcon3);
        update.setText("统计成绩");
        update.setBounds(30,465,60,60);
        left.add(update);

        addClickListener(update,tab4);

        JLabel jLabe4 = new JLabel("统计成绩",JLabel.CENTER);
        jLabe4.setBounds(30,530,60,20);
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
    public void getGradeByName(List<Grade> gradeList) {
        tableModel.setRowCount(0);
        if (gradeList.size() == 0){
            JOptionPane.showMessageDialog(jFrame,"没有相应的用户记录","提示",JOptionPane.WARNING_MESSAGE);
        }
        for (Grade grade : gradeList){
            Vector<String> vector = new Vector<String>();
            vector.add(grade.getId());
            vector.add(grade.getStuName());
            vector.add(grade.getClassId());
            vector.add(grade.getClassName());
            vector.add(String.valueOf(grade.getGrade()));
            tableModel.addRow(vector);
        }
    }

    @Override
    public void getClassName(List<Class> classList) {
        int size = classList.size();
        className = new String[size+1];
        className[0] = "全部";
        for (int i=0;i<size;i++){
            className[i+1] = classList.get(i).getName();
        }
    }

    @Override
    public void getGradeByNameAndClass(Grade grade) {
        tableModel.setRowCount(0);
        if (grade == null){
            JOptionPane.showMessageDialog(jFrame,"没有相应的用户记录","提示",JOptionPane.WARNING_MESSAGE);
        }
        Vector<String> vector = new Vector<String>();
        vector.add(grade.getId());
        vector.add(grade.getStuName());
        vector.add(grade.getClassId());
        vector.add(grade.getClassName());
        vector.add(String.valueOf(grade.getGrade()));
        tableModel.addRow(vector);
    }
}
