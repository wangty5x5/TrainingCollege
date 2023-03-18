package entity;

import javax.persistence.*;

/**
 * 申请;
 * approvalId:从10001开始;
 * type:1)"注册";2)"修改信息";
 * state:1)等待审批:W;2)通过审批:P;3)未通过审批:N;
 */
@Entity
@Table(name = "approval", schema = "college", catalog = "")
public class Approval {
    private int approvalId;
    private int institutionId;
    private String type;
    private String state;
    private int managerId;

    @Id
    @Column(name = "approvalId", nullable = false)
    public int getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(int approvalId) {
        this.approvalId = approvalId;
    }

    @Basic
    @Column(name = "institutionId", nullable = false)
    public int getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "state", nullable = false, length = 45)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "managerId", nullable = false)
    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Approval that = (Approval) o;

        if (approvalId != that.approvalId) return false;
        if (institutionId != that.institutionId) return false;
        if (managerId != that.managerId) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = approvalId;
        result = 31 * result + institutionId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + managerId;
        return result;
    }
}
