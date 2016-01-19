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

    boolean exists(long id);
}
