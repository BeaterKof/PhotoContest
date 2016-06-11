package com.photocontest.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Aioanei Andrei
 * Date: 5/21/16
 * Time: 12:03 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "file_voter")
public class FileVoter {

    /**
     * The FileVoter ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long file_voter_id;

    /**
     * The File ID
     */
    private long file_id;

    /**
     * The Voter ID
     */
    private long voter_id;

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
     * Gets the File voter ID
     * @return
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
     * Gets the FileVoter ID
     * @return the FileVoter ID
     */
    public long getFile_voter_id() {
        return file_voter_id;
    }

    /**
     * Sets the FileVoter ID
     * @param file_voter_id the FileVoter ID
     */
    public void setFile_voter_id(long file_voter_id) {
        this.file_voter_id = file_voter_id;
    }
}
