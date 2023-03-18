package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *id:7位数;省份决定前2位,城市决定3、4位,最后3位依注册顺序;
 * teachers:师资;
 * approvalState:1)等待审批:W;2)通过审批:P;3)未通过审批:N;
 */
@Entity
public class Institution {
    private int id;
    private String password;
    private String name;
    private String city;
    private String address;
    private int teachers;
    private double income;
    private String province;
    private String approvalState;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "city", nullable = false, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "teachers", nullable = false)
    public int getTeachers() {
        return teachers;
    }

    public void setTeachers(int teachers) {
        this.teachers = teachers;
    }

    @Basic
    @Column(name = "income", nullable = false, precision = 0)
    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Basic
    @Column(name = "province", nullable = false, length = 45)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "approvalState", nullable = false, length = 45)
    public String getApprovalState() {
        return approvalState;
    }

    public void setApprovalState(String approvalState) {
        this.approvalState = approvalState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Institution that = (Institution) o;

        if (id != that.id) return false;
        if (teachers != that.teachers) return false;
        if (Double.compare(that.income, income) != 0) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (approvalState != null ? !approvalState.equals(that.approvalState) : that.approvalState != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + teachers;
        temp = Double.doubleToLongBits(income);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (approvalState != null ? approvalState.hashCode() : 0);
        return result;
    }
}
