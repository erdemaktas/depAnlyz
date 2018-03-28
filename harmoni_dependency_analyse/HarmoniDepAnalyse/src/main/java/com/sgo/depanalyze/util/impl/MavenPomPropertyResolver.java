package com.sgo.depanalyze.util.impl;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import com.sgo.depanalyze.util.MavenPomProperties;

// TODO: Auto-generated Javadoc
/**
 * The Class MavenPomPropertyResolver.
 * 
 * @author Selçuk Giray ÖZDAMAR
 * @since May 27, 2016
 */
public class MavenPomPropertyResolver {
    /**
     * Resolve pom properties.
     * 
     * @param jarFilePath
     *            the jar file path
     * @return the maven pom properties
     * @author Selçuk Giray ÖZDAMAR
     * @since May 27, 2016
     */
    public static MavenPomProperties resolvePomProperties(String jarFilePath) {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarFilePath);
        } catch (IOException e) {
            throw new RuntimeException("IOException occured while creating JarFile object, file: " + jarFilePath, e);
        }
        // Getting the files into the jar
        Enumeration<? extends JarEntry> enumeration = jarFile.entries();
        // Iterates into the files in the jar file
        while (enumeration.hasMoreElements()) {
            ZipEntry zipEntry = enumeration.nextElement();
            // Is this a class?
            if (zipEntry.getName().contains("pom.xml")) {
                MavenPomProperties pomProperties = new MavenPomParser().parsePomFile(jarFile, zipEntry);
                return pomProperties;
            }
        }
        return null;
    }
}
