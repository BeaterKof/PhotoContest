package com.photocontest.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/7/16
 * Time: 2:50 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "reports")
public class Report implements Serializable {

    /**
     * The Report ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id")
    private long report_id;

    /**
     * The reporter email address
     */
    private String reporter_email;

    /**
     * The Report File ID
     */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "file_id")
    private File file;

    /**
     * The Contest ID
     */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contest_id")
    private Contest contest;

    /**
     * The Report message
     */
    @Size(max = 100)
    private String message;

    /**
     * Gets the Report ID
     * @return the Report ID
     */
    public long getReport_id() {
        return report_id;
    }

    /**
     * Sets the Report ID
     * @param report_id the Report ID
     */
    public void setReport_id(long report_id) {
        this.report_id = report_id;
    }

    /**
     * Gets the Report email address
     * @return the Report email address
     */
    public String getReporter_email() {
        return reporter_email;
    }

    /**
     * Sets the Report email address
     * @param reporter_email the Report email address
     */
    public void setReporter_email(String reporter_email) {
        this.reporter_email = reporter_email;
    }

    /**
     * Gets the Report message
     * @return the Report message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the Report message
     * @param message the Report message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }
}
