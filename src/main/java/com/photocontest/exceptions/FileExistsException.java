package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/21/16
 * Time: 2:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileExistsException extends Exception {

    /**
     * Constructor for the File exists exception
     * @param id the file id
     */
    public FileExistsException(long id){
        super("The file with id " + id + " already exists in the database.");
    }
}
