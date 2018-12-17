package Presenter;

import Model.Class;
import Model.ClassInfo;
import Model.Grade;
import Model.Student;
import UI.ICourseView;
import com.mysql.cj.util.StringUtils;
import dao.SqlOperation;
import dao.SqlOperationImpl;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CoursePresenter {
    private ICourseView courseView;
    private SqlOperation sqlOperation = SqlOperationImpl.getInstance();

    public CoursePresenter(ICourseView courseView) {
        this.courseView = courseView;
    }

    public void queryGradeByName(DefaultTableModel model,String name) {
        List<Grade> gradeList = sqlOperation.getGradeByStudentName(name);
        for (Grade grade : gradeList){
            Student student = sqlOperation.getStudentInfoById(grade.getId());
            grade.setBanji(student.getClassName());
            grade.setClassName(sqlOperation.getClassInfoById1(grade.getClassId()).getName());
        }
        courseView.getGradeByName(model,gradeList);

    }

    public void queryClassById() {
        List<Class> classList = sqlOperation.getAllClass();
        courseView.getClassName(classList);
    }

    public void queryGradeByNameAndClass(DefaultTableModel model,String name, String selectClass) {
        Grade grade = sqlOperation.getGrade(name,selectClass);
        if (grade == null){
            courseView.getGradeByNameAndClass(model, null);
            return;
        }
        Student student = sqlOperation.getStudentInfoById(grade.getId());
        grade.setBanji(student.getClassName());
        grade.setClassName(sqlOperation.getClassInfoById1(grade.getClassId()).getName());
        courseView.getGradeByNameAndClass(model,grade);
    }

    public void updateGrade(String id,String classId,String key,float value) {
        sqlOperation.modifyGrade(id,classId,key,value);
    }

    public void delGrade(String text) {
        courseView.delCourseResult(sqlOperation.delGrade(text));
    }


    public void insertGrade(String text, String text1, Class aClass) {
        if (StringUtils.isEmptyOrWhitespaceOnly(text)){
            courseView.insertResult(false,"账号为空");
            return;
        }
        if (StringUtils.isEmptyOrWhitespaceOnly(text1)){
            courseView.insertResult(false,"分数为空");
            return;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        if (!pattern.matcher(text1).matches()){
            courseView.insertResult(false,"分数数字有误");
            return;
        }
        if (Float.valueOf(text1) >100 && Float.valueOf(text1)<0){
            courseView.insertResult(false,"分数范围出错");
            return;
        }
        Student student = sqlOperation.getStudentInfoById(text);
        if (student == null){
            courseView.insertResult(false,"没有该学生");
            return;
        }
        Grade grade1 = sqlOperation.getGrade(student.getName(),aClass.getName());
        if (grade1 !=null){
            courseView.insertResult(false,"已经存在该门课程的成绩");
            return;
        }
        Grade grade = new Grade();
        grade.setClassId(aClass.getClassId());
        grade.setClassName(aClass.getName());
        grade.setGrade(Float.valueOf(text1));
        grade.setId(student.getId());
        grade.setStuName(student.getName());
        sqlOperation.insertGrade(grade);
        courseView.insertResult(true,"插入成功");
    }

    public void queryGradeById(DefaultTableModel tableModel, String name) {
        List<Grade> gradeList = sqlOperation.getGradeByStudentId(name);
        for (Grade grade : gradeList){
            Student student = sqlOperation.getStudentInfoById(grade.getId());
            grade.setBanji(student.getClassName());
            grade.setClassName(sqlOperation.getClassInfoById1(grade.getClassId()).getName());
        }
        courseView.getGradeByName(tableModel,gradeList);
    }

    public void queryGradeByIdAndClass(DefaultTableModel model,String id, String selectClass) {
        Grade grade = sqlOperation.getGradeByClassAndId(id,selectClass);
        if (grade == null){
            courseView.getGradeByNameAndClass(model, null);
            return;
        }
        Student student = sqlOperation.getStudentInfoById(grade.getId());
        grade.setBanji(student.getClassName());
        grade.setClassName(sqlOperation.getClassInfoById1(grade.getClassId()).getName());
        courseView.getGradeByNameAndClass(model,grade);
    }

    public void getClassInfo(String s) {
        //String[] titles2 = {"课程名称","任课老师","考试人数","及格人数","及格率","最高分","最低分","平均分"};
        int passMember = 0;
        float max = 0;
        float min = 0;
        float total = 0;
        ClassInfo classInfo = new ClassInfo();
        Class c = sqlOperation.getClassInfoById(s);
        classInfo.setName(s);
        classInfo.setTeacher(c.getTeacher());
        List<Grade> gradeList = sqlOperation.getAllGrade(s);
        classInfo.setTotalMember(gradeList.size());
        int i =0;
        for (Grade grade : gradeList){
            if (i == 0){
                min = grade.getGrade();
            }
            if (grade.getGrade()>60){
                passMember ++;
            }
            if (grade.getGrade()>max){
                max = grade.getGrade();
            }
            if (grade.getGrade()<min){
                min = grade.getGrade();
            }
            total += grade.getGrade();
            i++;
        }

        classInfo.setPassMember(passMember);
        if(gradeList.size() == 0){
            classInfo.setPassRate(0);
        }else {
            classInfo.setPassRate(Float.valueOf(passMember)/gradeList.size());
        }
        classInfo.setMaxGrade(max);
        classInfo.setMinGrade(min);
        if(gradeList.size() == 0){
            classInfo.setAverageGrade(0);
        }else {
            classInfo.setAverageGrade(total/gradeList.size());
        }
        courseView.resultOfClassInfo(classInfo);

    }

    public void getMaxStudentInfo(String s) {
        List<Grade> gradeList = sqlOperation.getMaxGrade(s);
        List<Student> studentList = new ArrayList<>();
        if (!gradeList.isEmpty()){
            String[] grades = new String[gradeList.size()];
            int i=0;
            for (Grade grade:gradeList){
                Student student = sqlOperation.getStudentInfoById(grade.getId());
                studentList.add(student);
                grades[i] = String.valueOf(grade.getGrade());
                i++;
            }
            courseView.resultOfGrade(true,studentList,grades);
        }else {
            courseView.resultOfGrade(false,null,null);
        }
    }
}
