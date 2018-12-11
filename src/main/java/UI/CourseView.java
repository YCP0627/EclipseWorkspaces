package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseView extends BaseView {

    @Override
    protected void onCreate() {
        super.onCreate();
        jFrame.setTitle("学生成绩管理");

        //左边框
        JPanel left = new JPanel();
        left.setBackground(Color.red);
        left.setLayout(null);
        left.setBounds(0,0,200,600);
        jFrame.add(left);

        JButton search = new JButton();
        search.setText("查找成绩");
        search.setBounds(50,50,100,100);
        left.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton insert = new JButton();
        insert.setText("录入成绩");
        insert.setBounds(50,230,100,100);
        left.add(insert);

        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton controller = new JButton();
        controller.setText("统计成绩");
        controller.setBounds(50,420,100,100);
        left.add(controller);

        controller.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });




    }
}
