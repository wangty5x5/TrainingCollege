package action;

import entity.Institution;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.InstitutionService;
import service.OrderService;

import java.util.List;

/**
 * 9.机构查看订单;
 */
@Controller
public class InstitutionOrderAction extends BaseAction {

    @Autowired
    private OrderService orderService;

    public String orders(){
        Institution institution = (Institution) request.getSession().getAttribute("institution");
        List<Order> orders = orderService.getListByInst(institution.getId());
        request.getSession().setAttribute("instOrders", orders);
        return "orders";
    }
}
