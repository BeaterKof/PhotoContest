package com.photocontest.services;

import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 12/22/15
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService extends UserDetailsService{

    boolean checkAvalilable(String email);
    User saveUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User getUserByEmail(String email) throws EmailNotFoundException;
}
