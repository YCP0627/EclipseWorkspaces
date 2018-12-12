package Presenter;

import Model.Student;
import UI.StudentView;
import dao.SqlOperation;
import dao.SqlOperationImpl;

public class StudentPresent {
    private StudentView studentView;

    public void setStudentView(StudentView studentView) {
        this.studentView = studentView;
    }

    public Student search(String s){
        SqlOperation operation = SqlOperationImpl.getInstance();
        Student student = operation.getStudentInfoById(s);
        return student;
    }

    public boolean del(String s){
        return true;
    }

    public  boolean mod(String s){
        return true;
    }

    public boolean add(Student student){
        return true;
    }
}
