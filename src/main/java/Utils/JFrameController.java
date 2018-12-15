package Utils;

import dao.SqlOperationImpl;

import javax.swing.*;
import java.sql.SQLException;
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
        if(top == -1){
            try {
                SqlOperationImpl.getInstance().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                System.exit(0);
            }

        }
        jFrameList.get(top).setVisible(true);
    }


}
