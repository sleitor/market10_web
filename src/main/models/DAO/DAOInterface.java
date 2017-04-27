package main.models.DAO;

import java.util.Collection;
import java.util.HashSet;

/**
 * Общий DAO интерфейс
 */
public interface DAOInterface<Type, Entity> {

    Collection<Entity> getAll();

    Entity getByID (Type id);

    boolean create (Entity entity);

    void update (Entity entity);

    void deleteByID (Type id);

}