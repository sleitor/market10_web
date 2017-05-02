package main.models.DAO;

import main.models.ConnectionPool;
import main.models.pojo.OrderProduct;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

@Component
public class OrderProductDAO implements OrderProductInterface {
    private Logger logger = Logger.getLogger(OrderProduct.class);

    @Override
    public HashSet<OrderProduct> getAll() {
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM orderProducts");
        ) {
            ResultSet resultSet = statement.executeQuery();
            HashSet<OrderProduct> orderProducts = new HashSet<>();
            while (resultSet.next()) {
                OrderProduct orderProduct = new OrderProduct(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        resultSet.getInt(4),
                        resultSet.getFloat(5)
                );
                orderProducts.add(orderProduct);
            }

            return orderProducts;

        } catch (SQLException e) {
            logger.debug("Ошибка получения списка товаров для всех заказов");
        }

        throw new NotImplementedException();
    }

    @Override
    public OrderProduct getByID(Long id) {
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM orderProducts WHERE uuid=?");
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new OrderProduct(
                    resultSet.getLong(1),
                    resultSet.getLong(2),
                    resultSet.getLong(3),
                    resultSet.getInt(4),
                    resultSet.getFloat(5)
            );

        } catch (SQLException e) {
            logger.debug("Ошибка получения списка товаров для заказа" + id);
        }

        throw new NotImplementedException();
    }

    @Override
    public int create(OrderProduct orderProduct) {
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO orderProducts (uuid_order, uuid_product, `count`, cost) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setLong(1, orderProduct.getUuid_order());
            statement.setLong(2, orderProduct.getUuid_product());
            statement.setInt(3, orderProduct.getCount());
            statement.setFloat(4, orderProduct.getCost());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            logger.debug("Ошибка добавления товара к заказу");
        }

        return 0;
    }

    @Override
    public void update(OrderProduct orderProduct) {
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE orderProducts SET  `count`=?, cost=? WHERE uuid=?")
        ) {

            statement.setInt(1, orderProduct.getCount());
            statement.setFloat(2, orderProduct.getCost());
            statement.setLong(3, orderProduct.getUuid());
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.debug("Ошибка обновления заказанного продукта" + orderProduct.getUuid());
        }

    }

    @Override
    public void deleteByID(Long id) {
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM orderProducts WHERE uuid=?")
        ) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.debug("Ощибка удаления товара из заказа" + id);
        }

    }

    @Override
    public ArrayList<OrderProduct> getAllByOrder(Long id) {
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM orderProducts WHERE uuid_order=?");
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<OrderProduct> orderProducts = new ArrayList<>();
            while (resultSet.next()) {
                OrderProduct orderProduct = new OrderProduct(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3),
                        resultSet.getInt(4),
                        resultSet.getFloat(5)
                );
                orderProducts.add(orderProduct);
            }

            return orderProducts;

        } catch (SQLException e) {
            logger.debug("Ошибка получения списка товаров для заказа" + id);
        }
        throw new NotImplementedException();
    }

    @Override
    public void deleteByOrderID(Long id) {
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM orderProducts WHERE uuid_order=?")
        ) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.debug("Ошибка удаления продуктов по заказу " + id);
        }
    }

    @Override
    public void deleteByProductID(Long id) {
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM orderProducts WHERE uuid_product=?")
        ) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.debug("Ошибка удаления продуктов по продукту " + id);
        }
    }
}
