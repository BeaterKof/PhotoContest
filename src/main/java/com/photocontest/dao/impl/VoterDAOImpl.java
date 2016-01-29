package com.photocontest.dao.impl;

import com.photocontest.dao.VoterDAO;
import com.photocontest.dao.generic.GenericDAOImpl;
import com.photocontest.model.User;
import com.photocontest.model.Voter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 2:22 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class VoterDAOImpl extends GenericDAOImpl<Voter, Long> implements VoterDAO {

    public VoterDAOImpl(){
        super(Voter.class);
    }

    @Override
    public boolean exists(String ip) {
        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.ip_address = :ip")
                .setParameter("ip", ip);

        int count = query.getResultList().size();
        return count > 0;
    }
}
