package com.photocontest.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/7/16
 * Time: 2:54 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "voters")
public class Voter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "voter_id")
    private long voter_id;

    private String ip_address;

    @ManyToOne
    @JoinColumn(name = "file_id",nullable = false, updatable = true, insertable = true)
    private File file;

    public long getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(long voter_id) {
        this.voter_id = voter_id;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
