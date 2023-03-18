package action;
import entity.*;
import entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.*;
import util.dateUtil;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Controller
public class LoginAction extends BaseAction {

    @Autowired
    private StudentService studentService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ClassService classService;

    @Override
    public String execute(){
        return SUCCESS;
    }

    public String studentLogin(){
        String mail = request.getParameter("email");
        String password = request.getParameter("stuPwd");
        if(!studentService.isExist(mail)){
            this.addActionError("账号不存在");
            return "fail";
        }
        if(!studentService.checkPassword(mail, password)){
            this.addActionError("密码不正确");
            return "fail";
        }
        Student student = studentService.getStudent(mail);
        if(student.getViPrank()=="W"){
            this.addActionError("邮箱未激活");
            return "fail";
        }
        request.getSession().setAttribute("student", student);
        this.setClass();
        return "student";
    }

    public String institutionLogin(){
        int institutionId = Integer.parseInt(request.getParameter("instId"));
        String password = request.getParameter("instPwd");
        if(!institutionService.isExist(institutionId)){
            this.addActionError("机构不存在");
            return "fail";
        }
        if(!institutionService.checkPassword(institutionId,password)){
            this.addActionError("密码不正确");
            return "fail";
        }
        Institution institution = institutionService.getInstitution(institutionId);
        if("W".equals(institution.getApprovalState())){
            this.addActionError("机构审核暂未通过");
            return "fail";
        }
        request.getSession().setAttribute("institution", institution);
        this.setClass();
        return "institution";
    }

    public String managerLogin(){
        String managerId = request.getParameter("managerId");
        String password = request.getParameter("managerPwd");
        if(!managerService.isExist(managerId)){
            this.addActionError("经理不存在");
            return "fail";
        }
        if(!managerService.checkPassword(managerId, password)){
            this.addActionError("密码不正确");
            return "fail";
        }
        Manager manager = managerService.getManager(managerId);
        request.getSession().setAttribute("manager", manager);
        this.setClass();
        return "manager";
    }

    private void setClass(){
        List<Course> courses = courseService.getAll();
        Date today = new Date();
        for(Course course:courses){
            Date start = new Date(course.getStartDate().getTime());
            if(new dateUtil().distance(today, start)<=14){
                int courseId = course.getCourseId();
                List<Class> classes = classService.getListById(courseId);
                List<ClassInfo> list = classInfoService.getListById(courseId);
                for(ClassInfo cl:list){
                    if(cl.getClassId()==0){
                        for(int i=1;i<=classes.size();i++){
                            if(classes.get(i).getEmptyNum()!=0){
                                cl.setClassId(courseId*10+i);
                                break;
                            }
                        }
                        classInfoService.update(cl);
                    }
                }
            }
        }
    }
}
