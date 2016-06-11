package com.photocontest.dao;

import com.photocontest.dao.generic.GenericDAO;
import com.photocontest.model.File;
import com.photocontest.model.Voter;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 2:21 AM
 * To change this template use File | Settings | File Templates.
 */
public interface VoterDAO extends GenericDAO<Voter, Long> {

    /**
     * Checks if the Voter exists in the database.
     *
     * @param ip the IP address of the Voter
     * @return true if the Voter exists in the database
     * @return false if the Voter does not exist in the database
     */

    boolean exists(String ip);
}
