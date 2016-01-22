package com.photocontest.servlets;

import com.photocontest.exceptions.FileNotFoundException;
import com.photocontest.exceptions.UserNotFoundException;
import com.photocontest.exceptions.VoterExistsException;
import com.photocontest.model.File;
import com.photocontest.model.User;
import com.photocontest.model.Voter;
import com.photocontest.services.FileService;
import com.photocontest.services.UserService;
import com.photocontest.services.VoterService;
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

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/21/16
 * Time: 10:57 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/userAjax/likePhoto/*")
public class LikesServlet extends HttpServlet {
    static final Logger logger = Logger.getLogger(UserAjaxDeleteFile.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private VoterService voterService;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
