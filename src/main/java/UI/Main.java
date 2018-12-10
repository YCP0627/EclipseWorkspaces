package UI;

import Model.Class;
import Model.Grade;
import Model.Student;
import dao.SqlOperation;
import dao.SqlOperationImpl;

public class Main {
    public static void main(String[] args) {
       LoginView loginView = new LoginView();
       loginView.show();

    }
}
