package com.photocontest.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contest_id")
    private long contest_id;
    private String name;
    private String description;
    private String prize;
    private Date start_date;
    private Date finish_date;

    public long getContest_id() {
        return contest_id;
    }

    public void setContest_id(long contest_id) {
        this.contest_id = contest_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }
}
