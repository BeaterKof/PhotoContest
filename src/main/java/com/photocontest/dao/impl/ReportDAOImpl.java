package com.photocontest.dao.impl;

import com.photocontest.dao.ContestDAO;
import com.photocontest.dao.ReportDAO;
import com.photocontest.dao.generic.GenericDAOImpl;
import com.photocontest.model.Contest;
import com.photocontest.model.Report;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 2:27 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ReportDAOImpl extends GenericDAOImpl<Report, Long> implements ReportDAO {

    /**
     * The Report DAO constructor
     */
    public ReportDAOImpl(){
        super(Report.class);
    }

    /**
     * Checks if an report exists.
     *
     * @param id the ID of the report
     * @return true if the Report exists in the database.
     * @return false if the Report does not exist in the database.
     */

    @Override
    public boolean exists(long id) {
        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.id = :id")
                .setParameter("id", id);

        int count = query.getResultList().size();
        return count > 0;
    }

    @Override
    public List<Report> getReportsByFileId(long fileId) {
        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.file_id = :file_id")
                .setParameter("file_id", fileId);

        ArrayList<Report> reportList = (ArrayList<Report>)query.getResultList();
        return reportList;
    }

    @Override
    public List<Report> getReportsByContestId(long contestId) {
        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.contest_id = :contest_id")
                .setParameter("contest_id", contestId);

        ArrayList<Report> reportList = (ArrayList<Report>)query.getResultList();
        return reportList;
    }
}
