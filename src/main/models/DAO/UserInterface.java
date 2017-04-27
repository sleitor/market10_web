package main.models.DAO;

import main.models.pojo.User;

import java.util.UUID;

/**
 * DAO интерфейс пользователей
 */
public interface UserInterface extends DAOInterface<Long, User> {

    User findUserByLoginAndPassword(String login, String password);
}
