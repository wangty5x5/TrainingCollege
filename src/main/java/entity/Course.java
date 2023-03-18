package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 *courseId:5位数;从10001开始;
 * period:课时,如20节课;
 * classTime:1)"全天";2)"周末";3)"工作日"
 * number:课程总人数——课程下各个班级人数总和;
 */
@Entity
public class Course {
    private int courseId;
    private int institutionId;
    private String institutionName;
    private Date startDate;
    private int period;
    private String classTime;
    private int number;
    private int emptyNum;
    private String category;

    @Id
    @Column(name = "courseId", nullable = false)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "institutionId", nullable = false)
    public int getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    @Basic
    @Column(name = "institutionName", nullable = false, length = 45)
    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    @Basic
    @Column(name = "startDate", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "period", nullable = false)
    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Basic
    @Column(name = "classTime", nullable = false, length = 45)
    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    @Basic
    @Column(name = "number", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "emptyNum", nullable = false)
    public int getEmptyNum() {
        return emptyNum;
    }

    public void setEmptyNum(int emptyNum) {
        this.emptyNum = emptyNum;
    }

    @Basic
    @Column(name = "category", nullable = false, length = 45)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (courseId != course.courseId) return false;
        if (institutionId != course.institutionId) return false;
        if (period != course.period) return false;
        if (number != course.number) return false;
        if (emptyNum != course.emptyNum) return false;
        if (institutionName != null ? !institutionName.equals(course.institutionName) : course.institutionName != null)
            return false;
        if (startDate != null ? !startDate.equals(course.startDate) : course.startDate != null) return false;
        if (classTime != null ? !classTime.equals(course.classTime) : course.classTime != null) return false;
        if (category != null ? !category.equals(course.category) : course.category != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = courseId;
        result = 31 * result + institutionId;
        result = 31 * result + (institutionName != null ? institutionName.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + period;
        result = 31 * result + (classTime != null ? classTime.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + emptyNum;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
