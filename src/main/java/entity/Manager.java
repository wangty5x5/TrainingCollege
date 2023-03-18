package entity;

import javax.persistence.*;

/**
 * managerId:系统分配;
 * profit:订单价格一半结算给机构;
 */
@Entity
@Table(name = "manager", schema = "college", catalog = "")
public class Manager {
    private String managerId;
    private String password;
    private String name;
    private double profit;

    @Id
    @Column(name = "managerId", nullable = false, length = 45)
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "profit", nullable = false, precision = 0)
    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manager that = (Manager) o;

        if (Double.compare(that.profit, profit) != 0) return false;
        if (managerId != null ? !managerId.equals(that.managerId) : that.managerId != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = managerId != null ? managerId.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(profit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
