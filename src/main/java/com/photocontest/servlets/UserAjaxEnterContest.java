package com.photocontest.servlets;

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
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/21/16
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/user/userAjax/enterContest/*")
public class UserAjaxEnterContest extends HttpServlet {
    static final Logger logger = Logger.getLogger(UserAjaxDeleteFile.class);

    @Autowired
    private ContestService contestService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    private WebApplicationContext springContext;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String contestIdString = request.getParameter("contestId");
        HttpSession session = request.getSession(true);
        long contestId = Long.parseLong(contestIdString);
        File file = (File)session.getAttribute("fileToContest");
        User user = (User)session.getAttribute("user");
        Contest contest = null;

        try {
            contest = contestService.getContestById(contestId);
            file.setContest(contest);
            file.setDate_added(new Date());
            fileService.updateFile(file);
            contest.getFileList().add(file);
            contestService.updateContest(contest);
            userService.updateUser(user);
        } catch (ContestNotFoundException e) {
            logger.error(e.getMessage());
        }catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);    //To change body of overridden methods use File | Settings | File Templates.
    }
}