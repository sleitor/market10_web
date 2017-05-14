package main.models.services;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import main.models.DAO.UserInterface;
import main.models.entity.EntUser;
import main.models.pojo.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Обработка пользователей
 */


public class UserService implements UserServiceInterface{

    private static MapperFactory factory = new DefaultMapperFactory.Builder().build();
    Logger logger = Logger.getLogger(UserService.class);
    private UserInterface userDAO;

    private UserInterface getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserInterface userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Collection<User> getAll() {
        List<EntUser> entUsers = (List<EntUser>) userDAO.getAll();
        entUsers.sort(Comparator.comparing(EntUser::getUuid));

        List<User> users = new ArrayList<>();
        BoundMapperFacade<EntUser, User> mapper = factory.getMapperFacade(EntUser.class, User.class);
        entUsers.forEach(entUser -> users.add(mapper.map(entUser)));
        return users;
    }

    @Override
    public User getByID(Long id) {

        BoundMapperFacade<EntUser, User> mapper = factory.getMapperFacade(EntUser.class, User.class);
        return mapper.map(userDAO.getByID(id));
    }

    @Override
    public int create(User user) {
        BoundMapperFacade<User, EntUser> mapper = factory.getMapperFacade(User.class, EntUser.class);
        return userDAO.create(mapper.map(user));
    }

    @Override
    public void update(User user) {
        BoundMapperFacade<User, EntUser> mapper = factory.getMapperFacade(User.class, EntUser.class);
        userDAO.update(mapper.map(user));
    }

    @Override
    public void deleteByID(Long id) {
        userDAO.deleteByID(id);
    }

    //    @Override
//    public User findUserByLoginAndPassword(String login, String password) {
//        return userDAO.findUserByLoginAndPassword(login, password);
//    }
//
    @Override
    public boolean modifyAccess(Long id) {

//        User user = userDAO.getByID(id);
//        user.setRole(!user.isRole());
//        userDAO.update(user);
//
        return false;
    }

    @Override
    public User findUserbyLogin(String login){
        BoundMapperFacade<EntUser, User> mapper = factory.getMapperFacade(EntUser.class, User.class);
        return mapper.map(userDAO.findUserByLogin(login));
    }

}
