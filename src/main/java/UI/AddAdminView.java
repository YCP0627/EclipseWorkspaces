package UI;

import Model.Adminstrator;
import Presenter.AddAdminPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class AddAdminView extends BaseView {
    AddAdminPresenter addAdminPresenter = new AddAdminPresenter(this);
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
    protected void onCreateView() {
        super.onCreateView();
        returnMain();
//        jFrame.getContentPane().setBackground(Color.white);
//        ImageIcon img  = new ImageIcon("src\\main\\resources\\TIM图片20181214230837.gif");
//        //下面这个代码用不了吗？是的我加进去图片就显示不了了我查下，我没有改变它的大小
//       // img.setImage(img.getImage().getScaledInstance(800,200, Image.SCALE_SMOOTH));
//        final JLabel jLabelPicture = new JLabel(img);
//        jLabelPicture.setBounds(0,0,800,200);
//        //jLabelPicture.setBackground(Color.blue);
//        //jFrame.getContentPane().add(jLabelPicture);
//        JPanel jPanel = new JPanel();
//        jPanel.setLayout(null);
//        jPanel.add(jLabelPicture);
//        jPanel.setBounds(0,0,800,200);
//        jPanel.setBackground(new Color(30,145,243));
//        jFrame.getContentPane().add(jPanel);
//
//        //JLabel jLabelPhone = new J



        jFrame.getContentPane().setBackground(Color.white);
        //jFrame.setLayout(new GridLayout(1,3));
        ImageIcon img  = new ImageIcon("src\\main\\resources\\删除管理员 (2).png");
        img.setImage(img.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
        final JLabel jLabelPicture = new JLabel(img,JLabel.HORIZONTAL);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(Color.white);
        jPanel.setBounds(0,0,800,200);
        jLabelPicture.setBounds(350,30,100,100);
        jPanel.add(jLabelPicture);
        jFrame.getContentPane().add(jPanel);

        JLabel jLabelName = new JLabel("手机号码:");
        jLabelName.setFont(new Font("宋体",Font.BOLD, 14));
        jLabelName.setBounds(200,200,100,30);
        final JTextField jTextFieldName = new JTextField();
        jTextFieldName.setBounds(300,200,250,30);

        JLabel jLabelPhone = new JLabel("    密码:");
        jLabelPhone.setFont(new Font("宋体",Font.BOLD, 14));
        jLabelPhone.setBounds(200,250,100,30);
        final JTextField jTextFieldPhone = new JTextField();
        jTextFieldPhone.setBounds(300,250,250,30);

        JLabel jLabel1 = new JLabel("    姓名:");
        jLabel1.setFont(new Font("宋体",Font.BOLD,14));
        jLabel1.setBounds(200,300,100,30);
        final JTextField jTextField1 = new JTextField();
        jTextField1.setBounds(300,300,250,30);

        JLabel jLabelStyle = new JLabel("    类别:");
        jLabelStyle.setFont(new Font("宋体",Font.BOLD,14));
        jLabelStyle.setBounds(200,350,100,30);
        final JRadioButton jRadioButton1 = new JRadioButton("管理员",true);
        jRadioButton1.setFont(new Font("宋体",Font.BOLD,14));
        jRadioButton1.setBounds(330,350,100,30);
        JRadioButton jRadioButton2 = new JRadioButton("超级管理员");
        jRadioButton2.setBounds(430,350,150,30);
        jRadioButton2.setFont(new Font("宋体",Font.BOLD,14));
        jRadioButton1.setBackground(Color.white);
        jRadioButton2.setBackground(Color.white);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);

        JButton jButton = new JButton("添加");
        jButton.setBackground(Color.white);
        jButton.setBounds(300,400,250,30);
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Adminstrator adminstrator = new Adminstrator();
                if(jRadioButton1.isSelected())
                {
                    adminstrator.setStyle("管理员");
                } else{
                    adminstrator.setStyle("超级管理员");
                }
                adminstrator.setPhone(String.valueOf(jTextFieldName.getText()));
                adminstrator.setPassword(Password.encryption(jTextFieldPhone.getText()));
                adminstrator.setName(String.valueOf(jTextField1.getText()));
                adminstrator.setRegisterDate(new Date());
                adminstrator.setLastDate(new Date());
                Boolean result = addAdminPresenter.add(adminstrator);
                if(result!=false)
                {
                    JOptionPane.showMessageDialog(jFrame,"添加成功","提示",JOptionPane.WARNING_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(jFrame,"输入的信息不能为空，请重新输入","提示",JOptionPane.WARNING_MESSAGE);
                }
            }
        });



        jFrame.getContentPane().add(jLabelName);
        jFrame.getContentPane().add(jTextFieldName);
        jFrame.getContentPane().add(jLabelPhone);
        jFrame.getContentPane().add(jTextFieldPhone);
        jFrame.getContentPane().add(jLabel1);
        jFrame.getContentPane().add(jTextField1);
        jFrame.getContentPane().add(jLabelStyle);
        jFrame.getContentPane().add(jRadioButton1);
        jFrame.getContentPane().add(jRadioButton2);
        jFrame.getContentPane().add(jButton);





    }
}
