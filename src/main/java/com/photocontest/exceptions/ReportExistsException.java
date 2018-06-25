package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Aioanei Andrei
 * Date: 6/11/16
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportExistsException extends Exception {

    /**
     * Constructor for the Report exists exception
     * @param id the file id
     */
    public ReportExistsException(long id){
        super("The file with id " + id + " already exists in the database.");
    }
}
