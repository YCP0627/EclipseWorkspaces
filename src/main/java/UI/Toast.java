package UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Toast {

    private int xOld = 0;
    private int yOld = 0;

    JDialog jDialog;

    public Toast(JFrame jFrame) {
        this.jDialog = new JDialog(jFrame);
        jDialog.setSize(180,140);
        jDialog.setUndecorated(true);
        jDialog.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - jDialog.getSize().width)/2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - jDialog.getSize().height)/2;
        jDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        jDialog.setLocation(x, y);
        jDialog.setLayout(null);
        jDialog.getContentPane().setBackground(Color.WHITE);


        JPanel jPanel = new JPanel();
        jPanel.setBounds(0,0,180,30);
        jPanel.setBackground(new Color(70,83,231));
        jDialog.getContentPane().add(jPanel);




        jDialog.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                xOld = e.getX();
                yOld = e.getY();
            }
        });
        jDialog.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xx = xOnScreen - xOld;
                int yy = yOnScreen - yOld;
                jDialog.setLocation(xx, yy);
            }
        });
    }

    public void show(){

        jDialog.setVisible(true);

    }
}
