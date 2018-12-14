package UI;

import Model.Class;
import Presenter.ClassPresenter;
import Utils.TabController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClassView extends BaseView {
   TabController tabController = new TabController();
    ClassPresenter classPresent = new ClassPresenter(this);
    StudentView student = new StudentView();//方便调用页面跳转的那个监听事件
    @Override
    protected void onCreate() {
        super.onCreate();
        returnMain();
        //左边导航
        ImageIcon img1 = new ImageIcon("src\\main\\resources\\查找书本.png");
        img1.setImage(img1.getImage().getScaledInstance(60,60, Image.SCALE_SMOOTH));
        final JLabel jLabel = new JLabel("查找课程信息",img1,JLabel.HORIZONTAL);
        jLabel.setHorizontalTextPosition(JLabel.CENTER);
        jLabel.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel.setSize(120,120);
        jLabel.setBackground(Color.black);

        ImageIcon img2 = new ImageIcon("src\\main\\resources\\增加书本.png");
        img2.setImage(img2.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));
        final JLabel jLabel1 = new JLabel("添加课程信息",img2,JLabel.HORIZONTAL);
        jLabel1.setHorizontalTextPosition(JLabel.CENTER);
        jLabel1.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel1.setSize(120,120);

        ImageIcon img3 = new ImageIcon("src\\main\\resources\\删除书本.png");
        img3.setImage(img3.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));
        final JLabel jLabel2 = new JLabel("删除课程信息",img3,JLabel.HORIZONTAL);
        jLabel2.setHorizontalTextPosition(JLabel.CENTER);
        jLabel2.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel2.setSize(120,120);

        ImageIcon img4 = new ImageIcon("src\\main\\resources\\修改书本.png");
        img4.setImage(img4.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH));
        final JLabel jLabel3 = new JLabel("修改课程信息",img4,JLabel.HORIZONTAL);
        jLabel3.setHorizontalTextPosition(JLabel.CENTER);
        jLabel3.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel3.setSize(120,120);

        final JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(4,1));
        jPanel.setSize(120,570);
        jPanel.add(jLabel);
        jPanel.add(jLabel1);
        jPanel.add(jLabel2);
        jPanel.add(jLabel3);
        jFrame.getContentPane().add(jPanel);
        //searchJpanel();
        student.addMouseClick(jLabel,searchJpanel());
        addJpanel();
        student.addMouseClick(jLabel1,addJpanel());
        delJpanel();
        student.addMouseClick(jLabel2,delJpanel());
        modiJpanel();
        student.addMouseClick(jLabel3,modiJpanel());
    }

    private JPanel searchJpanel(){
        JLabel jLabel4 = new JLabel("课程名:");
        jLabel4.setBounds(50,30,60,30);
        final JTextField jTextField = new JTextField();
        JButton jButton = new JButton("查询");
        jTextField.setBounds(130,30,410,30);
        //jTextField.setSize(100,40);
        //jButton.setSize(40,20);
        jButton.setBounds(560,30,80,30);
        jButton.setBackground(Color.white);
        String studentInfo[] = {"课程号","课程名","课时","课程性质","学分","老师"};
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
                String s = String.valueOf(jTextField.getText());
                Class lesson = classPresent.search(s);
                if(lesson!=null)
                {
                    String classPro = null;
                    if(lesson.getObligatory()==true)
                    {
                        classPro = "必修";
                    }
                    else{
                        classPro = "选修";
                    }
                    String s1[] = {lesson.getClassId(),lesson.getName(),String.valueOf(lesson.getClassHour()),
                            classPro,String.valueOf(lesson.getScore()),lesson.getTeacher()};
                    model.setRowCount(0);
                    model.addRow(s1);
                }
                else{
                    JOptionPane.showMessageDialog(jFrame,"该课程不存在请重新输入","提示",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        return jPanel1;
    }

    private JPanel addJpanel(){
        final JPanel jPanelAdd = new JPanel();
        jPanelAdd.setLayout(null);
        jPanelAdd.setBounds(110,0,700,600);
        jPanelAdd.setBackground(Color.white);

        JLabel jLabelAddId = new JLabel("  课程名:");
        jLabelAddId.setFont(new Font("宋体",Font.BOLD, 14));
        final JTextField jTextFieldAddId = new JTextField();
        jLabelAddId.setBounds(150,50,100,30);
        jTextFieldAddId.setBounds(250,50,270,30);

        JLabel jLabelAddName = new JLabel("    课时:");
        jLabelAddName.setFont(new Font("宋体",Font.BOLD, 14));
        final JTextField jTextFieldAddName = new JTextField();
        jLabelAddName.setBounds(150,100,100,30);
        jTextFieldAddName.setBounds(250,100,270,30);

        JLabel jLabelAddSex = new JLabel("课程性质:");
        jLabelAddSex.setFont(new Font("宋体",Font.BOLD, 14));
        //JTextField jTextFieldAddSex = new JTextField();
        jLabelAddSex.setBounds(150,150,100,30);
        //jTextFieldAddSex.setBounds(250,150,270,30);
        final JRadioButton jRadioButtonB = new JRadioButton("必修",true);
        final JRadioButton jRadioButtonG = new JRadioButton("选修");
        jRadioButtonB.setBackground(Color.white);
        jRadioButtonG.setBackground(Color.white);
        jRadioButtonB.setBounds(300,150,100,30);
        jRadioButtonG.setBounds(450,150,100,30);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButtonB);
        buttonGroup.add(jRadioButtonG);


        JLabel jLabelAddAge = new JLabel("    学分:");
        jLabelAddAge.setFont(new Font("宋体",Font.BOLD, 14));
        final JTextField jTextFieldAddAge = new JTextField();
        jLabelAddAge.setBounds(150,200,100,30);
        jTextFieldAddAge.setBounds(250,200,270,30);

        JLabel jLabelAddIdCard = new JLabel("任课老师:");
        jLabelAddIdCard.setFont(new Font("宋体",Font.BOLD, 14));
        final JTextField jTextFieldAddIdCard = new JTextField();
        jLabelAddIdCard.setBounds(150,250,100,30);
        jTextFieldAddIdCard.setBounds(250,250,270,30);

        JButton jButtonAdd = new JButton("添加");
        jButtonAdd.setFont(new Font("宋体",Font.BOLD,14));
        //jButtonAdd.setBackground(Color.blue);
        jButtonAdd.setBounds(250,320,270,30);

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

        jPanelAdd.add(jButtonAdd);
        tabController.addPanel(jPanelAdd);
        jFrame.getContentPane().add(jPanelAdd);
        jPanelAdd.setVisible(false);

        jButtonAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Class lesson = new Class();
                lesson.setName(String.valueOf(jTextFieldAddId.getText()));
                lesson.setClassHour(Integer.valueOf(jTextFieldAddName.getText()));
                Boolean classP;
                if(jRadioButtonB.isSelected()){
                    classP = true;
                } else{
                    classP = false;
                }
                lesson.setObligatory(classP);
                lesson.setScore(Float.valueOf(jTextFieldAddAge.getText()));
                lesson.setTeacher(String.valueOf(jTextFieldAddIdCard.getText()));
                Boolean result = classPresent.add(lesson);
                if(result!=false)
                {
                    JOptionPane.showMessageDialog(jFrame,"添加成功","提示",JOptionPane.WARNING_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(jFrame,"您的信息输入有误，请重新输入","提示",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        return  jPanelAdd;

    }

    private JPanel delJpanel(){
        JLabel jLabelDel = new JLabel("课程号:");
        jLabelDel.setBounds(50,30,60,30);
        final JTextField jTextFieldDel = new JTextField();
        JButton jButtonDel = new JButton("删除");
        jTextFieldDel.setBounds(130,30,410,30);
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

        jButtonDel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String classId = String.valueOf(jTextFieldDel.getText());
                Boolean result = classPresent.del(classId);
                if (result != false) {
                    JOptionPane.showMessageDialog(jFrame, "删除成功", "提示", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(jFrame, "您输入的课程号不存在，请重新输入", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        return jPanelDel;

    }

    private JPanel modiJpanel(){
        final JLabel jLabelMod = new JLabel("课程号");
        jLabelMod.setBounds(50,30,40,30);
        final JTextField jTextFieldMod = new JTextField();
        jTextFieldMod.setBounds(110,30,150,30);
        final JComboBox jComboBoxMod = new JComboBox();
        jComboBoxMod.addItem("属性");
        jComboBoxMod.addItem("课程名");
        jComboBoxMod.addItem("课时");
        jComboBoxMod.addItem("课程性质");
        jComboBoxMod.addItem("学分");
        jComboBoxMod.addItem("任课老师");
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
        jButtonMod.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String id = String.valueOf(jTextFieldMod.getText());
                String key = String.valueOf(jComboBoxMod.getSelectedItem());
                String value =String.valueOf(jTextFieldMod1.getText());
                Boolean result = classPresent.modi(id,key,value);
                if (result != false) {
                    JOptionPane.showMessageDialog(jFrame, "修改成功", "提示", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(jFrame, "您输入的信息有误，请重新输入", "提示", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        return jPanelMod;
    }

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



}
