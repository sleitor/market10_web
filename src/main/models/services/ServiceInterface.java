package main.models.services;

import java.util.Collection;

/**
 * Общий интерфейс для сервисов
 */
public interface ServiceInterface<Type, Entity> {

    Collection<Entity> getAll();

    Entity getByID (Type id);

    int create (Entity entity);

    void update (Entity entity);

    void deleteByID (Type id);

}
