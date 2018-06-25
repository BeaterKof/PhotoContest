package com.photocontest.dao.impl;

import com.photocontest.dao.UserDAO;
import com.photocontest.dao.generic.GenericDAOImpl;
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
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

    /**
     * The logger instance
     */
    static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    /**
     * The User DAO constructor
     */
    public UserDAOImpl(){
        super(User.class);
    }

    /**
     * Loads a User by its email address.
     *
     * @param email the email address of the User.
     * @return the User if the email address exists in the database
     * @return null if the email address does not exist in the database
     */

    public User loadByEmail(String email){
        Assert.notNull(email);

        User user = null;

        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.email = :email")
                .setParameter("email", email);

        try{
            user = (User) query.getSingleResult();
        }catch(NoResultException e){
            logger.error(e.getMessage());
        }
        return user;
    }

    /**
     * Checks if an email address is available in the database.
     *
     * @param email the searched email address
     * @return true if the email address is available
     * @return false if the email address is not available
     */

    public boolean checkAvailable(String email){
        Assert.notNull(email);

        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.email = :email")
                .setParameter("email", email);

        int count = query.getResultList().size();
        return count < 1;
    }

    /**
     * Checks if an email address exists in the database.
     *
     * @param email the email address
     * @return true if the email address exists
     * @return false if the email address does not exist
     */

    @Override
    public boolean exists(String email) {
        return !checkAvailable(email);
    }

}
