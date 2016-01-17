package com.photocontest.model;

import javax.persistence.*;
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

}
