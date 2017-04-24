package main.models.DAO;

import main.models.pojo.OrderProduct;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

/**
 * Created by User on 20.04.2017.
 */
public class OrderProductDAO implements OrderProductInterface {
    @Override
    public HashSet<OrderProduct> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public OrderProduct getByID(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public boolean create(OrderProduct orderProduct) {
        throw new NotImplementedException();
    }

    @Override
    public void update(OrderProduct orderProduct) {

    }

    @Override
    public void deleteByID(Long id) {

    }
}
