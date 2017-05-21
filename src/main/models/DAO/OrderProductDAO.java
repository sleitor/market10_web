package main.models.DAO;

import main.models.entity.EntOrderProduct;
import main.models.pojo.OrderProduct;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableTransactionManagement
public class OrderProductDAO implements OrderProductInterface {
    private Logger logger = Logger.getLogger(OrderProduct.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EntOrderProduct> getAll() {
        return entityManager.createQuery("select e from EntOrderProduct e", EntOrderProduct.class).getResultList();
    }

    @Override
    public EntOrderProduct getByID(Long id) {
        return entityManager.find(EntOrderProduct.class, id);
    }

    @Override
    @Transactional
    public int create(EntOrderProduct entOrderProduct) {
        entityManager.persist(entOrderProduct);
        return (entOrderProduct.getUuid() != null ? Math.toIntExact(entOrderProduct.getUuid()) : 0);
    }

    @Override
    @Transactional
    public void update(EntOrderProduct entOrderProduct) {
        entityManager.merge(entOrderProduct);
    }

    @Override
    @Transactional
    public void deleteByID(Long id) {
        entityManager.remove(getByID(id));
    }

    @Override
    public List<EntOrderProduct> getAllByOrder(Long id) {
        Query query = entityManager.createQuery("select e from EntOrderProduct e where e.ordersByUuidOrder.uuid=:id");
        query.setParameter("id", id);
        return (ArrayList<EntOrderProduct>) query.getResultList();
    }

    @Override
    public List<EntOrderProduct> getAllByProduct(Long id) {
        Query query = entityManager.createQuery("select e from EntOrderProduct e where e.productsByUuidProduct.uuid=:id");
        query.setParameter("id", id);
        return (ArrayList<EntOrderProduct>) query.getResultList();
    }

    @Override
    @Transactional
    public void deleteByOrderID(Long id) {

        List<EntOrderProduct> entOrderProducts = getAllByOrder(id);
        entOrderProducts.forEach(entOrderProduct -> entityManager.remove(entOrderProduct));
    }

    @Override
    @Transactional
    public void deleteByProductID(Long id) {

        List<EntOrderProduct> entOrderProducts = getAllByProduct(id);
        entOrderProducts.forEach(entOrderProduct -> entityManager.remove(entOrderProduct));
    }
}
