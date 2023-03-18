package service.impl;

import dao.OrderDao;
import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.OrderService;

import java.util.List;

/**
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void add(Order order) {
        orderDao.add(order);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public boolean isExist(int orderId) {
        return orderDao.isExist(orderId);
    }

    @Override
    public Order getOrder(int orderId) {
        return orderDao.getOrder(orderId);
    }

    @Override
    public List<Order> getListByInst(int institutionId) {
        return orderDao.getListByInst(institutionId);
    }

    @Override
    public List<Order> getListByStu(String studentMail) {
        return orderDao.getListByStu(studentMail);
    }

    @Override
    public List<Order> getListByState(String state) {
        return orderDao.getListByState(state);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public int getId(){
        return orderDao.getId();
    }

    @Override
    public void delete(int orderId){ orderDao.delete(orderId); }
}
