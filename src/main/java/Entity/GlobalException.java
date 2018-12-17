package Entity;

import javax.swing.*;

public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;
    private JFrame jFrame;


    public GlobalException(JFrame jFrame,String message) {
        super();
        this.message = message;
        this.jFrame = jFrame;
        JOptionPane.showMessageDialog(jFrame,message,"提示",JOptionPane.WARNING_MESSAGE);
    }

    public String getMessage() {
        return message;
    }
}
