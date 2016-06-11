package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/5/16
 * Time: 1:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmailNotFoundException extends Exception {

    /**
     * Constructor for the email address not found exception
     * @param email the User/Admin email address
     */
    public EmailNotFoundException(String email){
        super(email + " not found.");
    }
}
