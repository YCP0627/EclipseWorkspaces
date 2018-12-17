package Model;

import Utils.IsIdCard;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Student {
    @NotEmpty
    @Pattern(regexp = "B.*",message = "学号必须以B开头")
    @Size(min = 12,max = 12,message = "学号位数不对")
    private String id;
    @NotEmpty
    private String name;
    private String sex;
    private int age;
    @IsIdCard
    private String idCard;
    private String major;
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", idCard='" + idCard + '\'' +
                ", major='" + major + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

}
