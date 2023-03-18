package entity;

import javax.persistence.*;

/**
 * 班级;
 * classId:courseId*10+班级号;
 * number:班级人数;
 * emptyNum:空余人数;
 */
@Entity
@Table(name = "class", schema = "college", catalog = "")
public class Class {
    private int classId;
    private int courseId;
    private String teacher;
    private int number;
    private int emptyNum;
    private double price;

    @Id
    @Column(name = "classId", nullable = false)
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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
    @Column(name = "teacher", nullable = false, length = 45)
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
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
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Class that = (Class) o;

        if (classId != that.classId) return false;
        if (courseId != that.courseId) return false;
        if (number != that.number) return false;
        if (emptyNum != that.emptyNum) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (teacher != null ? !teacher.equals(that.teacher) : that.teacher != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = classId;
        result = 31 * result + courseId;
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + emptyNum;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
