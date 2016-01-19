package com.photocontest.dao.impl;

import com.photocontest.dao.ContestDAO;
import com.photocontest.dao.generic.GenericDAOImpl;
import com.photocontest.model.Contest;

import javax.persistence.Query;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContestDAOImpl extends GenericDAOImpl<Contest, Long> implements ContestDAO {

    public ContestDAOImpl(){
        super(Contest.class);
    }

    @Override
    public boolean exists(long id) {
        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.id = :id")
                .setParameter("id", id);

        int count = query.getResultList().size();
        return count > 0;
    }

}
