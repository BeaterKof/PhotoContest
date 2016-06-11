package com.photocontest.services;

import com.photocontest.exceptions.ContestNotFoundException;
import com.photocontest.model.Contest;
import com.photocontest.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/7/16
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ContestService {

    /**
     * Checks if an Contest exists in the database.
     *
     * @param contest the Contest checked
     * @return true if the Contest exists
     * @return false if the Contest does not exist
     */

    boolean exists(Contest contest);

    /**
     * Creates an contest in the database.
     *
     * @param contest the Contest to be created
     */

    void createContest(Contest contest);

    /**
     * Updates a contest.
     *
     * @param contest the new Contest value
     * @throws ContestNotFoundException if the Contest does not exist in the database
     */

    void updateContest(Contest contest) throws ContestNotFoundException;

    /**
     * Delets an Contest from the database.
     *
     * @param contest the Contest to be deleted
     * @throws ContestNotFoundException if contest does not exist in the database
     */

    void deleteContest(Contest contest) throws ContestNotFoundException;

    /**
     * Gets all the Contests.
     *
     * @return a list with all Contests
     */

    List<Contest> getAllContests();

    /**
     * Gets all the currently running Contests
     *
     * @return a list with all the running Contests
     */

    List<Contest> getRunningContests();

    /**
     * Gets an Contest by ID.
     *
     * @param id the ID of the Contest
     * @return the Contest searched
     * @throws ContestNotFoundException if the Contest does not exist in the database
     */

    Contest getContestById(long id) throws ContestNotFoundException;

    /**
     * Gets the latest Contest.
     *
     * @return the latest Contest
     */

    Contest getLastContest();

    /**
     * Get all the Contest without a winner.
     *
     * @return the list of all the Contests without a winner
     */

    List<Contest> getNoWinnerContests();

    /**
     * Get a Contest winner ID
     * @param contest the Contest from which the winner ID is returned
     * @return Contest winner ID
     */

    User getContestWinnerId(Contest contest);
}
