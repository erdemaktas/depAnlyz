package com.sgo.depanalyze.datatypes;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DependencyList.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 10:18:25 AM
 */
public class DependencyList {
    /** The nq dependency list. */
    private List<NamedQueryDependency> nqDependencyList = new ArrayList<NamedQueryDependency>();
    /** The plsql dependency list. */
    private List<PlsqlDependency> plsqlDependencyList = new ArrayList<PlsqlDependency>();
    /** The service dependency list. */
    private List<ServiceDependency> serviceDependencyList = new ArrayList<ServiceDependency>();
    /** The hbm table dependency list. */
    private List<HbmDaoDependency> hbmDaoDependencyList = new ArrayList<HbmDaoDependency>();
    /** The global usage list. */
    private List<GlobalVariableUsage> globalUsageList = new ArrayList<GlobalVariableUsage>();

    /**
     * Adds the dependency.
     * 
     * @param dependency
     *            the dependency
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    public void addDependency(NamedQueryDependency dependency) {
        nqDependencyList.add(dependency);
    }

    /**
     * Adds the dependency.
     * 
     * @param dependency
     *            the dependency
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:15:56 PM
     */
    public void addDependency(HbmDaoDependency dependency) {
        hbmDaoDependencyList.add(dependency);
    }

    /**
     * Adds the dependency.
     * 
     * @param dependency
     *            the dependency
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    public void addDependency(PlsqlDependency dependency) {
        plsqlDependencyList.add(dependency);
    }

    /**
     * Adds the dependency.
     * 
     * @param dependency
     *            the dependency
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    public void addDependency(ServiceDependency dependency) {
        serviceDependencyList.add(dependency);
    }

    /**
     * Gets the nq dependency list.
     * <p>
     * </p>
     * 
     * @return the nq dependency list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    public List<NamedQueryDependency> getNqDependencyList() {
        return nqDependencyList;
    }

    /**
     * Gets the plsql dependency list.
     * <p>
     * </p>
     * 
     * @return the plsql dependency list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    public List<PlsqlDependency> getPlsqlDependencyList() {
        return plsqlDependencyList;
    }

    /**
     * Gets the service dependency list.
     * <p>
     * </p>
     * 
     * @return the service dependency list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    public List<ServiceDependency> getServiceDependencyList() {
        return serviceDependencyList;
    }

    /**
     * Sets the nq dependency list.
     * <p>
     * </p>
     * 
     * @param nqDependencyList
     *            the new nq dependency list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    public void setNqDependencyList(List<NamedQueryDependency> nqDependencyList) {
        this.nqDependencyList = nqDependencyList;
    }

    /**
     * Sets the plsql dependency list.
     * <p>
     * </p>
     * 
     * @param plsqlDependencyList
     *            the new plsql dependency list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    public void setPlsqlDependencyList(List<PlsqlDependency> plsqlDependencyList) {
        this.plsqlDependencyList = plsqlDependencyList;
    }

    /**
     * Sets the service dependency list.
     * <p>
     * </p>
     * 
     * @param serviceDependencyList
     *            the new service dependency list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    public void setServiceDependencyList(List<ServiceDependency> serviceDependencyList) {
        this.serviceDependencyList = serviceDependencyList;
    }

    /**
     * Sets the hbm table dependency list.
     * <p>
     * </p>
     * 
     * @param hbmTableDependencyList
     *            the new hbm table dependency list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:16:07 PM
     */
    public void setHbmDaoDependencyList(List<HbmDaoDependency> hbmTableDependencyList) {
        this.hbmDaoDependencyList = hbmTableDependencyList;
    }

    /**
     * Gets the hbm table dependency list.
     * <p>
     * </p>
     * 
     * @return the hbm table dependency list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:16:07 PM
     */
    public List<HbmDaoDependency> getHbmDaoDependencyList() {
        return hbmDaoDependencyList;
    }

    /**
     * Sets the global usage list.
     * 
     * @param globalUsageList
     *            the new global usage list
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public void setGlobalUsageList(List<GlobalVariableUsage> globalUsageList) {
        this.globalUsageList = globalUsageList;
    }

    /**
     * Gets the global usage list.Ss
     * 
     * @return the global usage list
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public List<GlobalVariableUsage> getGlobalUsageList() {
        return globalUsageList;
    }

    /**
     * Adds the global variable usage.
     * 
     * @param globalVarUsage
     *            the global var usage
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public void addGlobalVariableUsage(GlobalVariableUsage globalVarUsage) {
        globalUsageList.add(globalVarUsage);
    }
}
