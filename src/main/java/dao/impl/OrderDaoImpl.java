package dao.impl;

import dao.OrderDao;
import entity.Order;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Transactional(rollbackFor = Exception.class)
@Repository("OrderDao")
public class OrderDaoImpl implements OrderDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public void update(Order order) {
        sessionFactory.getCurrentSession().update(order);
    }

    @Override
    public boolean isExist(int orderId) {
        if(sessionFactory.getCurrentSession().get(Order.class, orderId)!=null)
            return true;
        return false;
    }

    @Override
    public Order getOrder(int orderId) {
        return (Order) sessionFactory.getCurrentSession().get(Order.class, orderId);
    }

    @Override
    public List<Order> getListByInst(int institutionId) {
        String hql = "from Order as o where o.institutionId = " + institutionId + "";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Order> getListByStu(String studentMail) {
        String hql = "from Order as o where o.studentMail = '" + studentMail + "'";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Order> getListByState(String state){
        String hql = "from Order as o where o.state like '%" + state + "%'";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Order> getAll() {
        String hql = "from " + Order.class.getName();
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public int getId(){
        String hql = "select max(o.orderId) from Order as o";
        if(sessionFactory.getCurrentSession().createQuery(hql).uniqueResult()==null)
            return 100001;
        else
            return (Integer)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult()+1;
    }

    @Override
    public void delete(int orderId){
        Order order = this.getOrder(orderId);
        sessionFactory.getCurrentSession().delete(order);
    }
}
