package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/8/16
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileNotFoundException extends Exception {

    /**
     * Constructor for the File not found exception
     * @param id the File ID
     */
    public FileNotFoundException(long id){
        super("File with id " + id + " not found.");
    }
}
