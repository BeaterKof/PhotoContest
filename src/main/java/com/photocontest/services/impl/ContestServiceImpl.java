package com.photocontest.services.impl;

import com.photocontest.dao.ContestDAO;
import com.photocontest.exceptions.ContestNotFoundException;
import com.photocontest.model.Contest;
import com.photocontest.model.File;
import com.photocontest.model.User;
import com.photocontest.services.ContestService;
import com.photocontest.utils.ContestComparator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 1:00 AM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
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

    /**
     * Checks if an Contest exists in the database.
     *
     * @param contest the Contest checked
     * @return true if the Contest exists
     * @return false if the Contest does not exist
     */

    @Override
    public boolean exists(Contest contest) {
        return contestDAO.exists(contest.getContest_id());
    }

    /**
     * Creates an contest in the database.
     *
     * @param contest the Contest to be created
     */


    @Override
    public void createContest(Contest contest) throws ContestNotFoundException {
        if(contestDAO.exists(contest.getContest_id())){
            throw new ContestNotFoundException(contest.getName());
        } else {
            contestDAO.save(contest);
        }
    }

    /**
     * Updates a contest.
     *
     * @param contest the new Contest value
     * @throws ContestNotFoundException if the Contest does not exist in the database
     */

    @Override
    public void updateContest(Contest contest) throws ContestNotFoundException {
        if(!contestDAO.exists(contest.getContest_id())){
            throw new ContestNotFoundException(contest.getName());
        } else {
            contestDAO.update(contest);
        }
    }

    /**
     * Delets an Contest from the database.
     *
     * @param contest the Contest to be deleted
     * @throws ContestNotFoundException if contest does not exist in the database
     */

    @Override
    public void deleteContest(Contest contest) throws ContestNotFoundException {
        if(!contestDAO.exists(contest.getContest_id())){
            throw new ContestNotFoundException(contest.getName());
        } else {
            contestDAO.delete(contest);
        }
    }

    /**
     * Gets all the Contests.
     *
     * @return a list with all Contests
     */

    @Override
    public List<Contest> getAllContests() {
        List<Contest> list = contestDAO.findAll();

        if(list == null){
            list = new ArrayList<Contest>();
        }

        return list;
    }

    /**
     * Gets all the currently running Contests
     *
     * @return a list with all the running Contests
     */

    @Override
    public List<Contest> getRunningContests() {
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        List<Contest> contestList = contestDAO.findRunningContests(sqlDate);
        if(contestList == null){
            contestList = new ArrayList<Contest>();
        }
        return contestList;
    }

    /**
     * Gets an Contest by ID.
     *
     * @param id the ID of the Contest
     * @return the Contest searched
     * @throws ContestNotFoundException if the Contest does not exist in the database
     */

    @Override
    public Contest getContestById(long id) throws ContestNotFoundException {
        Contest contest = contestDAO.findById(id);
        if(contest == null){
            throw new ContestNotFoundException("id: " + id);
        }
        return contest;
    }

    /**
     * Gets the latest Contest.
     *
     * @return the latest Contest
     */

    @Override
    public Contest getLastContest(){
        List<Contest> contestList = contestDAO.findAll();
        Collections.sort(contestList, new ContestComparator());
        Contest lastContest;
        if(contestList.size() > 0){
            lastContest = contestList.get(contestList.size()- 1);
        } else {
            lastContest = new Contest();
        }

        return lastContest;
    }

    /**
     * Get all the Contest without a winner.
     *
     * @return the list of all the Contests without a winner
     */


    @Override
    public List<Contest> getNoWinnerContests(){
        List<Contest> list = contestDAO.findNoWinnerContests();
        if(list == null){
            return new ArrayList<Contest>();
        }
        return list;
    }

    /**
     * Get a Contest winner ID
     *
     * @param contest the Contest from which the winner ID is returned
     * @return Contest winner ID
     */

    @Override
    public User getContestWinner(Contest contest) {
        List<File> fileList = contest.getFileList();
        File maxVotersFile = fileList.get(0);

        if(fileList == null){
            return null;
        }

        if(fileList.size() > 2){
            for(int i=1; i<fileList.size(); i++){
                if( maxVotersFile.getVoterList().size() < fileList.get(i).getVoterList().size()){
                    maxVotersFile = fileList.get(i);
                }
            }
        }

        return maxVotersFile.getUser();
    }

    @Override
    public List<Contest> getContestsByAdmin(long adminId) {
        List<Contest> list = contestDAO.getContestsByAdmin(adminId);
        if(list == null){
            return new ArrayList<Contest>();
        } else {
            return list;
        }
    }

    @Override
    public void removeAdminFromAllContests(long adminId){
        List<Contest> list = getContestsByAdmin(adminId);
        for(Contest c : list){
            c.setAdmin(null);
        }
    }
}
