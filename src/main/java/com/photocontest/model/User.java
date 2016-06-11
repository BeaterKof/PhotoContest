package com.photocontest.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.swing.text.AsyncBoxView;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 5:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "users")
@Scope("session")
public class User implements Serializable {

    /**
     * The User ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long user_id;

    /**
     * The User email address
     */
    @Size(min = 8, max = 50)
    private String email;

    /**
     * The User password
     */
    @Size(min = 6, max = 60)
    private String password;

    /**
     * The User first name
     */
    @Size(min = 2, max = 30)
    private String first_name;

    /**
     * The User last name
     */
    @Size(min = 2, max = 30)
    private String last_name;

    /**
     * The User website
     */
    @Size(min = 2, max = 50)
    private String website;

    /**
     * The User description
     */
    @Size(max = 200)
    private String description;

    /**
     * The User type
     */
    private String type;

    /**
     * The User status
     */
    private int status;

    /**
     * The User list of Files
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files = new ArrayList<File>();

    /**
     * The User no args constructor
     */
    public User(){ }

    /**
     * User constructor
     * @param u new User value
     */
    public User(User u){
        this.user_id = u.user_id;
        this.email = u.email;
        this.password = u.password;
        this.first_name = u.first_name;
        this.last_name = u.last_name;
        this.website = u.website;
        this.description = u.description;
        this.type = u.type;
        this.status = u.status;
    }

    /**
     * Gets the User ID
     * @return the User ID
     */
    public long getUser_id() {
        return user_id;
    }

    /**
     * Sets the User ID
     * @param user_id the User ID
     */
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    /**
     * Gets the User email address
     * @return the User email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the User email address
     * @param email the User email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the User password
     * @return the User password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the User password
     * @param password the User password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the User first name
     * @return the User first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Sets the User first name
     * @param first_name the User first name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Gets the User last name
     * @return the User last name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Sets the User last name
     * @param last_name the User last name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Gets the User website
     * @return the User website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the User website
     * @param website the User website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Gets the User description
     * @return the User description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the User description
     * @param description the User description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the User status
     * @return the User status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the User status
     * @param status the User status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the User files
     * @return the User files
     */
    public List<File> getFiles() {
        return files;
    }

    /**
     * Sets the User files
     * @param files the User files
     */
    public void setFiles(List<File> files) {
        this.files = files;
    }

    /**
     * Gets the User type
     * @return the User type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the User type
     * @param type the User type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Adds a file in the User file list
     * @param file the file to be added
     */
    public void addFile(File file){
        file.setUser(this);
        files.add(file);
    }

    /**
     * Removes File from file list
     * @param file the File to be removed
     */
    public void removeFile(File file){
        for(Iterator<File> iter = files.listIterator(); iter.hasNext(); ){
            File f = iter.next();
            if(f.equals(file)){
                iter.remove();
            }
        }

        if(file != null){
            file.setUser(null);
        }
    }

}
