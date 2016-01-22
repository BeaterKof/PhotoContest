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

    void createVoter(Voter voter) throws VoterExistsException;
    boolean exists(String ip);
}
