package action;

import entity.Account;
import entity.ClassInfo;
import entity.Order;
import entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.AccountService;
import service.ClassInfoService;
import service.OrderService;
import service.StudentService;
import util.vipUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *3.学员信息;
 */
@Controller
public class StudentInfoAction extends BaseAction {

    @Autowired
    private StudentService studentService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClassInfoService classInfoService;

    public String studentInfo(){
        Student student = (Student) request.getSession().getAttribute("student");
        Account account = accountService.getAccount(student.getAccountId());
        List<Order> orders = orderService.getListByStu(student.getMail());
        double consume = 0.0;
        int[] datas = new int[3];
        for(Order order:orders){
            consume = consume + order.getPrice();
            if(order.getState().equals("C"))
                datas[0]++;
            else if(order.getState().equals("SE")||order.getState().equals("NE"))
                datas[1]++;
            else if(order.getState().equals("SW")||order.getState().equals("NW"))
                datas[2]++;
        }

        request.getSession().setAttribute("datas", datas);

        request.getSession().setAttribute("student", student);
        request.getSession().setAttribute("money", account.getMoney());
        request.getSession().setAttribute("ordersNum", orders.size());
        request.getSession().setAttribute("consume", consume);
        return "info";
    }

    public String modifyInfo(){
        Student student = (Student) request.getSession().getAttribute("student");
        String name = request.getParameter("name");
        String pastPsw = request.getParameter("pastPsw");
        String neoPsw = request.getParameter("neoPsw");
        if(pastPsw!=""){
            student.setPassword(neoPsw);
        }
        if(!name.equals(student.getName())){

            List<Order> orders = orderService.getListByStu(student.getMail());
            for(Order order:orders){
                order.setStudentName(name);
                orderService.update(order);
            }
            List<ClassInfo> infoList = classInfoService.getListByName(student.getName());
            for(ClassInfo info:infoList){
                info.setStudentName(name);
                classInfoService.update(info);
            }

            student.setName(name);
        }

        studentService.update(student);
        request.getSession().setAttribute("student", student);
        return "modify";
    }

    public String cancelVip(){
        Student student = (Student) request.getSession().getAttribute("student");
        student.setViPrank("N");
        studentService.update(student);
        request.getSession().setAttribute("student",student);
        return "cancel";
    }

    public String convertCredit(){
        Student student = (Student) request.getSession().getAttribute("student");
        String accountId = student.getAccountId();
        Account account = accountService.getAccount(accountId);
        double balance = account.getMoney();
        int point = Integer.parseInt((request.getParameter("point")));
        if(point>student.getCredit()){
            return "fail";
        }
        balance = balance + (double) point/10;
        account.setMoney(balance);
        student.setCredit(student.getCredit()-point);
        student.setViPrank(new vipUtil().getRank(student.getCredit()));

        studentService.update(student);
        accountService.update(account);
        request.getSession().setAttribute("student", student);
        return "convert";
    }

    public String charge(){
        Student student = (Student) request.getSession().getAttribute("student");
        Account account = accountService.getAccount(student.getAccountId());
        double money = Double.parseDouble(request.getParameter("money"));
        account.setMoney(account.getMoney()+money);
        accountService.update(account);
        return "charge";
    }

}
