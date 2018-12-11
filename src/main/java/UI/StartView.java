package UI;

import dao.SqlOperationImpl;

import javax.swing.*;
import java.awt.*;

public class StartView extends BaseView {

    @Override
    protected void onCreate() {
        super.onCreate();
        jFrame.setSize(200,100);
        jFrame.setLocationRelativeTo(null);
        JLabel jLabel = new JLabel("正在初始化资源",JLabel.CENTER);
        jLabel.setBounds(0,0,200,100);
        jFrame.getContentPane().add(jLabel);
        jFrame.setVisible(true);
        SqlOperationImpl.getInstance();
        startNewView(new LoginView());
    }

    @Override
    public void show() {
        onCreateView();
        onStart();
        onCreate();
    }
}
