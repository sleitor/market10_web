package main.models.DAO;

import main.models.ConnectionPool;
import main.models.entity.EntProduct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.util.List;

@Repository
public class ProductDAO implements ProductInterface {

    private Logger logger = Logger.getLogger(ProductDAO.class);

    private EntityManagerFactory emf =
            Persistence.
                    createEntityManagerFactory("mnf-pu");

    @PersistenceContext
    @Qualifier("entityManagerFactory")
    private EntityManager manager;


    @Override
    public List<EntProduct> getAll() {

        return emf.createEntityManager().
                createQuery("from EntProduct").getResultList();


//        try (
//                Connection connection = ConnectionPool.getInstance().getConnection();
//                PreparedStatement preparedStatement =
//                        connection.prepareStatement("SELECT * FROM products");
//        ) {
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                Product product = new Product(
//                        resultSet.getLong(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getInt(4),
//                        resultSet.getFloat(5));
//
//                products.add(product);
//            }
//        } catch (SQLException e) {
//            logger.debug("Ошибка получения списка продуктов");
//        }
//        return products;
    }

    @Override
    public EntProduct getByID(Long id) {

//        try (
//                Connection connection = ConnectionPool.getInstance().getConnection();
//                PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE uuid=?")
//        ) {
//            statement.setLong(1, id);
//            ResultSet resultSet = statement.executeQuery();
//            Product product = null;
//
//            if (resultSet.next()) {
//                product = new Product(
//                        resultSet.getLong(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getInt(4),
//                        resultSet.getFloat(5)
//                );
//            }
//
//            return product;
//
//        } catch (SQLException e) {
//            logger.debug("Ошибка получения пролукта");
//        }

        throw new NotImplementedException();
    }

    @Override
    public int create(EntProduct product) {

        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO products (name, description, quantity, cost) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getQuantity());
            statement.setFloat(4, product.getCost());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            logger.debug("Ошибка создания продукта");
        }

        return 0;
    }

    @Override
    public void update(EntProduct product) {

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
