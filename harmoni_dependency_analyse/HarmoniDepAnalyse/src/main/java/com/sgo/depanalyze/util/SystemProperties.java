package com.sgo.depanalyze.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.sgo.depanalyze.hibernate.dao.DataAccessUtil;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepParameter;

/**
 * The Class SystemProperties.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:54 AM
 */
public class SystemProperties {
    /** The param value map. */
    private static Map<String, String> paramValueMap = new ConcurrentHashMap<String, String>();
    /** The logger. */
    private static Logger logger = Logger.getLogger(SystemProperties.class);
    private static String environment;

    /**
     * Instantiates a new system properties.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    private SystemProperties() {
    }

    /**
     * Initialize.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    @SuppressWarnings("rawtypes")
    public static void initialize(String environment) {
        logger.debug("system parameter initialization started for environment:" + environment);
        List parameterList = DataAccessUtil.findAllParameters(environment);
        if (parameterList == null || parameterList.isEmpty()) {
            return;
        }
        for (Object obj : parameterList) {
            HmnDepParameter datamodel = (HmnDepParameter) obj;
            logger.debug(String.format("found system parameter (%s:%s)", datamodel.getParamName(), datamodel.getParamValue()));
            paramValueMap.put(datamodel.getParamName(), datamodel.getParamValue());
        }
        logger.debug("system parameter initialization finished, " + paramValueMap.size() + " parametes found");
        //
        SystemProperties.environment = environment;
    }

    /**
     * Gets the parameter value.
     * <p>
     * </p>
     * 
     * @param parameterName
     *            the parameter name
     * @return the parameter value
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static String getParameterValue(String parameterName) {
        return paramValueMap.get(parameterName);
    }

    /**
     * Gets the environment.
     * 
     * @return the environment
     * @author Selçuk Giray ÖZDAMAR
     * @since May 27, 2016
     */
    public static String getEnvironment() {
        return environment;
    }
}
