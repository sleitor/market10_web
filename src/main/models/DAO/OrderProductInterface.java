package main.models.DAO;

import main.models.entity.EntOrderProduct;

import java.util.List;

/**
 * DAO интерфейс продуктов в заказе
 */
public interface OrderProductInterface extends DAOInterface<Long, EntOrderProduct> {

    List<EntOrderProduct> getAllByOrder(Long id);

    List<EntOrderProduct> getAllByProduct(Long id);

    void deleteByOrderID(Long id);

    void deleteByProductID(Long id);
}
