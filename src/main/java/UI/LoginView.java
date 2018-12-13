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
        jFrame.setTitle("管理员登录");
        //jFrame.setLayout(new FlowLayout());
        //JLabel jLabel = new JLabel("管理员登录");
        JLabel jLabel1 = new JLabel("用户名:");
        jLabel1.setFont(new Font("宋体",Font.BOLD, 14));
        jLabel1.setBounds(265,200,60,30);
        JLabel jLabel2 = new JLabel("密码:");
        jLabel2.setFont(new Font("宋体",Font.BOLD, 14));
        jLabel2.setBounds(265,260,60,30);

        final JTextField jTextField = new JTextField(11);
        jTextField.setBounds(370,200,200,30);
        final JPasswordField jPasswordField1 = new JPasswordField();
        jPasswordField1.setBounds(370,260,200,30);
        JButton jButton = new JButton("登录");
        jButton.setBounds(275,320,270,30);
        jFrame.getContentPane().add(jLabel1);
        jFrame.getContentPane().add(jTextField);
        jFrame.getContentPane().add(jLabel2);
        jFrame.getContentPane().add(jPasswordField1);
        jFrame.getContentPane().add(jButton);

        ActionListener loginListenner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("登录")){
//                    String user = jTextField.getText();
//                    String password = new String(jPasswordField1.getPassword());
//                    loginPresenter.login(user,password);password
                    MenuView menuView = new MenuView();
                    startNewView(menuView);
                }
            }
        };
        jButton.addActionListener(loginListenner);
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
