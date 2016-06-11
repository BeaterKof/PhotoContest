package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/7/16
 * Time: 2:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmailExistsException extends Exception {

    /**
     * Constructor for the email address not found exception
     * @param email the User/Admin email address
     */
    public EmailExistsException(String email){
        super("The email address " + email + " already exists.");
    }
}
