package com.photocontest.services;

import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.FileExistsException;
import com.photocontest.exceptions.FileNotFoundException;
import com.photocontest.model.File;
import com.photocontest.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/7/16
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FileService {
    boolean exists(long id);
    File getFileById(long id) throws FileNotFoundException;;
    File createFile(File file) throws FileExistsException;
    void updateFile(File file) throws FileNotFoundException;
    void deleteFile(File file) throws FileNotFoundException;
    void deleteFileById(long id) throws FileNotFoundException;
}
