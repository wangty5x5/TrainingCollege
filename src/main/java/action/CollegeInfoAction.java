package action;

import entity.Institution;
import entity.Manager;
import entity.Order;
import entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.InstitutionService;
import service.ManagerService;
import service.OrderService;
import service.StudentService;
import util.instStaUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 12.经理统计信息;
 */
@Controller
public class CollegeInfoAction extends BaseAction {

    @Autowired
    private StudentService studentService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private OrderService orderService;

    public String managerSta(){
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        List<Institution> institutions = institutionService.getAll();
        List<instStaUtil> list = new ArrayList<instStaUtil>();
        for(Institution institution:institutions){
            instStaUtil i = new instStaUtil();
            i.setInstitutionId(institution.getId());
            i.setInstitutionName(institution.getName());
            i.setOrders(orderService.getListByInst(institution.getId()).size());
            i.setIncome(institution.getIncome());
            list.add(i);
        }

        List<Student> students = studentService.getAll();
        int[] data = new int[4];
        for(Student student:students){
            List<Order> orders = orderService.getListByStu(student.getMail());
            double consume = 0.0;
            for(Order order:orders)
                consume = consume + order.getPrice();
            if(consume<2000)
                data[0]++;
            else if(consume<5000)
                data[1]++;
            else if(consume<10000)
                data[2]++;
            else
                data[3]++;
        }

        request.getSession().setAttribute("data", data);
        request.getSession().setAttribute("manager", manager);
        request.getSession().setAttribute("users", studentService.getAll().size());
        request.getSession().setAttribute("profit", manager.getProfit());
        request.getSession().setAttribute("instSta", list);
        return "managerSta";
    }
}
