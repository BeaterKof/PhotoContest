package com.photocontest.utils;

import com.photocontest.dao.ContestDAO;
import com.photocontest.model.Contest;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Aioanei Andrei
 * Date: 5/19/16
 * Time: 2:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class TopThreeContestsService {

    @Autowired
    ContestDAO contestDAO;

    public ContestDAO getContestDAO() {
        return contestDAO;
    }

    public void setContestDAO(ContestDAO contestDAO) {
        this.contestDAO = contestDAO;
    }

    /**
     * Gets the top three latest contests.
     *
     * @return a list of the top three contests
     */

    public ArrayList<Contest> getCurrentTopThreeContests(){
         List<Contest> contestList = contestDAO.findAll();
        Collections.sort(contestList, new ContestComparator());
        if(contestList.isEmpty()){
            return new ArrayList<Contest>();
        }
        if(contestList.size() > 2){
            contestList.subList(0,3);
        }

        return (ArrayList<Contest>)contestList;
    }
}
