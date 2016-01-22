package com.photocontest.controller;

import com.photocontest.model.Contest;
import com.photocontest.services.ContestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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
    final static Logger logger = Logger.getLogger(GuestLinkNavigation.class);

    @Autowired
    private ContestService contestService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHomePage(){
        ModelAndView model = new ModelAndView("/user/home");
        return model;
    }

    @RequestMapping(value = "/contests", method = RequestMethod.GET)
    public ModelAndView getContestsPage(){
        ModelAndView model = new ModelAndView("/user/contests");
        return model;
    }

    @RequestMapping(value = "/allTimeBest", method = RequestMethod.GET)
    public ModelAndView getAllTimeBestPage(){
        ModelAndView model = new ModelAndView("/user/allTimeBest");
        return model;
    }

    @RequestMapping(value = "/photographers", method = RequestMethod.GET)
    public ModelAndView getPhotographersPage(){
        ModelAndView model = new ModelAndView("/user/photographers");
        return model;
    }

    @RequestMapping(value = "/userAccount", method = RequestMethod.GET)
    public ModelAndView userEdit(){
        ModelAndView model = new ModelAndView("/user/editUser");
        List<Contest> contestList = contestService.getRunningContests();

        if(contestList != null) {
            model.addObject("contestList", contestList);
        }

        return model;
    }

    @RequestMapping(value = "/other", method = RequestMethod.GET)
    public ModelAndView getOtherPage(){
        ModelAndView model = new ModelAndView("/user/other");
        return model;
    }

}
