package com.photocontest.dao;

import com.photocontest.dao.generic.GenericDAO;
import com.photocontest.model.File;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FileDAO extends GenericDAO<File, Integer> {

    File loadByUserId(int id);
}
