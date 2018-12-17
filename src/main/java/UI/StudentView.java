package UI;

import Model.Student;
import Presenter.StudentPresent;
import Utils.TabController;
import Utils.ValidatorUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class StudentView extends BaseView {


    TabController tabController = new TabController();
    StudentPresent studentPresent = new StudentPresent(this);


    public void returnMain()
    {
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                onDestory(); //写这个的话就是退回上一个界面。
                //退出主界面用下面这个
//                try {
//                    SqlOperationImpl.getInstance().close();
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }finally {
//                    System.exit(0);
//                }
            }
        });
    }
    @Override
    protected void onCreate() {
        returnMain();
        super.onCreate();
       //主界面/查询界面
        ImageIcon img1 = new ImageIcon("src\\main\\resources\\查找学生.png");
        img1.setImage(img1.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));
        final JLabel jLabel = new JLabel("查找学生信息",img1,JLabel.HORIZONTAL);
        jLabel.setHorizontalTextPosition(JLabel.CENTER);
        jLabel.setVerticalTextPosition(JLabel.BOTTOM);
        //jLabel.setBounds(50,200,100,100);
        jLabel.setSize(100,120);
        jLabel.setBackground(Color.black);
        //jFrame.getContentPane().add(jLabel);

        ImageIcon img2 = new ImageIcon("src\\main\\resources\\增加学生.png");
        img2.setImage(img2.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));
        final JLabel jLabel1 = new JLabel("添加学生信息",img2,JLabel.HORIZONTAL);
        jLabel1.setHorizontalTextPosition(JLabel.CENTER);
        jLabel1.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel1.setSize(100,120);
        //jLabel1.setBounds(200,200,100,100);
        //Frame.getContentPane().add(jLabel1);

        ImageIcon img3 = new ImageIcon("src\\main\\resources\\删除学生.png");
        img3.setImage(img3.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));
        final JLabel jLabel2 = new JLabel("删除学生信息",img3,JLabel.HORIZONTAL);
        jLabel2.setHorizontalTextPosition(JLabel.CENTER);
        jLabel2.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel2.setSize(100,120);
        //jLabel2.setBounds(350,200,100,100);
        //jFrame.getContentPane().add(jLabel2);

        ImageIcon img4 = new ImageIcon("src\\main\\resources\\修改学生.png");
        img4.setImage(img4.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));
        final JLabel jLabel3 = new JLabel("修改学生信息",img4,JLabel.HORIZONTAL);
        jLabel3.setHorizontalTextPosition(JLabel.CENTER);
        jLabel3.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel3.setSize(100,120);
        //jLabel3.setBounds(500,200,100,100);
        //jFrame.getContentPane().add(jLabel3);

        final JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(4,1));
        jPanel.setSize(100,540);
        jPanel.add(jLabel);
        jPanel.add(jLabel1);
        jPanel.add(jLabel2);
        jPanel.add(jLabel3);

        //jFrame.setLayout(new FlowLayout(FlowLayout.LEFT));
        //jFrame.getContentPane().setBackground(Color.white);
        jFrame.getContentPane().add(jPanel);

        JLabel jLabel4 = new JLabel("学号:");
        jLabel4.setBounds(50,30,40,30);
        final JTextField jTextField = new JTextField();
        JButton jButton = new JButton("查询");
        jTextField.setBounds(110,30,430,30);
        //jTextField.setSize(100,40);
        //jButton.setSize(40,20);
        jButton.setBounds(560,30,80,30);
        jButton.setBackground(Color.white);
        String studentInfo[] = {"姓名","学号","班级","性别","年龄","身份证号","专业"};
        final DefaultTableModel model = new DefaultTableModel(studentInfo,0);
        JTable jTable = new JTable(model);
        //jTable.setRowHeight(50);
        final JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(null);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(50,90,590,450);
        jPanel1.add(jLabel4);
        jPanel1.add(jTextField);
        jPanel1.add(jButton);
        jPanel1.add(jScrollPane);
        jPanel1.setBounds(110,0,700,600);
        jPanel1.setBackground(Color.white);
        jFrame.getContentPane().add(jPanel1);
        tabController.addPanel(jPanel1);

        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String s = jTextField.getText();
                if(s.isEmpty())
                {
                    JOptionPane.showMessageDialog(jFrame,"学号不能为空！","提示",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    Student student = studentPresent.search(s);
                    if(student!=null){
                        String s1[] = {student.getName(),student.getId(),student.getClassName(),student.getSex(),String.valueOf(student.getAge()) ,
                                student.getIdCard(),student.getMajor()};
                        model.setRowCount(0);
                        model.addRow(s1);
                    }
                    else{
                        JOptionPane.showMessageDialog(jFrame,"查无此人！","提示",JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        }
        );


        //增加学生面板
        final JPanel jPanelAdd = new JPanel();
        jPanelAdd.setLayout(null);
        jPanelAdd.setBounds(110,0,700,600);
        jPanelAdd.setBackground(Color.white);

        JLabel jLabelAddId = new JLabel("学    号:");
        jLabelAddId.setFont(new Font("宋体",Font.BOLD, 14));
        final JTextField jTextFieldAddId = new JTextField();
        jLabelAddId.setBounds(150,50,100,30);
        jTextFieldAddId.setBounds(250,50,270,30);

        JLabel jLabelAddName = new JLabel("姓    名:");
        jLabelAddName.setFont(new Font("宋体",Font.BOLD, 14));
        final JTextField jTextFieldAddName = new JTextField();
        jLabelAddName.setBounds(150,100,100,30);
        jTextFieldAddName.setBounds(250,100,270,30);

        JLabel jLabelAddSex = new JLabel("性    别:");
        jLabelAddSex.setFont(new Font("宋体",Font.BOLD, 14));
        //JTextField jTextFieldAddSex = new JTextField();
        jLabelAddSex.setBounds(150,150,100,30);
        //jTextFieldAddSex.setBounds(250,150,270,30);
        final JRadioButton jRadioButtonB = new JRadioButton("男",true);
        final JRadioButton jRadioButtonG = new JRadioButton("女");
        jRadioButtonB.setBackground(Color.white);
        jRadioButtonG.setBackground(Color.white);
        jRadioButtonB.setBounds(300,150,100,30);
        jRadioButtonG.setBounds(450,150,100,30);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButtonB);
        buttonGroup.add(jRadioButtonG);


        JLabel jLabelAddAge = new JLabel("年    龄:");
        jLabelAddAge.setFont(new Font("宋体",Font.BOLD, 14));
        final JTextField jTextFieldAddAge = new JTextField();
        jLabelAddAge.setBounds(150,200,100,30);
        jTextFieldAddAge.setBounds(250,200,270,30);

        JLabel jLabelAddIdCard = new JLabel("身份证号:");
        jLabelAddIdCard.setFont(new Font("宋体",Font.BOLD, 14));
        final JTextField jTextFieldAddIdCard = new JTextField();
        jLabelAddIdCard.setBounds(150,250,100,30);
        jTextFieldAddIdCard.setBounds(250,250,270,30);

        JLabel jLabelAddMajor = new JLabel("专    业:");
        jLabelAddMajor.setFont(new Font("宋体",Font.BOLD, 14));
        final JTextField jTextFieldAddMajor = new JTextField();
        jLabelAddMajor.setBounds(150,300,100,30);
        jTextFieldAddMajor.setBounds(250,300,270,30);

        JLabel jLabelAddClassN = new JLabel("班    级:");
        jLabelAddClassN.setFont(new Font("宋体",Font.BOLD, 14));
        final JTextField jTextFieldAddClassN = new JTextField();
        jLabelAddClassN.setBounds(150,350,100,30);
        jTextFieldAddClassN.setBounds(250,350,270,30);

        JButton jButtonAdd = new JButton("添加");
        jButtonAdd.setFont(new Font("宋体",Font.BOLD,14));
        //jButtonAdd.setBackground(Color.blue);
        jButtonAdd.setBounds(250,400,270,30);

        jPanelAdd.add(jLabelAddId);
        jPanelAdd.add(jTextFieldAddId);
        jPanelAdd.add(jLabelAddName);
        jPanelAdd.add(jTextFieldAddName);
        jPanelAdd.add(jLabelAddSex);
        jPanelAdd.add(jRadioButtonB);
        jPanelAdd.add(jRadioButtonG);
        jPanelAdd.add(jLabelAddAge);
        jPanelAdd.add(jTextFieldAddAge);
        jPanelAdd.add(jLabelAddIdCard);
        jPanelAdd.add(jTextFieldAddIdCard);
        jPanelAdd.add(jLabelAddMajor);
        jPanelAdd.add(jTextFieldAddMajor);
        jPanelAdd.add(jLabelAddClassN);
        jPanelAdd.add(jTextFieldAddClassN);
        jPanelAdd.add(jButtonAdd);
        tabController.addPanel(jPanelAdd);
        jFrame.getContentPane().add(jPanelAdd);
        jPanelAdd.setVisible(false);


        //删除学生面板
        JLabel jLabelDel = new JLabel("学号:");
        jLabelDel.setBounds(50,30,40,30);
        final JTextField jTextFieldDel = new JTextField();
        JButton jButtonDel = new JButton("删除");
        jTextFieldDel.setBounds(110,30,430,30);
        jButtonDel.setBounds(560,30,80,30);
        jButtonDel.setBackground(Color.white);
        final JPanel jPanelDel = new JPanel();
        jPanelDel.setLayout(null);
        jPanelDel.add(jLabelDel);
        jPanelDel.add(jTextFieldDel);
        jPanelDel.add(jButtonDel);
        jPanelDel.setBounds(110,0,700,600);
        jPanelDel.setBackground(Color.white);
        tabController.addPanel(jPanelDel);
        jFrame.getContentPane().add(jPanelDel);
        jPanelDel.setVisible(false);


        //修改学生面板
        JLabel jLabelMod = new JLabel("学号");
        jLabelMod.setBounds(50,30,40,30);
        final JTextField jTextFieldMod = new JTextField();
        jTextFieldMod.setBounds(110,30,150,30);
        final JComboBox jComboBoxMod = new JComboBox();
        jComboBoxMod.addItem("属性");
        jComboBoxMod.addItem("姓名");
        jComboBoxMod.addItem("性别");
        jComboBoxMod.addItem("年龄");
        jComboBoxMod.addItem("身份证号");
        jComboBoxMod.addItem("专业");
        jComboBoxMod.addItem("班级");
        jComboBoxMod.setBounds(270,30,80,30);
        final JTextField jTextFieldMod1 = new JTextField();
        jTextFieldMod1.setBounds(360,30,210,30);
        JButton jButtonMod = new JButton("修改");
        jButtonMod.setBounds(580,30,80,30);
        JPanel jPanelMod = new JPanel();
        jPanelMod.setLayout(null);
        jPanelMod.add(jLabelMod);
        jPanelMod.add(jTextFieldMod);
        jPanelMod.add(jComboBoxMod);
        jPanelMod.add(jTextFieldMod1);
        jPanelMod.add(jButtonMod);
        jPanelMod.setBounds(110,0,700,600);
        jPanelMod.setBackground(Color.white);
        tabController.addPanel(jPanelMod);
        jFrame.getContentPane().add(jPanelMod);
        jPanelMod.setVisible(false);







        //增加监听事件
        //1.为查询学生信息增加监听事件
        addMouseClick(jLabel,jPanel1);


        //2.为增加学生信息增加监听事件
        addMouseClick(jLabel1,jPanelAdd);
        jButtonAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Boolean result;
                Student student = new Student();
                if (jTextFieldAddAge.getText().isEmpty()){
                    JOptionPane.showMessageDialog(jFrame,"年龄不能为空","提示",JOptionPane.WARNING_MESSAGE);
                }
                student.setAge(Integer.valueOf(jTextFieldAddAge.getText()));
                student.setId(jTextFieldAddId.getText());
                student.setName(jTextFieldAddName.getText());
                if(jRadioButtonB.isSelected()){
                    student.setSex(jRadioButtonB.getText());
                }
                else if(jRadioButtonG.isSelected()){
                    student.setSex(jRadioButtonG.getText());
                }

                student.setIdCard(jTextFieldAddIdCard.getText());
                student.setMajor(jTextFieldAddMajor.getText());
                student.setClassName(jTextFieldAddClassN.getText());
                ValidatorUtil.validate(jFrame,student);
                result = studentPresent.add(student);
                if(result!=false)
                {
                    JOptionPane.showMessageDialog(jFrame,"添加成功","提示",JOptionPane.WARNING_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(jFrame,"该学号已存在，请重新输入","提示",JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        //3.为删除学生信息增加监听事件
        addMouseClick(jLabel2,jPanelDel);
        jButtonDel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String s = String.valueOf(jTextFieldDel.getText());
                if(s.isEmpty()){
                    JOptionPane.showMessageDialog(jFrame,"学号不能为空","提示",JOptionPane.WARNING_MESSAGE);
                }
                else {
                    Boolean result = studentPresent.del(s);
                    if(result!=false)
                    {
                        JOptionPane.showMessageDialog(jFrame,"删除成功","提示",JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(jFrame,"该学生不存在","提示",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        //4.为修改学生信息增加监听事件
        addMouseClick(jLabel3,jPanelMod);
        jButtonMod.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String studentId = jTextFieldMod.getText();
                String studentPro= String.valueOf(jComboBoxMod.getSelectedItem());
                String studentValue = jTextFieldMod1.getText();
                if(studentId.isEmpty()||studentValue.isEmpty()){
                    JOptionPane.showMessageDialog(jFrame,"输入的信息不能为空！","提示",JOptionPane.WARNING_MESSAGE);
                }
                else{
                    Boolean result = null;
                    try {
                        result = studentPresent.mod(studentId,studentPro,studentValue);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    if(result!=false)
                    {
                        JOptionPane.showMessageDialog(jFrame,"修改成功","提示",JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(jFrame,"该学生不存在！","提示",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });




    }

    public void addMouseClick(final JLabel jLabel, final JPanel jPanel){
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tabController.showPanel(jPanel);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                jLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
    }
}
