package service;

import entity.Account;

/**
 *
 */
public interface AccountService {

    /**
     * 添加账号;
     * @param account
     */
    public void add(Account account);

    /**
     * 更新账号信息;
     * @param account
     */
    public void update(Account account);

    /**
     * 查询余额;
     * @param accountId
     * @return
     */
    public double check(String accountId);

    public Account getAccount(String accountId);
}
