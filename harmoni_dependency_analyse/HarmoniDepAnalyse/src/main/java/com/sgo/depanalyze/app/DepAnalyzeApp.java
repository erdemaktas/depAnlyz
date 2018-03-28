/*
 * @author U079754 A. Emre Zorlu
 * @date Jun 7, 2016
 */
package com.sgo.depanalyze.app;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.sgo.depanalyze.util.Constants;
import com.sgo.depanalyze.util.SystemProperties;
import com.sgo.depanalyze.util.impl.DependencyAnalyzeRunner;
import com.sgo.depanalyze.util.intf.IDependencyAnalyzeRunner;

/**
 * The Class DepAnalyzeApp.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 10:18:25 AM
 */
public class DepAnalyzeApp {
    /** The environment. */
    static String ENVIRONMENT = "TEST";
    /** The Constant LOG4J_CONFIG_XML. */
    static final String LOG4J_CONFIG_XML = "log4j.xml";
    /** The logger. */
    static Logger logger = null;
    /** The command line. */
    static CommandLine commandLine = null;
    /** The Constant APP_CONTEXT_FILE. */
    static final String APP_CONTEXT_FILE = "/applicationContext.xml";

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ClassNotFoundException
     *             the class not found exception
     * @throws InterruptedException
     *             the interrupted exception
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.setProperty("user.timezone", "Turkey");
        configureLogger();
        // command line arguments
        commandLine = parseCommandLine(args);
        if (commandLine != null && commandLine.hasOption("environment")) {
            ENVIRONMENT = commandLine.getOptionValue("environment");
            // APP_CONTEXT_FILE = String.format("/applicationContext-%s.xml", ENVIRONMENT);
        }
        logger.info("Dependency Analyze starting for ENVIRONMENT: " + ENVIRONMENT);
        // initialize spring application context
        @SuppressWarnings("unused")
        ApplicationContext context = new ClassPathXmlApplicationContext(APP_CONTEXT_FILE);
        // initialize system Parameters
        SystemProperties.initialize(ENVIRONMENT);
        //
        configureDepAnalyzeLogger();
        //
        // IDependencyAnalyzeRunner dependencyAnalyzeRunner = new DependencyAnalyzeRunner(args);
        IDependencyAnalyzeRunner dependencyAnalyzeRunner = new DependencyAnalyzeRunner(ENVIRONMENT);
        boolean result = dependencyAnalyzeRunner.runDependencyAnalyze();
        if (result) {
            logger.info("Dependency analyze FINISHED successfully");
        } else {
            logger.error("Dependency analyze FAILED");
        }
    }

    /**
     * Configure logger.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    private static void configureLogger() {
        URL u = DepAnalyzeApp.class.getClassLoader().getResource(LOG4J_CONFIG_XML);
        // DOMConfigurator is used to configure logger from xml configuration file
        DOMConfigurator.configure(u);
        logger = Logger.getLogger(DepAnalyzeApp.class);
        logger.debug("Log4j appender configuration is successful !!");
    }

    /**
     * Configure dep analyze logger.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 3, 2013 9:11:24 AM
     */
    private static void configureDepAnalyzeLogger() {
        // initialize log level from db parameter
        String strLevel = SystemProperties.getParameterValue(Constants.PRM_DEPANALYZE_LOGGER_LEVEL);
        if (StringUtils.hasText(strLevel)) {
            Logger.getLogger(Constants.LOGGER_DEPANALYZE).setLevel(Level.toLevel(strLevel));
        }
    }

    /**
     * Parses the command line.
     * 
     * @param args
     *            the args
     * @return the command line
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 15, 2013 5:36:05 PM
     */
    private static CommandLine parseCommandLine(String[] args) {
        final CommandLineParser parser = new BasicParser();
        CommandLine commandLine = null;
        try {
            final Options options = new Options();
            options.addOption("environment", true, "environment option");
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            logger.error("ParseException occured in parseCommandLine with command line arguments:" + args.toString(), e);
        }
        return commandLine;
    }
}
