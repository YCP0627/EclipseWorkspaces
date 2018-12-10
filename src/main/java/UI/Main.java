package UI;

import Model.Student;
import dao.SqlOperation;
import dao.SqlOperationImpl;

public class Main {
    public static void main(String[] args) {
        SqlOperation operation = SqlOperationImpl.getInstance();
        Student student = operation.getStudentInfoById("B20160304301");
        System.out.println(student.toString());
    }
}
