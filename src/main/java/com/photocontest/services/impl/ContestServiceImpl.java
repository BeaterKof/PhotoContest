package com.photocontest.services.impl;

import com.photocontest.dao.ContestDAO;
import com.photocontest.dao.impl.ContestDAOImpl;
import com.photocontest.exceptions.ContestNotFoundException;
import com.photocontest.model.Contest;
import com.photocontest.services.ContestService;
import com.photocontest.utils.ContestComparator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    public ContestDAO getContestDAO() {
        return contestDAO;
    }

    public void setContestDAO(ContestDAO contestDAO) {
        this.contestDAO = contestDAO;
    }

    @Override
    public boolean exists(Contest contest) {
        return contestDAO.exists(contest.getContest_id());
    }

    @Override
    public void createContest(Contest contest) {
        contestDAO.save(contest);
    }

    @Override
    public void updateContest(Contest contest) throws ContestNotFoundException {
        if(!contestDAO.exists(contest.getContest_id())){
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

    @Override
    public List<Contest> getAllContests() {
        List<Contest> list = contestDAO.findAll();

        if(list == null){
            list = new ArrayList<Contest>();
        }

        return list;
    }

    @Override
    public List<Contest> getRunningContests() {
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        List<Contest> contestList = contestDAO.findRunningContests(sqlDate);
        if(contestList == null){
            contestList = new ArrayList<Contest>();
        }
        return contestList;
    }

    @Override
    public Contest getContestById(long id) throws ContestNotFoundException {
        Contest contest = contestDAO.findById(id);
        if(contest == null){
            throw new ContestNotFoundException(contest.getName());
        }
        return contest;
    }

    @Override
    public Contest getLastContest(){
        List<Contest> contestList = contestDAO.findAll();
        Collections.sort(contestList, new ContestComparator());
        Contest lastContest = null;
        if(contestList.size() > 0){
            lastContest = contestList.get(contestList.size()- 1);
        } else {
            lastContest = new Contest();
        }

        return lastContest;
    }
}
