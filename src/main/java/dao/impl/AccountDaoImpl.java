package dao.impl;

import dao.AccountDao;
import entity.Account;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *
 */
@Transactional(rollbackFor = Exception.class)
@Repository("AccountDao")
public class AccountDaoImpl implements AccountDao{
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(Account account) {
        sessionFactory.getCurrentSession().save(account);
    }

    @Override
    public void update(Account account) {
        sessionFactory.getCurrentSession().update(account);
    }

    @Override
    public double check(String accountId) {
        Account account = (Account) sessionFactory.getCurrentSession().get(Account.class, accountId);
        return account.getMoney();
    }

    @Override
    public Account getAccount(String accountId){
        return (Account) sessionFactory.getCurrentSession().get(Account.class, accountId);
    }
}
