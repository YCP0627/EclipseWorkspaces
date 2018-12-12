package Utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TabController {
    private static List<JPanel> jPanelList = new ArrayList<JPanel>();

    public static void addPanel(JPanel jPanel){
        if (!jPanelList.contains(jPanel)){
            jPanelList.add(jPanel);
        }else {
            System.out.println("已经存在");
        }
    }

    public static void showPanel(JPanel jPanel){
        for(JPanel jPanel1:jPanelList){
            if (jPanel1 == jPanel){
                jPanel.setVisible(true);
            }else {
                jPanel1.setVisible(false);
            }
        }
    }
}
