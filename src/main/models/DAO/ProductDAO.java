package main.models.DAO;

import main.models.entity.EntProduct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

        return manager.createQuery("select e from EntProduct e", EntProduct.class).getResultList();

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
        return (product.getUuid() != null ? Math.toIntExact(product.getUuid()) : 0);

    }

    @Override
    @Transactional
    public void update(EntProduct product) {

        manager.merge(product);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteByID(Long id) {

        EntProduct entProduct = manager.find(EntProduct.class, id);
        logger.info("Contains: " + manager.contains(entProduct));
        manager.remove(entProduct);

    }
}
