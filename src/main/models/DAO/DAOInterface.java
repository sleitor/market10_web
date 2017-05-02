package main.models.DAO;

import java.util.Collection;

/**
 * Общий DAO интерфейс
 */
public interface DAOInterface<Type, Entity> {

    Collection<Entity> getAll();

    Entity getByID (Type id);

    int create (Entity entity);

    void update (Entity entity);

    void deleteByID (Type id);

}