package com.photocontest.controller.ajax;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/16/16
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/files/*")
public class ImageServlet extends HttpServlet{
    static final Logger logger = Logger.getLogger(ImageServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pathInfo = request.getPathInfo();
        String filePath = pathInfo.substring(pathInfo.indexOf('/'));
        ServletContext context = getServletContext();

        String filename = context.getRealPath(filePath);
        String mime = context.getMimeType(filename);

        if (mime == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setContentType(mime);
        java.io.File javaIoFile = new java.io.File(filename);
        response.setContentLength((int)javaIoFile.length());

        FileInputStream in = new FileInputStream(javaIoFile);
        OutputStream out = response.getOutputStream();

        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0){
            out.write(buf, 0, count);
        }
        out.close();
        in.close();
    }
}
