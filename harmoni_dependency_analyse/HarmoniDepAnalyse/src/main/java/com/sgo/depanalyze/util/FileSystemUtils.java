package com.sgo.depanalyze.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

/**
 * The Class FileSystemUtils.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:54 AM
 */
public class FileSystemUtils {
    /**
     * List files.
     * 
     * @param folderPath
     *            the folder path
     * @return the list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static List<String> listFiles(String folderPath) {
        List<String> filePathList = new ArrayList<String>();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                filePathList.add(listOfFiles[i].getAbsolutePath().replace("\\", "/"));
            } else if (listOfFiles[i].isDirectory()) {
                // System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        return filePathList;
    }

    /**
     * Removes the extension.
     * 
     * @param fileNameWithExt
     *            the file name with ext
     * @return the string
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static String removeExtension(String fileNameWithExt) {
        String fileNameWithOutExt = FilenameUtils.removeExtension(fileNameWithExt);
        return fileNameWithOutExt;
    }

    /**
     * Gets the base name.
     * <p>
     * </p>
     * 
     * @param filename
     *            the filename
     * @return the base name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static String getBaseName(String filename) {
        return FilenameUtils.getBaseName(filename);
    }

    /**
     * Checks if is folder exists.
     * 
     * @param folderPath
     *            the folder path
     * @return true, if is folder exists
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since 25.Haz.2014 03:58:48
     */
    public static boolean isFolderExists(String folderPath) {
        return new File(folderPath).exists();
    }
}
