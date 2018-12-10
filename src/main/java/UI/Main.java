package UI;

import Model.Class;
import Model.Grade;
import Model.Student;
import dao.SqlOperation;
import dao.SqlOperationImpl;

public class Main {
    public static void main(String[] args) {
       SqlOperation operation = SqlOperationImpl.getInstance();
       /*Student stu = new Student();
       stu.setId("B20160304325");
       stu.setName("何冲");
       stu.setSex("男");
       stu.setAge(20);
       stu.setIdCard("430626199806273022");
       stu.setMajor("软件工程");
       stu.setClassName("16软件3班");
       operation.insertStudentInfo(stu);*/

       //operation.delStudentInfo("B20160304325");

       /* Class lesson=new Class();
        lesson.setClassHour(40);
        lesson.setClassId("001");
        lesson.setName("软件工程");
        lesson.setObligatory(true);
        lesson.setScore(2);
        lesson.setTeacher("喻彩平");
        operation.insertClassInfo(lesson);*/

       /*Grade grade = new Grade();
       grade.setClassId("001");
       grade.setClassName("软件工程");
       grade.setGrade(99);
       grade.setId("B20160304325");
       grade.setStuName("何冲");
      operation.insertGrade(grade);*/

       /*Student student = operation.getStudentInfoById("B20160304325");
       System.out.println(student.getId()+student.getName());*/

       //operation.modifyGrade("B20160304325","001","class_grade",100);
       // operation.modifyStudentInfo("B20160304325","age",19);
       // operation.modifyClassInfo("001","score","50");
    }
}
