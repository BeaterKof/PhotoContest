package com.photocontest.utils;

import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;
import sun.util.calendar.BaseCalendar;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Andrei
 * Date: 1/14/16
 * Time: 4:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class UniqueValueGenerators {

    /**
     * Generates a 66 characters string containing random numbers and the current date and time.
     *
     * @return a string of 66 characters
     */
    public static String generateString(){
        String filename = "";

        long millis = System.currentTimeMillis();

        Calendar c = Calendar.getInstance();
        String datetime = new SimpleDateFormat("ddMMMyyyyHHmmss").format(c.getTime());
        String randChars = UUID.randomUUID().toString();

        filename = randChars + "_" + datetime + "_" + millis;

        return filename;
    }
}
