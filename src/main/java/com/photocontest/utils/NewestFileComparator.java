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

    /**
     * Compares two Files by their creation dates.
     *
     * @param o1 first File to be compared
     * @param o2 the second File to be compared
     * @return a negative value if the first File creation date is not older than the second
     * @return a positive value if the first File creation date is older than the second
     * @return if the File creation dates are equal
     */

    @Override
    public int compare(File o1, File o2) {
        return o2.getDate_added().compareTo(o1.getDate_added());
    }
}
