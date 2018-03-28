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
 * The Class FeJarFileScanner.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:54 AM
 */
public class HmnFrontendJarScanner implements IJarFileScanner {
    /** The logger. */
    static Logger logger = Logger.getLogger(HmnFrontendJarScanner.class);
    /** The clazz loader. */
    private ClassLoader clazzLoader;

    /**
     * Instantiates a new fe jar file scanner.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public HmnFrontendJarScanner() {
    }

    /**
     * Instantiates a new fe jar file scanner.
     * 
     * @param clazzLoader
     *            the clazz loader
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public HmnFrontendJarScanner(ClassLoader clazzLoader) {
        this.clazzLoader = clazzLoader;
    }

    /**
     * Sets the class loader.
     * <p>
     * </p>
     * 
     * @param clazzLoader
     *            the new class loader
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
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
                    // Class<?> clazz = Class.forName(className, false, clazzLoader);
                    Class<?> clazz = FileSystemClassLoader.loadClass(className);
                    // this.clazzLoader.loadClass(className);
                    // Verify the type of the "class"
                    if (!(clazz.isInterface() || clazz.isAnnotation() || clazz.isEnum())) {
                        clazzes.add(clazz);
                    }
                // } catch (java.lang.NoClassDefFoundError e) {
                // logger.error("NoClassDefFoundError error occured: " + e.getMessage(), e);
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
