package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 2:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReportNotFoundException extends Exception {

    public ReportNotFoundException(long id){
        super("Raportul cu id-ul " +  id + " nu a fost gasit." );
    }
}
