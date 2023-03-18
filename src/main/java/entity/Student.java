package entity;

import javax.persistence.*;

/**
 * VIPRank:E(0~99);D(100~299);C(300~799);B(800~1499);A(1500~2999);S(>3000);N(取消会员);0(未激活)
 * 会员折扣:E(1);D(0.98);C(0.95);B(0.9);A(0.85);S(0.8)
 * credit:每消费10¥获得1积分(取整);每10积分可兑换1¥;
 */
@Entity
@Table(name = "student", schema = "college", catalog = "")
public class Student {
    private String mail;
    private String password;
    private String name;
    private String viPrank;
    private int credit;
    private String accountId;
    private String ucode;

    @Id
    @Column(name = "mail", nullable = false, length = 45)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
    @Column(name = "VIPrank", nullable = false, length = 20)
    public String getViPrank() {
        return viPrank;
    }

    public void setViPrank(String viPrank) {
        this.viPrank = viPrank;
    }

    @Basic
    @Column(name = "credit", nullable = false)
    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Basic
    @Column(name = "accountId", nullable = false, length = 45)
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "ucode", nullable = false, length = 60)
    public String getUcode() {
        return ucode;
    }

    public void setUcode(String ucode) {
        this.ucode = ucode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student that = (Student) o;

        if (credit != that.credit) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (viPrank != null ? !viPrank.equals(that.viPrank) : that.viPrank != null) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (ucode != null ? !ucode.equals(that.ucode) : that.ucode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mail != null ? mail.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (viPrank != null ? viPrank.hashCode() : 0);
        result = 31 * result + credit;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (ucode != null ? ucode.hashCode() : 0);
        return result;
    }
}
