package com.photocontest.services.impl;

import com.photocontest.dao.ContestDAO;
import com.photocontest.dao.impl.ContestDAOImpl;
import com.photocontest.exceptions.ContestNotFoundException;
import com.photocontest.model.Contest;
import com.photocontest.services.ContestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 1:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class ContestServiceImpl implements ContestService {
    static final Logger logger = Logger.getLogger(ContestServiceImpl.class);

    @Autowired
    private ContestDAO contestDAO;

    @Override
    public boolean exists(Contest contest) {
        return contestDAO.exists(contest.getContest_id());
    }

    @Override
    public Contest createContest(Contest contest) {
        contestDAO.save(contest);
        return contest;
    }

    @Override
    public void updateContest(Contest contest) throws ContestNotFoundException {
        if(contestDAO.exists(contest.getContest_id())){
            throw new ContestNotFoundException(contest.getName());
        }
        contestDAO.update(contest);
    }

    @Override
    public void deleteContest(Contest contest) throws ContestNotFoundException {
        if(contestDAO.exists(contest.getContest_id())){
            throw new ContestNotFoundException(contest.getName());
        }
        contestDAO.delete(contest);
    }
}