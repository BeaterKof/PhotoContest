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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "report_id")
    private long report_id;

    private String reporter_email;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "file_id",nullable = true, updatable = true, insertable = true)
    private File file;

    @Size(max = 100)
    private String message;

    public long getReport_id() {
        return report_id;
    }

    public void setReport_id(long report_id) {
        this.report_id = report_id;
    }

    public String getReporter_email() {
        return reporter_email;
    }

    public void setReporter_email(String reporter_email) {
        this.reporter_email = reporter_email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
