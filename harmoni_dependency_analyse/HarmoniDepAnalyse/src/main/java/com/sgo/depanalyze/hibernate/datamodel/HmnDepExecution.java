package com.sgo.depanalyze.hibernate.datamodel;

import java.util.Date;

// Generated Oct 24, 2013 9:13:46 AM by Hibernate Tools 3.4.0.CR1
/**
 * DepHmnArtifacts generated by hbm2java.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 10:14:07 AM
 */
public class HmnDepExecution extends HmnDepDatamodel implements java.io.Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2353066641486866935L;
    /** The host computer. */
    private String hostComputer;
    /** The execution date. */
    private Date executionDate;
    /** The finish date. */
    private Date finishDate;
    /** The environment. */
    private String environment;

    /**
     * Instantiates a new hmn dep execution.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public HmnDepExecution() {
        super();
    }

    /**
     * Instantiates a new hmn dep execution.
     * 
     * @param id
     *            the id
     * @param executionDate
     *            the execution date
     * @param hostComputer
     *            the host computer
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public HmnDepExecution(Long id, String environment, Date executionDate, String hostComputer) {
        super(id);
        this.environment = environment;
        this.hostComputer = hostComputer;
        this.executionDate = executionDate;
    }

    /**
     * Gets the host computer.
     * <p>
     * </p>
     * 
     * @return the host computer
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public String getHostComputer() {
        return hostComputer;
    }

    /**
     * Sets the host computer.
     * <p>
     * </p>
     * 
     * @param hostComputer
     *            the new host computer
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public void setHostComputer(String hostComputer) {
        this.hostComputer = hostComputer;
    }

    /**
     * Gets the execution date.
     * <p>
     * </p>
     * 
     * @return the execution date
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public Date getExecutionDate() {
        return executionDate;
    }

    /**
     * Sets the execution date.
     * <p>
     * </p>
     * 
     * @param executionDate
     *            the new execution date
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    /**
     * Sets the finish date.
     * 
     * @param finishDate
     *            the finish date
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * Gets the finish date.
     * 
     * @return the finish date
     */
    public Date getFinishDate() {
        return finishDate;
    }

    /**
     * Sets the environment.
     *
     * @param environment the new environment
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