package service.impl;

import dao.CourseDao;
import entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CourseService;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public void add(Course course) {
        courseDao.add(course);
    }

    @Override
    public void update(Course course) {
        courseDao.update(course);
    }

    @Override
    public boolean isExist(int courseId) {
        return courseDao.isExist(courseId);
    }

    @Override
    public Course getCourse(int courseId) {
        return courseDao.getCourse(courseId);
    }

    @Override
    public void delete(int courseId) {
        courseDao.delete(courseId);
    }

    @Override
    public List<Course> getListById(int institutionId) {
        return courseDao.getListById(institutionId);
    }

    @Override
    public List<Course> getListByName(String institutionName) {
        return courseDao.getListByName(institutionName);
    }

    @Override
    public List<Course> getListByType(String category) {
        return courseDao.getListByType(category);
    }

    @Override
    public List<Course> getListByTime(Date date) {
        return courseDao.getListByTime(date);
    }

    @Override
    public List<Course> getAll() {
        return courseDao.getAll();
    }

    @Override
    public List<Course> getList(int institutionId, String category, Date date){
        return courseDao.getList(institutionId, category, date);
    }

    @Override
    public int getId(){return courseDao.getId();}
}
