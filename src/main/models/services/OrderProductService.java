package main.models.services;

import main.models.DAO.OrderProductDAO;
import main.models.DAO.OrderProductInterface;
import main.models.pojo.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Обработка товаров для заказа
 */
@Service
public class OrderProductService implements OrderProductServiceInterface {

    private ProductServiceInterface productService = new ProductService();
    private OrderProductInterface orderProductDAO = new OrderProductDAO();

    @Autowired
    public OrderProductService(ProductServiceInterface productService, OrderProductInterface orderProductDAO) {
        this.productService = productService;
        this.orderProductDAO = orderProductDAO;
    }

    @Override
    public Collection<OrderProduct> getAll() {
        return orderProductDAO.getAll();
    }

    @Override
    public OrderProduct getByID(Long id) {
        OrderProduct orderProduct = orderProductDAO.getByID(id);
        orderProduct.setName_product(
                productService.getByID(
                        orderProduct
                                .getUuid_product())
                        .getName());
        return orderProduct;
    }

    @Override
    public int create(OrderProduct orderProduct) {
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
        ArrayList<OrderProduct> orderProducts = orderProductDAO.getAllByOrder(id);
        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.setName_product(
                    productService.getByID(orderProduct.getUuid_product()).getName());
        }
        return orderProducts;
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
