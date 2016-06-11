package com.photocontest.services;

import com.photocontest.exceptions.EmailExistsException;
import com.photocontest.exceptions.EmailNotFoundException;
import com.photocontest.exceptions.UserNotFoundException;
import com.photocontest.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 12/22/15
 * Time: 11:24 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService extends UserDetailsService{

    /**
     * Checks if an User email is available.
     *
     * @param email the User email
     * @return true if the User is available
     * @return false if the User is not available
     */

    boolean checkAvalilable(String email);

    /**
     * Checks if an User email exists in the database.
     * @param email the User email
     * @return true if the User exists
     * @return false if the User does not exist
     */

    boolean exists(String email);

    /**
     * Create User in the database.
     *
     * @param user the User to be created
     * @return the created User
     * @throws EmailExistsException if the User does not exist in the database
     */

    User createUser(User user) throws EmailExistsException;

    /**
     * Update User in the database.
     *
     * @param user the new User value
     * @throws UserNotFoundException if the User does not exist in the database
     */

    void updateUser(User user) throws UserNotFoundException;

    /**
     * Removes an User from the database.
     *
     * @param user the User to be removed
     * @throws UserNotFoundException if the User does not exist in the database
     */

    void deleteUser(User user) throws UserNotFoundException;

    /**
     * Gets an User by his email address.
     *
     * @param email the User email address
     * @return the User
     * @throws EmailNotFoundException if the User email does not exist in the database
     */

    User getUserByEmail(String email) throws EmailNotFoundException;

    /**
     * Gets an User by his ID.
     *
     * @param id the User ID.
     * @return the User with the given ID
     * @throws UserNotFoundException if the User does not exist in the databse
     */

    User getUserById(long id) throws UserNotFoundException;

    /**
     * Gets the list with all the Users.
     *
     * @return the list of all Users that exist in the database
     */

    List<User> getAllUsers();
}
