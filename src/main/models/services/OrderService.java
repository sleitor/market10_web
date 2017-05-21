package main.models.services;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import main.models.DAO.OrderInterface;
import main.models.entity.EntOrder;
import main.models.entity.EntOrderProduct;
import main.models.pojo.Order;
import main.models.pojo.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Обработка заказа
 */
@Service
public class OrderService implements OrderServiceInterface {

    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private final OrderInterface orderDAO;

    @Autowired
    public OrderService(OrderInterface orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<Order> getAll() {

        mapperFactory.classMap(EntOrder.class, Order.class)
                .field("usersByUuidUser.uuid", "uuid_user")
                .field("usersByUuidUser.userName", "login_user")
                .byDefault()
                .register();

        List<EntOrder> entOrders = (List<EntOrder>) orderDAO.getAll();
        entOrders.sort(Comparator.comparing(EntOrder::getUuid));

        MapperFacade mapper = mapperFactory.getMapperFacade();
        List<Order> orders = new ArrayList<>();
        entOrders.forEach(entOrder -> orders.add(mapper.map(entOrder, Order.class)));


        return orders;
    }

    @Override
    public Order getByID(Long id) {
        mapperFactory.classMap(EntOrder.class, Order.class)
                .field("usersByUuidUser.uuid", "uuid_user")
                .field("usersByUuidUser.userName", "login_user")
                .byDefault()
                .register();

        EntOrder entOrder = orderDAO.getByID(id);

        MapperFacade mapper = mapperFactory.getMapperFacade();
        Order order = mapper.map(orderDAO.getByID(id), Order.class);

        mapperFactory.classMap(EntOrderProduct.class, OrderProduct.class)
                .field("productsByUuidProduct.name", "name_product")
                .field("productsByUuidProduct.uuid", "uuid_product")
                .byDefault().register();
        MapperFacade mapperProduct = mapperFactory.getMapperFacade();

        List<EntOrderProduct> entOrderProducts = entOrder.getOrderProductsByUuid();
        entOrderProducts.sort(Comparator.comparing(EntOrderProduct::getUuid));

        List<OrderProduct> orderProducts = new ArrayList<>();
        entOrderProducts.forEach(entOrderProduct -> orderProducts.add(mapperProduct.map(entOrderProduct, OrderProduct.class)));

        order.setOrderProducts(orderProducts);

        return order;
    }

    @Override
    public int create(Order order) {
        BoundMapperFacade<Order, EntOrder> mapperFacade = mapperFactory.getMapperFacade(Order.class, EntOrder.class);
        return orderDAO.create(mapperFacade.map(order));
    }

    @Override
    public void update(Order order) {
        BoundMapperFacade<Order, EntOrder> mapperFacade = mapperFactory.getMapperFacade(Order.class, EntOrder.class);
        orderDAO.update(mapperFacade.map(order));
    }

    @Override
    public void deleteByID(Long id) {
        orderDAO.deleteByID(id);
    }
}
