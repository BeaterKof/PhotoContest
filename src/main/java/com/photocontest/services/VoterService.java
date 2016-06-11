package com.photocontest.services;

import com.photocontest.exceptions.VoterExistsException;
import com.photocontest.model.Voter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/7/16
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */
public interface VoterService {

    /**
     * Creates a Voter in the database.
     *
     * @param voter the Voter to be created
     * @throws VoterExistsException if the Voter does not exist in the database.
     */

    void createVoter(Voter voter) throws VoterExistsException;

    /**
     * Checks if the Voter exists in the database.
     *
     * @param ip the IP address of the Voter
     * @return true if the Voter exists in the database
     * @return false if the Voter does not exist in the database
     */

    boolean exists(String ip);
}
