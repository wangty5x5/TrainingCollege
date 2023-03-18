package service;

import entity.Manager;

import java.util.List;

/**
 *
 */
public interface ManagerService {

    /**
     * 注册新经理;
     * @param manager
     */
    public void register(Manager manager);

    /**
     *
     * @param managerId
     * @return
     */
    public boolean isExist(String managerId);

    /**
     *
     * @param managerId
     * @return
     */
    public Manager getManager(String managerId);

    /**
     * 检验密码是否正确;
     * @param managerId
     * @param password
     * @return
     */
    public boolean checkPassword(String managerId, String password);

    /**
     *
     * @param manager
     */
    public void update(Manager manager);

    /**
     *
     * @return
     */
    public List<Manager> getAll();
}
