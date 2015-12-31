package com.photocontest.services.impl;

import com.photocontest.dao.UserDAO;
import com.photocontest.model.User;
import com.photocontest.services.UserService;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 12/22/15
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void getUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean checkAvalilable(String userName) {
        return true;
    }

    @Override
    public boolean createUser(User user) {
        if(checkAvalilable(user.getEmail()) == false){
            //email not available
            return false;
        } else {
            try{
                userDAO.save(user);
            } catch(Exception e) {
                //
                return false;
            }
        }
        return true;
    }

    @Override
    public User loadUserByEmail(String email){
        return new User();
    }

}
