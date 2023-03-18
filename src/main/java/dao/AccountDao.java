package dao;

import entity.Account;

/**
 *
 */
public interface AccountDao {

    /**
     * 添加网络支付;
     * @param account
     */
    public void add(Account account);

    /**
     * 更新余额;
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
