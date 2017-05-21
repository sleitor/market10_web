package main.models.services;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import main.models.DAO.OrderProductDAO;
import main.models.DAO.OrderProductInterface;
import main.models.entity.EntOrderProduct;
import main.models.pojo.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Обработка товаров для заказа
 */
@Service
public class OrderProductService implements OrderProductServiceInterface {

    private static MapperFactory factory = new DefaultMapperFactory.Builder().build();
    private ProductServiceInterface productService = new ProductService();
    private OrderProductInterface orderProductDAO = new OrderProductDAO();

    @Autowired
    public OrderProductService(ProductServiceInterface productService, OrderProductInterface orderProductDAO) {
        this.productService = productService;
        this.orderProductDAO = orderProductDAO;
    }

    @Override
    public Collection<OrderProduct> getAll() {
        List<EntOrderProduct> entOrderProducts = (List<EntOrderProduct>) orderProductDAO.getAll();
        entOrderProducts.sort(Comparator.comparing(EntOrderProduct::getUuid));
        BoundMapperFacade<EntOrderProduct, OrderProduct> mapperFacade = factory.getMapperFacade(EntOrderProduct.class, OrderProduct.class);

        List<OrderProduct> orderProducts = new ArrayList<>();
        entOrderProducts.forEach(entOrderProduct -> orderProducts.add(mapperFacade.map(entOrderProduct)));
        return orderProducts;
    }

    @Override
    public OrderProduct getByID(Long id) {

        BoundMapperFacade<EntOrderProduct, OrderProduct> mapperFacade = factory.getMapperFacade(EntOrderProduct.class, OrderProduct.class);
        OrderProduct orderProduct = mapperFacade.map(orderProductDAO.getByID(id));
        orderProduct.setName_product(productService.getByID(orderProduct.getUuid_product()).getName());
        return orderProduct;
    }

    @Override
    public int create(OrderProduct orderProduct) {
        factory.classMap(OrderProduct.class, EntOrderProduct.class)
                .field("uuid_order", "ordersByUuidOrder.uuid")
                .field("uuid_product", "productsByUuidProduct.uuid")
                .byDefault()
                .register();


        MapperFacade mapperFacade = factory.getMapperFacade();
        return orderProductDAO.create(mapperFacade.map(orderProduct, EntOrderProduct.class));
    }

    @Override
    public void update(OrderProduct orderProduct) {
        BoundMapperFacade<OrderProduct, EntOrderProduct> mapperFacade = factory.getMapperFacade(OrderProduct.class, EntOrderProduct.class);
        orderProductDAO.update(mapperFacade.map(orderProduct));
    }

    @Override
    public void deleteByID(Long id) {
        orderProductDAO.deleteByID(id);
    }

    @Override
    public ArrayList<OrderProduct> getAllByOrder(Long id) {
        List<EntOrderProduct> entOrderProducts = orderProductDAO.getAllByOrder(id);
        entOrderProducts.sort(Comparator.comparing(EntOrderProduct::getUuid));

        BoundMapperFacade<EntOrderProduct, OrderProduct> mapperFacade = factory.getMapperFacade(EntOrderProduct.class, OrderProduct.class);

        ArrayList<OrderProduct> orderProducts = new ArrayList<>();
        entOrderProducts.forEach(entOrderProduct -> orderProducts.add(mapperFacade.map(entOrderProduct)));

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
