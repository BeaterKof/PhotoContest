package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class ContestNotFoundException extends Exception {

    /**
     * Constructor for the Contest not found exception
     * @param message the name of the Contest
     */
    public ContestNotFoundException(String message) {
        super("Concursul cu numele: " + message + " nu a fost gasit.");
    }
}
