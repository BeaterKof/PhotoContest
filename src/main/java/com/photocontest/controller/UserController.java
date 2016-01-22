package com.photocontest.controller;

import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.exceptions.UserNotFoundException;
import com.photocontest.model.Contest;
import com.photocontest.model.File;
import com.photocontest.model.User;
import com.photocontest.services.FileService;
import com.photocontest.services.UserService;
import com.photocontest.services.impl.FileServiceImpl;
import com.photocontest.utils.DataValidator;
import com.photocontest.utils.LoginBean;
import com.photocontest.utils.UniqueValueGenerators;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/4/16
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@SessionAttributes("user")
public class UserController implements ServletContextAware{
    static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "/submitSignUpForm", method = RequestMethod.POST)
    public ModelAndView createUserAccount(@ModelAttribute("userForm") @Valid User user, BindingResult result){
        ModelAndView modelAndView;
        if(result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            int errorNr = result.getFieldErrorCount();
            for(int i=0; i < errorNr; i++){
                sb.append(result.getFieldErrors().get(i).getField().toUpperCase());
                sb.append(" ");
                sb.append(result.getFieldErrors().get(i).getDefaultMessage());
                sb.append("\n");
            }
            String errorString = sb.toString();
            modelAndView = new ModelAndView("guest/signUp");
            modelAndView.addObject("errorString", errorString);

            return modelAndView;
        }

        /* Set user fields */
        user.setStatus(1);

        /* Create User */
        try{
            userService.createUser(user);
        } catch(EmailExistsException e){
            logger.error(e.getMessage());
        }


        modelAndView = new ModelAndView("user/home");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping(value = "/submitSignInForm", method = RequestMethod.POST)
    public ModelAndView userLogin(@Valid @ModelAttribute("loginBean") LoginBean loginBean){
        ModelAndView modelAndView;
        String email = loginBean.getEmail();
        String password = loginBean.getPassword();
        String display = "block";
        User user = null;

        if(email == "" || email == null || password == "" || password == null) {
            modelAndView = new ModelAndView("/guest/home");
            String errorMessage = "User email or password are not valid";
            modelAndView.addObject("display",display);
            modelAndView.addObject("errorMessage",errorMessage);

            return modelAndView;
        }

        if(!userService.exists(email)){
            modelAndView = new ModelAndView("/guest/home");
            String errorMessage = "User email does not exist.";
            modelAndView.addObject("display",display);
            modelAndView.addObject("errorMessage",errorMessage);

            return modelAndView;
        }

        try {
             user = userService.getUserByEmail(email);
        } catch (EmailNotFoundException e) {
            logger.error(e.getMessage());
        }


        if(!passwordEncoder.matches(password,user.getPassword())){
            modelAndView = new ModelAndView("/guest/home");
            String errorMessage = "Password does not match.";
            modelAndView.addObject("display",display);
            modelAndView.addObject("errorMessage",errorMessage);

            return modelAndView;
        }

        if(user.getStatus() == 0){
            modelAndView = new ModelAndView("/guest/home");
            String errorMessage = "Contul acestui user a fost dezactivat.";
            modelAndView.addObject("display",display);
            modelAndView.addObject("errorMessage",errorMessage);

            return modelAndView;
        }

        modelAndView = new ModelAndView("/user/home");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/user/userSignOut")
    public ModelAndView userSignOut(@ModelAttribute User user, SessionStatus sessionStatus){
        ModelAndView modelAndView = new ModelAndView("/guest/home");

        try {
            userService.updateUser(user);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        sessionStatus.setComplete();

        return modelAndView;
    }

    @RequestMapping(value = "/deleteUser")
    public ModelAndView deleteUser(HttpSession session,SessionStatus sessionStatus){
        User user = (User) session.getAttribute("user");
        try {
            userService.deleteUser(user);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        }
        sessionStatus.setComplete();

        return new ModelAndView("/guest/home");
    }

    @RequestMapping(value = "/user/modifyUserAccount", method = RequestMethod.POST)
    public ModelAndView modifyUserAccount(@RequestParam(value = "newFirstName")String newFirstName,
                                          @RequestParam(value = "newLastName")String newLastName,
                                          @RequestParam(value = "newEmail")String newEmail,
                                          @RequestParam(value = "newWebsite")String newWebsite,
                                          @RequestParam(value = "newDescription")String newDescription,
                                          @ModelAttribute("user") User user){

        ModelAndView modelAndView = new ModelAndView("/user/editUser");

        if(newFirstName.length() < 2 || newFirstName.length() > 30 ||
           newLastName.length() < 2 || newLastName.length() > 30 ||
           newWebsite.length() > 50 || newDescription.length() > 50 ||
                DataValidator.isEmailAddress(newEmail)) {

            String errorMessage = "The data you inserted is not valid. No changes were made in the account info.";
            modelAndView.addObject("errorMessage",errorMessage);

            return modelAndView;
        }

        if(!user.getEmail().equals(newEmail)){
            if(userService.exists(newEmail)){
                String errorMessage = "Email is already taken.";
                modelAndView.addObject("errorMessage",errorMessage);

                return modelAndView;
            }
        }

        user.setFirst_name(newFirstName);
        user.setLast_name(newLastName);
        user.setDescription(newDescription);
        user.setWebsite(newWebsite);

        try {
            userService.updateUser(user);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        }

        modelAndView.addObject("user",user);

        return modelAndView;
    }

    @RequestMapping(value = "/user/modifyUserPassword", method = RequestMethod.POST)
    public ModelAndView modifyUserAccount(@RequestParam("oldPass")String oldPass,
                                          @RequestParam("newPass")String newPass,
                                          @ModelAttribute("user") User user){

        ModelAndView modelAndView = new ModelAndView("/user/editUser");

        if(oldPass != newPass){
            if(passwordEncoder.matches(oldPass,user.getPassword())){
                String encodedNewPass = passwordEncoder.encode(newPass);
                user.setPassword(encodedNewPass);

                try {
                    userService.updateUser(user);
                } catch (UserNotFoundException e) {
                    logger.error(e.getMessage());
                }
                modelAndView.addObject("user", user);
                return modelAndView;

            } else {
                String errorMessage = "The old password is incorrect.";
                modelAndView.addObject("errorMessage",errorMessage);

                return modelAndView;
            }
        } else {
            String errorMessage = "The new password must be different.";
            modelAndView.addObject("errorMessage",errorMessage);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/user/uploadFile")
    public ModelAndView uploadFile(@ModelAttribute("file") File uploadedFile,
                                   @ModelAttribute("user") User user,
                                   MultipartFile image){
        ModelAndView model;
        String fileType = "";
        String fileExtension = "";

        /* Test if file has an extension */
        if( image.getContentType().contains("/")){
            String[] parts = image.getContentType().split("/");
            fileType = parts[0];
            fileExtension = parts[1];
        }

        /* Compose full file path */
        String fileFolder = "user_files//";
        StringBuilder filePath = new StringBuilder();
        filePath.append(servletContext.getRealPath("/") + "/") ;
        filePath.append(fileFolder);
        filePath.append(UniqueValueGenerators.generateString());
        filePath.append("." + fileExtension);


        /* Append file to server */
        try{
            java.io.File javaIoFile = new java.io.File(filePath.toString());
            FileUtils.writeByteArrayToFile(javaIoFile, image.getBytes());

            /* Prepare file object for persist */
            String pathName = javaIoFile.getPath();
            String[] s = pathName.split("PhotoContest_war_exploded");
            String realName = s[1].substring(1);

            uploadedFile.setPath(realName);
            uploadedFile.setType(image.getContentType());
            uploadedFile.setDate_added(new Date());

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        /* User persist */
        uploadedFile.setUser(user);
        user.getFiles().add(uploadedFile);


        try {
            userService.updateUser(user);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        }

        model = new ModelAndView("/user/editUser");
        model.addObject("user",user);
        return model;
    }

}