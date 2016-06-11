package com.photocontest.dao;

import com.photocontest.dao.generic.GenericDAO;
import com.photocontest.model.File;
import com.photocontest.model.Report;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 2:27 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ReportDAO extends GenericDAO<Report, Long> {

    /**
     * Checks if an report exists.
     *
     * @param id the ID of the report
     * @return true if the Report exists in the database.
     * @return false if the Report does not exist in the database.
     */

    boolean exists(long id);
    List<Report> getReportsByFileId(long fileId);
    public List<Report> getReportsByContestId(long contestId);
}
