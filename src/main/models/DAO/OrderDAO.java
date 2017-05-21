package main.models.DAO;

import main.controllers.JPAUtil;
import main.models.entity.EntOrder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@EnableTransactionManagement
public class OrderDAO implements OrderInterface {

    private Logger logger = Logger.getLogger(OrderDAO.class);

    @PersistenceContext
    @Qualifier("entityManagerFactory")
    private EntityManager manager;

    @Override
    public List<EntOrder> getAll() {

        return JPAUtil.getInstance().createEntityManager().createQuery("select e from EntOrder e where e.deleted=false ", EntOrder.class).getResultList();
    }

    @Override
    public EntOrder getByID(Long id) {
        return manager.find(EntOrder.class, id);

    }

    @Override
    @Transactional
    public int create(EntOrder entOrder) {
        entOrder.setUuid(null);
        manager.persist(entOrder);
        return (entOrder.getUuid() != null ? Math.toIntExact(entOrder.getUuid()) : 0);
    }

    @Override
    @Transactional
    public void update(EntOrder entOrder) {
        manager.merge(entOrder);
    }

    @Override
    @Transactional
    public void deleteByID(Long id) {
        EntOrder entOrder = getByID(id);
        entOrder.setDeleted(true);
        update(entOrder);
//        manager.remove(entOrder);

    }
}
