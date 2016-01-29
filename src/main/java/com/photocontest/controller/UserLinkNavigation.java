package com.photocontest.controller;

import com.photocontest.model.Contest;
import com.photocontest.model.User;
import com.photocontest.services.ContestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public ModelAndView getHomePage(ModelAndView model,@ModelAttribute("user")User user){
        Contest lastContest = contestService.getLastContest();

        model.addObject("lastContest", lastContest);
//        model.addObject("user", getPrincipal());
        model.addObject("user",user);
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

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
