package Presenter;

import Model.Adminstrator;
import UI.ILoginView;
import Utils.LoginInfo;
import dao.SqlOperation;
import dao.SqlOperationImpl;

import java.util.Date;

public class LoginPresenter {
    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    public void login(final String user, final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SqlOperation operation = SqlOperationImpl.getInstance();
                Adminstrator adminstrator = operation.getAdmin(user);
                if (adminstrator != null && adminstrator.getPassword().equals(password)){
                    LoginInfo loginInfo = LoginInfo.getInstance();
                    loginInfo.setAdminInRedis(adminstrator);
                    operation.updateAdmin(user,"login_count",adminstrator.getLoginCount()+1);
                    operation.updateAdmin(user,"last_login_date",new Date());
                    loginView.loginResult(true);
                }else {
                    loginView.loginResult(false);
                }
            }
        }).start();
    }
}
