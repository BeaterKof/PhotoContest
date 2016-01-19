package com.photocontest.dao;

import com.photocontest.dao.generic.GenericDAO;
import com.photocontest.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 8:07 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserDAO extends GenericDAO<User, Long> {

    boolean checkAvailable(String email);
    boolean exists(String email);
    User loadByEmail(String email);
}
