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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contest_id")
    private long contest_id;

    @Size(min = 2, max=50)
    private String name;

    @Size(max = 100)
    private String description;

    @NotNull
    private String prize;

    @NotNull
    private Date start_date;

    @NotNull
    private Date finish_date;

    @OneToMany(mappedBy = "contest",fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<File> fileList = new ArrayList<File>();

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

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }
}
