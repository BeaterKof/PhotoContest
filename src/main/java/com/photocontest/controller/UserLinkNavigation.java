package com.photocontest.controller;

import com.photocontest.exceptions.ContestNotFoundException;
import com.photocontest.exceptions.UserNotFoundException;
import com.photocontest.model.Contest;
import com.photocontest.model.File;
import com.photocontest.model.User;
import com.photocontest.services.ContestService;
import com.photocontest.services.FileService;
import com.photocontest.services.UserService;
import com.photocontest.utils.TopFileComparator;
import com.photocontest.utils.TopThreeContests;
import com.photocontest.utils.TopThreeContestsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/13/16
 * Time: 1:10 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes("user")
public class UserLinkNavigation {

    /**
     * The logger instance
     */
    final static Logger logger = Logger.getLogger(GuestLinkNavigation.class);

    /**
     * The Contest service instance
     */
    @Autowired
    private ContestService contestService;

    /**
     * The User service instance
     */
    @Autowired
    private UserService userService;

    /**
     * The top three contests instance
     */
    @Autowired
    TopThreeContests topThreeContests;


    /**
     * The File service instance
     */
    @Autowired
    private FileService fileService;

    /**
     * Populates and returns home page for users.
     * @param user the current user
     * @return the user home page
     */

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHomePage(@ModelAttribute("user")User user){
        ModelAndView model = new ModelAndView("user/home");
        Contest lastContest = contestService.getLastContest();
        model.addObject("topThree", topThreeContests.getList());
        model.addObject("lastContest",lastContest);

        return model;
    }

    /**
     * Populates and returns the page with all the contests.
     * @return the contests page
     */

    @RequestMapping(value = "/contests", method = RequestMethod.GET)
    public ModelAndView getContestsPage(){
        ModelAndView model = new ModelAndView("user/contests");
        List contestList = null;
        try {
            contestList = contestService.getAllContests();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        model.addObject("topThree", topThreeContests.getList());
        model.addObject(contestList);

        return model;
    }

    /**
     * Populates and returns the page with all time best photos.
     * @return the all time best page
     */

    @RequestMapping(value = "/allTimeBest", method = RequestMethod.GET)
    public ModelAndView getAllTimeBestPage(){
        ModelAndView model = new ModelAndView("/user/allTimeBest");
        List<File> allFiles = fileService.getAllFiles();
        Collections.sort(allFiles, new TopFileComparator());
        model.addObject("topThree", topThreeContests.getList());

        if(allFiles.size() <= 50){
            model.addObject("allTimeBestList",allFiles);
            return model;
        }

        List<File> allTimeBestList = allFiles.subList(0,50);
        model.addObject("allTimeBestList",allTimeBestList);
        return model;
    }

    /**
     * Populates and returns the page with photographers.
     * @return the photographers page
     */

    @RequestMapping(value = "/photographers", method = RequestMethod.GET)
    public ModelAndView getPhotographersPage(){
        ModelAndView model = new ModelAndView("/user/photographers");
        List<User> photographerList = userService.getAllUsers();
        model.addObject("topThree", topThreeContests.getList());
        model.addObject("photographerList",photographerList);

        return model;
    }

    /**
     * Gets the user account page.
     * @return the user account page
     */

    @RequestMapping(value = "/userAccount", method = RequestMethod.GET)
    public ModelAndView userEdit(){
        ModelAndView model = new ModelAndView("/user/editUser");
        List<Contest> contestList = contestService.getRunningContests();
        model.addObject("topThree", topThreeContests.getList());

        if(contestList != null) {
            model.addObject("contestList", contestList);
        }

        return model;
    }

    /**
     * Gets the "other" page.
     * @return the "other" page
     */

    @RequestMapping(value = "/other", method = RequestMethod.GET)
    public ModelAndView getOtherPage(){
        ModelAndView model = new ModelAndView("/user/other");
        model.addObject("topThree", topThreeContests.getList());
        return model;
    }

    /**
     * Populate and return the page with all the photos from a single contest.
     * @param request the HTTP servlet request containing the Contest ID
     * @return the single contest page
     */

    @RequestMapping(value = "/singleContest", method = RequestMethod.GET)
    public ModelAndView getOneContest(HttpServletRequest request){
        ModelAndView model = new ModelAndView("user/singleContest");
        Contest contest;
        User user;
        long contestId = Long.parseLong(request.getParameter("contestId"));
        try {
            contest = contestService.getContestById(contestId);
            model.addObject("contest",contest);
            if(contest.getWinner() != null ){
                model.addObject("winner",contest.getWinner());
            }
        } catch (ContestNotFoundException e) {
            logger.error(e.getMessage());
        }
        model.addObject("topThree", topThreeContests.getList());

        return model;
    }

}
