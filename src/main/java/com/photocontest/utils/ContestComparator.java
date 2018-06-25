package com.photocontest.utils;

import com.photocontest.model.Contest;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/21/16
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContestComparator implements Comparator<Contest> {

    /**
     * Compares two Contests by their finish dates.
     *
     * @param o1 first Contest to be compared
     * @param o2 the second Contest to be compared
     * @return a negative value if the first Contest finish date is not older than the second
     * @return a positive value if the first Contest is older than the second
     * @return if the Contests finish dates are equal
     */

    @Override
    public int compare(Contest o1, Contest o2) {
        return o1.getFinish_date().compareTo(o2.getFinish_date());
    }
}
