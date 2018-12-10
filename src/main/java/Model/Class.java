package Model;

public class Class {
    private String classId;
    private String name;
    private int classHour;
    private Boolean isObligatory;
    private float score;
    private String teacher;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassHour() {
        return classHour;
    }

    public void setClassHour(int classHour) {
        this.classHour = classHour;
    }

    public Boolean getObligatory() {
        return isObligatory;
    }

    public void setObligatory(Boolean obligatory) {
        isObligatory = obligatory;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
