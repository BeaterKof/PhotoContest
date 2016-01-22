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

    @Override
    public int compare(Contest o1, Contest o2) {
        return o1.getFinish_date().compareTo(o2.getFinish_date());
    }
}
