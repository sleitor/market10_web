package main.models.services;

import main.models.pojo.User;

/**
 * Интерфейс обработки пользователей
 */
public interface UserServiceInterface extends ServiceInterface<Long, User> {

    User findUserByLoginAndPassword(String login, String password);

}
