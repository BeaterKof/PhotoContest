package com.photocontest.controller;

import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.model.Admin;
import com.photocontest.services.AdminService;
import com.photocontest.utils.ConnectSysdba;
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

    @Autowired
    private ConnectSysdba connectSysdba;


    @RequestMapping(value = "/dba/home", method = RequestMethod.GET)
    public ModelAndView getDbaIndex(){

        try {
            connectSysdba.connect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ModelAndView("dba/home");
    }

    @RequestMapping(value = "/dba/logout", method = RequestMethod.GET)
    public ModelAndView dbaLogout(){

        try {
            connectSysdba.disconnect();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return new ModelAndView("dba/home");
    }

}
