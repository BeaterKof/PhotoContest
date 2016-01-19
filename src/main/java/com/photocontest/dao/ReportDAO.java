package com.photocontest.dao;

import com.photocontest.dao.generic.GenericDAO;
import com.photocontest.model.File;
import com.photocontest.model.Report;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/19/16
 * Time: 2:27 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ReportDAO extends GenericDAO<Report, Integer> {

    boolean exists(long id);
}
