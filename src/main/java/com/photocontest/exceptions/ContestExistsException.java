package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Aioanei Andrei
 * Date: 6/11/16
 * Time: 2:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContestExistsException extends Exception {
    /**
     * Constructor for the Contest exists exception
     * @param id the file id
     */
    public ContestExistsException(long id){
        super("The file with id " + id + " already exists in the database.");
    }
}
