package dao;

import Entity.AppConfig;
import Model.Achievement;
import Model.Class;
import Model.Student;

import java.sql.*;

public class SqlOperationImpl implements SqlOperation {

    private static volatile SqlOperation sqlOperation;
    private Connection connection;
    private Statement statement;

    private SqlOperationImpl(){
        try {
            java.lang.Class.forName(AppConfig.MYSQL_DRIVER.getValue());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection(AppConfig.MYSQL_URL.getValue(), AppConfig.MYSQL_USER.getValue(), AppConfig.MYSQL_PASSWORD.getValue());
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //单例的双重校验锁
    public static SqlOperation getInstance() {
        if (sqlOperation==null){
            synchronized (SqlOperationImpl.class){
                if (sqlOperation==null){
                    sqlOperation = new SqlOperationImpl();
                    return sqlOperation;
                }
            }
        }
        return sqlOperation;
    }

    public void close() throws SQLException {
        statement.close();
        connection.close();
    }

    public Boolean insertStudentInfo(Student student) {
        String s = String.format("insert into student(id,name,sex,age,idCard,major,className)" +
                " values(\"%s\",\"%s\",\"%s\",%d,\"%s\",\"%s\",\"%s\") ", student.getId(),student.getName(),student.getSex(),student.getAge(),
                student.getIdCard(),student.getMajor(),student.getClassName());
        System.out.println(s);
        return execute(s);
    }

    public Boolean delStudentInfo(String id) {
        String s = String.format("delete from student where id = \"%s\"",id);
        return execute(s);
    }

    public Boolean modifyStudentInfo(String id, String key, String value) {
        String s = String.format("update student set %s = \"%s\" where id = \"%s\"",key,value,id);
        return execute(s);
    }

    public Student getStudentInfoById(String id) {
        String s = String.format("select * from student where id = \"%s\"",id);
        try {
            PreparedStatement pre = connection.prepareStatement(s);
            ResultSet result = pre.executeQuery();
            if (result.next()){
                Student student = new Student();
                student.setId(result.getString("id"));
                student.setAge(result.getInt("age"));
                student.setName(result.getString("name"));
                student.setSex(result.getString("sex"));
                student.setIdCard(result.getString("idCard"));
                student.setMajor(result.getString("major"));
                student.setClassName(result.getString("className"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student getStudentInfoByIdCard(String idCard) {
        return null;
    }

    public Boolean insertAchInfo(Achievement arc) {
        return null;
    }

    public Boolean delAch(String id) {
        return null;
    }

    public Boolean modifyAch(String id, String key, String value) {
        return null;
    }

    public Achievement getAchInfoByStudentName(String name) {
        return null;
    }

    public Boolean insertClassInfo(Class c) {

        return null;
    }

    public Boolean delClassInfo(String classId) {
        return null;
    }

    public Boolean modifyClassInfo(String id, String key, String value) {
        return null;
    }

    public Class getClassInfoByName(String name) {
        return null;
    }

    private Boolean execute(String s){
        try {
            statement.execute(s);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
