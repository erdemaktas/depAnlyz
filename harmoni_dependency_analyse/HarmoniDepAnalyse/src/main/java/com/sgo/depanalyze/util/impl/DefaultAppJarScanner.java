package com.sgo.depanalyze.util.impl;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.apache.log4j.Logger;

import com.sgo.depanalyze.datatypes.HarmoniJarEntries;
import com.sgo.depanalyze.enums.JarEntryHmnType;
import com.sgo.depanalyze.util.FileSystemClassLoader;
import com.sgo.depanalyze.util.MavenPomProperties;
import com.sgo.depanalyze.util.intf.IJarFileScanner;

/**
 * The Class DefaultAppJarScanner.
 * 
 * @author U065352 - SELÇUK GİRAY ÖZDAMAR
 * @since Mar 3, 2014 8:03:30 PM
 */
public class DefaultAppJarScanner implements IJarFileScanner {
    /** The logger. */
    static Logger logger = Logger.getLogger(DefaultAppJarScanner.class);
    /** The clazz loader. */
    private ClassLoader clazzLoader;

    public DefaultAppJarScanner() {
    }

    public DefaultAppJarScanner(ClassLoader clazzLoader) {
        this.clazzLoader = clazzLoader;
    }

    public void setClassLoader(ClassLoader clazzLoader) {
        this.clazzLoader = clazzLoader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.IJarFileScanner#scanJar(java.lang.String)
     */
    public HarmoniJarEntries scanJar(String jarFile) throws RuntimeException {
        JarFile jar = null;
        try {
            jar = new JarFile(jarFile);
        } catch (IOException e) {
            throw new RuntimeException("IOException occured while createil JarFile object, file: " + jarFile, e);
        }
        return scanJar(jar);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.IJarFileScanner#scanJar(java.util.jar.JarFile)
     */
    @Override
    public HarmoniJarEntries scanJar(JarFile jarFile) {
        MavenPomProperties pomProperties = null;
        Map<JarEntryHmnType, List<Class<?>>> classes = new HashMap<JarEntryHmnType, List<Class<?>>>();
        List<Class<?>> clazzes = new ArrayList<Class<?>>();
        classes.put(JarEntryHmnType.SERVICE_CLASS, clazzes);
        // Getting the files into the jar
        Enumeration<? extends JarEntry> enumeration = jarFile.entries();
        // Iterates into the files in the jar file
        while (enumeration.hasMoreElements()) {
            ZipEntry zipEntry = enumeration.nextElement();
            // Is this a class?
            if (zipEntry.getName().endsWith(".class")) {
                // Relative path of file into the jar.
                String className = zipEntry.getName();
                // Complete class name
                className = className.replace(".class", "").replace("/", ".");
                try {
                    // Load class definition from JVM
                    Class<?> clazz = FileSystemClassLoader.loadClass(className);
                    // Verify the type of the "class"
                    if (!(clazz.isInterface() || clazz.isAnnotation() || clazz.isEnum())) {
                        clazzes.add(clazz);
                    }
                } catch (Exception e) {
                    logger.error("Exception occured: " + e.getMessage(), e);
                }
            } else if (zipEntry.getName().contains("pom.xml")) {
                pomProperties = new MavenPomParser().parsePomFile(jarFile, zipEntry);
            }
        }
        HarmoniJarEntries hmnJarEntries = new HarmoniJarEntries(null, clazzes, null);
        hmnJarEntries.setPomProperties(pomProperties);
        return hmnJarEntries;
    }

    @Override
    public HarmoniJarEntries scanJar(URL url) {
        JarFile jar = null;
        try {
            jar = new JarFile(new File(url.toURI()));
        } catch (IOException e) {
            throw new RuntimeException("IOException occured while creating JarFile object, file: " + url.toString(), e);
        } catch (URISyntaxException e) {
            throw new RuntimeException("URISyntaxException occured while creating JarFile object, file: " + url.toString(), e);
        }
        return scanJar(jar);
    }
}
