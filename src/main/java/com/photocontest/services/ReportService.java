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

    void createReport(Report report);
    void deleteReport(Report report) throws ReportNotFoundException;
    List<Report> getAllReports();
    Report getReportById(long id) throws ReportNotFoundException;
}
