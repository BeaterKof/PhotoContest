package com.photocontest.services;

import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.exceptions.UserNotFoundException;
import com.photocontest.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 12/22/15
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService extends UserDetailsService{

    boolean checkAvalilable(String email);
    boolean exists(String email);
    User createUser(User user) throws EmailExistsException;
    void updateUser(User user) throws UserNotFoundException;
    void deleteUser(User user) throws UserNotFoundException;
    User getUserByEmail(String email) throws EmailNotFoundException;
    User getUserById(long id) throws UserNotFoundException;;
    List<User> getAllUsers();
}
