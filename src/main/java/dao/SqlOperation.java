package dao;

import Model.Adminstrator;
import Model.Grade;
import Model.Class;
import Model.Student;

import java.sql.SQLException;
import java.util.List;

public interface SqlOperation {
    void close() throws SQLException;
    Boolean insertStudentInfo(Student student);
    Boolean delStudentInfo(String id);
    Boolean modifyStudentInfo(String id,String key,Object value);
    Boolean modifyStudentInfo1(String id,String key,String value);
    Student getStudentInfoById(String id);
    Student getStudentInfoByIdCard(String idCard);

    Boolean insertGrade(Grade arc);
    Boolean delGrade(String id);
    Boolean modifyGrade(String id,String class_id,String key,float value);
    List<Grade> getGradeByStudentName(String name);
    Grade getGrade(String name,String class_name);
    List<Grade> getGradeByStudentId(String id);


    Boolean insertClassInfo(Class c);
    Boolean delClassInfo(String classId);
    Boolean modifyClassInfo(String id,String key,String  value);
    Class getClassInfoById(String name);

    void modifyGradeName(String id,String studentName) throws SQLException;

    Adminstrator getAdmin(String phone);
    Boolean updateAdmin(String phone,String key, Object value);
    Boolean delAdmi(String phone);
    Boolean addAdmi(Adminstrator adminstrator);


    List<Class> getAllClass();

    Grade getGradeByClassAndId(String id, String selectClass);
    List<Grade> getMaxGrade(String s);

    List<Grade> getAllGrade(String s);
}
