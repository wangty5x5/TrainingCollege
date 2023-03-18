package dao.impl;

import dao.ClassDao;
import entity.Class;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Transactional(rollbackFor = Exception.class)
@Repository("ClassDao")
public class ClassDaoImpl implements ClassDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(Class c) {
        sessionFactory.getCurrentSession().save(c);
    }

    @Override
    public void update(Class c) {
        sessionFactory.getCurrentSession().update(c);
    }

    @Override
    public boolean isExist(int classId) {
        if(sessionFactory.getCurrentSession().get(Class.class, classId)!=null)
            return true;
        return false;
    }

    @Override
    public Class getClass(int classId) {
        return (Class) sessionFactory.getCurrentSession().get(Class.class, classId);
    }

    @Override
    public List<Class> getListById(int courseId) {
        String hql = "from Class as c where c.courseId = " + courseId + "";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public void delete(int classId) {
        Class c = this.getClass(classId);
        sessionFactory.getCurrentSession().delete(c);
    }
}
