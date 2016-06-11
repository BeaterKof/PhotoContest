package com.photocontest.services.impl;

import com.photocontest.dao.UserDAO;
import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.exceptions.UserNotFoundException;
import com.photocontest.model.User;
import com.photocontest.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 12/22/15
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class UserServiceImpl implements UserService{
    static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * Checks if an User email is available.
     *
     * @param email the User email
     * @return true if the User is available
     * @return false if the User is not available
     */

    @Override
    public boolean checkAvalilable(String email) {
        return userDAO.checkAvailable(email);
    }

    /**
     * Checks if an User email exists in the database.
     * @param email the User email
     * @return true if the User exists
     * @return false if the User does not exist
     */


    @Override
    public boolean exists(String email) {
        return userDAO.exists(email);
    }

    /**
     * Create User in the database.
     *
     * @param user the User to be created
     * @return the created User
     * @throws EmailExistsException if the User does not exist in the database
     */

    @Override
    public User createUser(User user) throws EmailExistsException {
        if(!checkAvalilable(user.getEmail())){
            throw new EmailExistsException(user.getEmail());
        }
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.save(user);
        } catch(Exception e) {
            logger.error(e.getMessage());
            return null;
        }

        return user;
    }

    /**
     * Gets an User by his email address.
     *
     * @param email the User email address
     * @return the User
     * @throws EmailNotFoundException if the User email does not exist in the database
     */

    @Override
    public User getUserByEmail(String email) throws EmailNotFoundException{

        final User user = userDAO.loadByEmail(email);

        if(user == null){
            throw new EmailNotFoundException(email);
        }

        return user;
    }

    /**
     * Gets an UserDetail object for an User with the given email address.
     * Used for the authentication provider.
     *
     * @param s User email address
     * @return an UserDetails object for the User with the given email
     * @throws UsernameNotFoundException if the User does not exist in the Database
     */

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final User user = userDAO.loadByEmail(s);

        if(user == null){
            throw new UsernameNotFoundException(s);
        }

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

        return userDetails;
    }

    /**
     * Update User in the database.
     *
     * @param user the new User value
     * @throws UserNotFoundException if the User does not exist in the database
     */


    @Override
    public void updateUser(User user) throws UserNotFoundException {
        if(!exists(user.getEmail())){
            throw new UserNotFoundException(user.getEmail());
        }
        userDAO.update(user);
    }

    /**
     * Removes an User from the database.
     *
     * @param user the User to be removed
     * @throws UserNotFoundException if the User does not exist in the database
     */


    @Override
    public void deleteUser(User user) throws UserNotFoundException{
        if(!exists(user.getEmail())){
            throw new UserNotFoundException(user.getEmail());
        }
        userDAO.delete(user);
    }

    /**
     * Gets the list with all the Users.
     *
     * @return the list of all Users that exist in the database
     */

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userDAO.findAll();
        if(userList == null){
            userList = new ArrayList<User>();
        }
        return userList;
    }

    /**
     * Gets an User by his ID.
     *
     * @param id the User ID.
     * @return the User with the given ID
     * @throws UserNotFoundException if the User does not exist in the databse
     */

    @Override
    public User getUserById(long id) throws UserNotFoundException {
        User user = userDAO.findById(id);
        if(user == null){
            throw new UserNotFoundException("id " + id);
        }
        return user;
    }
}
