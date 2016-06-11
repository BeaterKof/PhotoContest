package com.photocontest.dao.generic;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 6:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GenericDAO<T, ID extends Serializable> {

    /**
     * Save an entity.
     * @param entity
     * @return the saved entity
     */

    T save(T entity);

    /**
     * Update the value of an entity with another.
     * @param entity
     * @return the updated entity
     */

    T update(T entity);

    /**
     * Delete an entity.
     * @param entity the entity to be deleted
     */

    void delete(T entity);

    /**
     * Find an entity by its ID.
     * @param id the ID of the entity to be found
     * @return
     */

    T findById(ID id);

    /**
     * Find all entities.
     * @return list of found entities.
     */

    List<T> findAll();

    /**
     * Flush current transaction.
     */

    void flush();
}
