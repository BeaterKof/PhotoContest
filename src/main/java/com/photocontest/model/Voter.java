package com.photocontest.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * The Voter ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "voter_id")
    private long voter_id;

    /**
     * The Voter IP address
     */
    private String ip_address;

    /**
     * The Voter File list
     */
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "voterList")
    private List<File> files = new ArrayList<File>();

    /**
     * Gets the Voter Id
     * @return the Voter Id
     */
    public long getVoter_id() {
        return voter_id;
    }

    /**
     * Sets the Voter ID
     * @param voter_id the Voter ID
     */
    public void setVoter_id(long voter_id) {
        this.voter_id = voter_id;
    }

    /**
     * Gets the Voter IP Address
     * @return the Voter IP Address
     */
    public String getIp_address() {
        return ip_address;
    }

    /**
     * Sets the Voter IP Address
     * @param ip_address the Voter IP Address
     */
    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    /**
     * Gets the Voter files
     * @return the Voter files
     */
    public List<File> getFiles() {
        return files;
    }

    /**
     * Sets the Voter files
     * @param files the Voter files
     */
    public void setFiles(List<File> files) {
        this.files = files;
    }
}
