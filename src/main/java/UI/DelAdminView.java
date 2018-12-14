package UI;

import Presenter.DelAdminPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DelAdminView extends BaseView {
    DelAdminPresenter delAdminPresenter = new DelAdminPresenter(this);

    @Override
    protected void onCreate() {
        super.onCreate();
        jFrame.setBackground(Color.white);
        //jFrame.setLayout(new GridLayout(1,3));
        ImageIcon img  = new ImageIcon("src\\main\\resources\\删除管理员 (1).png");
        img.setImage(img.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH));
        final JLabel jLabelPicture = new JLabel(img,JLabel.HORIZONTAL);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        //jPanel.setBackground(Color.white);
        jPanel.setBounds(0,0,800,200);
        jLabelPicture.setBounds(350,60,100,100);
        jPanel.add(jLabelPicture);
        jFrame.getContentPane().add(jPanel);

        JLabel jLabel = new JLabel("手机号码:");
        jLabel.setFont(new Font("宋体",Font.BOLD, 14));
        //jLabel.setSize(30,30);
        jLabel.setBounds(200,250,100,30);
        final JTextField jTextField = new JTextField();
        //jTextField.setSize(200,30);
        jTextField.setBounds(300,250,200,30);
        JButton jButton = new JButton("删除");
        jButton.setBackground(Color.white);
        jButton.setBounds(510,250,80,30);
        //jButton.setSize(50,30);
        jFrame.getContentPane().add(jLabel);
        jFrame.getContentPane().add(jTextField);
        jFrame.getContentPane().add(jButton);

        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Boolean result;
                String phone = String.valueOf(jTextField.getText());
                result = delAdminPresenter.del(phone);
                if(result!=false)
                {
                    JOptionPane.showMessageDialog(jFrame,"删除成功","提示",JOptionPane.WARNING_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(jFrame,"学号不存在或该学生正在校读书","提示",JOptionPane.WARNING_MESSAGE);
                }

            }
        });
    }
}
