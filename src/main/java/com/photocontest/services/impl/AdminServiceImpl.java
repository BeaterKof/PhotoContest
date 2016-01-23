package com.photocontest.services.impl;

import com.photocontest.dao.AdminDAO;
import com.photocontest.dao.impl.AdminDAOImpl;
import com.photocontest.exceptions.AdminNotFoundException;
import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.model.Admin;
import com.photocontest.model.User;
import com.photocontest.services.AdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/7/16
 * Time: 2:39 AM
 * To change this template use File | Settings | File Templates.
 */

public class AdminServiceImpl implements AdminService {
    static final Logger logger = Logger.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public boolean checkAvailable(String email) {
        return adminDAO.checkAvailable(email);
    }

    @Override
    public Admin createAdmin(Admin admin) throws EmailExistsException {
        if(!checkAvailable(admin.getEmail())){
            throw new EmailExistsException(admin.getEmail());
        }

        try{
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminDAO.save(admin);
        } catch(Exception e) {
            logger.error(e.getMessage());
        }

        return admin;
    }

    @Override
    public void updateAdmin(Admin admin) throws AdminNotFoundException {
        if(!checkAvailable(admin.getEmail())){
            throw new AdminNotFoundException(admin.getEmail());
        }

        try{
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminDAO.update(admin);
        } catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteAdmin(Admin admin) throws AdminNotFoundException {
        if(!checkAvailable(admin.getEmail())){
            throw new AdminNotFoundException(admin.getEmail());
        }

        try{
            adminDAO.delete(admin);
        } catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public Admin getAdminByEmail(String email) throws EmailNotFoundException {
        final Admin admin = adminDAO.getAdminByEmail(email);

        if(admin == null){
            throw new EmailNotFoundException(email);
        }
        return admin;
    }

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> adminList = adminDAO.findAll();
        if(adminList == null){
            adminList = new ArrayList<Admin>();
        }
        return adminList;
    }
}
