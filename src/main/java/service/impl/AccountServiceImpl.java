package service.impl;

import dao.AccountDao;
import entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AccountService;

/**
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void add(Account account) {
        accountDao.add(account);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public double check(String accountId) {
        return accountDao.check(accountId);
    }

    @Override
    public Account getAccount(String accountId){ return accountDao.getAccount(accountId); }
}
