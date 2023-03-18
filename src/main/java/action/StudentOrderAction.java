package action;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.*;
import util.dateUtil;
import util.discountUtil;
import util.vipUtil;

import java.util.Date;
import java.util.List;

/**
 * 5.学员查看订单;
 */
@Controller
public class StudentOrderAction extends BaseAction{

    @Autowired
    private StudentService studentService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private CourseService courseService;

    public String getOrders(){
        Student student = (Student) request.getSession().getAttribute("student");
        List<Order> orders = orderService.getListByStu(student.getMail());
        request.getSession().setAttribute("type", "all");
        request.getSession().setAttribute("student", student);
        request.getSession().setAttribute("orders", orders);
        return "orders";
    }

    public String changeOrder(){
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = orderService.getOrder(orderId);
        Student student = (Student) request.getSession().getAttribute("student");
        Account account = accountService.getAccount(student.getAccountId());
        Manager manager = managerService.getManager("707");
        if("UP".equals(order.getState())){
            double price = new discountUtil().getDiscount(student.getViPrank())*order.getPrice();
            if(account.getMoney()>=price){
                order.setState("NW");
                account.setMoney(account.getMoney()-price);
                accountService.update(account);
                int credit = (int) Math.floor(price/10);
                student.setCredit(student.getCredit()+credit);
                student.setViPrank(new vipUtil().getRank(student.getCredit()));
                studentService.update(student);
                manager.setProfit(price+manager.getProfit());
                managerService.update(manager);
            }else {
                return "fail";
            }
        }
        else {
            order.setState("C");
            Date today = new Date();
            Date start = new Date(courseService.getCourse(order.getCourseId()).getStartDate().getTime());
            int distance = new dateUtil().distance(today, start);
            if(distance>=14){
                account.setMoney(account.getMoney()+order.getPrice());
                accountService.update(account);
                int credit = (int) Math.floor(order.getPrice()/10);
                student.setCredit(student.getCredit()-credit);
                student.setViPrank(new vipUtil().getRank(student.getCredit()));
                studentService.update(student);
                manager.setProfit(manager.getProfit()-order.getPrice());
                managerService.update(manager);
            }
            else if(distance<14&&distance>0){
                double money = order.getPrice()/2;
                account.setMoney(account.getMoney()+money);
                accountService.update(account);
                int credit = (int) Math.floor(order.getPrice()/10);
                student.setCredit(student.getCredit()-credit);
                student.setViPrank(new vipUtil().getRank(student.getCredit()));
                studentService.update(student);
                manager.setProfit(manager.getProfit()-money);
                managerService.update(manager);
            }
        }
        orderService.update(order);

        return "change";
    }

    public String searchOrder(){
        String type = request.getParameter("type");
        List<Order> orders;
        if("all".equals(type)){
            orders = orderService.getAll();
        }
        else if("done".equals(type)){
            orders = orderService.getListByState("E");
        }
        else if("wait".equals(type)){
            orders = orderService.getListByState("W");
        }
        else if("notPay".equals(type)){
            orders = orderService.getListByState("UP");
        }
        else
            orders = orderService.getListByState("C");
        request.getSession().setAttribute("orders", orders);
        request.getSession().setAttribute("type", type);
        return "search";
    }

}
