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

    void createReport(Report report) throws ReportNotFoundException;

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

    /**
     * Updates a Report entity.
     * @param report the new report value
     * @throws ReportNotFoundException if report could not be found
     */

    void updateReport(Report report) throws ReportNotFoundException;

    /**
     * Gets all the Reports with a File ID.
     * @param fileId the ID of the File
     * @return the Reports list
     */

    List<Report> getReportsByFileId(long fileId);

    /**
     * Gets all the Reports with a Contest ID.
     * @param contestId the ID of the Contest
     * @return the Reports list
     */

    public List<Report> getReportsByContestId(long contestId);

    /**
     * Delete all Reports for a File by the File ID.
     * @param fileId the File ID
     */

    public void deleteFileReports(long fileId);

    /**
     * Delete all Reports for a Contest by the Contest ID.
     * @param contestId the Contest ID
     */

    public void deleteContestReports(long contestId);
}
