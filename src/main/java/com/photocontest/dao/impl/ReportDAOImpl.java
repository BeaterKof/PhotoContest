package com.photocontest.dao.impl;

import com.photocontest.dao.ContestDAO;
import com.photocontest.dao.ReportDAO;
import com.photocontest.dao.generic.GenericDAOImpl;
import com.photocontest.model.Contest;
import com.photocontest.model.Report;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 2:27 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ReportDAOImpl extends GenericDAOImpl<Report, Long> implements ReportDAO {

    public ReportDAOImpl(){
        super(Report.class);
    }

    @Override
    public boolean exists(long id) {
        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.id = :id")
                .setParameter("id", id);

        int count = query.getResultList().size();
        return count > 0;
    }

    @Override
    public void deleteById(long id) {

    }
}
