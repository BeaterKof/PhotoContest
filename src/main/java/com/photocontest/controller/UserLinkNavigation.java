package com.photocontest.controller;

import com.photocontest.exceptions.ContestNotFoundException;
import com.photocontest.model.Contest;
import com.photocontest.model.User;
import com.photocontest.services.ContestService;
import com.photocontest.services.UserService;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHomePage(ModelAndView model,@ModelAttribute("user")User user){
        Contest lastContest = contestService.getLastContest();
        model.addObject("user", getPrincipal());

        return model;
    }

    @RequestMapping(value = "/contests", method = RequestMethod.GET)
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

    @RequestMapping(value = "/allTimeBest", method = RequestMethod.GET)
    public ModelAndView getAllTimeBestPage(){
        ModelAndView model = new ModelAndView("/user/allTimeBest");
        return model;
    }

    @RequestMapping(value = "/photographers", method = RequestMethod.GET)
    public ModelAndView getPhotographersPage(){
        ModelAndView model = new ModelAndView("/user/photographers");

        List<User> photographerList = userService.getAllUsers();
        model.addObject("photographerList",photographerList);

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

    @RequestMapping(value = "/singleContest", method = RequestMethod.GET)
    public ModelAndView getOneContest(HttpServletRequest request){
        ModelAndView model = new ModelAndView("user/singleContest");
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
