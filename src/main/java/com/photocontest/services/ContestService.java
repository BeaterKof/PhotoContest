package com.photocontest.services;

import com.photocontest.exceptions.ContestNotFoundException;
import com.photocontest.model.Contest;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/7/16
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ContestService {

    boolean exists(Contest contest);
    void createContest(Contest contest);
    void updateContest(Contest contest) throws ContestNotFoundException;
    void deleteContest(Contest contest) throws ContestNotFoundException;
    List<Contest> getAllContests();
    List<Contest> getRunningContests();
    Contest getContestById(long id) throws ContestNotFoundException;
    Contest getLastContest();
}
