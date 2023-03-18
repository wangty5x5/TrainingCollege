package service;

import entity.Order;

import java.util.List;

/**
 *
 */
public interface OrderService {

    /**
     * 添加新订单；
     * @param order
     */
    public void add(Order order);

    /**
     * 更新订单;
     * @param order
     */
    public void update(Order order);

    /**
     * 检查订单是否存在;
     * @param orderId
     * @return
     */
    public boolean isExist(int orderId);

    /**
     * 通过订单编号获得订单;
     * @param orderId
     * @return
     */
    public Order getOrder(int orderId);

    /**
     * 根据机构编号获取订单;
     * @param institutionId
     * @return
     */
    public List<Order> getListByInst(int institutionId);

    /**
     * 根据学员编号获得订单;
     * @param studentMail
     * @return
     */
    public List<Order> getListByStu(String studentMail);

    /**
     * 根据订单状态获取订单;
     * @param state
     * @return
     */
    public List<Order> getListByState(String state);

    /**
     * 获得全部订单;
     * @return
     */
    public List<Order> getAll();

    public int getId();

    public void delete(int orderId);
}
