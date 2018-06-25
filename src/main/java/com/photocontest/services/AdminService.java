package com.photocontest.services;

import com.photocontest.exceptions.AdminNotFoundException;
import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.model.Admin;
import com.photocontest.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/7/16
 * Time: 12:59 AM
 * To change this template use File | Settings | File Templates.
 */
public interface AdminService {

    /**
     * Checks if an email address is available in the database.
     *
     * @param email the email to be checked
     * @return true if the email does not exist
     * @return false if the email exists
     */

    boolean checkAvailable(String email);

    /**
     * Creates an new Admin in the database.
     *
     * @param admin the Admin to be created
     * @return the Admin created
     * @throws EmailExistsException if the email of the Admin
     * already exists in the database
     */

    Admin createAdmin(Admin admin) throws EmailExistsException;

    /**
     * Updates an Admin in the database
     *
     * @param admin the new Admin value
     * @throws AdminNotFoundException if the admin does not exist
     */

    void updateAdmin(Admin admin) throws AdminNotFoundException;

    /**
     * Deletes an Admin from the database
     *
     * @param admin the Admin to be deleted
     * @throws AdminNotFoundException if admin does not exist
     */

    void deleteAdmin(Admin admin) throws AdminNotFoundException;

    /**
     * Gets an Admin by its Email address
     *
     * @param email the admin email address
     * @return the Admin retrieved
     * @throws EmailNotFoundException if the email does not exist in the database
     */

    Admin getAdminByEmail(String email) throws EmailNotFoundException;

    /**
     * Gets an Admin by its ID
     *
     * @param id the admin ID
     * @return the Admin retrieved
     * @throws AdminNotFoundException if the ID does not exist in the database
     */

    Admin getAdminById(long id) throws  AdminNotFoundException;

    /**
     * Gets a list with all the Admins from the database.
     *
     * @return the list of all Admins
     */

    List<Admin> getAllAdmins();
}
