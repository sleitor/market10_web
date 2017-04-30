package main.models.DAO;

import main.models.pojo.OrderProduct;

import java.util.ArrayList;

/**
 * DAO интерфейс продуктов в заказе
 */
public interface OrderProductInterface extends DAOInterface<Long, OrderProduct> {

    ArrayList<OrderProduct> getAllByOrder(Long id);

    void deleteByOrderID(Long id);

    void deleteByProductID(Long id);
}
