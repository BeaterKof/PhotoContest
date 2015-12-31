package com.photocontest.dao.impl;

import com.photocontest.dao.AdminDAO;
import com.photocontest.dao.generic.GenericDAOImpl;
import com.photocontest.model.Admin;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminDAOImpl extends GenericDAOImpl<Admin, Integer> implements AdminDAO {
    @Override
    public boolean checkAvailableEmail(String email) {
        return false;
    }
}
