package action;

import entity.Approval;
import entity.Course;
import entity.Institution;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.ApprovalService;
import service.CourseService;
import service.InstitutionService;
import service.OrderService;

import java.util.List;

/**
 *7.机构信息;
 */
@Controller
public class InstitutionInfoAction extends BaseAction {

    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ApprovalService approvalService;
    @Autowired
    private CourseService courseService;

    public String institutionInfo(){
        Institution institution = (Institution) request.getSession().getAttribute("institution");
        List<Order> orders = orderService.getListByInst(institution.getId());
        request.getSession().setAttribute("institution", institution);
        request.getSession().setAttribute("orderNum", orders.size());
        return "instInfo";
    }

    public String modifyInfo(){
        Institution institution = (Institution) request.getSession().getAttribute("institution");
        String pastPsw = request.getParameter("pastPsw");
        String neoPsw = request.getParameter("neoPsw");
        if(pastPsw!=""){
            institution.setPassword(neoPsw);
        }
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        if(province!=""){
            institution.setProvince(province);
            institution.setCity(city);
        }

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int teachers = Integer.valueOf(request.getParameter("teachers"));

        if(!name.equals(institution.getName())){
            List<Course> courses = courseService.getListById(institution.getId());
            for(Course course:courses){
                course.setInstitutionName(name);
                courseService.update(course);
            }
            institution.setName(name);
        }

        institution.setAddress(address);
        institution.setTeachers(teachers);
        institution.setApprovalState("W");
        institutionService.update(institution);

        //生成申请;
        Approval approval = new Approval();
        approval.setApprovalId(approvalService.getId());
        approval.setInstitutionId(institution.getId());
        approval.setType("修改信息");
        approval.setState("W");
        approval.setManagerId(707);
        approvalService.add(approval);

        this.addActionError("修改成功,等待申请通过!");
        return "modify";
    }
}
