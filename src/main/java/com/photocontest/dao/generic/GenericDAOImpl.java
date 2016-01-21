package com.photocontest.dao.generic;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private static final Logger logger = Logger.getLogger(GenericDAOImpl.class);
    protected Class<T> entityClass;
    
    @PersistenceContext
    protected EntityManager entityManager;

    public GenericDAOImpl(Class<T> persistentClass){
        this.entityClass = persistentClass;
    }

    public GenericDAOImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public T save(T entity){
        this.entityManager.persist(entity);
        logger.info("Entity saved!");
        return entity;
    }

    @Override
    public T update(T entity){
        this.entityManager.merge(entity);
        logger.info("Entity updated!");
        return entity;
    }

    @Override
    public void delete(T entity){
        entity = this.entityManager.merge(entity);
        this.entityManager.remove(entity);
        logger.info("Entity deleted");
    }

    @Override
    public T findById(ID id){
        return this.entityManager.find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll(){
        return this.entityManager
                .createQuery("select x from " + entityClass.getSimpleName() + " x" )
                .getResultList();
    }

    @Override
    public void flush(){
        this.entityManager.flush();
    }

}
