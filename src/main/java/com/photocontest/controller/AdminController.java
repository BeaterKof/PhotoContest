package com.photocontest.controller;

import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.model.Admin;
import com.photocontest.model.Contest;
import com.photocontest.model.Report;
import com.photocontest.model.User;
import com.photocontest.services.AdminService;
import com.photocontest.services.ContestService;
import com.photocontest.services.ReportService;
import com.photocontest.services.UserService;
import com.photocontest.utils.LoginBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/18/16
 * Time: 7:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@SessionAttributes("admin")
public class AdminController {
    static final Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private ContestService contestService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView getAdminIndex(){
        return new ModelAndView("admin/login");
    }

    @RequestMapping(value = "/admin/getAdminForm", method = RequestMethod.GET)
    public ModelAndView getAdminForm(){
        return new ModelAndView("/admin/createAdmin");
    }

    @RequestMapping(value = "/admin/getContestForm", method = RequestMethod.GET)
    public ModelAndView getContestForm(){
        return new ModelAndView("/admin/createContest");
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView getAdminHome(){

        ModelAndView model = new ModelAndView("admin/home");

        /* Adding user, admin and contest lists to model */
        List<User> userList = userService.getAllUsers();
        List<Contest> contestList = contestService.getAllContests();
        List<Admin> adminList = adminService.getAllAdmins();
        List<Report> reportList = reportService.getAllReports();


        model.addObject("userList",userList);
        model.addObject("contestList",contestList);
        model.addObject("adminList",adminList);
        model.addObject("reportList",adminList);
//        model.addObject("admin",admin);

        return model;
    }

    @RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
    public String adminLogout(SessionStatus sessionStatus){

        SecurityContextHolder.getContext().setAuthentication(null);
        sessionStatus.setComplete();

        return "redirect:/guest/home";
    }

    @RequestMapping(value = "/admin/adminLogin", method = RequestMethod.POST)
    public String adminLogin(@Valid @ModelAttribute("loginBean") LoginBean loginBean,
                                    BindingResult result,final RedirectAttributes redirectAttributes){
        String email = loginBean.getEmail();
        String password = loginBean.getPassword();

        if(result.hasErrors()){
            String errorMessage = "Datele introduse nu respecta normele!";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/admin";
        }

        if(adminService.checkAvailable(email)){
            String errorMessage = "Acest admin nu exista in baza de date!";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/admin";
        }

        Admin admin = null;
        try {
            admin = adminService.getAdminByEmail(email);
        } catch (EmailNotFoundException e) {
            logger.error(e.getMessage());
        }

//        if(!passwordEncoder.matches(password,admin.getPassword())){
//              String errorMessage = "Acest admin nu exista in baza de date!";
//              redirectAttributes.addFlashAttribute("errorMessage",errorMessage);
//
//            return "redirect:/admin";
//        }

        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/admin/createNewAdmin", method = RequestMethod.POST)
    public String createAdmin(@Valid @ModelAttribute("admin") Admin admin,
                                    BindingResult result, final RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            String errorMessage = "Datele introduse nu respecta normele!";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/admin/createNewAdmin";
        }

        if(!adminService.checkAvailable(admin.getEmail())){
            String errorMessage = "Exista deja un cont cu aceasta adresa de email.";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/admin/createNewAdmin";
        }

        try {
            adminService.createAdmin(admin);
        } catch (EmailExistsException e) {
            logger.error(e.getMessage());
        }

        String message = "Contul a fost creeat cu succes!!";
        redirectAttributes.addFlashAttribute("message",message);

        return "redirect:/admin/home";
    }

    @RequestMapping(value = "/admin/createNewContest", method = RequestMethod.POST)
    public String createContest(@Valid @ModelAttribute("contest") Contest contest,
                                    BindingResult result, RedirectAttributes redirectAttributes){


        if(result.hasErrors()){
            String errorMessage = "Unul din campuri nu este valid.";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/admin/getContestForm";
        }

        contestService.createContest(contest);

        String message = "Concurs adaugat cu succes!!";
        redirectAttributes.addFlashAttribute("message",message);

        return "redirect:/admin/getContestForm";
    }
}
