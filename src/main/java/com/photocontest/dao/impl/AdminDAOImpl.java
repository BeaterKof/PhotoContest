package com.photocontest.dao.impl;

import com.photocontest.dao.AdminDAO;
import com.photocontest.dao.generic.GenericDAOImpl;
import com.photocontest.model.Admin;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminDAOImpl extends GenericDAOImpl<Admin, Long> implements AdminDAO {
    static final Logger logger = Logger.getLogger(FileDAOImpl.class);

    public AdminDAOImpl(){
        super(Admin.class);
    }

    @Override
    public boolean checkAvailable(String email) {
        Assert.notNull(email);

        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.email = :email")
                .setParameter("email", email);

        int count = query.getResultList().size();
        return count < 1;
    }

    @Override
    public Admin getAdminByEmail(String email) {
        Assert.notNull(email);
        Admin admin = null;

        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.email = :email")
                .setParameter("email", email);

        try{
            admin = (Admin)query.getSingleResult();
        }catch(NoResultException e){
            logger.error(e.getMessage());
        }
        return admin;
    }
}
