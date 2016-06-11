package com.photocontest.dao;

import com.photocontest.dao.generic.GenericDAO;
import com.photocontest.model.Contest;

import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ContestDAO extends GenericDAO<Contest, Long> {

    /**
     * Checks if an contest exists by its ID.
     *
     * @param id the ID of the contest
     * @return true if the contest exists in the database
     *  false if the contest does not exist in the database
     */

    boolean exists(long id);

    /**
     * Returns a list with all the running contests.
     * @param currentDate the current date
     * @return the list of current contests
     */

    List<Contest> findRunningContests(Date currentDate);

    /**
     * Gets the contests that have no winners
     * @return a list with all the contests without winners
     */

    public List<Contest> findNoWinnerContests();
}
