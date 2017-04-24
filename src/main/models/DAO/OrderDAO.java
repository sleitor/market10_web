package main.models.DAO;

import main.models.pojo.Order;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

/**
 * Created by User on 20.04.2017.
 */
public class OrderDAO implements OrderInterface {
    @Override
    public HashSet<Order> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public Order getByID(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public boolean create(Order order) {
        throw new NotImplementedException();
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void deleteByID(Long id) {

    }
}
