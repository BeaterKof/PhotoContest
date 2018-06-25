package com.photocontest.utils;

import com.photocontest.model.Contest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Aioanei Andrei
 * Date: 5/24/16
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class TopThreeContests {

    @Autowired
    TopThreeContestsService topThreeContestsService;

    private ArrayList<Contest> list;

    public ArrayList<Contest> getList() {
        return list;
    }

    public void setList(ArrayList<Contest> list) {
        this.list = list;
    }

    /**
     * Updates the list with the top three contests with the current top three contests.
     */

    public void refreshList(){
        this.list = topThreeContestsService.getCurrentTopThreeContests();
    }
}
