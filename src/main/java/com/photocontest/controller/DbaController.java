package com.photocontest.controller;

import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.model.Admin;
import com.photocontest.services.AdminService;
import com.photocontest.utils.LoginBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/23/16
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@SessionAttributes({"admin"})
public class DbaController {
    static final Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;




    @RequestMapping(value = "/admin/dbaLogin", method = RequestMethod.POST)
    public String daoLogin(@Valid @ModelAttribute("loginBean") LoginBean loginBean,
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

}
