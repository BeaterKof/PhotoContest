package com.photocontest.controller.ajax;

import com.photocontest.exceptions.ContestNotFoundException;
import com.photocontest.exceptions.FileNotFoundException;
import com.photocontest.exceptions.ReportNotFoundException;
import com.photocontest.exceptions.UserNotFoundException;
import com.photocontest.model.Contest;
import com.photocontest.model.File;
import com.photocontest.model.Report;
import com.photocontest.model.User;
import com.photocontest.services.ContestService;
import com.photocontest.services.FileService;
import com.photocontest.services.ReportService;
import com.photocontest.services.UserService;
import com.photocontest.utils.FileUtils;
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

    /**
     * The logger instance
     */
    static final Logger logger = Logger.getLogger(UserAjaxController.class);

    /**
     * The Contest service instance
     */
    @Autowired
    private ContestService contestService;

    /**
     * The File service instance
     */
    @Autowired
    private FileService fileService;

    /**
     * The User service instance
     */
    @Autowired
    private UserService userService;

    /**
     * The Report service instance
     */
    @Autowired
    private ReportService reportService;

    /**
     * The application context instance
     */
    private WebApplicationContext springContext;

    /**
     * Enter a file into a contest.
     * The contest id is obtained from:
     * <per>
     *     request.getParameter("contestId");
     * </per>
     * The file id is obtained from:
     * <per>
     *     (String)session.getAttribute("buffFileId");
     * </per>
     * @param request
     * @param response
     */

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

    /**
     * Removes a photo from the database.
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     */

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

            /* Remove voters for photo */
            fisier.setContest(null);
            fisier.getVoterList().clear();
            fileService.updateFile(fisier);

            /* Delete file not in a contest */
            user.removeFile(fisier);
            userService.updateUser(user);
            user = userService.getUserById(user.getUser_id());

            /* Delete file from disk */
            FileUtils.deleteFileFromDisk(session.getServletContext(), fisier);

        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }

        session.setAttribute("user",user);
    }

    /**
     * Sets the value of a File ID in the HttpSession object in order for it to be used later.
     * The ID will be used for Report submitting(enterFileInContest) and entering(submitReport) a file into a contest.
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     */

    @RequestMapping("/user/userAjax/loadFileId")
    public void loadFileId(HttpServletRequest request,HttpServletResponse response){

        String fileIdString = request.getParameter("fileId");
        String contestIdString = request.getParameter("contestId");
        HttpSession session = request.getSession();

        session.setAttribute("buffFileId",fileIdString);
        session.setAttribute("contestId",contestIdString);
    }

    /**
     * Submits a report to the report queue.
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     */

    @RequestMapping("/user/userAjax/submitReport")
    public void submitReport(HttpServletRequest request,HttpServletResponse response){

        String reportContent = request.getParameter("reportContent");
        HttpSession session = request.getSession(true);

        String fileIdString = (String)session.getAttribute("buffFileId");
        String contestIdString = (String)session.getAttribute("contestId");
        long fileId = Long.parseLong(fileIdString);
        long contestId = Long.parseLong(contestIdString);

        File file = null;
        Contest contest = null;
        try {
            file = fileService.getFileById(fileId);
            contest = contestService.getContestById(contestId);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (ContestNotFoundException e) {
            logger.error(e.getMessage());
        }
        User user = (User)session.getAttribute("user");
        Report report = new Report();
        report.setMessage(reportContent);
        report.setReporter_email(user.getEmail());
        reportService.createReport(report);

        report.setFile(file);
        report.setContest(contest);
        try {
            reportService.updateReport(report);
        } catch (ReportNotFoundException e) {
            logger.error(e.getMessage());
        }
    }
}