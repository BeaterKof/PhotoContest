package com.photocontest.security;

import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.model.Admin;
import com.photocontest.model.User;
import com.photocontest.services.AdminService;
import com.photocontest.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: Aioanei Andrei
 * Date: 3/8/16
 * Time: 5:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class CustomAdminDetailsService implements UserDetailsService {
    static final Logger logger = Logger.getLogger(CustomUserDetails.class);

    @Autowired
    private AdminService adminService;

    /**
     * Loads an Admin and its credentials.
     *
     * @param email the Admin email address
     * @return a CustomAdminDatails object containing the Admin data and
     * the credentials used by Spring Security
     * @throws UsernameNotFoundException
     */

    public UserDetails loadUserByUsername(final String email)
            throws UsernameNotFoundException {
        Admin admin = null;
        try {
            admin = adminService.getAdminByEmail(email);
        } catch (EmailNotFoundException e) {
            logger.error(e.getMessage());
        }
        if(admin==null){
            logger.error("User not found");
            throw new UsernameNotFoundException("Username not found");
        }

        return new CustomAdminDetails(admin);
    }
}
