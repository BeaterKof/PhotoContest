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

    boolean exists(long id);
    List<Contest> findRunningContests(Date currentDate);
}
