package main.models.services;

import main.models.DAO.OrderDAO;
import main.models.DAO.OrderInterface;
import main.models.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Обработка заказа
 */
@Service
public class OrderService implements OrderServiceInterface {
    private static OrderInterface orderDAO = new OrderDAO();

    private final OrderProductServiceInterface orderProductService;

    @Autowired
    public OrderService(OrderProductServiceInterface orderProductService) {
        this.orderProductService = orderProductService;
    }

    @Override
    public Collection<Order> getAll() {
        return orderDAO.getAll();
    }

    @Override
    public Order getByID(Long id) {
        Order order = orderDAO.getByID(id);
        order.setOrderProducts(orderProductService.getAllByOrder(id));
        return order;
    }

    @Override
    public int create(Order order) {

        return orderDAO.create(order);
    }

    @Override
    public void update(Order order) {
        orderDAO.update(order);
    }

    @Override
    public void deleteByID(Long id) {
        orderDAO.deleteByID(id);
    }
}
