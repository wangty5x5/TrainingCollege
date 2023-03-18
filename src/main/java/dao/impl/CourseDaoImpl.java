package dao.impl;

import dao.CourseDao;
import entity.Course;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Transactional(rollbackFor = Exception.class)
@Repository("CourseDao")
public class CourseDaoImpl implements CourseDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(Course course) {
        sessionFactory.getCurrentSession().save(course);
    }

    @Override
    public void update(Course course) {
        sessionFactory.getCurrentSession().update(course);
    }

    @Override
    public boolean isExist(int courseId){
        if(sessionFactory.getCurrentSession().get(Course.class, courseId)!=null)
            return true;
        return false;
    }

    @Override
    public Course getCourse(int courseId) {
        return (Course) sessionFactory.getCurrentSession().get(Course.class, courseId);
    }

    @Override
    public void delete(int courseId) {
        Course course = this.getCourse(courseId);
        sessionFactory.getCurrentSession().delete(course);
    }

    @Override
    public List<Course> getListById(int institutionId) {
        String hql = "from Course as c where c.institutionId = " + institutionId + "";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Course> getListByName(String institutionName) {
        String hql = "from Course as c where c.institutionName = '" + institutionName + "'";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Course> getListByType(String category) {
        String hql = "from Course as c where c.category = '" + category + "'";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Course> getListByTime(Date date) {
        String hql = "from Course as c where c.startDate > " + date + "";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Course> getAll() {
        String hql = "from " + Course.class.getName();
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Course> getList(int institutionId, String category, Date date){
        String hql = "from Course as c where (c.institutionId = "+institutionId+") and (c.category = '"+category+"') and (c.startDate > "+date+")";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public int getId(){
        String hql = "select max(c.courseId) from Course as c";
        if(sessionFactory.getCurrentSession().createQuery(hql).uniqueResult()==null)
            return 10001;
        else
            return (Integer)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult()+1;
    }
}
