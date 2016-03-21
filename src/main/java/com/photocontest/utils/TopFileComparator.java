package com.photocontest.utils;

import com.photocontest.model.File;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 2/3/16
 * Time: 1:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class TopFileComparator implements Comparator<File> {

    @Override
    public int compare(File o1, File o2) {
        return ((Integer)o2.getVoterList().size()).compareTo((Integer)o1.getVoterList().size());
    }
}
