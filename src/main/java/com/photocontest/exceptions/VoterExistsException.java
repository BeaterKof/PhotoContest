package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/21/16
 * Time: 11:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class VoterExistsException extends Exception{

    /**
     * Constructor for the Voter exists exception
     * @param ip the IP address of a Voter
     */
    public VoterExistsException(String ip){
        super("Voter with ip: " + ip + " already exists in the database.");
    }
}
