package com.photocontest.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

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

    private long reporter_id;

    @Size(max = 100)
    private String message;

    public long getReport_id() {
        return report_id;
    }

    public void setReport_id(long report_id) {
        this.report_id = report_id;
    }

    public long getReporter_id() {
        return reporter_id;
    }

    public void setReporter_id(long reporter_id) {
        this.reporter_id = reporter_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
