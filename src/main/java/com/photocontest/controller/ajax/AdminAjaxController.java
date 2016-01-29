package com.photocontest.controller.ajax;

import com.photocontest.exceptions.AdminNotFoundException;
import com.photocontest.exceptions.ContestNotFoundException;
import com.photocontest.exceptions.UserNotFoundException;
import com.photocontest.model.Admin;
import com.photocontest.model.Contest;
import com.photocontest.model.User;
import com.photocontest.services.AdminService;
import com.photocontest.services.ContestService;
import com.photocontest.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/23/16
 * Time: 1:50 AM
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class AdminAjaxController {
    static final Logger logger = Logger.getLogger(AdminAjaxController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ContestService contestService;

    @RequestMapping("/admin/adminUserManager")
    public void userManager(HttpServletRequest request, HttpServletResponse response){

        String userIdString = request.getParameter("userId");
        String action = request.getParameter("action");
        HttpSession session = request.getSession(true);
        long userId = Long.parseLong(userIdString);

        if( action.equals("delete")){
            try {
                User user = userService.getUserById(userId);
                userService.deleteUser(user);
            } catch (UserNotFoundException e) {
                logger.error(e.getMessage());
            }
        } else if( action.equals("deactivate")){
            try {
                User user = userService.getUserById(userId);
                if(user.getStatus() == 0){
                    user.setStatus(1);
                } else {
                    user.setStatus(0);
                }

                userService.updateUser(user);
            } catch (UserNotFoundException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @RequestMapping("/admin/adminAdminManager")
    public void adminManager(HttpServletRequest request, HttpServletResponse response){

        String adminIdString = request.getParameter("adminId");
        HttpSession session = request.getSession(true);
        long adminId = Long.parseLong(adminIdString);

        try {
            Admin admin = adminService.getAdminById(adminId);
            adminService.deleteAdmin(admin);
        } catch (AdminNotFoundException e) {
            logger.error(e.getMessage());
        }

    }

    @RequestMapping("/admin/adminContestManager")
    public void contestManager(HttpServletRequest request, HttpServletResponse response){

        String contestIdString = request.getParameter("contestId");
        HttpSession session = request.getSession(true);
        long contestId = Long.parseLong(contestIdString);

        try {
            Contest contest = contestService.getContestById(contestId);
            contestService.deleteContest(contest);
        } catch (ContestNotFoundException e) {
            logger.error(e.getMessage());
        }

    }


    @RequestMapping("/admin/adminReportsManager")
    public void reportsManager(HttpServletRequest request, HttpServletResponse response){

        String contestIdString = request.getParameter("contestId");
        HttpSession session = request.getSession(true);
        long contestId = Long.parseLong(contestIdString);

        try {
            Contest contest = contestService.getContestById(contestId);
            contestService.deleteContest(contest);
        } catch (ContestNotFoundException e) {
            logger.error(e.getMessage());
        }

    }
}
