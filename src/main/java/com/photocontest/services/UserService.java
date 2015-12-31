package com.photocontest.services;

import com.photocontest.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 12/22/15
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {

    boolean checkAvalilable(String userName);
    boolean createUser(User user);
    User loadUserByEmail(String email);
}
