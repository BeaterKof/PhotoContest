package com.photocontest.controller;

import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.model.Admin;
import com.photocontest.model.Contest;
import com.photocontest.services.AdminService;
import com.photocontest.services.ContestService;
import com.photocontest.utils.LoginBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView getAdminIndex(){
        return new ModelAndView("/admin/login");
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
        return new ModelAndView("/admin/createContest");
    }

    @RequestMapping(value = "/admin/logout", method = RequestMethod.GET)
    public ModelAndView adminLogout(SessionStatus sessionStatus){

        SecurityContextHolder.getContext().setAuthentication(null);
        sessionStatus.setComplete();

        return new ModelAndView("/admin");
    }

    @RequestMapping(value = "/admin/adminLogin", method = RequestMethod.POST)
    public ModelAndView getHomePage(@Valid @ModelAttribute("loginBean") LoginBean loginBean,
                                    BindingResult result){
        ModelAndView model;
        String email = loginBean.getEmail();
        String password = loginBean.getPassword();

        if(result.hasErrors()){
            model = new ModelAndView("/admin/login");
            String errorMessage = "Datele introduse nu respecta normele.";
            model.addObject(errorMessage);

            return model;
        }

        if(adminService.checkAvailable(email)){
            model = new ModelAndView("/admin/login");
            String errorMessage = "Acest admin nu exista in baza de date";
            model.addObject(errorMessage);

            return model;
        }

        Admin admin = null;
        try {
            admin = adminService.getAdminByEmail(email);
        } catch (EmailNotFoundException e) {
            logger.error(e.getMessage());
        }

//        if(!passwordEncoder.matches(password,admin.getPassword())){
//            model = new ModelAndView("/admin/login");
//            String errorMessage = "Parola gresita!";
//            model.addObject(errorMessage);
//
//            return model;
//        }

        model = new ModelAndView("/admin/home");
        model.addObject("admin",admin);

        return model;
    }

    @RequestMapping(value = "/admin/createNewAdmin", method = RequestMethod.POST)
    public ModelAndView createAdmin(@Valid @ModelAttribute("admin") Admin admin,
                                    BindingResult result){
        ModelAndView model;

        if(result.hasErrors()){
            String errorMessage = "Unul din campuri nu este valid.";
            model = new ModelAndView("/admin/createAdmin");
            model.addObject(errorMessage);

            return model;
        }

        if(!adminService.checkAvailable(admin.getEmail())){
            String errorMessage = "Exista deja un cont cu aceasta adresa de email.";
            model = new ModelAndView("/admin/createAdmin");
            model.addObject(errorMessage);

            return model;
        }

        try {
            adminService.createAdmin(admin);
        } catch (EmailExistsException e) {
            logger.error(e.getMessage());
        }

        String errorMessage = "Contul a fost creeat cu succes!!";
        model = new ModelAndView("/admin/createAdmin");
        model.addObject(errorMessage);

        return model;
    }

    @RequestMapping(value = "/admin/createNewContest", method = RequestMethod.POST)
    public ModelAndView createContest(@Valid @ModelAttribute("contest") Contest contest,
                                    BindingResult result){
        ModelAndView model = null;

        if(result.hasErrors()){
            String errorMessage = "Unul din campuri nu este valid.";
            model = new ModelAndView("/admin/createContest");
            model.addObject(errorMessage);

            return model;
        }

        contestService.createContest(contest);

        model = new ModelAndView("/admin/home");
        String errorMessage = "Concurs adaugat cu succes!!";
        model.addObject(errorMessage);

        return model;
    }
}
