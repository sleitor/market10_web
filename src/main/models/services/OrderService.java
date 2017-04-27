package main.models.services;

import main.models.DAO.OrderDAO;
import main.models.DAO.OrderInterface;
import main.models.pojo.Order;

import java.util.Collection;
import java.util.UUID;

/**
 * Обработка заказа
 */
public class OrderService implements OrderServiceInterface {
    private static OrderInterface orderDAO = new OrderDAO();

    @Override
    public Collection<Order> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public Order getByID(Long id) {
        return orderDAO.getByID(id);
    }

    @Override
    public boolean create(Order order) {
         return orderDAO.create(order);
    }

    @Override
    public void update(Order order) {
        orderDAO.create(order);
    }

    @Override
    public void deleteByID(Long id) {
        orderDAO.deleteByID(id);
    }
}
