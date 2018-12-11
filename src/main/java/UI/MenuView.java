package UI;

import javax.swing.*;
import java.awt.*;

public class MenuView extends BaseView {
    @Override
    protected void onCreate() {
        super.onCreate();
        ImageIcon img1 = new ImageIcon("D:\\EclipseWorkspaces\\JavaExperience\\target\\classes\\学生1.png");
        img1.setImage(img1.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
        JLabel jLabel = new JLabel(img1);
        jLabel.setBounds(200,100,100,100);
        jFrame.getContentPane().add(jLabel);
    }
}
