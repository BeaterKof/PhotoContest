package com.photocontest.controller;

import com.photocontest.exceptions.*;
import com.photocontest.model.Contest;
import com.photocontest.model.File;
import com.photocontest.model.User;
import com.photocontest.security.CustomUserDetails;
import com.photocontest.security.CustomUserDetailsService;
import com.photocontest.services.ContestService;
import com.photocontest.services.FileService;
import com.photocontest.services.UserService;
import com.photocontest.utils.DataValidator;
import com.photocontest.utils.LoginBean;
import com.photocontest.utils.UniqueValueGenerators;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/4/16
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@SessionAttributes(value = "user")
public class UserController implements ServletContextAware{

    /**
     * The logger instance
     */
    static final Logger logger = Logger.getLogger(UserController.class);

    /**
     * The User service instance
     */
    @Autowired
    private UserService userService;

    /**
     * The Custom User Details service instance
     */
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * The password encoder instance
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * The File service instance
     */
    @Autowired
    private FileService fileService;

    /**
     * The Servlet context instance
     */
    private ServletContext servletContext;

    /**
     * The Servlet context setter
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * Process a sign up form and create a new user account if the details are correct.
     *<p>
     * Automatically binds all fields from the submitted form to an User object.
     * @param user the object containing the form fields
     * @param result the result of form-object binding
     * @param session the current session object
     * @param redirectAttributes attributes to be passed to a response
     * @return a redirect to the guest sign up page if authentication fails
     * @return a redirect to the user home page if authentication succeeds
     */

    @RequestMapping(value = "/submitSignUpForm", method = RequestMethod.POST)
    public String createUserAccount(@ModelAttribute("userForm") @Valid User user,BindingResult result,
                                    HttpSession session, final RedirectAttributes redirectAttributes){

        if(result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            int errorNr = result.getFieldErrorCount();
            sb.append("<br/>");
            for(int i=0; i < errorNr; i++){
                sb.append(result.getFieldErrors().get(i).getField().toUpperCase());
                sb.append(" ");
                sb.append(result.getFieldErrors().get(i).getDefaultMessage());
                sb.append("<br/>");
            }
            String errorMessage = sb.toString();
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/guest/signUp";
        }

        /* Set user fields */
        user.setStatus(1);          //set status active
        user.setType("ROLE_USER");  //set role

        if(userService.exists(user.getEmail())){
            String errorMessage = "This email address has already been used";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/guest/signUp";
        }

        /* Create User */
        try{
            userService.createUser(user);
        } catch(EmailExistsException e){
            logger.error(e.getMessage());
        }

        CustomUserDetails userDetails = (CustomUserDetails)customUserDetailsService.loadUserByUsername(user.getEmail());

        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        session.setAttribute("user",userDetails.getUser());
        return "redirect:/user/home";
    }

    /**
     * Process a sing in form and logs the user into their account if the
     * provided data is valid.
     *
     * @param loginBean an object containing all the values from the submitted form
     * @param result the result of form-object binding
     * @param redirectAttributes attributes to be passed to a response
     * @param session the current session object
     * @return a redirect to the user home page if authentication succeeds
     * @return a redirect to the guest sign in page if authentication fails
     */

    @RequestMapping(value = "/submitSignInForm", method = RequestMethod.POST)
    public String userLogin(@Valid @ModelAttribute("loginBean") LoginBean loginBean, BindingResult result,
                            RedirectAttributes redirectAttributes,HttpSession session){

        if(result.hasErrors()){
            String errorMessage = "User email or password are not valid.";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/guest/signIn";
        }

        String email = loginBean.getEmail();
        String password = loginBean.getPassword();
        CustomUserDetails userDetails = null;

        if(email == "" || email == null || password == "" || password == null) {
            String errorMessage = "User email or password are not valid.";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/guest/signIn";
        }

        if(!userService.exists(email)){
            String errorMessage = "User email does not exist.";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/guest/signIn";
        }

        userDetails = (CustomUserDetails)customUserDetailsService.loadUserByUsername(email);

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            String errorMessage = "Wrong password match!";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/guest/signIn";
        }

        if(userDetails.getStatus() == 0){
            String errorMessage = "This account has been disabled!";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);

            return "redirect:/guest/signIn";
        }

        /* Spring Security Authentication */
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        session.setAttribute("user",userDetails.getUser());

        return "redirect:/user/home";
    }

    /**
     * Signs user out.
     *
     * @param request the servlet request
     * @param response  the servlet response
     * @param sessionStatus current session status
     * @return a redirect to the guest home page
     */

    @RequestMapping(value = "/user/userSignOut")
    public String userSignOut(HttpServletRequest request, HttpServletResponse response,SessionStatus sessionStatus){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        sessionStatus.setComplete();

        return "redirect:/guest/home";
    }

    /**
     * Deletes an user account.
     *
     * Removes the account and all related data (images, votes)
     *
     * @param user the current user
     * @param sessionStatus the current session status(used to end current session)
     * @param request the current session request
     * @param response the current session response
     * @return a redirect to the guest home page
     */

    @RequestMapping(value = "/deleteUser")
    public String deleteUser(@ModelAttribute User user, SessionStatus sessionStatus,
                             HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        try {
            List<File> fileList = user.getFiles();
            for(File fisier : fileList){
                fisier.getVoterList().clear();
                /* Remove from contest in order to delete the file */
                fisier.setContest(null);
                /* Delete file from disk */
                com.photocontest.utils.FileUtils.deleteFileFromDisk(session.getServletContext(), fisier);
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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        sessionStatus.setComplete();

        return "redirect:/guest/home";
    }

    /**
     * Modifies the current data of an user.
     *
     * @param newFirstName the new first name
     * @param newLastName the new last name
     * @param newEmail the new email address
     * @param newWebsite the new website
     * @param newDescription the new description
     * @param user the current user
     * @param redirectAttributes the attributes to be passed in the response
     * @param session the current session
     * @return a redirect to the user account page
     */
    @RequestMapping(value = "/user/modifyUserAccount", method = RequestMethod.POST)
    public String modifyUserAccount(@RequestParam(value = "newFirstName")String newFirstName,
                                          @RequestParam(value = "newLastName")String newLastName,
                                          @RequestParam(value = "newEmail")String newEmail,
                                          @RequestParam(value = "newWebsite")String newWebsite,
                                          @RequestParam(value = "newDescription")String newDescription,
                                          @ModelAttribute("user") User user,
                                          RedirectAttributes redirectAttributes,
                                          HttpSession session){

        if(newFirstName.length() < 2 || newFirstName.length() > 30 ||
           newLastName.length() < 2 || newLastName.length() > 30 ||
           newWebsite.length() > 50 || newDescription.length() > 50 ||
                !DataValidator.isEmailAddress(newEmail)) {

            String errorMessage = "The data you inserted is not valid. No changes were made in the account info.";
            redirectAttributes.addFlashAttribute("formErrorMessage",errorMessage);

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

        session.setAttribute("user",user);

        return "redirect:/user/userAccount";
    }

    /**
     * Modifies the user password.
     *
     * @param oldPass old password
     * @param newPass new password
     * @param user the current user
     * @param redirectAttributes the attributes to be passed in the response
     * @return a redirect to the user account page
     */

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

    /**
     * Upload a file by saving the file info into the database and the file itself in
     * servlet-context//user_files folder.
     *
     * @param uploadedFile the file object to be persisted in the database
     * @param image the object containing the image file itself
     * @param session current session
     * @param redirectAttributes the attributes to be passed in the response
     * @return a redirect to the user account page
     */

    @RequestMapping(value = "/user/uploadFile")
    public String uploadFile(@ModelAttribute("file") File uploadedFile,
                             MultipartFile image, HttpSession session, RedirectAttributes redirectAttributes){

        User user = (User) session.getAttribute("user");
        String fileExtension = "";

        if(image.getSize() > 5242880){
            String errorMessage = "Image is bigger than 5MB!";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);
            return "redirect:/user/userAccount";
        }

        if(image.isEmpty()){
            String errorMessage = "No image was selected!";
            redirectAttributes.addFlashAttribute("errorMessage",errorMessage);
            return "redirect:/user/userAccount";
        }

        /* Test if file has an extension */
        if( image.getContentType().contains("/")){
            String[] parts = image.getContentType().split("/");
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
        try {
            java.io.File javaIoFile = new java.io.File(filePath.toString());
            FileUtils.writeByteArrayToFile(javaIoFile, image.getBytes());

            /* Prepare file object for persist */
            String pathName = javaIoFile.getPath();
            String[] s = pathName.split("PhotoContest_war_exploded");
            String realName = s[1].substring(1);

            uploadedFile.setPath(realName);
            uploadedFile.setType(image.getContentType());
            uploadedFile.setDate_added(new Date());

            uploadedFile.setUser(user);
            user.addFile(uploadedFile);
            fileService.createFile(uploadedFile);
        } catch (FileExistsException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        session.setAttribute("user",user);
        return "redirect:/user/userAccount";
    }

}