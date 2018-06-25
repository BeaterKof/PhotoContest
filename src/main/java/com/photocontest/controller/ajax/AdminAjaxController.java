package com.photocontest.controller.ajax;

import com.photocontest.exceptions.*;
import com.photocontest.model.*;
import com.photocontest.services.*;
import com.photocontest.utils.FileUtils;
import com.photocontest.utils.SysdbaRman;
import com.photocontest.utils.TopThreeContests;
import com.photocontest.utils.TopThreeContestsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/23/16
 * Time: 1:50 AM
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class AdminAjaxController {

    /**
     * The logger instance
     */
    static final Logger logger = Logger.getLogger(AdminAjaxController.class);

    /**
     * The User service instance
     */
    @Autowired
    private UserService userService;

    /**
     * The Admin service instance
     */
    @Autowired
    private AdminService adminService;

    /**
     * The Contest service instance
     */
    @Autowired
    private ContestService contestService;

    /**
     * The Report service instance
     */
    @Autowired
    private ReportService reportService;

    /**
     * The File service instance
     */
    @Autowired
    private FileService fileService;

    /**
     * The top three contests instance
     */
    @Autowired
    TopThreeContests topThreeContests;

    /**
     * Deletes or deactivates an User account.
     * The action performed is decided by the value of:
     * <pre>
     *     request.getParameter("action");
     * </pre>
     *
     * @param request the HTTP servlet request
     *
     */

    @RequestMapping("/admin/adminUserManager")
    public void userManager(HttpServletRequest request){

        String userIdString = request.getParameter("userId");
        String action = request.getParameter("action");
        HttpSession session = request.getSession(true);
        long userId = Long.parseLong(userIdString);


        if( action.equals("delete")){
            try {
                User user = userService.getUserById(userId);
                List<File> fileList = user.getFiles();
                for(File fisier : fileList){
                    fisier.getVoterList().clear();
                    /* Remove from contest in order to delete the file */
                    fisier.setContest(null);

                    /* Delete file from disk */
                    FileUtils.deleteFileFromDisk(session.getServletContext(),fisier);
                    fileService.updateFile(fisier);
                }
                fileList.clear();
                userService.updateUser(user);
                userService.deleteUser(user);
            } catch (UserNotFoundException e) {
                logger.error(e.getMessage());
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage());
            }
        } else if( action.equals("deactivate")){
            try {
                User user = userService.getUserById(userId);
                if(user.getStatus() == 0){
                    user.setStatus(1);
                } else {
                    user.setStatus(0);
                }

                userService.updateUser(user);
            } catch (UserNotFoundException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * Deletes an Admin account.
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     */

    @RequestMapping("/admin/adminAdminManager")
    public void adminManager(HttpServletRequest request, HttpServletResponse response){

        String adminIdString = request.getParameter("adminId");
        HttpSession session = request.getSession(true);
        long adminId = Long.parseLong(adminIdString);

        try {
            Admin admin = adminService.getAdminById(adminId);
            contestService.removeAdminFromAllContests(adminId);
            adminService.deleteAdmin(admin);
        } catch (AdminNotFoundException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * Removes a contest.
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     */

    @RequestMapping("/admin/adminContestManager")
    public void contestManager(HttpServletRequest request, HttpServletResponse response){

        String contestIdString = request.getParameter("contestId");
        HttpSession session = request.getSession(true);
        long contestId = Long.parseLong(contestIdString);

        try {
            Contest contest = contestService.getContestById(contestId);

            for(File f : contest.getFileList()){
                f.setContest(null);
                f.getVoterList().clear();
                try {
                    fileService.updateFile(f);
                } catch (FileNotFoundException e) {
                    logger.error(e.getMessage());
                }
            }
            contestService.updateContest(contest);
            contest.setAdmin(null);
            contestService.deleteContest(contest);
        } catch (ContestNotFoundException e) {
            logger.error(e.getMessage());
        }

        topThreeContests.refreshList();
    }

    /**
     * Delete reported file or remove file from contest.
     * Decision is made by the value of:
     * <pre>
     *      request.getParameter("action");
     * </pre>
     *
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     */

    @RequestMapping("/admin/adminReportsManager")
    public void reportsManager(HttpServletRequest request, HttpServletResponse response){

        String reportIdString = request.getParameter("reportId");
        String fileIdString = request.getParameter("fileId");
        String action = request.getParameter("action");
        HttpSession session = request.getSession(true);
        long reportId = Long.parseLong(reportIdString);
        long fileId = Long.parseLong(fileIdString);

        try {
            Report report = reportService.getReportById(reportId);
            File fisier = fileService.getFileById(report.getFile().getFile_id());

            if(action.equals("removeFile")){
                ServletContext context = session.getServletContext();
                report.setFile(null);
                reportService.updateReport(report);

                /* Remove voters for photo */
                fisier.setContest(null);
                fisier.getVoterList().clear();
                fileService.updateFile(fisier);

                User user = fisier.getUser();
                user.removeFile(fisier);
                userService.updateUser(user);
                fileService.deleteFile(fisier);
                FileUtils.deleteFileFromDisk(context,fisier);
            } else if(action.equals("removeFromContest")){
                fisier.setContest(null);
                fileService.updateFile(fisier);
            }
            reportService.deleteReport(report);

        } catch (ReportNotFoundException e) {
            logger.error(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Makes a full database restore.
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     */

    @RequestMapping("/dba/adminAjax/recovery")
    public void dbRecovery(HttpServletRequest request, HttpServletResponse response){
        if(request.isUserInRole("ROLE_DBA")){
            SysdbaRman.restore();
        }
        return;
    }

    /**
     * Makes a full database backup.
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     */

    @RequestMapping("/dba/adminAjax/backup")
    public void dbBackup(HttpServletRequest request, HttpServletResponse response){
        if(request.isUserInRole("ROLE_DBA")){
            SysdbaRman.backup();
        }
        return;
    }


}
