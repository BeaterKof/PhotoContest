package com.photocontest.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 5:39 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "admins")
public class Admin implements Serializable {

    /**
     * The Admin ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id")
    private long admin_id;

    /**
     * The Admin name
     */
    @Size(min = 2, max = 30)
    private String name;

    /**
     * The Admin type
     */
    private String type;


    /**
     * The Admin password
     */
    @Size(min = 6, max = 60)
    private String password;

    /**
     * The Admin email address
     */
    @Size(min = 8, max = 50)
    private String email;

    /**
     * Gets Admin ID
     * @return admin_id the Admin ID
     */
    public long getAdmin_id() {
        return admin_id;
    }

    /**
     * The Admin no argument constructor
     */
    public Admin(){ }

    /**
     * The Admin constructor
     * @param a the Admin values
     */
    public Admin(Admin a){
        this.admin_id = a.admin_id;
        this.email = a.email;
        this.name = a.name;
        this.password = a.password;
        this.type = a.type;
    }

    /**
     * The Admin ID setter
     */
    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    /**
     * Gets the name of the Admin
     * @return the Admin name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Admin name
     * @param name the new Admin name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets Admin type
     * @return the Admin type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the Admin
     * @param type the new Admin type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the Admin password
     * @return the Admin password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the Admin
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the email address of an Admin
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address for the Admin
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
