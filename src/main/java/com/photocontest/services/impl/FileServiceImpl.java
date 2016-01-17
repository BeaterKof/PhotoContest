package com.photocontest.services.impl;

import com.photocontest.dao.FileDAO;
import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.model.File;
import com.photocontest.model.User;
import com.photocontest.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/8/16
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileServiceImpl implements FileService{

    @Autowired
    private FileDAO fileDAO;

    public FileDAO getFileDAO() {
        return fileDAO;
    }

    public void setFileDAO(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    @Override
    public File createFile(File file) {

        try {
            fileDAO.save(file);
        } catch(Exception e){
            //message to view
        }
        return file;
    }

    @Override
    public void updateFile(File file) {
        try {
            fileDAO.update(file);
        } catch(Exception e){
            //message to view
        }
    }

    @Override
    public void deleteFile(File file) {
        try {
            fileDAO.delete(file);
        } catch(Exception e){
            //message to view
        }
    }
}