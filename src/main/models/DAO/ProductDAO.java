package main.models.DAO;

import main.models.ConnectionPool;
import main.models.pojo.Product;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Component
public class ProductDAO implements ProductInterface {

    private Logger logger = Logger.getLogger(ProductDAO.class);

    @Override
    public Set<Product> getAll() {
        Set<Product> products = new HashSet<>();

        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement("SELECT * FROM products");
        ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getFloat(5));

                products.add(product);
            }
        } catch (SQLException e) {
            logger.debug("Ошибка получения списка продуктов");
        }
        return products;
    }

    @Override
    public Product getByID(Long id) {

        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE uuid=?")
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Product product = null;

            if (resultSet.next()) {
                product = new Product(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getFloat(5)
                );
            }

            return product;

        } catch (SQLException e) {
            logger.debug("Ошибка получения пролукта");
        }

        throw new NotImplementedException();
    }

    @Override
    public boolean create(Product product) {

        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO products (name, description, quantity, cost) VALUES (?,?,?,?)");
        ) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getQuantity());
            statement.setFloat(4, product.getCost());
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.debug("Ошибка создания продукта");
        }

        return true;
    }

    @Override
    public void update(Product product) {

        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE products SET name=?, description=?, quantity=?, cost=? WHERE uuid=?");
        ) {

            statement.setLong(5, product.getUuid());
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getQuantity());
            statement.setFloat(4, product.getCost());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug("Ошибка обновления товара");
        }
    }

    @Override
    public void deleteByID(Long id) {


        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE uuid=?");
        ) {

            statement.setLong(1,id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.debug("Ошибка удаления товара");
        }

    }
}
