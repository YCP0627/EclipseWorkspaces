package dao;

import Entity.AppConfig;
import Model.Adminstrator;
import Model.Grade;
import Model.Class;
import Model.Student;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Boolean modifyStudentInfo(String id, String key, Object value) {
        String s=null;
        if(value instanceof  String){
            s = String.format("update student set %s = \"%s\" where id = \"%s\"",key,(String)value,id);
        }
        else{
            s = String.format("update student set %s = \"%d\" where id = \"%s\"",key,(Integer)value,id);
        }

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

    public Boolean insertGrade(Grade grade) {
        String s=String.format("insert into grade(id,name,class_id,class_name,class_grade)"+
                "values(\"%s\",\"%s\",\"%s\",\"%s\",%f)",grade.getId(),grade.getStuName(),grade.getClassId(),
                grade.getClassName(),grade.getGrade());
        return execute(s);
    }

    public Boolean delGrade(String id) {
        String s=String.format("delete from grade where grade_id = %s ",id);
        return execute(s);
    }

    public Boolean modifyGrade(String id,String class_id, String key, float value) {
        String s = String.format("update grade set %s=%f where id=\"%s\" and class_id=\"%s\"",key,value,id,class_id);
        return execute(s);
    }

    public List<Grade> getGradeByStudentName(String name) {
        List<Grade> gradeList = new ArrayList<Grade>();
        String s= String.format("select * from grade where name=\"%s\"",name);
        try{
        PreparedStatement pre = connection.prepareStatement(s);
        ResultSet result = pre.executeQuery();
        while (result.next()){
            Grade grade = new Grade();
            grade.setGradeId(result.getInt("grade_id"));
            grade.setId(result.getString("id"));
            grade.setStuName(result.getString("name"));
            grade.setClassId(result.getString("class_id"));
            grade.setClassName(result.getString("class_name"));
            grade.setGrade(result.getInt("class_grade"));
            gradeList.add(grade);
        }
        result.close();
        return gradeList;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Grade getGrade(String name,String class_name){
        String s = String.format("select * from grade where name=\"%s\" and class_name=\"%s\"",name,class_name);
        try{
            PreparedStatement pre = connection.prepareStatement(s);
            ResultSet result = pre.executeQuery();
            if (result.next()){
                Grade grade = new Grade();
                grade.setGradeId(result.getInt("grade_id"));
                grade.setId(result.getString("id"));
                grade.setStuName(result.getString("name"));
                grade.setClassId(result.getString("class_id"));
                grade.setClassName(result.getString("class_name"));
                grade.setGrade(result.getInt("class_grade"));
                result.close();
                return grade;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Grade> getGradeByStudentId(String id) {
        List<Grade> gradeList = new ArrayList<Grade>();
        String s= String.format("select * from grade where id=\"%s\"",id);
        try{
            PreparedStatement pre = connection.prepareStatement(s);
            ResultSet result = pre.executeQuery();
            while (result.next()){
                Grade grade = new Grade();
                grade.setId(result.getString("id"));
                grade.setStuName(result.getString("name"));
                grade.setClassId(result.getString("class_id"));
                grade.setClassName(result.getString("class_name"));
                grade.setGrade(result.getInt("class_grade"));
                grade.setGradeId(result.getInt("grade_id"));
                gradeList.add(grade);
            }
            result.close();
            return gradeList;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Boolean insertClassInfo(Class c) {
        String s=String.format("insert into class(class_id,class_name,class_hour,class_proprety,score,teacher)"+
                "values(\"%s\",\"%s\",%d,%b,%f,\"%s\")",c.getClassId(),c.getName(),c.getClassHour(),c.getObligatory(),
                c.getScore(),c.getTeacher());
        return execute(s);
    }

    public Boolean delClassInfo(String classId) {
        String s=String.format("delete from class where class_id=\"%s\"",classId);
        return execute(s);
    }

    public Boolean modifyClassInfo(String id, String key, Object value) {
        String s=null;
        if(value instanceof String){
            s=String.format("update class set %s=\"%s\" where class_id=\"%s\"",key,(String)value,id);
        }
        else if(value instanceof  Integer){
            s=String.format("update class set %s=%d where class_id=\"%s\"",key,(Integer)value,id);
        }
        else if(value instanceof  Float){
            s=String.format("update class set %s=%f where class_id=\"%s\"",key,(Float)value,id);
        }
        return execute(s);
    }

    public Class getClassInfoById(String class_id) {
        String s = String.format("select * from class where name = \"%s\"",class_id);
        try{
            PreparedStatement pre = connection.prepareStatement(s);
            ResultSet result = pre.executeQuery();
            if(result.next()){
                Class lesson = new Class();
                lesson.setClassId(result.getString("class_id"));
                lesson.setName(result.getString("class_name"));
                lesson.setClassHour(result.getInt("class_hour"));
                lesson.setObligatory(result.getBoolean("class_proprety"));
                lesson.setScore(result.getFloat("score"));
                lesson.setTeacher(result.getString("teacher"));
                return lesson;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean updateAdmin(String phone, String key, Object value) {
        String s;
        if (value instanceof String){
            s = String.format("update admin set %s = \"%s\" where phone = \"%s\"",key,value,phone);
        }else if (value instanceof Integer){
            s = String.format("update admin set %s = %d where phone = \"%s\"",key,value,phone);
        }else {
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = simpleDateFormat.format((Date) value);
            s = String.format("update admin set %s = \"%s\" where phone = \"%s\"",key,date,phone);
        }
        return execute(s);
    }

    @Override
    public List<Class> getAllClass() {
        String s = "select * from class";
        List<Class> classList = new ArrayList<Class>();
        try{
            PreparedStatement pre = connection.prepareStatement(s);
            ResultSet result = pre.executeQuery();
            while (result.next()){
                Class aClass = new Class();
                aClass.setClassId(result.getString("class_id"));
                aClass.setClassHour(result.getInt("class_hour"));
                aClass.setName(result.getString("class_name"));
                aClass.setObligatory(result.getBoolean("class_proprety"));
                aClass.setScore(result.getFloat("score"));
                aClass.setTeacher(result.getString("teacher"));
                classList.add(aClass);
            }
            return classList;
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Grade getGradeByClassAndId(String id, String selectClass) {
        String s = String.format("select * from grade where id=\"%s\" and class_name=\"%s\"",id,selectClass);
        try{
            PreparedStatement pre = connection.prepareStatement(s);
            ResultSet result = pre.executeQuery();
            if (result.next()){
                Grade grade = new Grade();
                grade.setId(result.getString("id"));
                grade.setStuName(result.getString("name"));
                grade.setClassId(result.getString("class_id"));
                grade.setClassName(result.getString("class_name"));
                grade.setGradeId(result.getInt("grade_id"));
                grade.setGrade(result.getInt("class_grade"));
                result.close();
                return grade;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Adminstrator getAdmin(String phone) {
        String s= String.format("select * from admin where phone=\"%s\"",phone);
        try{
            PreparedStatement pre = connection.prepareStatement(s);
            ResultSet result = pre.executeQuery();
            if (result.next()){
                Adminstrator adminstrator = new Adminstrator();
                adminstrator.setLastDate(result.getDate("last_login_date"));
                adminstrator.setLoginCount(result.getInt("login_count"));
                adminstrator.setName(result.getString("name"));
                adminstrator.setPassword(result.getString("password"));
                adminstrator.setStyle(result.getString("style"));
                adminstrator.setRegisterDate(result.getDate("register_date"));
                return adminstrator;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
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
