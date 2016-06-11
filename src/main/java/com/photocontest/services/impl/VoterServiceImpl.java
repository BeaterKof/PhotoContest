package com.photocontest.services.impl;

import com.photocontest.dao.VoterDAO;
import com.photocontest.exceptions.VoterExistsException;
import com.photocontest.model.Voter;
import com.photocontest.services.VoterService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 2:19 AM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class VoterServiceImpl implements VoterService {

    @Autowired
    private VoterDAO voterDAO;

    public VoterDAO getVoterDAO() {
        return voterDAO;
    }

    public void setVoterDAO(VoterDAO voterDAO) {
        this.voterDAO = voterDAO;
    }

    /**
     * Creates a Voter in the database.
     *
     * @param voter the Voter to be created
     * @throws VoterExistsException if the Voter does not exist in the database.
     */

    @Override
    public void createVoter(Voter voter) throws VoterExistsException {
        if(voterDAO.exists(voter.getIp_address())){
            throw new VoterExistsException(voter.getIp_address());
        }
        voterDAO.save(voter);
    }

    /**
     * Checks if the Voter exists in the database.
     *
     * @param ip the IP address of the Voter
     * @return true if the Voter exists in the database
     * @return false if the Voter does not exist in the database
     */

    //check if voter exists for a file
    @Override
    public boolean exists(String ip) {
        return voterDAO.exists(ip);
    }

}
