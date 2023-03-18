package dao;

import entity.Manager;

import java.util.List;

/**
 *
 */
public interface ManagerDao {

    /**
     * 添加经理;
     * @param manager
     */
    public void add(Manager manager);

    /**
     * 检查经理是否存在;
     * @param managerId
     * @return
     */
    public boolean isExist(String managerId);

    /**
     * 根据编号查找经理;
     * @param managerId
     * @return
     */
    public Manager getManager(String managerId);

    /**
     * 检查密码是否正确;
     * @param managerId
     * @param password
     * @return
     */
    public boolean checkPassword(String managerId, String password);

    /**
     * 更新经理信息;
     * @param manager
     */
    public void update(Manager manager);

    /**
     * 获得所有经理;
     * @return
     */
    public List<Manager> getAll();
}
