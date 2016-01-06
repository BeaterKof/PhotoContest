package com.photocontest.dao.impl;

import com.photocontest.dao.UserDAO;
import com.photocontest.dao.generic.GenericDAOImpl;
import com.photocontest.model.User;
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
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {

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
            ////
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
}
