package com.photocontest.services.impl;

import com.photocontest.dao.FileDAO;
import com.photocontest.dao.ReportDAO;
import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.FileExistsException;
import com.photocontest.exceptions.FileNotFoundException;
import com.photocontest.model.File;
import com.photocontest.model.User;
import com.photocontest.services.FileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/8/16
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class FileServiceImpl implements FileService{
    static final Logger logger = Logger.getLogger(FileServiceImpl.class);

    @Autowired
    private FileDAO fileDAO;

    @Autowired
    private ReportDAO reportDAO;

    public FileDAO getFileDAO() {
        return fileDAO;
    }

    public void setFileDAO(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public ReportDAO getReportDAO() {
        return reportDAO;
    }

    public void setReportDAO(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    /**
     * Checks if an File exists in the database.
     *
     * @param id the File id
     * @return true if the File exists in the database.
     * @return false if the File does not exist in the database.
     */

    @Override
    public boolean exists(long id) {
        return fileDAO.exists(id);
    }

    /**
     * Creates a File in the database.
     *
     * @param file the File to be created
     * @return the created File
     * @throws FileExistsException if the file exists in the database
     */

    @Override
    public File createFile(File file) throws FileExistsException {
        if(fileDAO.exists(file.getFile_id())){
            throw new FileExistsException(file.getFile_id());
        }
        try {
            fileDAO.save(file);
        } catch(Exception e){
            logger.error(e.getMessage());
        }
        return file;
    }

    /**
     * Update a File in the database.
     *
     * @param file the new File data
     * @throws FileNotFoundException if the File does not exist in the database
     */

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

    /**
     * Remove a File from the database.
     *
     * @param file the File to be removed from the database
     * @throws FileNotFoundException if the File does not exist in the Database
     */

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

    /**
     * Checks if an File exists in the database.
     *
     * @param id the File id
     * @return true if the File exists in the database.
     * @return false if the File does not exist in the database.
     */

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

    /**
     * Gets all the Files from the database.
     *
     * @return the list of all Files from the database
     */

    @Override
    public List<File> getAllFiles() {
        List<File> list = fileDAO.findAll();

        if(list == null){
            list = new ArrayList<File>();
        }
        return list;
    }
}
