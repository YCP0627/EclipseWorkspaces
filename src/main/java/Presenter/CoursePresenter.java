package Presenter;

import Model.Class;
import Model.Grade;
import UI.ICourseView;
import dao.SqlOperation;
import dao.SqlOperationImpl;

import java.util.List;

public class CoursePresenter {
    private ICourseView courseView;
    SqlOperation sqlOperation = SqlOperationImpl.getInstance();

    public CoursePresenter(ICourseView courseView) {
        this.courseView = courseView;
    }

    public void queryGradeByName(String name) {
        List<Grade> gradeList = sqlOperation.getGradeByStudentName(name);
        courseView.getGradeByName(gradeList);

    }

    public void queryClassById() {
        List<Class> classList = sqlOperation.getAllClass();
        courseView.getClassName(classList);
    }

    public void queryGradeByNameAndClass(String name, String selectClass) {
        Grade grade = sqlOperation.getGrade(name,selectClass);
        courseView.getGradeByNameAndClass(grade);
    }
}
