package service.impl;

import dao.ApprovalDao;
import entity.Approval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ApprovalService;

import java.util.List;

/**
 *
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    private ApprovalDao approvalDao;

    @Override
    public void add(Approval approval) {
        approvalDao.add(approval);
    }

    @Override
    public void update(Approval approval) {
        approvalDao.update(approval);
    }

    @Override
    public boolean isExist(int approvalId) {
        return approvalDao.isExist(approvalId);
    }

    @Override
    public Approval getApproval(int approvalId) {
        return approvalDao.getApproval(approvalId);
    }

    @Override
    public void delete(int approvalId) {
        approvalDao.delete(approvalId);
    }

    @Override
    public List<Approval> getListByInst(int institutionId) {
        return approvalDao.getListByInst(institutionId);
    }

    @Override
    public List<Approval> getListByMana(int managerId) {
        return approvalDao.getListByMana(managerId);
    }

    @Override
    public List<Approval> getListByType(String type) {
        return approvalDao.getListByType(type);
    }

    @Override
    public List<Approval> getListByState(String state) {
        return approvalDao.getListByState(state);
    }

    @Override
    public List<Approval> getAll() {
        return approvalDao.getAll();
    }

    @Override
    public int getId() {
        return approvalDao.getId();
    }
}
