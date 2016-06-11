package com.photocontest.utils;

import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.model.Admin;
import com.photocontest.services.AdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/18/16
 * Time: 8:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class OnApplicationLoadBean {
    static final Logger logger = Logger.getLogger(OnApplicationLoadBean.class);
    @Autowired
    private AdminService adminService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    TopThreeContests topThreeContests;

    @Autowired
    TopThreeContestsService topThreeContestsService;

    /**
     * This method is executed on the application start.
     * It checks if the default users exist and if not it creates them.
     * It sets the values for the top three contests that appear on each page.
     */

    public void load(){
        String email = "admin@mail.com";
        String pass = "asdasdasd";

        String daoEmail = "dba@mail.com";
        String daoPass = "asdasdasd";
        String password = passwordEncoder.encode(pass);
        String daoPassword = passwordEncoder.encode(daoPass);

        if(adminService.checkAvailable(email)){
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setPassword(password);
            admin.setName("GeneratedAdmin");
            admin.setType("admin");

            try {
                adminService.createAdmin(admin);
            } catch (EmailExistsException e) {
                logger.error(e.getMessage());
            }
        }

        if(adminService.checkAvailable(daoEmail)){
            Admin dao = new Admin();
            dao.setEmail(daoEmail);
            dao.setPassword(daoPassword);
            dao.setName("GeneratedDba");
            dao.setType("dba");

            try {
                adminService.createAdmin(dao);
            } catch (EmailExistsException e) {
                logger.error(e.getMessage());
            }
        }

        topThreeContests.setList(topThreeContestsService.getCurrentTopThreeContests());
    }
}
