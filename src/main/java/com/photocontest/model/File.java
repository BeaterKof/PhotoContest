package com.photocontest.model;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id")
    private long file_id;

    @Size(min = 2, max = 30)
    private String name;

    private String type;

    @Size(max = 100)
    private String description;

    private Date date_added;

    private String path;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false, updatable = true, insertable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "contest_id",nullable = true, updatable = true, insertable = true)
    private Contest contest;

    @OneToMany(mappedBy = "file",fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    List<Voter> voterList = new ArrayList<Voter>();

    public long getFile_id() {
        return file_id;
    }

    public void setFile_id(long file_id) {
        this.file_id = file_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDate_added() {
        return date_added;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public List<Voter> getVoterList() {
        return voterList;
    }

    public void setVoterList(List<Voter> voterList) {
        this.voterList = voterList;
    }
}
