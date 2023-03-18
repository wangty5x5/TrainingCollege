package dao.impl;

import dao.InstitutionDao;
import entity.Institution;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Transactional(rollbackFor = Exception.class)
@Repository("InstitutionDao")
public class InstitutionDaoImpl implements InstitutionDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(Institution institution) {
        sessionFactory.getCurrentSession().save(institution);
    }

    @Override
    public void update(Institution institution) {
        sessionFactory.getCurrentSession().update(institution);
    }

    @Override
    public boolean isExist(int id) {
        if(sessionFactory.getCurrentSession().get(Institution.class, id)!=null)
            return true;
        return false;
    }

    @Override
    public boolean checkPassword(int id, String password) {
        Institution institution = this.getInstitution(id);
        if(password.equals(institution.getPassword()))
            return true;
        return false;
    }

    @Override
    public Institution getInstitution(int id) {
        return (Institution) sessionFactory.getCurrentSession().get(Institution.class, id);
    }

    @Override
    public List<Institution> getListByName(String name) {
        String hql = "from Institution as i where i.name like '" + name + "%'";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Institution> getListByProvince(String province){
        String hql = "from Institution as i where i.province = '" + province + "'";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Institution> getListByCity(String city) {
        String hql = "from Institution as i where i.city = '" + city + "'";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<Institution> getAll() {
        String hql = "from " + Institution.class.getName();
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    public int getId(int num){
        int min = num;
        int max = num + 999;
        String hql = "select max(i.id) from Institution as i where i.id between " + min + " and " + max + "";
        if(sessionFactory.getCurrentSession().createQuery(hql).uniqueResult()==null){
            return min+1;
        }
        else {
            int result = (Integer) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult() + 1;
            return result;
        }

    }

    @Override
    public List<Institution> getListByAd(String province, String city){
        String hql = "from Institution as i where (i.province = '" + province + "') and (i.city = '" + city + "')";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }
}
