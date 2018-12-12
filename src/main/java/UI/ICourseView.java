package UI;

import Model.Class;
import Model.Grade;

import java.util.List;

public interface ICourseView {
    void getGradeByName(List<Grade> gradeList);

    void getClassName(List<Class> classList);

    void getGradeByNameAndClass(Grade grade);
}
