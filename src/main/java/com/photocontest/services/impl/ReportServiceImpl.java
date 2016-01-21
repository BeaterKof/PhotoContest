package com.photocontest.services.impl;

import com.photocontest.dao.ReportDAO;
import com.photocontest.exceptions.ReportNotFoundException;
import com.photocontest.model.Report;
import com.photocontest.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 2:29 AM
 * To change this template use File | Settings | File Templates.
 */

public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDAO reportDAO;

    public void setReportDAO(ReportDAO reportDAO){
        this.reportDAO = reportDAO;
    }

    public ReportDAO getReportDAO() {
        return reportDAO;
    }

    @Override
    public void createReport(Report report) {
        reportDAO.save(report);
    }

    @Override
    public void deleteReport(Report report) throws ReportNotFoundException {
        if(!reportDAO.exists(report.getReport_id())){
            throw new ReportNotFoundException(report.getReport_id());
        }
        reportDAO.delete(report);
    }
}
