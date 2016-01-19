package com.photocontest.dao.impl;

import com.photocontest.dao.FileDAO;
import com.photocontest.dao.generic.GenericDAOImpl;
import com.photocontest.model.File;
import com.photocontest.model.User;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileDAOImpl extends GenericDAOImpl<File, Long> implements FileDAO {
    static final Logger logger = Logger.getLogger(FileDAOImpl.class);

    public FileDAOImpl(){
        super(File.class);
    }

    @Override
    public File loadByUserId(long id) {
        Assert.notNull(id);

        File file = null;

        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.id = :id")
                .setParameter("id", id);

        try{
            file = (File) query.getSingleResult();
        }catch(NoResultException e){
            logger.error(e.getMessage());
        }
        return file;
    }

}
