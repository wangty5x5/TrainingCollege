package dao.impl;

import dao.ManagerDao;
import entity.Manager;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Transactional(rollbackFor = Exception.class)
@Repository("ManagerDao")
public class ManagerDaoImpl implements ManagerDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(Manager manager) {
        sessionFactory.getCurrentSession().save(manager);
    }

    @Override
    public boolean isExist(String managerId) {
        if(sessionFactory.getCurrentSession().get(Manager.class, managerId)!=null)
            return true;
        return false;
    }

    @Override
    public Manager getManager(String managerId) {
        return (Manager) sessionFactory.getCurrentSession().get(Manager.class, managerId);
    }

    @Override
    public boolean checkPassword(String managerId, String password) {
        Manager manager = (Manager) sessionFactory.getCurrentSession().get(Manager.class, managerId);
        if(password.equals(manager.getPassword()))
            return true;
        return false;
    }

    @Override
    public void update(Manager manager) {
        sessionFactory.getCurrentSession().update(manager);
    }

    @Override
    public List<Manager> getAll() {
        String hql = "from " + Manager.class.getName();
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }
}
