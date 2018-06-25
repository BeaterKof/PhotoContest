package com.photocontest.security;

import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.model.User;
import com.photocontest.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Aioanei Andrei
 * Date: 3/4/16
 * Time: 8:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class CustomUserDetailsService implements UserDetailsService {
    static final Logger logger = Logger.getLogger(CustomUserDetails.class);

    @Autowired
    private UserService userService;

    /**
     * Load an User by email address and returns a CustomUserDetails object
     * which contains the User and his credentials used by Spring Security
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */

    public UserDetails loadUserByUsername(final String email)
            throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.getUserByEmail(email);
        } catch (EmailNotFoundException e) {
            logger.error(e.getMessage());
        }
        if(user==null){
            logger.error("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        boolean status = false;
        if(user.getStatus()==1){
            status = true;
        }

        return new CustomUserDetails(user);
    }
}
