package Model;

public class ClassInfo {
    private String Name;
    private String teacher;
    private int totalMember;
    private int passMember;
    private float passRate;
    private float maxGrade;
    private float minGrade;
    private float averageGrade;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(int totalMember) {
        this.totalMember = totalMember;
    }

    public int getPassMember() {
        return passMember;
    }

    public void setPassMember(int passMember) {
        this.passMember = passMember;
    }

    public float getPassRate() {
        return passRate;
    }

    public void setPassRate(float passRate) {
        this.passRate = passRate;
    }

    public float getMaxGrade() {
        return maxGrade;
    }

    public void setMaxGrade(float maxGrade) {
        this.maxGrade = maxGrade;
    }

    public float getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(float minGrade) {
        this.minGrade = minGrade;
    }

    public float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }
}
