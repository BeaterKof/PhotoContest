package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/8/16
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminNotFoundException extends Exception {

    public AdminNotFoundException(String email){
        super("Admin with email " + email + " not found.");
    }
}
