package com.photocontest.dao.impl;

import com.photocontest.dao.FileDAO;
import com.photocontest.dao.generic.GenericDAOImpl;
import com.photocontest.model.File;
import com.photocontest.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
@Repository
public class FileDAOImpl extends GenericDAOImpl<File, Long> implements FileDAO {

    /**
     * The logger instance
     */
    static final Logger logger = Logger.getLogger(FileDAOImpl.class);

    /**
     * The Contest DAO constructor
     */
    public FileDAOImpl(){
        super(File.class);
    }

    /**
     * Finds an File in the database by its ID.
     *
     * @param id the ID of the File
     * @return the File if it exists in the database
     * @return null if the File does not exist in the database
     */

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

    /**
     * Checks if an File exists in the database.
     *
     * @param id the File id
     * @return true if the File exists in the database.
     * @return false if the File does not exist in the database.
     */

    @Override
    public boolean exists(long id) {
        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.id = :id")
                .setParameter("id", id);

        int count = query.getResultList().size();
        return count > 0;
    }

}
