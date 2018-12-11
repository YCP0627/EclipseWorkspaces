package Presenter;

import Model.Adminstrator;
import UI.ILoginView;
import dao.SqlOperation;
import dao.SqlOperationImpl;

import java.util.Date;

public class LoginPresenter {
    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    public void login(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SqlOperation operation = SqlOperationImpl.getInstance();
                Adminstrator adminstrator = operation.getAdmin("13169166450");
                if (adminstrator != null){
                    operation.updateAdmin("13169166451","login_count",adminstrator.getLoginCount()+1);
                    operation.updateAdmin("13169166451","last_login_date",new Date());
                    loginView.loginResult(true);
                }else {
                    loginView.loginResult(false);
                }
            }
        }).start();
    }
}
