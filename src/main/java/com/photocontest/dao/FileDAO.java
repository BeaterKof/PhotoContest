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
public interface FileDAO extends GenericDAO<File, Long> {

    /**
     * Finds an File in the database by its ID.
     *
     * @param id the ID of the File
     * @return the File if it exists in the database
     * @return null if the File does not exist in the database
     */

    boolean exists(long id);

    /**
     * Checks if an File exists in the database.
     *
     * @param id the File id
     * @return true if the File exists in the database.
     * @return false if the File does not exist in the database.
     */

    File loadByUserId(long id);
}
