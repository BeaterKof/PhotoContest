package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/7/16
 * Time: 2:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmailExistsException extends Exception {

    public EmailExistsException(String email){
        super("The email address " + email + " already exists.");
    }
}
