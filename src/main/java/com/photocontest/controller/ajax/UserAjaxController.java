package com.photocontest.controller.ajax;

import com.photocontest.exceptions.ContestNotFoundException;
import com.photocontest.exceptions.FileNotFoundException;
import com.photocontest.exceptions.UserNotFoundException;
import com.photocontest.model.Contest;
import com.photocontest.model.File;
import com.photocontest.model.User;
import com.photocontest.services.ContestService;
import com.photocontest.services.FileService;
import com.photocontest.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/23/16
 * Time: 1:10 AM
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class UserAjaxController {

    static final Logger logger = Logger.getLogger(UserAjaxController.class);

    @Autowired
    private ContestService contestService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    private WebApplicationContext springContext;

    @RequestMapping("/user/userAjax/enterContest")
    public void EnterFileInContest(HttpServletRequest request,HttpServletResponse response){

        String contestIdString = request.getParameter("contestId");
        HttpSession session = request.getSession(true);
        long contestId = Long.parseLong(contestIdString);
        File file = (File)session.getAttribute("fileToContest");
        User user = (User)session.getAttribute("user");
        Contest contest = null;

        try {
            contest = contestService.getContestById(contestId);
            logger.error("contest name try - :" + contest.getName());

            file.setDate_added(new Date());
            file.setContest(contest);
            if(!contest.getFileList().contains(file)){
                //contest.getFileList().add(file);
                //contestService.updateContest(contest);

               fileService.updateFile(file);

            /* Session User file list update */
                user = userService.getUserById(user.getUser_id());
                session.setAttribute("user", user);
            }
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        } catch (ContestNotFoundException e) {
            logger.error(e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @RequestMapping("/user/userAjax/deletePhoto")
    public void UserDeleteFile(HttpServletRequest request,HttpServletResponse response){

        String fileIdString = request.getParameter("fileId");
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");
        long fileId = Long.parseLong(fileIdString);
        File file = null;

        for(File f : user.getFiles()){
            if(f.getFile_id() == fileId){
                file = f;
                user.getFiles().remove(f);
                break;
            }
        }

        /* Delete file from disk */
//        String path =
//        java.io.File diskFile = new java.io.File("");


        try {
            fileService.deleteFileById(fileId);
            userService.updateUser(user);
            session.setAttribute("user",user);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }

    }


    @RequestMapping("/user/userAjax/loadFileId")
    public void LoadFileId(HttpServletRequest request,HttpServletResponse response){

        String fileIdString = request.getParameter("fileId");
        HttpSession session = request.getSession(true);
        long fileId = Long.parseLong(fileIdString);
        File file = null;

        try {
            file = fileService.getFileById(fileId);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }

        session.setAttribute("fileToContest",file);
    }

}