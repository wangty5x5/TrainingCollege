package entity;

import javax.persistence.*;

/**
 * 模拟网络支付;
 * accountId:6位;
 */
@Entity
@Table(name = "account", schema = "college", catalog = "")
public class Account {
    private String accountId;
    private double money;

    @Id
    @Column(name = "accountId", nullable = false, length = 45)
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "money", nullable = false, precision = 0)
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account that = (Account) o;

        if (Double.compare(that.money, money) != 0) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = accountId != null ? accountId.hashCode() : 0;
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
