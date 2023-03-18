package entity;

import javax.persistence.*;

/**
 * 学员课程信息;
 * studentId:courseId*1000+课程内学号;
 * classId:若为空,代表未选择班级,系统将自动分配班级;
 * absence:缺席次数,可以为空;
 * grade:成绩,可以为空;
 */
@Entity
@Table(name = "classInfo", schema = "college", catalog = "")
public class ClassInfo {
    private int studentId;
    private int courseId;
    private Integer classId;
    private int orderId;
    private String studentName;
    private Integer absence;
    private Double grade;

    @Id
    @Column(name = "studentId", nullable = false)
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "courseId", nullable = false)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "classId", nullable = true)
    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "orderId", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "studentName", nullable = false, length = 45)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "absence", nullable = true)
    public Integer getAbsence() {
        return absence;
    }

    public void setAbsence(Integer absence) {
        this.absence = absence;
    }

    @Basic
    @Column(name = "grade", nullable = true, precision = 0)
    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassInfo that = (ClassInfo) o;

        if (studentId != that.studentId) return false;
        if (courseId != that.courseId) return false;
        if (orderId != that.orderId) return false;
        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
        if (studentName != null ? !studentName.equals(that.studentName) : that.studentName != null) return false;
        if (absence != null ? !absence.equals(that.absence) : that.absence != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentId;
        result = 31 * result + courseId;
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        result = 31 * result + orderId;
        result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
        result = 31 * result + (absence != null ? absence.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        return result;
    }
}
