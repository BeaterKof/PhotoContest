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

    /**
     * Loads a User by its email address.
     *
     * @param email the email address of the User.
     * @return the User if the email address exists in the database
     * @return null if the email address does not exist in the database
     */

    boolean checkAvailable(String email);

    /**
     * Checks if an email address is available in the database.
     *
     * @param email the searched email address
     * @return true if the email address is available
     * @return false if the email address is not available
     */

    boolean exists(String email);

    /**
     * Checks if an email address exists in the database.
     *
     * @param email the email address
     * @return true if the email address exists
     * @return false if the email address does not exist
     */

    User loadByEmail(String email);
}
