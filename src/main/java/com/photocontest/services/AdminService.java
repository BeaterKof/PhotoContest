package com.photocontest.services;

import com.photocontest.exceptions.AdminNotFoundException;
import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.model.Admin;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/7/16
 * Time: 12:59 AM
 * To change this template use File | Settings | File Templates.
 */
public interface AdminService {

    boolean checkAvailable(String email);
    Admin createAdmin(Admin admin) throws EmailExistsException;
    void updateAdmin(Admin admin) throws AdminNotFoundException;
    void deleteAdmin(Admin admin) throws AdminNotFoundException;
}
