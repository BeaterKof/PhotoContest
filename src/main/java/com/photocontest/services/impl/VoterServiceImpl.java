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

    @Override
    public void createVoter(Voter voter) throws VoterExistsException {
        if(voterDAO.exists(voter.getIp_address())){
            throw new VoterExistsException(voter.getIp_address());
        }
        voterDAO.save(voter);
    }

    @Override
    public boolean exists(String ip) {
        return voterDAO.exists(ip);
    }
}
