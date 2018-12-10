package dao;

import Model.Grade;
import Model.Class;
import Model.Student;

import java.sql.SQLException;

public interface SqlOperation {
    void close() throws SQLException;
    Boolean insertStudentInfo(Student student);
    Boolean delStudentInfo(String id);
    Boolean modifyStudentInfo(String id,String key,Object value);
    Student getStudentInfoById(String id);
    Student getStudentInfoByIdCard(String idCard);

    Boolean insertGrade(Grade arc);
    Boolean delGrade(String id,String class_id);
    Boolean modifyGrade(String id,String class_id,String key,float value);
    Grade getGradeByStudentName(String name);
    Grade getGrade(String name,String class_name);


    Boolean insertClassInfo(Class c);
    Boolean delClassInfo(String classId);
    Boolean modifyClassInfo(String id,String key,Object value);
    Class getClassInfoById(String class_id);

}
