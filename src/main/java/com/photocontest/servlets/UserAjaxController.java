package com.photocontest.servlets;

import com.photocontest.exceptions.FileNotFoundException;
import com.photocontest.services.FileService;
import com.photocontest.services.impl.FileServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 6:48 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/user/UserAjaxController/*")
public class UserAjaxController extends HttpServlet {
    static final Logger logger = Logger.getLogger(UserAjaxController.class);

    @Autowired
    private FileService fileService;

    private WebApplicationContext springContext;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileIdString = request.getParameter("fileId");
        long fileId = Long.parseLong(fileIdString);
        fileService = new FileServiceImpl();

        try {
            fileService.deleteFileById(fileId);
        } catch (FileNotFoundException e) {
            logger.error(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
