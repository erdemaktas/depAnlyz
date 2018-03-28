package com.sgo.depanalyze.google_reflections;

import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.sgo.depanalyze.util.Constants;
import com.sgo.depanalyze.util.FileSystemClassLoader;

/**
 * The Class HmnReflections.
 * 
 * @author U065352 - SELÇUK GİRAY ÖZDAMAR
 * @since Dec 27, 2013 9:37:36 AM
 */
public class HmnReflections {
    /** The reflections. */
    private Reflections reflections;
    /** The logger. */
    static Logger logger = Logger.getLogger(HmnReflections.class);

    /**
     * The Class SingletonHolder.
     * 
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:37:36 AM
     */
    static class SingletonHolder {
        /** The Constant INSTANCE. */
        static final HmnReflections INSTANCE = new HmnReflections();
    }

    /**
     * The Constructor.
     * 
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:37:36 AM
     */
    private HmnReflections() {
        logger.info("initialize of Reflections started...");
        ClassLoader clsLoader = FileSystemClassLoader.getClassLoader();
        this.reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(Constants.HMN_CLASS_PREFIX, clsLoader)).addClassLoader(clsLoader));
        // this.reflections = new Reflections(Constants.HMN_CLASS_PREFIX);
        logger.info("initialize of Reflections finished...");
    }

    /**
     * Gets the instance.
     * 
     * @return the instance
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:37:36 AM
     */
    public static HmnReflections getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Gets the reflections.
     * 
     * @return the reflections
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:37:36 AM
     */
    public static Reflections getReflections() {
        return getInstance().reflections;
    }
}
