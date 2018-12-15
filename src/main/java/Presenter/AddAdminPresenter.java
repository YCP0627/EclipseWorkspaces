package Presenter;

import Model.Adminstrator;
import UI.AddAdminView;
import dao.SqlOperation;
import dao.SqlOperationImpl;

public class AddAdminPresenter {
    SqlOperation operation = SqlOperationImpl.getInstance();
    private AddAdminView addAdminView;
    public AddAdminPresenter(AddAdminView addAdminView){this.addAdminView=addAdminView;}

    public Boolean add(Adminstrator adminstrator){
        Boolean result;
        result = operation.addAdmi(adminstrator);
        return result;
    }
}
