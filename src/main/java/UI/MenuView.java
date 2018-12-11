package UI;

import javax.swing.*;
import java.awt.*;

public class MenuView extends BaseView {
    @Override
    protected void onCreate() {
        super.onCreate();
        jFrame.setLayout(new GridLayout(2,3));
        ImageIcon imageIcon = new ImageIcon("nb");
    }
}
