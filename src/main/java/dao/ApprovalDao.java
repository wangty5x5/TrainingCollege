package dao;

import entity.Approval;

import java.util.List;

/**
 *
 */
public interface ApprovalDao {

    /**
     * 添加新申请;
     * @param approval
     */
    public void add(Approval approval);

    /**
     * 更新申请;
     * @param approval
     */
    public void update(Approval approval);

    /**
     * 检查申请是否存在;
     * @param approvalId
     * @return
     */
    public boolean isExist(int approvalId);

    /**
     * 根据id获得申请;
     * @param approvalId
     * @return
     */
    public Approval getApproval(int approvalId);

    /**
     * 撤销申请;
     * @param approvalId
     */
    public void delete(int approvalId);

    /**
     * 根据机构id获得申请列表;
     * @param institutionId
     * @return
     */
    public List<Approval> getListByInst(int institutionId);

    /**
     * 根据经理id获取申请列表;
     * @param managerId
     * @return
     */
    public List<Approval> getListByMana(int managerId);

    /**
     * 根据种类获得申请列表;
     * @param type
     * @return
     */
    public List<Approval> getListByType(String type);

    /**
     * 根据状态获得申请列表;
     * @param state
     * @return
     */
    public List<Approval> getListByState(String state);

    /**
     *
     * @return
     */
    public List<Approval> getAll();

    public int getId();
}
