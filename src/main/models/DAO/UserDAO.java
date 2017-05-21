package main.models.DAO;

import main.models.entity.EntUser;
import main.models.entity.EntUserRoles;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement
public class UserDAO implements UserInterface {

    private Logger logger = Logger.getLogger(UserDAO.class);

    @PersistenceContext
    @Qualifier("entityManagerFactory")
    private EntityManager manager;

    @Override
    public List<EntUser> getAll() {

        return manager.createQuery("select e from EntUser e where e.deleted=false ", EntUser.class).getResultList();
    }

    @Override
    public EntUser getByID(Long id) {

        return manager.find(EntUser.class, id);
    }

    @Override
    @Transactional
    public int create(EntUser entUser) {

        EntUserRoles userRoles = new EntUserRoles(entUser.getUserName(), "ROLE_USER");
        ArrayList<EntUserRoles> userRolesArrayList = new ArrayList<>();
        userRolesArrayList.add(userRoles);
        entUser.setUserRolesByUserName(userRolesArrayList);
        entUser.setEnabled(1);
        manager.persist(entUser);


        return (int) (entUser.getUuid() != null ? Math.toIntExact(entUser.getUuid()) : 0);
    }

    @Override
    @Transactional
    public void update(EntUser user) {

        manager.merge(user);
    }

    @Override
    @Transactional
    public void deleteByID(Long id) {

        EntUser entUser = getByID(id);
        entUser.setDeleted(true);
        update(entUser);
    }

    @Override
    public EntUser findUserByLogin(String login) {
        Query query = manager.createQuery("select e from EntUser e where e.userName = :login");
        query.setParameter("login", login);
        return (EntUser) query.getSingleResult();
    }
}
