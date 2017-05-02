package main.models.DAO;

import main.models.pojo.User;

/**
 * DAO интерфейс пользователей
 */
public interface UserInterface extends DAOInterface<Long, User> {

    User findUserByLoginAndPassword(String login, String password);

    User findUserByLogin(String login);
}
