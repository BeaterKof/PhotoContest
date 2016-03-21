package com.photocontest.utils;

import com.photocontest.model.File;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 2/3/16
 * Time: 1:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class NewestFileComparator implements Comparator<File> {

    @Override
    public int compare(File o1, File o2) {
        return o2.getDate_added().compareTo(o1.getDate_added());
    }
}
