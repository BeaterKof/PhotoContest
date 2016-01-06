package com.photocontest.services.impl;

import com.photocontest.dao.UserDAO;
import com.photocontest.exceptions.EmailNotFoundException;
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

    public void getUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean checkAvalilable(String email) {
        return userDAO.checkAvailable(email);
    }

    @Override
    public boolean createUser(User user) {
        if(!checkAvalilable(user.getEmail())){
            //email not available
            return false;
        } else {
            try{
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userDAO.save(user);
            } catch(Exception e) {
                //
                return false;
            }
        }
        return true;
    }

    @Override
    public User loadUserByEmail(String email) throws EmailNotFoundException{

        User user = userDAO.loadByEmail(email);

        if(user == null){
            throw new EmailNotFoundException(email);
        }

        return user;
    }

    //for authentication provider
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.loadByEmail(s);

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
    public boolean updateUser(User user) {

        if(!checkAvalilable(user.getEmail())){
            return false;
        }

        try{
            userDAO.update(user);
        } catch (Exception e){
            //
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteUser(User user) {
        if(!checkAvalilable(user.getEmail())){
            return false;
        }

        try{
            userDAO.delete(user);
        } catch (Exception e){
            //
            return false;
        }
        return true;
    }
}
