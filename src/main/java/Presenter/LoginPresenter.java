package Presenter;

import Model.Adminstrator;
import UI.ILoginView;
import Utils.LoginInfo;
import com.mysql.cj.util.StringUtils;
import dao.SqlOperation;
import dao.SqlOperationImpl;

import java.util.Date;

public class LoginPresenter {
    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    public void login(final String user, final String password){
        if (StringUtils.isEmptyOrWhitespaceOnly(user)||StringUtils.isEmptyOrWhitespaceOnly(password)){
            loginView.loginResult(false,"账号密码不能为空");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                SqlOperation operation = SqlOperationImpl.getInstance();
                Adminstrator adminstrator = operation.getAdmin(user);
                if (adminstrator==null){
                    loginView.loginResult(false,"不存在的账号，请联系管理员。");
                    return;
                }
                if (adminstrator.getPassword().equals(password)){
                    LoginInfo loginInfo = LoginInfo.getInstance();
                    //下面这一行是往reids数据库里面加数据
                    loginInfo.setAdminInRedis(adminstrator);
                    operation.updateAdmin(user,"login_count",adminstrator.getLoginCount()+1);
                    operation.updateAdmin(user,"last_login_date",new Date());
                    loginView.loginResult(true,"成功");
                }else {
                    loginView.loginResult(false,"登陆密码错误");
                }
            }
        }).start();
    }
}
