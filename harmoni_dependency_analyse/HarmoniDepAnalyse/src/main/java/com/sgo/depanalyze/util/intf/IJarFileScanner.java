package com.sgo.depanalyze.util.intf;

import java.io.IOException;
import java.net.URL;
import java.util.jar.JarFile;

import com.sgo.depanalyze.datatypes.HarmoniJarEntries;

/**
 * The Interface IJarFileScanner.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:53 AM
 */
public interface IJarFileScanner {
    /**
     * scan jar entries.
     * 
     * @param jarFile
     *            path of jar file
     * @return the map
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    HarmoniJarEntries scanJar(String jarFile) throws RuntimeException;

    /**
     * Scan jar.
     * 
     * @param jarFile
     *            the jar file
     * @return the map
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    HarmoniJarEntries scanJar(JarFile jarFile);

    /**
     * Scan jar.
     * 
     * @param url
     *            the url
     * @return the harmoni jar entries
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Mar 3, 2014 6:06:50 PM
     */
    HarmoniJarEntries scanJar(URL url);
}
