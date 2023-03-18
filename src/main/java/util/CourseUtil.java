package util;

import java.sql.Date;

/**
 *
 */
public class CourseUtil {

    private int courseId;
    private String category;
    private int classNum;
    private Date start;
    private int period;
    private String time;
    private int number;
    private int emptyNum;

    public int getCourseId(){ return courseId; }

    public void setCourseId(int id){ this.courseId = id; }

    public String getCategory(){ return category; }

    public void setCategory(String category){ this.category = category; }

    public int getClassNum(){ return classNum; }

    public void setClassNum(int n){ this.classNum = n; }

    public Date getStart(){ return start; }

    public void setStart(Date date){ this.start = date; }

    public int getPeriod(){ return period; }

    public void setPeriod(int period){ this.period = period; }

    public String getTime(){ return time; }

    public void setTime(String time){ this.time = time; }

    public int getNumber(){ return number; }

    public void setNumber(int n){ this.number = n; }

    public int getEmptyNum(){ return emptyNum; }

    public void setEmptyNum(int empty){ this.emptyNum = empty; }
}
