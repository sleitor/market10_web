package main.models.services;

import main.models.DAO.UserInterface;
import main.models.pojo.User;

import java.util.Collection;

/**
 * Обработка пользователей
 */


public class UserService implements UserServiceInterface{

    private UserInterface userDAO;

    public void setUserDAO(UserInterface userDAO) {
        this.userDAO = userDAO;
    }

    private UserInterface getUserDAO() {
        return userDAO;
    }

    @Override
    public Collection<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User getByID(Long id) {
        return userDAO.getByID(id);
    }

    @Override
    public boolean create(User user) {
        return userDAO.create(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void deleteByID(Long id) {
        userDAO.deleteByID(id);
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        return userDAO.findUserByLoginAndPassword(login, password);
    }

    @Override
    public boolean modifyAccess(Long id) {

        User user = userDAO.getByID(id);
        user.setRole(!user.isRole());
        userDAO.update(user);

        return false;
    }
}
