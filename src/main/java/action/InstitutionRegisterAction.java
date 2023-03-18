package action;

import entity.Approval;
import entity.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.ApprovalService;
import service.InstitutionService;
import util.LocalUtil;

/**
 *6.机构注册;
 */
@Controller
public class InstitutionRegisterAction extends BaseAction {

    private LocalUtil util;

    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private ApprovalService approvalService;

    public String instRegister(){
        String name = request.getParameter("instName");
        String password = request.getParameter("instPassword");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        int teachers = Integer.parseInt(request.getParameter("teachers"));

        util = new LocalUtil();
        int code = util.getCode(province, city);
        int institutionId = institutionService.getId(code);

        Institution institution = new Institution();
        institution.setId(institutionId);
        institution.setPassword(password);
        institution.setName(name);
        institution.setProvince(province);
        institution.setCity(city);
        institution.setAddress(address);
        institution.setTeachers(teachers);
        institution.setIncome(0.0);
        institution.setApprovalState("W");

        Approval approval = new Approval();
        approval.setApprovalId(approvalService.getId());
        approval.setInstitutionId(institutionId);
        approval.setType("注册");
        approval.setState("W");
        approval.setManagerId(707);

        institutionService.add(institution);
        approvalService.add(approval);
        this.addActionError("注册成功,等待申请通过!");
        return SUCCESS;
    }
}
