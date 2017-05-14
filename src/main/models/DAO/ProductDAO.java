package main.models.DAO;

import main.models.ConnectionPool;
import main.models.entity.EntProduct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@EnableTransactionManagement
public class ProductDAO implements ProductInterface {

    private Logger logger = Logger.getLogger(ProductDAO.class);


    @PersistenceContext
    @Qualifier("entityManagerFactory")
    private EntityManager manager;

//    private EntityManager em = JPAUtil.getInstance().createEntityManager();

    @Override
    public List<EntProduct> getAll() {

        return manager.createQuery("from EntProduct ", EntProduct.class).getResultList();

    }

    @Override
    public EntProduct getByID(Long id) {
        return manager.find(EntProduct.class, id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int create(EntProduct product) {

        logger.info("product " + product.getUuid());
        logger.info("product " + product.getName());
        logger.info("product " + product.getDescription());
        logger.info("product " + product.getQuantity());
        logger.info("product " + product.getCost());
        manager.persist(product);

        logger.info("product uuid" + product.getUuid());
        return (int) (product.getUuid() != null ? Math.toIntExact(product.getUuid()) : 0);

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
            statement.setDouble(4, product.getCost());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.debug("Ошибка обновления товара");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByID(Long id) {

        EntProduct entProduct = manager.find(EntProduct.class, id);
        logger.info("Contains: " + manager.contains(entProduct));
        manager.remove(entProduct);

    }
}
