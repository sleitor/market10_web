package main.models.DAO;

import main.models.entity.EntUser;

/**
 * DAO интерфейс пользователей
 */
public interface UserInterface extends DAOInterface<Long, EntUser> {

//    EntUser findUserByLoginAndPassword(String login, String password);

    EntUser findUserByLogin(String login);
}
