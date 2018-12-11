package UI;

import dao.SqlOperationImpl;

import javax.swing.*;
import java.awt.*;

public class StartView extends BaseView {

    @Override
    protected void onCreate() {
        super.onCreate();
        jFrame.setUndecorated(true);
        jFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        jFrame.setSize(200,100);
        jFrame.setLocationRelativeTo(null);
        ImageIcon imageIcon = new ImageIcon("D:\\EclipseWorkspaces\\JavaExperience\\src\\main\\resources\\loading.gif");
        JLabel jLabel = new JLabel(imageIcon);
        jLabel.setBounds(0,0,200,80);
        JLabel jLabel2 = new JLabel("正在连接数据库",JLabel.CENTER);
        jLabel2.setBounds(0,70,200,20);
        jFrame.add(jLabel2);
        jFrame.getContentPane().add(jLabel);
        jFrame.setVisible(true);
        SqlOperationImpl.getInstance();
        jLabel2.setText("连接数据库成功");
        jLabel2.updateUI();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        startNewView(new LoginView());
    }

    @Override
    public void show() {
        onCreateView();
        onStart();
        onCreate();
    }
}
