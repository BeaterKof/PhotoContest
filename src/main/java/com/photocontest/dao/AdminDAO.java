package com.photocontest.dao;

import com.photocontest.dao.generic.GenericDAO;
import com.photocontest.model.Admin;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/2/15
 * Time: 1:21 AM
 * To change this template use File | Settings | File Templates.
 */
public interface AdminDAO extends GenericDAO<Admin, Long> {

    /**
     * Checks if an email address is available.
     * @param email the email address
     * @return true if the address is available
     * @return false if the address is not available
     */

    boolean checkAvailable(String email);

    /**
     * Returns an admin by its email address.
     *
     * @param email the email address of the user
     * @return the admin object if the email address exists in the database
     * @return null value if the email address does not exist in the database
     */

    Admin getAdminByEmail(String email);
}
