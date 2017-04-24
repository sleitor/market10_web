package main.models.DAO;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by User on 21.04.2017.
 */
public interface DAOInterface<Type, Entity> {

    Collection<Entity> getAll();

    Entity getByID (Type id);

    boolean create (Entity entity);

    void update (Entity entity);

    void deleteByID (Type id);

}