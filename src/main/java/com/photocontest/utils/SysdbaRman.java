package com.photocontest.utils;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Aioanei Andrei
 * Date: 3/27/16
 * Time: 12:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class SysdbaRman {
    private static final Logger logger = Logger.getLogger(SysdbaRman.class);

    /**
     * Opens an CMD on the server and executes an RMAN backup for the database.
     */

    public static void backup(){
        try{
            ProcessBuilder builder = new ProcessBuilder(
                    "rman.exe", "cmdfile C:\\rman_backup.cmd");

            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                logger.info(line);
            }
        }catch(java.io.IOException e){
            logger.error(e.getMessage());
        }
    }

    /**
     * Opens an CMD on the server and executes an RMAN restore for the database.
     */

    public static void restore(){
        try{
            ProcessBuilder builder = new ProcessBuilder(
                "rman.exe", "cmdfile C:\\rman_restore.cmd");

            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                logger.info(line);
            }
        }catch(java.io.IOException e){
            logger.error(e.getMessage());
        }
    }
}
