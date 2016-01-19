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

    private long ip_address;

    @ManyToOne
    @JoinColumn(name = "file_id",nullable = false, updatable = true, insertable = true)
    private File file;
}
