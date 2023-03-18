package action;

import entity.Approval;
import entity.Institution;
import entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.ApprovalService;
import service.InstitutionService;
import service.ManagerService;

import java.util.List;

/**
 * 11.经理审批;
 */
@Controller
public class ApprovalAction extends BaseAction{

    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private InstitutionService institutionService;

    public String approval(){
        List<Approval> list = approvalService.getListByState("W");
        request.getSession().setAttribute("approvals", list);
        return "approvals";
    }

    public String approve(){
        int approvalId = Integer.parseInt(request.getParameter("approvalId"));
        Approval approval = approvalService.getApproval(approvalId);
        Institution institution = institutionService.getInstitution(approval.getInstitutionId());
        institution.setApprovalState("P");
        approval.setState("P");

        institutionService.update(institution);
        approvalService.update(approval);
        return "approve";
    }
}
