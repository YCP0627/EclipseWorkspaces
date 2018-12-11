package UI;

import javax.swing.*;
import java.awt.*;

public class MenuView extends BaseView {
    @Override
    protected void onCreate() {
        super.onCreate();
        ImageIcon img1 = new ImageIcon("D:\\EclipseWorkspaces\\JavaExperience\\src\\main\\resources\\删除用户.png");
        JLabel jLabel = new JLabel(img1);
        jLabel.setBounds(200,100,100,100);
        jFrame.getContentPane().add(jLabel);
    }
}
