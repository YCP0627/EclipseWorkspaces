package UI;

import Presenter.LoginPresenter;
import Utils.RedisUtil;
import redis.clients.jedis.Jedis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends BaseView implements ILoginView {

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate() {
        super.onCreate();
        loginPresenter = new LoginPresenter(this);
        initUI();
    }

    private void initUI() {
        JLabel jLabel = new JLabel();
        jLabel.setText("登陆");
        jLabel.setBounds(200,400,80,40);
        jFrame.add(jLabel);
        JButton jButton = new JButton();
        jButton.setBounds(0,0,40,40);
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginPresenter.login();
            }
        });
        jFrame.add(jButton);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(50,50,500,60);
        jFrame.getContentPane().add(jTextField);
    }

    public void loginResult(Boolean success) {
        if (success){
            MenuView menuView = new MenuView();
            startNewView(menuView);
        }else {
            JOptionPane.showMessageDialog(jFrame,"账号不存在或者密码错误","提示",JOptionPane.WARNING_MESSAGE);
        }
    }
}
