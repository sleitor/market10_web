package main.models.DAO;

import main.models.pojo.User;

import java.util.UUID;

/**
 * Created by User on 20.04.2017.
 */
public interface UserInterface extends DAOInterface<Long, User> {

    User findUserByLoginAndPassword(String login, String password);
}
