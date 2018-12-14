package Presenter;

import UI.ClassView;
import Model.Class;
import dao.SqlOperation;
import dao.SqlOperationImpl;

public class ClassPresenter {
    private ClassView classView;
    SqlOperation operation = SqlOperationImpl.getInstance();
    public ClassPresenter(ClassView classView) {
        this.classView = classView;
    }

    public Class search(String className){
        Class lesson = operation.getClassInfoById(className);
        return  lesson;
    }

    public Boolean add(Class lesson){
        Boolean result = operation.insertClassInfo(lesson);
        return result;
    }

    public Boolean del(String classID){
        Boolean result = operation.delClassInfo(classID);
        return result;
    }

    public Boolean modi(String id,String key,String value){
        Boolean result = false;
        String value1= null;
        switch (key){
            case "课程名":
                result = operation.modifyClassInfo(id,"class_name",value);
                break;
            case "课时":
                result = operation.modifyClassInfo(id,"class_hour",value);
                break;
            case "课程性质":
                if(value.equals("必修"))
                {
                     value1="1";
                } else{
                     value1="0";
                }
                result = operation.modifyClassInfo(id,"class_proprety",value1);
                break;
            case "学分":
                result=operation.modifyClassInfo(id,"score",value);
                break;
            case "任课老师":
                result=operation.modifyClassInfo(id,"teacher",value);
                break;
             default:
                 break;
        }
        return result;
    }

}
