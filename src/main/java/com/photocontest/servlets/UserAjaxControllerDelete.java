package com.photocontest.servlets;

import com.photocontest.exceptions.FileNotFoundException;
import com.photocontest.exceptions.UserNotFoundException;
import com.photocontest.model.File;
import com.photocontest.model.User;
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

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 6:48 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/user/userAjaxController/deletePhoto/*")
public class UserAjaxControllerDelete extends HttpServlet {
    static final Logger logger = Logger.getLogger(UserAjaxControllerDelete.class);

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
            userService.updateUser(user);
            fileService.deleteFile(file);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        }

        request.getSession().setAttribute("user",user);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
