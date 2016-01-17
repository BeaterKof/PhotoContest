package com.photocontest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/14/16
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataValidator {

    public static boolean isEmailAddress(String email){
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = pattern.matcher(email);

        return mat.matches();
    }
}
