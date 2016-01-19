package com.photocontest.services.impl;

import com.photocontest.dao.VoterDAO;
import com.photocontest.model.Voter;
import com.photocontest.services.VoterService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 2:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class VoterServiceImpl implements VoterService {

    @Autowired
    private VoterDAO voterDAO;

    @Override
    public void addVoter(Voter voter) {
        voterDAO.save(voter);
    }
}