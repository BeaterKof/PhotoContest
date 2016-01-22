package com.photocontest.services.impl;

import com.photocontest.dao.FileDAO;
import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.FileExistsException;
import com.photocontest.exceptions.FileNotFoundException;
import com.photocontest.model.File;
import com.photocontest.model.User;
import com.photocontest.services.FileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/8/16
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */

public class FileServiceImpl implements FileService{
    static final Logger logger = Logger.getLogger(FileServiceImpl.class);

    @Autowired
    private FileDAO fileDAO;

    public FileDAO getFileDAO() {
        return fileDAO;
    }

    public void setFileDAO(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    @Override
    public File createFile(File file) throws FileExistsException {
        if(!fileDAO.exists(file.getFile_id())){
            throw new FileExistsException(file.getFile_id());
        }
        try {
            fileDAO.save(file);
        } catch(Exception e){
            logger.error(e.getMessage());
        }
        return file;
    }

    @Override
    public void updateFile(File file) throws FileNotFoundException {
        if(!fileDAO.exists(file.getFile_id())){
            throw new FileNotFoundException(file.getFile_id());
        }
        try {
            fileDAO.update(file);
        } catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteFile(File file) throws FileNotFoundException {
        if(!fileDAO.exists(file.getFile_id())){
            throw new FileNotFoundException(file.getFile_id());
        }
        try {
            fileDAO.delete(file);
            fileDAO.flush();
        } catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public void deleteFileById(long id) throws FileNotFoundException {
        if(!fileDAO.exists(id)){
            throw new FileNotFoundException(id);
        }
        try {
            File file = fileDAO.findById(id);
            fileDAO.delete(file);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public boolean exists(long id) {
        return fileDAO.exists(id);
    }

    @Override
    public File getFileById(long id) throws FileNotFoundException {
        File file = null;
        if(!fileDAO.exists(id)){
            throw new FileNotFoundException(id);
        }
        try{
            file = fileDAO.findById(id);
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return file;
    }


}
