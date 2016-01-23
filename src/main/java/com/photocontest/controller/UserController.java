package com.photocontest.controller;

import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.exceptions.FileExistsException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
@SessionAttributes(value = "user", types = {User.class})
public class UserController implements ServletContextAware{
    static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "/submitSignUpForm", method = RequestMethod.POST)
    public String createUserAccount(@ModelAttribute("userForm") @Valid User user,
                                          BindingResult result,final RedirectAttributes redirectAttributes){

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
            redirectAttributes.addFlashAttribute("errorString",errorString);

            return "redirect:/guest/signUp";
        }

        /* Set user fields */
        user.setStatus(1);

        /* Create User */
        try{
            userService.createUser(user);
        } catch(EmailExistsException e){
            logger.error(e.getMessage());
        }
        redirectAttributes.addFlashAttribute("user",user);

        return "redirect:/user/home";
    }

    @RequestMapping(value = "/submitSignInForm", method = RequestMethod.POST)
    public String userLogin(@Valid @ModelAttribute("loginBean") LoginBean loginBean,
                            RedirectAttributes redirectAttributes){

        String email = loginBean.getEmail();
        String password = loginBean.getPassword();
        String display = "block";
        User user = null;

        if(email == "" || email == null || password == "" || password == null) {
            String errorMessage = "User email or password are not valid.";
            redirectAttributes.addFlashAttribute("display",display);
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/guest/home";
        }

        if(!userService.exists(email)){
            String errorMessage = "User email does not exist.";
            redirectAttributes.addFlashAttribute("display",display);
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/guest/home";
        }

        try {
             user = userService.getUserByEmail(email);
        } catch (EmailNotFoundException e) {
            logger.error(e.getMessage());
        }


        if(!passwordEncoder.matches(password,user.getPassword())){
            String errorMessage = "Passwords don't match!";
            redirectAttributes.addFlashAttribute("display",display);
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/guest/home";
        }

        if(user.getStatus() == 0){
            String errorMessage = "This account has been disabled!";
            redirectAttributes.addFlashAttribute("display",display);
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/guest/home";
        }

        redirectAttributes.addFlashAttribute("user",user);

        return "redirect:/user/home";
    }

    @RequestMapping(value = "/user/userSignOut")
    public String userSignOut(@ModelAttribute User user, SessionStatus sessionStatus){

        SecurityContextHolder.getContext().setAuthentication(null);
        sessionStatus.setComplete();

        return "redirect:/guest/home";
    }

    @RequestMapping(value = "/deleteUser")
    public String deleteUser(@ModelAttribute User user,SessionStatus sessionStatus){

        try {
            userService.deleteUser(user);
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(null);
        sessionStatus.setComplete();

        return "redirect:/guest/home";
    }

    @RequestMapping(value = "/user/modifyUserAccount", method = RequestMethod.POST)
    public String modifyUserAccount(@RequestParam(value = "newFirstName")String newFirstName,
                                          @RequestParam(value = "newLastName")String newLastName,
                                          @RequestParam(value = "newEmail")String newEmail,
                                          @RequestParam(value = "newWebsite")String newWebsite,
                                          @RequestParam(value = "newDescription")String newDescription,
                                          @ModelAttribute("user") User user,
                                          RedirectAttributes redirectAttributes){

        if(newFirstName.length() < 2 || newFirstName.length() > 30 ||
           newLastName.length() < 2 || newLastName.length() > 30 ||
           newWebsite.length() > 50 || newDescription.length() > 50 ||
                DataValidator.isEmailAddress(newEmail)) {

            String errorMessage = "The data you inserted is not valid. No changes were made in the account info.";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/user/userAccount";
        }

        if(!user.getEmail().equals(newEmail)){
            if(userService.exists(newEmail)){
                String errorMessage = "Email is already taken.";
                redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

                return "redirect:/user/userAccount";
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

        redirectAttributes.addFlashAttribute("user",user);

        return "redirect:/user/userAccount";
    }

    @RequestMapping(value = "/user/modifyUserPassword", method = RequestMethod.POST)
    public String modifyUserPassword(@RequestParam("oldPass")String oldPass,
                                          @RequestParam("newPass")String newPass,
                                          @ModelAttribute("user") User user,
                                          RedirectAttributes redirectAttributes){

        if(oldPass != newPass){
            if(passwordEncoder.matches(oldPass,user.getPassword())){
                String encodedNewPass = passwordEncoder.encode(newPass);
                user.setPassword(encodedNewPass);

                try {
                    userService.updateUser(user);
                } catch (UserNotFoundException e) {
                    logger.error(e.getMessage());
                }
                String errorMessage = "The password was changed!";
                redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            } else {
                String errorMessage = "The old password is incorrect.";
                redirectAttributes.addFlashAttribute("errorMessage",errorMessage);
            }
        } else {
            String errorMessage = "The new password must be different.";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

        }
        return "redirect:/user/userAccount";
    }

    @RequestMapping(value = "/user/uploadFile")
    public String uploadFile(@ModelAttribute("file") File uploadedFile,@ModelAttribute("user") User user,
                             MultipartFile image, HttpSession session, RedirectAttributes redirectAttributes){

        String fileType = "";
        String fileExtension = "";

        if(image.isEmpty()){
            String errorMessage = "No image was selected!";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);
            return "redirect:/user/userAccount";
        }

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


        try {
            uploadedFile.setUser(user);
            //fileService.createFile(uploadedFile);
            user.getFiles().add(uploadedFile);
            userService.updateUser(user);
            user = userService.getUserById(user.getUser_id());
        } catch (UserNotFoundException e) {
            logger.error(e.getMessage());
        }

        session.setAttribute("user",user);
        return "redirect:/user/userAccount";
    }

}