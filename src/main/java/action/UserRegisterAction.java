package action;

import entity.Account;
import entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.AccountService;
import service.StudentService;
import util.MailUtil;
import util.UrlUtil;
import util.UuidUtil;

/**
 *2.学员注册;
 */
@Controller
public class UserRegisterAction extends BaseAction {

    @Autowired
    private StudentService studentService;
    @Autowired
    private AccountService accountService;

    public String register(){
        String mail = request.getParameter("mail");
        if(studentService.isExist(mail)){
            this.addActionError("此邮箱已注册!");
            return "fail";
        }
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String accountId = request.getParameter("accountId");

        Student student = new Student();
        student.setMail(mail);
        student.setPassword(password);
        student.setName(name);
        student.setViPrank("0");
        student.setCredit(0);
        student.setAccountId(accountId);
        student.setUcode(UuidUtil.getUUID());
        MailUtil.sendMail(mail, student.getUcode());
        studentService.register(student);

        Account account = new Account();
        account.setAccountId(accountId);
        account.setMoney(0.0);
        accountService.add(account);

        String url = UrlUtil.getUrl(mail);
        if(url==null){
            request.getSession().setAttribute("url", "#");
        }
        else {
            request.getSession().setAttribute("url", url);
        }
        request.getSession().setAttribute("mail", student.getMail());
        return "register";
    }

    public String activate(){
        String ucode = request.getParameter("ucode");
        String mail = (String) request.getSession().getAttribute("mail");
        Student student = studentService.getStudent(mail);
        if(student.getUcode().equals(ucode)){
            student.setViPrank("E");
        }
        return "activate";
    }
}
