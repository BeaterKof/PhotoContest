package com.photocontest.controller;

import com.photocontest.exceptions.ContestNotFoundException;
import com.photocontest.exceptions.UserNotFoundException;
import com.photocontest.model.Contest;
import com.photocontest.model.File;
import com.photocontest.model.User;
import com.photocontest.services.ContestService;
import com.photocontest.services.FileService;
import com.photocontest.services.UserService;
import com.photocontest.utils.NewestFileComparator;
import com.photocontest.utils.TopFileComparator;
import com.photocontest.utils.TopThreeContests;
import com.photocontest.utils.TopThreeContestsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 12/14/15
 * Time: 5:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class GuestLinkNavigation {

    /**
     * The logger instance
     */
    final static Logger logger = Logger.getLogger(GuestLinkNavigation.class);

    /**
     * The Contest service instance
     */
    @Autowired
    ContestService contestService;

    /**
     * The User service instance
     */
    @Autowired
    UserService userService;

    /**
     * The File service instance
     */
    @Autowired
    FileService fileService;

    /**
     * The top three contests instance
     */
    @Autowired
    TopThreeContests topThreeContests;

    /**
     * Redirects to the guest home page method.
     * @return a redirect to the guest home page
     */

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage(){
        return "redirect:/guest/home";
    }

    /**
     * Populates and returns the guest home page.
     * @return the guest home page
     */

    @RequestMapping(value = "/guest/home", method = RequestMethod.GET)
         public ModelAndView getHomePage(){
        ModelAndView model = new ModelAndView("guest/home");
        String secondMenu = "allPhotos";

        Contest lastContest = contestService.getLastContest();

        model.addObject("topThree", topThreeContests.getList());
        model.addObject("secondMenu", secondMenu);
        model.addObject("lastContest", lastContest);
        return model;
    }

    /**
     * Returns home page with top voted photos of the last contest.
     * @return the guest home page with top photos from the last contest
     */

    @RequestMapping(value = "/guest/homeShowTop", method = RequestMethod.GET)
    public ModelAndView getHomePageTop(){
        ModelAndView model = new ModelAndView("guest/home");
        Contest lastContest = null;
        String secondMenu = "topPhotos";

        lastContest = contestService.getLastContest();
        Collections.sort(lastContest.getFileList(), new TopFileComparator());

        model.addObject("topThree", topThreeContests.getList());
        model.addObject("secondMenu", secondMenu);
        model.addObject("lastContest", lastContest);
        return model;
    }

    /**
     * Returns home page with newest photos of the last contest.
     * @return the guest home page with newest photos from the last contest
     */

    @RequestMapping(value = "/guest/homeShowNewest", method = RequestMethod.GET)
    public ModelAndView getHomePageNewest(){
        ModelAndView model = new ModelAndView("guest/home");
        Contest lastContest = null;
        String secondMenu = "newestPhotos";

        lastContest = contestService.getLastContest();
        Collections.sort(lastContest.getFileList(), new NewestFileComparator());

        model.addObject("topThree", topThreeContests.getList());
        model.addObject("secondMenu", secondMenu);
        model.addObject("lastContest", lastContest);
        return model;
    }

    /**
     * Populates and returns the guest contests page.
     * @return the guest contests page
     */

    @RequestMapping(value = "/guest/contests", method = RequestMethod.GET)
    public ModelAndView getContestsPage(){
        ModelAndView model = new ModelAndView("guest/contests");
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
     * Gets the page with the most voted photos of all time.
     * @return the guest all time best page
     */

    @RequestMapping(value = "/guest/allTimeBest", method = RequestMethod.GET)
    public ModelAndView getAllTimeBestPage(){
        ModelAndView model = new ModelAndView("guest/allTimeBest");

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
     * Gets the guest page with all the photographers.
     * @return the guest photographers page
     */

    @RequestMapping(value = "/guest/photographers", method = RequestMethod.GET)
    public ModelAndView getPhotographersPage(){
        ModelAndView model = new ModelAndView("guest/photographers");
        List<User> photographerList = userService.getAllUsers();
        model.addObject("topThree", topThreeContests.getList());
        model.addObject("photographerList",photographerList);

        return model;
    }

    /**
     * Gets the sign up form page.
     * @return the guest sign up form page
     */

    @RequestMapping(value = "/guest/signUp", method = RequestMethod.GET)
    public ModelAndView getSignUpPage(){
        ModelAndView model = new ModelAndView("guest/signUp");
        model.addObject("topThree", topThreeContests.getList());
        return model;
    }

    /**
     * Gets the sign in form page.
     * @return the guest sign in form page
     */

    @RequestMapping(value = "/guest/signIn", method = RequestMethod.GET)
    public ModelAndView getSignInPage(){
        ModelAndView model = new ModelAndView("guest/signIn");
        model.addObject("topThree", topThreeContests.getList());
        return model;
    }

    /**
     * Gets the "other" page. (currently an empty page)
     * @return the guest other page
     */

    @RequestMapping(value = "/guest/other", method = RequestMethod.GET)
    public ModelAndView getOtherPage(){
        ModelAndView model = new ModelAndView("guest/other");
        model.addObject("topThree", topThreeContests.getList());
        return model;
    }

    /**
     * Gets the page for a single contest.
     * @param request the HTTP servlet request containing the contest id
     * @return the page for a single contest
     */

    @RequestMapping(value = "/guest/singleContest", method = RequestMethod.GET)
    public ModelAndView getOneContest(HttpServletRequest request){
        ModelAndView model = new ModelAndView("guest/singleContest");
        Contest contest;
        User user;
        long contestId = Long.parseLong(request.getParameter("contestId"));
        try {
            contest = contestService.getContestById(contestId);
            model.addObject("lastContest",contest);
            if(contest.getWinner() != null ){
                model.addObject("winner",contest.getWinner());
            }
        } catch (ContestNotFoundException e) {
            logger.error(e.getMessage());
        }

        model.addObject("topThree", topThreeContests.getList());
        return model;
    }

    /**
     * Gets the page for a single photographer.
     * @param request the HTTP servlet request containing the photographer id
     * @return the page for a single photographer
     */

    @RequestMapping(value = "/guest/singlePhotographer", method = RequestMethod.GET)
    public ModelAndView getOnePhotographer(HttpServletRequest request){
        ModelAndView model = new ModelAndView("guest/singlePhotographer");

        User photographer;
        long photographerId = Long.parseLong(request.getParameter("photographerId"));

        try {
            photographer = userService.getUserById(photographerId);
            model.addObject("photographer",photographer);
        } catch (UserNotFoundException e) {
            logger.error(e);
        }

        model.addObject("topThree", topThreeContests.getList());
        return model;
    }
}
