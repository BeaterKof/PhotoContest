package com.photocontest.controller;

import com.photocontest.services.ContestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndexPage(){
        ModelAndView model = new ModelAndView("guest/home");
        return model;
    }

    @RequestMapping(value = "/guest/home", method = RequestMethod.GET)
    public ModelAndView getHomePage(){
        ModelAndView model = new ModelAndView("guest/home");
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
        return model;
    }

    @RequestMapping(value = "/guest/photographers", method = RequestMethod.GET)
    public ModelAndView getPhotographersPage(){
        ModelAndView model = new ModelAndView("guest/photographers");
        return model;
    }

    @RequestMapping(value = "/guest/signUp", method = RequestMethod.GET)
    public ModelAndView getSignUpPage(){
        ModelAndView model = new ModelAndView("guest/signUp");
        return model;
    }

    @RequestMapping(value = "/guest/other", method = RequestMethod.GET)
    public ModelAndView getOtherPage(){
        ModelAndView model = new ModelAndView("guest/other");
        return model;
    }

}
