package main.models.DAO;

import main.models.ConnectionPool;
import main.models.pojo.Order;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

@Component
public class OrderDAO implements OrderInterface {

    private Logger logger = Logger.getLogger(OrderDAO.class);

    @Override
    public HashSet<Order> getAll() {

        HashSet<Order> orders = new HashSet<>();
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Order order = new Order(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getDate(3),
                        resultSet.getFloat(4),
                        resultSet.getString(5)
                );
                orders.add(order);
            }

        } catch (SQLException e) {
            logger.debug("Ошибка получения заказов");
        }

        return orders;
    }

    @Override
    public Order getByID(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public boolean create(Order order) {


        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO  orders ( `uuid_user` ,  `date` ,  `cost` ) VALUES (?,?,?)");
        ) {

            statement.setLong(1, order.getUuid_user());
            statement.setDate(2, order.getDate());
            statement.setFloat(2, order.getCost());
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.debug("Ошибка создания нового заказа");
        }

        return false;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void deleteByID(Long id) {

    }
}
