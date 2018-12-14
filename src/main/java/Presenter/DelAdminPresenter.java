package Presenter;

import UI.DelAdminView;
import dao.SqlOperation;
import dao.SqlOperationImpl;

public class DelAdminPresenter {
    private DelAdminView delAdminView;
    SqlOperation operation = SqlOperationImpl.getInstance();
    public DelAdminPresenter(DelAdminView delAdminView){this.delAdminView=delAdminView; }

    public Boolean del(String phone){
        Boolean result;
        result = operation.delAdmi(phone);
        return result;
    }
}
