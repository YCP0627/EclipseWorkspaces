package UI;

import Presenter.LoginPresenter;
import Utils.RedisUtil;
import Utils.ValidatorUtil;
import redis.clients.jedis.Jedis;

import javax.swing.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends BaseView implements ILoginView {

    private LoginPresenter loginPresenter;
    private int xOld = 0;
    private int yOld = 0;

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


        JLabel jLabel1 = new JLabel("账号:");
        jLabel1.setFont(new Font("微软雅黑",Font.BOLD, 14));
        jLabel1.setBounds(65,190,60,30);
        JLabel jLabel2 = new JLabel("密码:");
        jLabel2.setFont(new Font("微软雅黑",Font.BOLD, 14));
        jLabel2.setBounds(65,250,60,30);


        final JTextField jTextField = new JTextField(11);
        jTextField.setBounds(120,190,215,30);
        final JPasswordField jPasswordField1 = new JPasswordField();
        jPasswordField1.setBounds(120,250,215,30);
        JButton jButton = new JButton("登录");
        jButton.setBounds(65,310,270,30);
        jButton.setBackground(Color.WHITE);
        jFrame.getContentPane().add(jLabel1);
        jFrame.getContentPane().add(jTextField);
        jFrame.getContentPane().add(jLabel2);
        jFrame.getContentPane().add(jPasswordField1);
        jFrame.getContentPane().add(jButton);

        initJframe();

        jPasswordField1.setFocusable(true);

        jPasswordField1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER){
                    String  user = jTextField.getText();
                    String password = new String(jPasswordField1.getPassword());
                    loginPresenter.login(user,password);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        ActionListener loginListenner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("登录")){
                    @Pattern(regexp ="^1(3|4|5|7|8)\\d{9}$",message = "账号格式错误，请重新输入")
                    @NotEmpty
                    String user;
                    user = jTextField.getText();
                    ValidatorUtil.validate(jFrame,user);
                    String password = new String(jPasswordField1.getPassword());
                    loginPresenter.login(user,password);
//                    MenuView menuView = new MenuView();
//                    startNewView(menuView);
                }
            }
        };
        jButton.addActionListener(loginListenner);
    }

    private void initJframe() {
        Font f = new Font("微软雅黑",Font.BOLD,14);
        jFrame.setUndecorated(true);
        jFrame.getContentPane().setBackground(Color.WHITE);
        jFrame.setSize(400,380);
        jFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        jFrame.setLocationRelativeTo(null);

        JLabel jLabel1 = new JLabel("X");
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setFont(f);
        jLabel1.setBounds(375,10,20,20);
        jFrame.getContentPane().add(jLabel1);

        jLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                onDestory();
            }
        });

        final JLabel jLabel2 = new JLabel("一");
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setFont(f);
        jLabel2.setBounds(345,10,20,20);
        jFrame.getContentPane().add(jLabel2);

        jLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                jFrame.setExtendedState(JFrame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel2.setBackground(Color.RED);
                jLabel2.updateUI();
            }
        });


        ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\1441607770749111.gif");
        JLabel jLabel = new JLabel(imageIcon);
        jLabel.setBounds(0,0,400,200);
        jFrame.getContentPane().add(jLabel);

        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                xOld = e.getX();
                yOld = e.getY();
            }
        });

        jFrame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int yOnScreen = e.getYOnScreen();
                int xOnScreen = e.getXOnScreen();
                int xx = xOnScreen - xOld;
                int yy = yOnScreen - yOld;
                jFrame.setLocation(xx, yy);
            }
        });


    }

    public void loginResult(Boolean success,String s) {
        if (success){
            MenuView menuView = new MenuView();
            startNewView(menuView);
        }else {
            JOptionPane.showMessageDialog(jFrame,s,"提示",JOptionPane.WARNING_MESSAGE);
        }
    }
}
