package main.models.services;

import main.models.pojo.User;

/**
 * Created by User on 20.04.2017.
 */
public interface UserServiceInterface extends ServiceInterface<Long, User> {

    User findUserByLoginAndPassword(String login, String password);

}
