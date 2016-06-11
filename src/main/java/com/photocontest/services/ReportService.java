package com.photocontest.services;

import com.photocontest.exceptions.FileNotFoundException;
import com.photocontest.exceptions.ReportNotFoundException;
import com.photocontest.model.File;
import com.photocontest.model.Report;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/7/16
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ReportService {

    /**
     * Creates a new report in the database.
     *
     * @param report the Report to be created
     */

    void createReport(Report report);

    /**
     * Removes a Report from the database.
     *
     * @param report the Report to be deleted
     * @throws ReportNotFoundException if Report does not exist in the database
     */

    void deleteReport(Report report) throws ReportNotFoundException;

    /**
     * Get all the Reports from the database.
     *
     * @return a list of all the Reports from the database
     */

    List<Report> getAllReports();

    /**
     * Gets an Report from the database by its ID.
     *
     * @param id the Report ID
     * @return the Report with the given ID
     * @throws ReportNotFoundException if the Report does not exist
     */

    Report getReportById(long id) throws ReportNotFoundException;

    void updateReport(Report report) throws ReportNotFoundException;

    List<Report> getReportsByFileId(long fileId);
    public List<Report> getReportsByContestId(long contestId);
    public void deleteFileReports(long fileId);
    public void deleteContestReports(long contestId);
}
