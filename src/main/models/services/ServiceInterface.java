package main.models.services;

import main.models.DAO.DAOInterface;

import java.util.Collection;

/**
 * Created by User on 21.04.2017.
 */
public interface ServiceInterface<Type, Entity> {

    Collection<Entity> getAll();

    Entity getByID (Type id);

    boolean create (Entity entity);

    void update (Entity entity);

    void deleteByID (Type id);

}
