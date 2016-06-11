package com.photocontest.utils;

import com.photocontest.model.File;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;

/**
 * Created with IntelliJ IDEA.
 * User: Aioanei Andrei
 * Date: 5/28/16
 * Time: 9:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileUtils {
    public static final Logger logger = Logger.getLogger(FileUtils.class);

    /**
     * Deletes a file from disk.
     *
     * @param context the current application servlet context
     * @param fisier the File data used to delete the file itself from the disk.
     */

    public static void deleteFileFromDisk(ServletContext context,File fisier){

        String filePath = context.getRealPath(fisier.getPath());
        java.io.File file = new java.io.File(filePath);

        if( file != null || file.exists()){
            file.delete();
        } else {
            logger.error("Fisierul nu a fost gasit pe disc. Calea fisierului este: " + filePath);
        }
    }
}
