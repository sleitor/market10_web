package main.models.services;

import main.models.DAO.UserDAO;
import main.models.DAO.UserInterface;
import main.models.pojo.User;

import java.util.Collection;

/**
 * Created by User on 20.04.2017.
 */
public class UserService implements UserServiceInterface{
    private static UserInterface userDAO = new UserDAO();

    @Override
    public Collection<User> getAll() {
        return null;
    }

    @Override
    public User getByID(Long id) {
        return null;
    }

    @Override
    public boolean create(User user) {
        return userDAO.create(user);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteByID(Long id) {

    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        return userDAO.findUserByLoginAndPassword(login, password);
    }
}
