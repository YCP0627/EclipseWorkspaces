package UI;

import Model.Class;
import Model.Grade;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface ICourseView {
    void getGradeByName(DefaultTableModel model,List<Grade> gradeList);

    void getClassName(List<Class> classList);

    void getGradeByNameAndClass(DefaultTableModel model,Grade grade);

    void delCourseResult(Boolean delGrade);

    void insertResult(boolean b, String s);
}
