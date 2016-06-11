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

    /**
     * Compares two Files by their number of voters.
     *
     * @param o1 first File to be compared
     * @param o2 the second File to be compared
     * @return a negative value if the first File number of voters smaller than the second
     * @return a positive value if the first File number of voters is bigger than the second
     * @return if the File number of voters are equal
     */

    @Override
    public int compare(File o1, File o2) {
        return ((Integer)o2.getVoterList().size()).compareTo((Integer)o1.getVoterList().size());
    }
}
