package action;

import entity.Institution;
import entity.Manager;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.InstitutionService;
import service.ManagerService;
import service.OrderService;
import util.managerOrderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *13.订单管理;结算;
 */
@Controller
public class ManagerOrderAction extends BaseAction{

    @Autowired
    private OrderService orderService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private InstitutionService institutionService;

    public String managerOrder(){
        List<Order> undoList = orderService.getListByState("N");
        List<managerOrderUtil> undoUtils = this.getUtils(undoList);

        List<Order> doneList = orderService.getListByState("S");
        List<managerOrderUtil> doneUtils = this.getUtils(doneList);

        request.getSession().setAttribute("undoUtils", undoUtils);
        request.getSession().setAttribute("doneUtils", doneUtils);
        return "orders";
    }

    public String settle(){
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = orderService.getOrder(orderId);
        if("NE".equals(order.getState())){
            order.setState("SE");
        }
        else if("NW".equals(order.getState())){
            order.setState("SW");
        }
        Institution institution = institutionService.getInstitution(order.getInstitutionId());
        institution.setIncome(institution.getIncome()+order.getPrice()/2);
        manager.setProfit(manager.getProfit()-order.getPrice()/2);

        orderService.update(order);
        institutionService.update(institution);
        managerService.update(manager);
        request.getSession().setAttribute("manager", manager);
        return "settle";
    }

    private List<managerOrderUtil> getUtils(List<Order> list){
        List<managerOrderUtil> utils = new ArrayList<managerOrderUtil>();
        for(Order order:list){
            managerOrderUtil util = new managerOrderUtil();
            util.setOrderId(order.getOrderId());
            util.setInstitutionId(order.getInstitutionId());
            util.setInstitutionName(institutionService.getInstitution(order.getInstitutionId()).getName());
            util.setStudentMail(order.getStudentMail());
            util.setPrice(order.getPrice());
            util.setState(order.getState());
            utils.add(util);
        }
        return utils;
    }
}
