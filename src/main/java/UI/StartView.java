package UI;

import Utils.LoginInfo;
import dao.SqlOperationImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class StartView extends BaseView {

    private int xOld = 0;
    private int yOld = 0;

    @Override
    protected void onCreate() {
        super.onCreate();
        jFrame.setUndecorated(true);
        jFrame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        jFrame.getContentPane().setBackground(new Color(70,127,231));
        Font f = new Font("微软雅黑",Font.BOLD,14);
        jFrame.setSize(400,260);
        jFrame.setLocationRelativeTo(null);
        ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\cool-loading-animated-gif-11.gif");
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
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xx = xOnScreen - xOld;
                int yy = yOnScreen - yOld;
                jFrame.setLocation(xx, yy);
            }
        });

        JLabel jLabelClose = new JLabel("X");
        jLabelClose.setFont(f);
        jLabelClose.setForeground(Color.WHITE);
        jLabelClose.setBounds(375,10,20,20);
        jLabelClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
                super.mouseClicked(e);
            }
        });
        jFrame.add(jLabelClose);

        JLabel jLabel = new JLabel(imageIcon);
        jLabel.setBounds(0,-30,400,260);
        JLabel jLabel2 = new JLabel("正在连接数据库",JLabel.CENTER);
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setFont(f);
        jLabel2.setBounds(0,200,400,20);
        jFrame.add(jLabel2);
        jFrame.getContentPane().add(jLabel);
        jFrame.setVisible(true);
        SqlOperationImpl.getInstance();
        jLabel2.setText("正在连接Redis");
        jLabel2.updateUI();
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        LoginInfo.getInstance();
        jLabel2.setText("资源初始化成功");
        jLabel2.updateUI();
        startNewView(new LoginView());
    }

    @Override
    public void show() {
        onCreateView();
        onStart();
        onCreate();
    }
}
