package Presenter;

import Model.Student;
import UI.StudentView;
import dao.SqlOperation;
import dao.SqlOperationImpl;

public class StudentPresent {
    private StudentView studentView;

    public  StudentPresent(StudentView studentView) {
        this.studentView = studentView;
    }

    public Student search(String s){
        SqlOperation operation = SqlOperationImpl.getInstance();
        Student student = operation.getStudentInfoById(s);
        return student;
    }

    public boolean del(String s){
        SqlOperation operation = SqlOperationImpl.getInstance();
        Boolean result = operation.delStudentInfo(s);
        return result;
    }

    public  boolean mod(String s1,String s2,String value){
        Boolean result;
        SqlOperation operation = SqlOperationImpl.getInstance();
        switch (s2){
            case "姓名":
                result = operation.modifyStudentInfo1(s1,"name",value);
                break;
            case "性别":
                result = operation.modifyStudentInfo1(s1,"sex",value);
                break;
            case "年龄":
                result = operation.modifyStudentInfo1(s1,"age",value);
                break;
            case "专业":
                result = operation.modifyStudentInfo1(s1,"major",value);
                break;
            case "身份证号":
                result = operation.modifyStudentInfo1(s1,"idCard",value);
                break;
            case "班级":
                result = operation.modifyStudentInfo1(s1,"className",value);
                break;
             default:
                 result=false;
                 break;
        }
        return result;
    }

    public boolean add(Student student){
        Boolean result;
        SqlOperation operation = SqlOperationImpl.getInstance();
        result = operation.insertStudentInfo(student);
        return result;
    }
}
