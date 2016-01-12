package com.photocontest.services.impl;

import com.photocontest.dao.UserDAO;
import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.exceptions.UserNotFoundException;
import com.photocontest.model.User;
import com.photocontest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 12/22/15
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void getUserDAO() {
        this.userDAO = userDAO;
    }

    @Override
    public boolean checkAvalilable(String email) {
        return userDAO.checkAvailable(email);
    }

    @Override
    public User createUser(User user) throws EmailExistsException {
        if(!checkAvalilable(user.getEmail())){
            throw new EmailExistsException(user.getEmail());
        }
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.save(user);
        } catch(Exception e) {
            //
            return null;
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws EmailNotFoundException{

        final User user = userDAO.loadByEmail(email);

        if(user == null){
            throw new EmailNotFoundException(email);
        }

        return user;
    }

    //for authentication provider
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final User user = userDAO.loadByEmail(s);

        if(user == null){
            throw new UsernameNotFoundException(s);
        }

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

        return userDetails;
    }

    @Override
    public void updateUser(User user) throws UserNotFoundException {

        if(!checkAvalilable(user.getEmail())){
            throw new UserNotFoundException(user.getEmail());
        }

        userDAO.update(user);
    }

    @Override
    public void deleteUser(User user) throws UserNotFoundException{
        if(checkAvalilable(user.getEmail())){
            throw new UserNotFoundException(user.getEmail());
        }

        userDAO.delete(user);
    }
}
