package com.sgo.depanalyze.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * The Class FileSystemClassLoader.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:53 AM
 */
public class FileSystemClassLoader {
    /** The Constant primitiveTypeNameMap. */
    private static final Map<String, Class<?>> PRIMITIVE_TYPE_MAP = new HashMap<String, Class<?>>(16);
    /** The logger. */
    private static Logger logger = Logger.getLogger(FileSystemClassLoader.class);
    static {
        List<Class<?>> primitiveTypes = new ArrayList<Class<?>>();
        primitiveTypes.addAll(Arrays.asList(new Class<?>[] { /***/
        boolean.class, /***/
        byte.class, /***/
        char.class, /***/
        double.class, /***/
        float.class, /***/
        int.class, /***/
        long.class, /***/
        short.class /***/
        }));
        for (Class<?> primitiveClazz : primitiveTypes) {
            PRIMITIVE_TYPE_MAP.put(primitiveClazz.getName(), primitiveClazz);
        }
    }
    /** The class loader. */
    private static ClassLoader CLASS_LOADER = null;

    /**
     * Gets the class loader.
     * <p>
     * </p>
     * 
     * @return the class loader
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static ClassLoader getClassLoader() {
        return CLASS_LOADER;
    }

    /**
     * Adds the url.
     * 
     * @param u
     *            the URL
     * @throws RuntimeException
     *             the runtime exception
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static void addURL(URL u) throws RuntimeException {
        addURL(new URL[] { u });
    }

    /**
     * Adds the url.
     * 
     * @param urls
     *            the urls
     * @throws RuntimeException
     *             the runtime exception
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static void addURL(URL[] urls) throws RuntimeException {
        if (CLASS_LOADER == null) {
            CLASS_LOADER = URLClassLoader.newInstance(urls, FileSystemClassLoader.class.getClassLoader());
            return;
        }
        URLClassLoader sysLoader = (URLClassLoader) CLASS_LOADER;
        URL existingUrls[] = sysLoader.getURLs();
        Map<String, URL> existingUrlMap = new HashMap<String, URL>();
        for (int i = 0; i < existingUrls.length; i++) {
            existingUrlMap.put(existingUrls[i].toString(), existingUrls[i]);
        }
        //
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] { URL.class });
        } catch (SecurityException e) {
            throw new RuntimeException("SecurityException occured, getDeclaredMethod:'addURL' failed for URLClassLoader", e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("NoSuchMethodException occured, getDeclaredMethod:'addURL' failed for URLClassLoader", e);
        }
        method.setAccessible(true);
        for (URL url : urls) {
            if (!existingUrlMap.containsKey(url.toString())) {
                try {
                    method.invoke(sysLoader, new Object[] { url });
                } catch (IllegalAccessException e) {
                    // logger.error("IllegalAccessException occured for url: " + url.toString());
                    throw new RuntimeException("IllegalAccessException occured, 'addURL' invocation failed with reflection, URL:" + url.toString(), e);
                } catch (InvocationTargetException e) {
                    // logger.error("InvocationTargetException occured for url: " + url.toString());
                    throw new RuntimeException("InvocationTargetException occured, 'addURL' invocation failed with reflection, URL:" + url.toString(), e);
                }
            }
        }
    }

    /**
     * Adds the jar file.
     * 
     * @param file
     *            the file
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @throws RuntimeException
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static void addJarFile(String file) throws RuntimeException {
        try {
            addURL(new File(file).toURI().toURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException("MalformedURLException occured for file: " + file, e);
        }
    }

    /**
     * Adds the jar file.
     * 
     * @param fileList
     *            the file list
     * @throws RuntimeException
     *             the runtime exception
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static void addJarFile(List<String> fileList) throws RuntimeException {
        if (fileList == null || fileList.isEmpty()) {
            throw new IllegalArgumentException("fileList is null or empty");
        }
        URL[] urls = new URL[fileList.size()];
        for (int i = 0; i < fileList.size(); i++) {
            String filePath = fileList.get(i);
            File f = new File(filePath);
            URI uri = f.toURI();
            try {
                urls[i] = uri.toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException("MalformedURLException occured for file: " + filePath, e);
            }
        }
        addURL(urls);
    }

    /**
     * Gets the clazz.
     * <p>
     * </p>
     * 
     * @param className
     *            the clazz name
     * @return the clazz
     * @throws ClassNotFoundException
     *             the class not found exception
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static Class<?> loadClass(String className) throws ClassNotFoundException {
        if (PRIMITIVE_TYPE_MAP.containsKey(className)) {
            return PRIMITIVE_TYPE_MAP.get(className);
        }
        Class<?> result = null;
        try {
            result = CLASS_LOADER.loadClass(className);
            // } catch (ClassNotFoundException e) {
            // String errLog = "Cannot load class with name: " + className;
            // logger.debug(errLog, e);
            // throw new ClassNotFoundException(errLog, e);
            // } catch (java.lang.IncompatibleClassChangeError e) {
            // String errLog = "Cannot load class with name: " + className;
            // logger.debug(errLog, e);
            // throw new ClassNotFoundException(errLog, e);
        } catch (Throwable t) {
            String errLog = "Cannot load class with name: " + className;
            logger.debug(errLog, t);
            throw new ClassNotFoundException(errLog, t);
        }
        return result;
    }
}
