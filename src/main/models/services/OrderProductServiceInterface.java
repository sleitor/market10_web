package main.models.services;

import main.models.pojo.OrderProduct;

import java.util.ArrayList;

/**
 * Интерфейс обработки продуктов в заказе
 */
public interface OrderProductServiceInterface extends ServiceInterface<Long, OrderProduct> {

    ArrayList<OrderProduct> getAllByOrder(Long id);

    void deleteByOrderID(Long id);

    void deleteByProductID(Long id);

}
