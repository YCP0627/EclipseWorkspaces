package UI;

import Model.Class;
import Model.Grade;
import Model.Student;
import dao.SqlOperation;
import dao.SqlOperationImpl;

import java.util.List;

/**说明
 * 第一步：新建一个界面继承于BaseView,Alt+insert快捷键重写onCreate方法
 * 第二步：新建一个于界面对应的Presenter，比如LoginPresenter，用于处理业务逻辑，在Presenter里面引入对应界面的接口
 * 第三部：在界面的java文件上只写界面，引入相应的Presenter并初始化，当需要使用业务逻辑的时候，调用presenter的方法，比如
 * 登陆功能，按钮点击的时候调用presenter的login方法
 * 第四部：在presenter的login方法进行登陆，结果通过引用的界面接口，返回给界面的java
 * 第五步：在界面java里面进行结果展示
 * 总结：Presnter处理业务，UI处理界面
 */

public class Main {
    public static void main(String[] args) {
       StartView startView = new StartView();
       startView.show();
    }
}
