package com.photocontest.utils;

import com.photocontest.model.Contest;
import com.photocontest.services.ContestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Aioanei Andrei
 * Date: 5/24/16
 * Time: 11:23 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ContestEndedChecker {
    private static final Logger logger = Logger.getLogger(ContestEndedChecker.class);
    @Autowired
    ContestService contestService;

    /**
     * Checks if a Contest has ended and has no winner defined.
     * In case it does not, the Contest winner will be added to the Contest.
     *
     * This method will run on 00:01:00 everyday
     * cron format: sec min hour day mon week
     */

   @Scheduled(cron="0 1 0 * * *")
    public void checkEndedContests(){
        List<Contest> contestList = contestService.getNoWinnerContests();
        for(Contest contest : contestList){
            if(contest.getFinish_date().before(new Date()) || contest.getFinish_date().equals(new Date())){
                if(contestService.getContestWinnerId(contest)!= null){
                    contest.setWinner(contestService.getContestWinnerId(contest));
                }
            }
        }
       logger.info("--------------------------------------------------------");
       logger.info("schedule_log_check OK");
       logger.info("--------------------------------------------------------");
    }
}
