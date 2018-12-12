package UI;

import Model.Student;
import Presenter.StudentPresent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentView extends BaseView {

    StudentPresent studentPresent = new StudentPresent();

    @Override
    protected void onCreate() {
        super.onCreate();
       //主界面
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

        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String s = String.valueOf(jTextField.getText());
                Student student = studentPresent.search(s);
                if(student!=null){
                    String s1[] = {student.getName(),student.getId(),student.getClassName(),student.getSex(),String.valueOf(student.getAge()) ,
                    student.getIdCard(),student.getMajor()};
                    model.setRowCount(0);
                    model.addRow(s1);
                }
                else{
                    JOptionPane.showMessageDialog(jFrame,"学号不存在或者输入错误","提示",JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        );


        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jPanel1.setVisible(true);


            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                jLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });


    }
}
