package Utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class JFrameController {
    private static int top = -1 ;
    private static List<JFrame> jFrameList = new ArrayList<>();

    public static void addFrame(JFrame jFrame){
        if (!jFrameList.contains(jFrame)){
            jFrameList.add(jFrame);
            top++;
        }
    }

    public static void removeFrame(JFrame jFrame){
        jFrame.dispose();
        jFrameList.remove(jFrame);
        top--;
        jFrameList.get(top).setVisible(true);
    }


}
