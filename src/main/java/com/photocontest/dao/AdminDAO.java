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
public interface AdminDAO extends GenericDAO<Admin, Integer> {

    boolean checkAvailable(String email);
    Admin getAdminByEmail(String email);
}
