package main.models.DAO;

import main.models.ConnectionPool;
import main.models.pojo.Product;
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
    @Override
    public Set<Product> getAll(){
        Set<Product> products = new HashSet<>();
        ;

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
//                product.setUuid(UUID.fromString(resultSet.getString(1)));
//                product.setName(resultSet.getString(2));
//                product.setDescription(resultSet.getString(3));
//                product.setQuantity(resultSet.getInt(4));
//                product.setCost(resultSet.getFloat(5));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

            if (resultSet.next()){
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
            e.printStackTrace();
        }

        throw new NotImplementedException();
    }

    @Override
    public boolean create(Product product) {
        throw new NotImplementedException();
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void deleteByID(Long id) {

    }
}
