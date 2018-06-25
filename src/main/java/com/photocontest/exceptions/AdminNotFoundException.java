package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/8/16
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminNotFoundException extends Exception {

    /**
     * Constructor for the Admin not found exception
     * @param email the Admin email address
     */
    public AdminNotFoundException(String email){
        super("Admin with email " + email + " not found.");
    }
}
