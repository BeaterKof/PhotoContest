package com.photocontest.services;

import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.FileExistsException;
import com.photocontest.exceptions.FileNotFoundException;
import com.photocontest.model.File;
import com.photocontest.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/7/16
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FileService {

    /**
     * Checks if an File exists in the database.
     *
     * @param id the File id
     * @return true if the File exists in the database.
     * @return false if the File does not exist in the database.
     */

    boolean exists(long id);

    /**
     * Finds an File in the database by its ID.
     *
     * @param id the ID of the File
     * @return the File if it exists in the database
     * @return null if the File does not exist in the database
     */

    File getFileById(long id) throws FileNotFoundException;

    /**
     * Creates a File in the database.
     *
     * @param file the File to be created
     * @return the created File
     * @throws FileExistsException if the file exists in the database
     */

    File createFile(File file) throws FileExistsException;

    /**
     * Update a File in the database.
     *
     * @param file the new File data
     * @throws FileNotFoundException if the File does not exist in the database
     */

    void updateFile(File file) throws FileNotFoundException;

    /**
     * Remove a File from the database.
     *
     * @param file the File to be removed from the database
     * @throws FileNotFoundException if the File does not exist in the Database
     */

    void deleteFile(File file) throws FileNotFoundException;

    /**
     * Gets all the Files from the database.
     *
     * @return the list of all Files from the database
     */

    List<File> getAllFiles();
}
