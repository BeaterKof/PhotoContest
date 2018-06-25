package com.photocontest.services.impl;

import com.photocontest.dao.ReportDAO;
import com.photocontest.exceptions.ReportNotFoundException;
import com.photocontest.model.Report;
import com.photocontest.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 2:29 AM
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDAO reportDAO;

    public ReportDAO getReportDAO() {
        return reportDAO;
    }

    public void setReportDAO(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    /**
     * Creates a new report in the database.
     *
     * @param report the Report to be created
     */

    @Override
    public void createReport(Report report) throws ReportNotFoundException {
        if(reportDAO.exists(report.getReport_id())){
            throw new ReportNotFoundException(report.getReport_id());
        }
        reportDAO.save(report);
    }

    /**
     * Removes a Report from the database.
     *
     * @param report the Report to be deleted
     * @throws ReportNotFoundException if Report does not exist in the database
     */

    @Override
    public void deleteReport(Report report) throws ReportNotFoundException {
        if(!reportDAO.exists(report.getReport_id())){
            throw new ReportNotFoundException(report.getReport_id());
        }
        reportDAO.delete(report);
    }

    /**
     * Get all the Reports from the database.
     *
     * @return a list of all the Reports from the database
     */

    @Override
    public List<Report> getAllReports() {
        List<Report> reportList = reportDAO.findAll();
        if(reportList == null){
            reportList = new ArrayList<Report>();
        }
        return reportList;
    }

    /**
     * Gets an Report from the database by its ID.
     *
     * @param id the Report ID
     * @return the Report with the given ID
     * @throws ReportNotFoundException if the Report does not exist
     */

    @Override
    public Report getReportById(long id) throws ReportNotFoundException{
        if(!reportDAO.exists(id)){
            throw new ReportNotFoundException(id);
        }
        return reportDAO.findById(id);
    }

    @Override
    public void updateReport(Report report) throws ReportNotFoundException {
        if(!reportDAO.exists(report.getReport_id())){
            throw new ReportNotFoundException(report.getReport_id());
        } else {
            reportDAO.update(report);
        }
    }

    @Override
    public List<Report> getReportsByFileId(long fileId) {
        List<Report> reportList = reportDAO.getReportsByFileId(fileId);
        if(reportList == null){
            return new ArrayList<Report>();
        } else {
            return reportList;
        }
    }

    @Override
    public List<Report> getReportsByContestId(long contestId) {
        List<Report> reportList = reportDAO.getReportsByFileId(contestId);
        if(reportList == null){
            return new ArrayList<Report>();
        } else {
            return reportList;
        }
    }

    @Override
    public void deleteFileReports(long fileId) {
        List<Report> reportList = getReportsByFileId(fileId);
        for( Report r : reportList){
            reportDAO.delete(r);
        }
    }

    @Override
    public void deleteContestReports(long contestId) {
        List<Report> reportList = getReportsByFileId(contestId);
        for( Report r : reportList){
            reportDAO.delete(r);
        }
    }
}
