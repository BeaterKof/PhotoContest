package com.photocontest.services.impl;

import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.model.User;
import com.photocontest.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/24/16
 * Time: 4:06 AM
 * To change this template use File | Settings | File Templates.
 */
@Service("costumUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
    static final Logger logger = Logger.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.getUserByEmail(email);
        } catch (EmailNotFoundException e) {
            logger.error(e.getMessage());
        }
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        boolean status = false;
        if(user.getStatus()==1){
            status = true;
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getType()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                status, true, true, true, authorities);
    }
}
