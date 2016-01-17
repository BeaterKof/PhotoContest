package com.photocontest.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/8/16
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileNotFoundException extends Exception {

    public FileNotFoundException(String filename){
        super("File with name " + filename + " not found.");
    }
}
