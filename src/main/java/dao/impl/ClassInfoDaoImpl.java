package dao.impl;

import dao.ClassInfoDao;
import entity.ClassInfo;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Transactional(rollbackFor = Exception.class)
@Repository("ClassInfoDao")
public class ClassInfoDaoImpl implements ClassInfoDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(ClassInfo classInfo) {
        sessionFactory.getCurrentSession().save(classInfo);
    }

    @Override
    public void update(ClassInfo classInfo) {
        sessionFactory.getCurrentSession().update(classInfo);
    }

    @Override
    public boolean isExist(int studentId) {
        if(sessionFactory.getCurrentSession().get(ClassInfo.class, studentId)!=null)
            return true;
        return false;
    }

    @Override
    public ClassInfo getInfo(int studentId) {
        return (ClassInfo) sessionFactory.getCurrentSession().get(ClassInfo.class, studentId);
    }

    @Override
    public List<ClassInfo> getListById(int courseId) {
        String hql = "from ClassInfo as c where c.courseId = " + courseId + "";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<ClassInfo> getAll() {
        String hql = "from " + ClassInfo.class.getName();
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<ClassInfo> getListByName(String name){
        String hql = "from ClassInfo as c where c.studentName = '" + name + "'";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }
}
