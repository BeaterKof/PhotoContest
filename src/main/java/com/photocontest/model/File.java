package com.photocontest.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 5:55 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "files")
public class File implements Serializable {

    /**
     * The File ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id")
    private long file_id;

    /**
     * The File name
     */
    @Size(min = 2, max = 30)
    private String name;

    /**
     * The File type
     */
    private String type;

    /**
     * The File description
     */
    @Size(max = 100)
    private String description;

    /**
     * The File add date
     */
    private Date date_added;

    /**
     * The File path
     */
    private String path;

    /**
     * The User owning the File
     */
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false, updatable = true, insertable = true)
    private User user;

    /**
     * The Contest in which the File is in
     */
    @ManyToOne
    @JoinColumn(name = "contest_id",nullable = true, updatable = true, insertable = true)
    private Contest contest;

    /**
     * The File list of voters
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "file_voter", joinColumns = {
            @JoinColumn(name = "file_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "voter_id",
                    nullable = false, updatable = false) })
    public List<Voter> voterList = new ArrayList<Voter>();

    /**
     * Gets the File ID
     * @return the File ID
     */
    public long getFile_id() {
        return file_id;
    }

    /**
     * Sets the File ID
     * @param file_id the File ID
     */
    public void setFile_id(long file_id) {
        this.file_id = file_id;
    }

    /**
     * Gets the File name
     * @return the File name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the File name
     * @param name the File name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the File type
     * @return the File type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the File type
     * @param type the File type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the File description
     * @return the File description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the File description
     * @param description the File description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the File path
     * @return the File path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the File path
     * @param path the File path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gets the File date of addition
     * @return the File date of addition
     */
    public Date getDate_added() {
        return date_added;
    }

    /**
     * Sets the File date of addition
     * @param date_added the File date of addition
     */
    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }

    /**
     * Gets the User
     * @return the User
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the User
     * @param user the File ID
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the File ID
     * @return the File ID
     */
    public Contest getContest() {
        return contest;
    }

    /**
     * Sets the File contest
     * @param contest the File contest
     */
    public void setContest(Contest contest) {
        this.contest = contest;
    }

    /**
     * Gets the File voter list
     * @return the File voter list
     */
    public List<Voter> getVoterList() {
        return voterList;
    }

    /**
     * Sets the File voter list
     * @param voterList the File voter list
     */
    public void setVoterList(List<Voter> voterList) {
        this.voterList = voterList;
    }

    /**
     * File equals method overwrite
     * @param file another File
     * @return true if the Files are equal
     * @return false if the Files are not equal
     */
    public boolean equals(File file){
        if(this.file_id == file.file_id){
            return true;
        }
        return false;
    }
}
