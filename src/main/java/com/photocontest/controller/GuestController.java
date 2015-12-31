package com.photocontest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 12/14/15
 * Time: 5:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class GuestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndexPage(){
        ModelAndView model = new ModelAndView("guest/home");
        return model;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHomePage(){
        ModelAndView model = new ModelAndView("guest/home");
        return model;
    }

    @RequestMapping(value = "/contests", method = RequestMethod.GET)
    public ModelAndView getContestsPage(){
        ModelAndView model = new ModelAndView("guest/contests");
        return model;
    }

    @RequestMapping(value = "/allTimeBest", method = RequestMethod.GET)
    public ModelAndView getAllTimeBestPage(){
        ModelAndView model = new ModelAndView("guest/allTimeBest");
        return model;
    }

    @RequestMapping(value = "/photographers", method = RequestMethod.GET)
    public ModelAndView getPhotographersPage(){
        ModelAndView model = new ModelAndView("guest/photographers");
        return model;
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.GET)
    public ModelAndView getSignUpPage(){
        ModelAndView model = new ModelAndView("guest/signUp");
        return model;
    }


}
