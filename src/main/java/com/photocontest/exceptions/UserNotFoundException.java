package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/8/16
 * Time: 12:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserNotFoundException extends Exception {

    /**
     * Constructor for the User not found exception
     * @param email the User email address
     */
    public UserNotFoundException(String email){
        super("User with email" + email + " not found.");
    }
}
