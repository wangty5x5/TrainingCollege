package dao.impl;

import dao.ApprovalDao;
import entity.Approval;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Transactional(rollbackFor = Exception.class)
@Repository("ApprovalDao")
public class ApprovalDaoImpl implements ApprovalDao{
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(Approval approval) {
        sessionFactory.getCurrentSession().save(approval);
    }

    @Override
    public void update(Approval approval) {
        sessionFactory.getCurrentSession().update(approval);
    }

    @Override
    public boolean isExist(int approvalId) {
        if(sessionFactory.getCurrentSession().get(Approval.class, approvalId)!=null)
            return true;
        return false;
    }

    @Override
    public Approval getApproval(int approvalId) {
        return (Approval) sessionFactory.getCurrentSession().get(Approval.class, approvalId);
    }

    @Override
    public void delete(int approvalId) {
        Approval approval = this.getApproval(approvalId);
        sessionFactory.getCurrentSession().delete(approval);
    }

    @Override
    public List<Approval> getListByInst(int institutionId) {
        String hql = "from Approval as a where a.institutionId = " + institutionId + "";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Approval> getListByMana(int managerId) {
        String hql = "from Approval as a where a.managerId = " + managerId + "";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Approval> getListByType(String type) {
        String hql = "from Approval as a where a.type = '" + type + "'";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Approval> getListByState(String state) {
        String hql = "from Approval as a where a.state = '" + state + "'";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Approval> getAll() {
        String hql = "from " + Approval.class.getName();
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public int getId(){
        String hql = "select max(a.approvalId) from Approval as a";
        if(sessionFactory.getCurrentSession().createQuery(hql).uniqueResult()==null)
            return 10001;
        else
            return (Integer)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult()+1;
    }
}
