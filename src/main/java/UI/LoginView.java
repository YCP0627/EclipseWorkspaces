package UI;

import Presenter.LoginPresenter;
import Utils.RedisUtil;
import redis.clients.jedis.Jedis;

import javax.swing.*;
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
        RedisUtil redisUtil = new RedisUtil();
        Jedis jedis = redisUtil.redisPoolFactory().getResource();
        jedis.set("hello","123");
        JLabel jLabel = new JLabel();
        jLabel.setText("登陆");
        jLabel.setBounds(200,400,80,40);
        jFrame.add(jLabel);
        JButton jButton = new JButton();
        jButton.setBounds(0,0,40,40);
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuView menuView = new MenuView();
                startNewView(menuView);
            }
        });
        jFrame.add(jButton);
    }

    public void loginResult(Boolean success) {

    }
}
