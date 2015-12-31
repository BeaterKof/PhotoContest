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

    T save(T entity);
    T update(T entity);
    void delete(T entity);
    T findById(ID id);
    List<T> findAll();
    void flush();
}
