package com.photocontest.security;

import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.model.User;
import com.photocontest.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Aioanei Andrei
 * Date: 2/24/16
 * Time: 8:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private UserService userService;

    /**
     * Checks the authority of an user and returns an Authentication object
     * used for the authentication in Spring Security
     * @param authentication
     * @return User and credentials if he is an admin
     * @return null if the user is not an admin
     * @throws AuthenticationException
     */

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        String name = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        try {
            User user = userService.getUserByEmail(name);
        } catch (EmailNotFoundException e) {
            logger.error(e.getMessage());
        }

        if (name.equals("admin") && password.equals("system")) {
            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
            return auth;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
