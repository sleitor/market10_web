package main.models.services;

import main.models.DAO.OrderProductDAO;
import main.models.DAO.OrderProductInterface;
import main.models.pojo.OrderProduct;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Обработка товаров для заказа
 */
public class OrderProductService implements OrderProductInterface {

    private static OrderProductInterface orderProductDAO = new OrderProductDAO();

    @Override
    public Collection<OrderProduct> getAll() {
        return orderProductDAO.getAll();
    }

    @Override
    public OrderProduct getByID(Long id) {
        return orderProductDAO.getByID(id);
    }

    @Override
    public boolean create(OrderProduct orderProduct) {
        return orderProductDAO.create(orderProduct);
    }

    @Override
    public void update(OrderProduct orderProduct) {
        orderProductDAO.update(orderProduct);
    }

    @Override
    public void deleteByID(Long id) {
        orderProductDAO.deleteByID(id);
    }

    @Override
    public ArrayList<OrderProduct> getAllByOrder(Long id) {
        return orderProductDAO.getAllByOrder(id);
    }

    @Override
    public void deleteByOrderID(Long id) {
        orderProductDAO.deleteByOrderID(id);
    }

    @Override
    public void deleteByProductID(Long id) {
        orderProductDAO.deleteByProductID(id);
    }
}
