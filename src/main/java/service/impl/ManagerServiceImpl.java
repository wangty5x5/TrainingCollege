package service.impl;

import dao.ManagerDao;
import entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ManagerService;

import java.util.List;

/**
 *
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Override
    public void register(Manager manager) {
        managerDao.add(manager);
    }

    @Override
    public boolean isExist(String managerId) {
        return managerDao.isExist(managerId);
    }

    @Override
    public Manager getManager(String managerId) {
        return managerDao.getManager(managerId);
    }

    @Override
    public boolean checkPassword(String managerId, String password) {
        return managerDao.checkPassword(managerId, password);
    }

    @Override
    public void update(Manager manager) {
        managerDao.update(manager);
    }

    @Override
    public List<Manager> getAll() {
        return managerDao.getAll();
    }
}
