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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long user_id;

    @Size(min = 8, max = 50)
    private String email;

    @Size(min = 6, max = 60)
    private String password;

    @Size(min = 2, max = 30)
    private String first_name;

    @Size(min = 2, max = 30)
    private String last_name;

    @Size(min = 2, max = 50)
    private String website;

    @Size(max = 200)
    private String description;

    private String type;

    private int status;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files = new ArrayList<File>();

    public User(){ }

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

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addFile(File file){
        file.setUser(this);
        files.add(file);
    }

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
