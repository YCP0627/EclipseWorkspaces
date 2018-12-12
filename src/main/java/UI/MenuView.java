package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuView extends BaseView {
    @Override
    protected void onCreate() {
        super.onCreate();
        jFrame.setSize(800,500);
        jFrame.getContentPane().setBackground(Color.white);

        JLabel jLabel6 = new JLabel("功能宝箱");
        jLabel6.setBounds(350,50,300,40);
        jLabel6.setFont(new Font("宋体",Font.BOLD, 20));
        jFrame.getContentPane().add(jLabel6);


        JLabel jLabel7 = new JLabel("更多功能，带给您更贴心的理念");
        jLabel7.setBounds(330,90,300,20);
        jLabel7.setFont(new Font("宋体",Font.PLAIN, 10));
        jFrame.getContentPane().add(jLabel7);

        ImageIcon img1 = new ImageIcon("D:\\EclipseWorkspaces\\JavaExperience\\target\\classes\\icon_学生.png");
        img1.setImage(img1.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        final JLabel jLabel = new JLabel("学生信息管理",img1,JLabel.HORIZONTAL);
        jLabel.setHorizontalTextPosition(JLabel.CENTER);
        jLabel.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel.setBounds(50,200,100,100);
        jFrame.getContentPane().add(jLabel);

        ImageIcon img2 = new ImageIcon("D:\\EclipseWorkspaces\\JavaExperience\\target\\classes\\icon_课程.png");
        img2.setImage(img2.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        final JLabel jLabel1 = new JLabel("课程信息管理",img2,JLabel.HORIZONTAL);
        jLabel1.setHorizontalTextPosition(JLabel.CENTER);
        jLabel1.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel1.setBounds(200,200,100,100);
        jFrame.getContentPane().add(jLabel1);

        ImageIcon img3 = new ImageIcon("D:\\EclipseWorkspaces\\JavaExperience\\target\\classes\\icon_成绩.png");
        img3.setImage(img3.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        final JLabel jLabel2 = new JLabel("成绩信息管理",img3,JLabel.HORIZONTAL);
        jLabel2.setHorizontalTextPosition(JLabel.CENTER);
        jLabel2.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel2.setBounds(350,200,100,100);
        jFrame.getContentPane().add(jLabel2);

        ImageIcon img4 = new ImageIcon("D:\\EclipseWorkspaces\\JavaExperience\\target\\classes\\icon_管理员1.png");
        img4.setImage(img4.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        final JLabel jLabel3 = new JLabel("添加管理员",img4,JLabel.HORIZONTAL);
        jLabel3.setHorizontalTextPosition(JLabel.CENTER);
        jLabel3.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel3.setBounds(500,200,100,100);
        jFrame.getContentPane().add(jLabel3);

        ImageIcon img5 = new ImageIcon("D:\\EclipseWorkspaces\\JavaExperience\\target\\classes\\icon_管理员2.png");
        img5.setImage(img5.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        final JLabel jLabel4 = new JLabel("删除管理员",img5,JLabel.HORIZONTAL);
        jLabel4.setHorizontalTextPosition(JLabel.CENTER);
        jLabel4.setVerticalTextPosition(JLabel.BOTTOM);
        jLabel4.setBounds(650,200,100,100);
        jFrame.getContentPane().add(jLabel4);


        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                jLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        jLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                jLabel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        jLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                jLabel2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        jLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                jLabel3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });

        jLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                jLabel4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
    }
}
