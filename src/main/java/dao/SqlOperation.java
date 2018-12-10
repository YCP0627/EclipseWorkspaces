package dao;

import Model.Achievement;
import Model.Class;
import Model.Student;

import java.sql.SQLException;

public interface SqlOperation {
    void close() throws SQLException;
    Boolean insertStudentInfo(Student student);
    Boolean delStudentInfo(String id);
    Boolean modifyStudentInfo(String id,String key,String value);
    Student getStudentInfoById(String id);
    Student getStudentInfoByIdCard(String idCard);

    Boolean insertAchInfo(Achievement arc);
    Boolean delAch(String id);
    Boolean modifyAch(String id,String key,String value);
    Achievement getAchInfoByStudentName(String name);


    Boolean insertClassInfo(Class c);
    Boolean delClassInfo(String classId);
    Boolean modifyClassInfo(String id,String key,String value);
    Class getClassInfoByName(String name);

}
