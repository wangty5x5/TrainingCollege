package service;

import entity.Course;

import java.util.Date;
import java.util.List;

/**
 *
 */
public interface CourseService {

    /**
     * 发布新课程计划;
     * @param course
     */
    public void add(Course course);

    /**
     * 更新课程计划;
     * @param course
     */
    public void update(Course course);

    /**
     * 检查课程是否存在;
     * @param courseId
     * @return
     */
    public boolean isExist(int courseId);

    /**
     * 根据课程编号获得课程;
     * @param courseId
     * @return
     */
    public Course getCourse(int courseId);

    /**
     * 删除课程;
     * @param courseId
     */
    public void delete(int courseId);

    /**
     * 根据机构编号获得课程;
     * @param institutionId
     * @return
     */
    public List<Course> getListById(int institutionId);
    /**
     * 根据机构名称获得课程;
     * @param institutionName
     * @return
     */
    public List<Course> getListByName(String institutionName);

    /**
     * 根据课程种类获得课程;
     * @param category
     * @return
     */
    public List<Course> getListByType(String category);

    /**
     * 根据课程开始时间获得课程;
     * @param date
     * @return
     */
    public List<Course> getListByTime(Date date);

    /**
     * 获得全部课程;
     * @return
     */
    public List<Course> getAll();

    public List<Course> getList(int institutionId, String category, Date date);

    public int getId();
}
