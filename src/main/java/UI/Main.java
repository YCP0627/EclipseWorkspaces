package UI;

import Model.Class;
import Model.Grade;
import Model.Student;
import dao.SqlOperation;
import dao.SqlOperationImpl;

public class Main {
    public static void main(String[] args) {
       SqlOperation operation = SqlOperationImpl.getInstance();
       /* Class lesson=new Class();
        lesson.setClassHour(40);
        lesson.setClassId("001");
        lesson.setName("软件工程");
        lesson.setObligatory(true);
        lesson.setScore(2);
        lesson.setTeacher("喻彩平");
        operation.insertClassInfo(lesson);*/
       Grade grade = new Grade();
       grade.setClassId("001");
       grade.setClassName("软件工程");
       grade.setGrade(99);
       grade.setId("B20160304301");
       grade.setStuName("喻彩平");
       operation.insertGrade(grade);
    }
}
