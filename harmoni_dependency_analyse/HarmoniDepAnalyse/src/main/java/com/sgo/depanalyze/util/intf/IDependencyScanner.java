package com.sgo.depanalyze.util.intf;

import java.util.jar.JarFile;

import com.sgo.depanalyze.datatypes.ClassLevelDependencyList;
import com.sgo.depanalyze.datatypes.DependencyList;
import com.sgo.depanalyze.datatypes.DependencyScanResult;

/**
 * The Interface IDependencyScanner.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:53 AM
 */
public interface IDependencyScanner {
    /**
     * Scan dependencies for given jar file path.
     * 
     * @param jarFile
     *            the jar file full path
     * @return the map
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    DependencyScanResult scanDependencies(String jarFile);

    /**
     * Scan dependencies for given jar file.
     * 
     * @param jarFile
     *            the jar file
     * @return the map
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    DependencyScanResult scanDependencies(JarFile jarFile);

    /**
     * Scan dependencies for given class.
     * 
     * @param sourceClazz
     *            the source clazz
     * @return the map
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    ClassLevelDependencyList scanDependencies(Class<?> sourceClazz);

    /**
     * Scan dependencies for given method.
     * 
     * @param method
     *            the method
     * @return the dependency list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    DependencyList scanDependencies(java.lang.reflect.Method method);
}
