package com.photocontest.controller.ajax;

import com.photocontest.exceptions.ContestNotFoundException;
import com.photocontest.exceptions.FileNotFoundException;
import com.photocontest.exceptions.UserNotFoundException;
import com.photocontest.model.Contest;
import com.photocontest.model.File;
import com.photocontest.model.Report;
import com.photocontest.model.User;
import com.photocontest.services.ContestService;
import com.photocontest.services.FileService;
import com.photocontest.services.ReportService;
import com.photocontest.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
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
@Scope("session")
public class UserAjaxController {

    static final Logger logger = Logger.getLogger(UserAjaxController.class);

    @Autowired
    private ContestService contestService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    private WebApplicationContext springContext;

    @RequestMapping("/user/userAjax/enterContest")
    public void enterFileInContest(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");

        String contestIdString = request.getParameter("contestId");
        String fileIdString = (String)session.getAttribute("buffFileId");
        long fileId = Long.parseLong(fileIdString);
        long contestId = Long.parseLong(contestIdString);

        Contest contest = null;
        File file = null;

        try {
            file = fileService.getFileById(fileId);
            file.setDate_added(new Date());

            if(contestId == 0){
                file.setContest(null);
                fileService.updateFile(file);
            } else {
                contest = contestService.getContestById(contestId);
                file.setContest(contest);

                if(!contest.getFileList().contains(file)){
                    fileService.updateFile(file);
                }
            }

            /* Session User file list update */
            user = userService.getUserById(user.getUser_id());
            session.setAttribute("user", user);

        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        } catch (ContestNotFoundException e) {
            logger.error(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    @RequestMapping("/user/userAjax/deletePhoto")
    public void userDeleteFile(HttpServletRequest request,HttpServletResponse response){

        String fileIdString = request.getParameter("fileId");
        HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");


        if(fileIdString == null){
            return;
        }

        try {
            long fileId = Long.parseLong(fileIdString);
            File fisier = fileService.getFileById(fileId);

            fisier.setContest(null);
            fisier.getVoterList().clear();
            fileService.updateFile(fisier);

            // sterge fisier neinscris in concurs
            user.removeFile(fisier);
            userService.updateUser(user);
            user = userService.getUserById(user.getUser_id());

            /* Delete file from disk */
            ServletContext context = session.getServletContext();
            String filePath = context.getRealPath(fisier.getPath());
            java.io.File file = new java.io.File(filePath);
            logger.info(file.getAbsolutePath());

            if( file != null || file.exists()){
                file.delete();
            } else {
                logger.error("Fisierul nu a fost gasit pe disc. Calea fisierului este: " + filePath);
            }

        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }

        session.setAttribute("user",user);
    }


    @RequestMapping("/user/userAjax/loadFileId")
    public void loadFileId(HttpServletRequest request,HttpServletResponse response){

        String fileIdString = request.getParameter("fileId");
        String contestIdString = request.getParameter("contestId");
        HttpSession session = request.getSession();

        session.setAttribute("buffFileId",fileIdString);
        session.setAttribute("contestId",contestIdString);
    }


    @RequestMapping("/user/userAjax/submitReport")
    public void submitReport(HttpServletRequest request,HttpServletResponse response){

        String reportContent = request.getParameter("reportContent");
        HttpSession session = request.getSession(true);

        String fileIdString = (String)session.getAttribute("buffFileId");
        String contestIdString = (String)session.getAttribute("contestId");
        long fileId = Long.parseLong(fileIdString);
        long contestId = Long.parseLong(contestIdString);

        File file = null;
        try {
            file = fileService.getFileById(fileId);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }
        User user = (User)session.getAttribute("user");
        Report report = new Report();
        report.setFile_id(file.getFile_id());
        report.setContest_id(contestId);
        report.setMessage(reportContent);
        report.setReporter_email(user.getEmail());

        reportService.createReport(report);

    }
}