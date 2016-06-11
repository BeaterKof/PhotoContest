package com.photocontest.controller.ajax;

import com.photocontest.exceptions.FileNotFoundException;
import com.photocontest.exceptions.VoterExistsException;
import com.photocontest.model.File;
import com.photocontest.model.Voter;
import com.photocontest.services.FileService;
import com.photocontest.services.VoterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/23/16
 * Time: 1:37 AM
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class GuestAjaxController {

    /**
     * The logger instance
     */
    static final Logger logger = Logger.getLogger(GuestAjaxController.class);

    /**
     * The File service instance
     */
    @Autowired
    private FileService fileService;

    /**
     * The Voter service instance
     */
    @Autowired
    private VoterService voterService;

    /**
     * The Servlet context instance
     */
    @Autowired
    private ServletContext context;

    /**
     * Add a new vote to a file.
     * Create a new Voter object and append it to the database.
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     */

    @RequestMapping("/guest/userAjax/likePhoto")
    public void likePhoto(HttpServletRequest request, HttpServletResponse response){
        String fileIdString = request.getParameter("fileId");
        String clientIp = request.getParameter("clientIp");
        HttpSession session = request.getSession(true);
        long fileId = Long.parseLong(fileIdString);
        Voter voter = new Voter();
        File file = null;

        try {
            file = fileService.getFileById(fileId);
            voter.getFiles().add(file);
            voter.setIp_address(clientIp);
            if(!file.getVoterList().contains(voter)){
                file.getVoterList().add(voter);
                fileService.updateFile(file);
            }
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }

    }
}
