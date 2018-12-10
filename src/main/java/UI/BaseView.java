package UI;

import javax.swing.*;

public abstract class BaseView {
    JFrame jFrame;


    protected void onCreateView(){
        jFrame = new JFrame();
        jFrame.setSize(800,600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
    }

    //第一次运行对数据进行初始化
    protected  void onStart(){

    }

    //视图处于后台不可见状态
    protected void onPause(){}
    //完成视图的创建
    protected  void onCreate(){

    }

    protected void onRestart(){
        jFrame.setVisible(true);
    }

    //关闭界面
    protected void onDestory(){
        jFrame.dispose();
    }

    public void show(){
        onCreateView();
        onStart();
        onCreate();
        jFrame.setVisible(true);
    }

    protected void startNewView(BaseView baseView){
        jFrame.setVisible(false);
        baseView.show();
    }
}
