package com.photocontest.controller;

import com.photocontest.exceptions.ContestNotFoundException;
import com.photocontest.model.Contest;
import com.photocontest.model.File;
import com.photocontest.model.User;
import com.photocontest.services.ContestService;
import com.photocontest.services.FileService;
import com.photocontest.services.UserService;
import com.photocontest.utils.NewestFileComparator;
import com.photocontest.utils.TopFileComparator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    final static Logger logger = Logger.getLogger(GuestLinkNavigation.class);

    @Autowired
    ContestService contestService;

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage(){
        return "redirect:/guest/home";
    }

    @RequestMapping(value = "/guest/home", method = RequestMethod.GET)
         public ModelAndView getHomePage(){
        ModelAndView model = new ModelAndView("guest/home");
        String secondMenu = "allPhotos";

        Contest lastContest = contestService.getLastContest();

        model.addObject("secondMenu", secondMenu);
        model.addObject("lastContest", lastContest);
        return model;
    }

    @RequestMapping(value = "/guest/homeShowTop", method = RequestMethod.GET)
    public ModelAndView getHomePageTop(){
        ModelAndView model = new ModelAndView("guest/home");
        Contest lastContest = null;
        String secondMenu = "topPhotos";

        lastContest = contestService.getLastContest();
        Collections.sort(lastContest.getFileList(), new TopFileComparator());

        model.addObject("secondMenu", secondMenu);
        model.addObject("lastContest", lastContest);
        return model;
    }

    @RequestMapping(value = "/guest/homeShowNewest", method = RequestMethod.GET)
    public ModelAndView getHomePageNewest(){
        ModelAndView model = new ModelAndView("guest/home");
        Contest lastContest = null;
        String secondMenu = "newestPhotos";

        lastContest = contestService.getLastContest();
        Collections.sort(lastContest.getFileList(), new NewestFileComparator());

        model.addObject("secondMenu", secondMenu);
        model.addObject("lastContest", lastContest);
        return model;
    }

    @RequestMapping(value = "/guest/contests", method = RequestMethod.GET)
    public ModelAndView getContestsPage(){
        ModelAndView model = new ModelAndView("guest/contests");
        List contestList = null;
        try {
            contestList = contestService.getAllContests();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        model.addObject(contestList);

        return model;
    }

    @RequestMapping(value = "/guest/allTimeBest", method = RequestMethod.GET)
    public ModelAndView getAllTimeBestPage(){
        ModelAndView model = new ModelAndView("guest/allTimeBest");

        List<File> allFiles = fileService.getAllFiles();
        Collections.sort(allFiles, new TopFileComparator());

        if(allFiles.size() <= 50){
            model.addObject("allTimeBestList",allFiles);
            return model;
        }

        List<File> allTimeBestList = allFiles.subList(0,50);
        model.addObject("allTimeBestList",allTimeBestList);
        return model;
    }

    @RequestMapping(value = "/guest/photographers", method = RequestMethod.GET)
    public ModelAndView getPhotographersPage(){
        ModelAndView model = new ModelAndView("guest/photographers");

        List<User> photographerList = userService.getAllUsers();
        model.addObject("photographerList",photographerList);

        return model;
    }

    @RequestMapping(value = "/guest/signUp", method = RequestMethod.GET)
    public ModelAndView getSignUpPage(){
        ModelAndView model = new ModelAndView("guest/signUp");
        return model;
    }

    @RequestMapping(value = "/guest/signIn", method = RequestMethod.GET)
    public ModelAndView getSignInPage(){
        ModelAndView model = new ModelAndView("guest/signIn");
        return model;
    }

    @RequestMapping(value = "/guest/other", method = RequestMethod.GET)
    public ModelAndView getOtherPage(){
        ModelAndView model = new ModelAndView("guest/other");
        return model;
    }

    @RequestMapping(value = "/guest/singleContest", method = RequestMethod.GET)
    public ModelAndView getOneContest(HttpServletRequest request){
        ModelAndView model = new ModelAndView("guest/singleContest");
        Contest contest;
        long contestId = Long.parseLong(request.getParameter("contestId"));
        try {
            contest = contestService.getContestById(contestId);
            model.addObject("contest",contest);
        } catch (ContestNotFoundException e) {
            logger.error(e.getMessage());
        }

        return model;
    }

}
