package com.sgo.depanalyze.hibernate.datamodel;

// Generated Nov 6, 2013 1:49:00 PM by Hibernate Tools 3.4.0.CR1
/**
 * DepHmnParameters generated by hbm2java.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 10:14:07 AM
 */
public class HmnDepParameter extends HmnDepDatamodel implements java.io.Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6620990455036181819L;
    /** The param name. */
    private String paramName;
    /** The param value. */
    private String paramValue;
    /** The environment. */
    private String environment;

    /**
     * Instantiates a new hmn dep parameter.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public HmnDepParameter() {
        super();
    }

    /**
     * Instantiates a new hmn dep parameter.
     * 
     * @param id
     *            the id
     * @param paramName
     *            the param name
     * @param paramValue
     *            the param value
     * @param environment
     *            the environment
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public HmnDepParameter(Long id, String paramName, String paramValue, String environment) {
        super(id);
        this.paramName = paramName;
        this.paramValue = paramValue;
        this.environment = environment;
    }

    /**
     * Gets the param name.
     * <p>
     * </p>
     * 
     * @return the param name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public String getParamName() {
        return this.paramName;
    }

    /**
     * Sets the param name.
     * <p>
     * </p>
     * 
     * @param paramName
     *            the new param name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    /**
     * Gets the param value.
     * <p>
     * </p>
     * 
     * @return the param value
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public String getParamValue() {
        return this.paramValue;
    }

    /**
     * Sets the param value.
     * <p>
     * </p>
     * 
     * @param paramValue
     *            the new param value
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * Sets the environment.
     * 
     * @param environment
     *            the new environment
     * @author Selçuk Giray ÖZDAMAR
     * @since May 25, 2016
     */
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    /**
     * Gets the environment.
     * 
     * @return the environment
     * @author Selçuk Giray ÖZDAMAR
     * @since May 25, 2016
     */
    public String getEnvironment() {
        return environment;
    }
}
