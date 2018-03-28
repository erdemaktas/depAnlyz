package com.sgo.depanalyze.util;

import org.apache.log4j.Logger;

/**
 * The Class AlarmLogger.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 28, 2013 3:33:58 PM
 */
public final class AlarmLogger {
    /** The logger. */
    private static Logger logger = Logger.getLogger(AlarmLogger.class);

    /**
     * Log error.
     * 
     * @param message
     *            the message object to log.
     * @param t
     *            the exception to log, including its stack trace
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 28, 2013 3:33:58 PM
     */
    public static void logError(Object message, Throwable t) {
        logger.error(message, t);
    }

    /**
     * Log error.
     * 
     * @param message
     *            the message object to log.
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 28, 2013 3:33:58 PM
     */
    public static void logError(Object message) {
        logger.error(message);
    }
}
