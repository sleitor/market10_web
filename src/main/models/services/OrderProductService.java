package main.models.services;

import main.models.DAO.OrderProductDAO;
import main.models.DAO.OrderProductInterface;
import main.models.pojo.OrderProduct;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

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
}
