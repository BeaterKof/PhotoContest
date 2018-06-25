package com.photocontest.dao.impl;

import com.photocontest.dao.ContestDAO;
import com.photocontest.dao.generic.GenericDAOImpl;
import com.photocontest.model.Contest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class ContestDAOImpl extends GenericDAOImpl<Contest, Long> implements ContestDAO {

    /**
     * The Contest DAO constructor
     */

    public ContestDAOImpl(){
        super(Contest.class);
    }

    /**
     * Checks if an contest exists by its ID.
     *
     * @param id the ID of the contest
     * @return true if the contest exists in the database
     *  false if the contest does not exist in the database
     */

    @Override
    public boolean exists(long id) {
        Query query = this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.id = :id")
                .setParameter("id", id);

        int count = query.getResultList().size();
        return count > 0;
    }

    /**
     * Returns a list with all the running contests.
     * @param currentDate the current date
     * @return the list of current contests
     */

    @Override
    public List<Contest> findRunningContests(Date currentDate) {
        return this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.finish_date > :currentDate")
                .setParameter("currentDate",currentDate)
                .getResultList();
    }

    /**
     * Gets the Contest that have no winners
     * @return a list of all contests with no winner
     */

    @Override
    public List<Contest> findNoWinnerContests() {
        return this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.finish_date IS NULL")
                .getResultList();
    }

    @Override
    public List<Contest> getContestsByAdmin(long adminId) {
        return this.entityManager.createQuery("select u from " +
                this.entityClass.getSimpleName() + " u where u.admin.admin_id = :admin_id")
                .setParameter("admin_id", adminId).getResultList();
    }
}
