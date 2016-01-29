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
    static final Logger logger = Logger.getLogger(GuestAjaxController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private VoterService voterService;

    @Autowired
    private ServletContext context;

    @RequestMapping("/guest/userAjax/likePhoto")
    public void LikePhoto(HttpServletRequest request, HttpServletResponse response){
        String fileIdString = request.getParameter("fileId");
        String clientIp = request.getParameter("clientIp");
        HttpSession session = request.getSession(true);
        long fileId = Long.parseLong(fileIdString);
        File file = null;
        Voter voter = new Voter();

        if(!voterService.exists(clientIp)){
            try {
                file = fileService.getFileById(fileId);
                voter.setFile(file);
                voter.setIp_address(clientIp);
                voterService.createVoter(voter);
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage());
            } catch (VoterExistsException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
