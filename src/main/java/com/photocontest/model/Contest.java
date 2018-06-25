package com.photocontest.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 10/4/15
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "contests")
public class Contest implements Serializable {

    /**
     * The Contest ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contest_id")
    private long contest_id;

    /**
     * The Contest name
     */
    @Size(min = 2, max=50)
    private String name;

    /**
     * The Contest description
     */
    @Size(max = 100)
    private String description;

    /**
     * The Contest prize
     */
    @NotNull
    private String prize;

    /**
     * The Contest start date
     */
    @NotNull
    private Date start_date;

    /**
     * The Contest ending date
     */
    @NotNull
    private Date finish_date;

    /**
     * The ID of the admin that created the contest
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id", unique= true, nullable=true, insertable=true, updatable=true)
    private Admin admin;

    /**
     * The ID of the Contest winner.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_id", unique= true, nullable=true, insertable=true, updatable=true)
    private User winner;

    /**
     * The list of Files that entered the Contest
     */
    @OneToMany(mappedBy = "contest",fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<File> fileList = new ArrayList<File>();

    /**
     * Get the Contest ID
     * @return the Contest ID
     */
    public long getContest_id() {
        return contest_id;
    }

    /**
     * Set Contest ID
     * @param contest_id the Contest ID
     */
    public void setContest_id(long contest_id) {
        this.contest_id = contest_id;
    }

    /**
     * Get the Contest name
     * @return the Contest name
     */
    public String getName() {
        return name;
    }

    /**
     * Set Contest name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the Contest description
     * @return the Contest description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set Contest description
     * @param description the Contest description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the Contest prize
     * @return the Contest prize
     */
    public String getPrize() {
        return prize;
    }

    /**
     * Set Contest prize
     * @param prize the Contest prize
     */
    public void setPrize(String prize) {
        this.prize = prize;
    }

    /**
     * Get the Contest start date
     * @return the Contest start date
     */
    public Date getStart_date() {
        return start_date;
    }

    /**
     * Set Contest start date
     * @param start_date the Contest start date
     */
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    /**
     * Get the Contest ending date
     * @return the Contest ending date
     */
    public Date getFinish_date() {
        return finish_date;
    }

    /**
     * Set Contest ending date
     * @param finish_date the Contest ending date
     */
    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }

    /**
     * Get the Contest file list
     * @return
     */
    public List<File> getFileList() {
        return fileList;
    }

    /**
     * Set Contest file list
     * @param fileList the Contest file list
     */
    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }
}
