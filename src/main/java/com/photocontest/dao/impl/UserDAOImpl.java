package com.photocontest.dao.impl;

import com.photocontest.dao.UserDAO;
import com.photocontest.dao.generic.GenericDAOImpl;
import com.photocontest.model.User;
import org.apache.log4j.Logger;
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
@Transactional
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {
    static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    public UserDAOImpl(){
        super(User.class);
    }

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

    public boolean checkAvailable(String email){
        Assert.notNull(email);

        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.email = :email")
                .setParameter("email", email);

        int count = query.getResultList().size();
        return count < 1;
    }

    @Override
    public boolean exists(String email) {
        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.email = :email")
                .setParameter("email", email);

        int count = query.getResultList().size();
        return count > 0;
    }
}
