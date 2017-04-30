package main.models.services;

import main.models.pojo.User;

/**
 * Интерфейс обработки пользователей
 */
public interface UserServiceInterface extends ServiceInterface<Long, User> {

    User findUserByLoginAndPassword(String login, String password);

    /**
     * Производит поиск пользователя по id.  Меняет права прользователя не противоположные Возвращает результат выполнения операции.
     *
     * @param id
     * @return boolean
     */
    boolean modifyAccess(Long id);
}
