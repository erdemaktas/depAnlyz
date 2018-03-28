package com.sgo.depanalyze.datatypes;

import com.sgo.depanalyze.enums.CRUD_FLAG;

/**
 * The Class HbmTableDependency.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Dec 4, 2013 3:15:14 PM
 */
public class HbmDaoDependency {
    /** The dependency path. */
    private String dependencyPath;
    /** The dependency level. */
    private int dependencyLevel;
    /** The read write flag. */
    private CRUD_FLAG crudFlag;
    /** The dao class name. */
    private String daoClassName;
    /** The dao method name. */
    private String daoMethodName;

    public HbmDaoDependency() {
        super();
    }

    public HbmDaoDependency(String daoClassName, String daoMethodName, CRUD_FLAG crudFlag, String dependencyPath, int dependencyLevel) {
        super();
        this.dependencyPath = dependencyPath;
        this.dependencyLevel = dependencyLevel;
        this.crudFlag = crudFlag;
        this.daoClassName = daoClassName;
        this.daoMethodName = daoMethodName;
    }

    /**
     * Gets the dao class name.
     * <p>
     * </p>
     * 
     * @return the dao class name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 5:12:27 PM
     */
    public String getDaoClassName() {
        return daoClassName;
    }

    /**
     * Sets the dao class name.
     * <p>
     * </p>
     * 
     * @param daoClassName
     *            the new dao class name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 5:12:27 PM
     */
    public void setDaoClassName(String daoClassName) {
        this.daoClassName = daoClassName;
    }

    /**
     * Gets the dao method name.
     * <p>
     * </p>
     * 
     * @return the dao method name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 5:12:27 PM
     */
    public String getDaoMethodName() {
        return daoMethodName;
    }

    /**
     * Sets the dao method name.
     * <p>
     * </p>
     * 
     * @param daoMethodName
     *            the new dao method name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 5:12:27 PM
     */
    public void setDaoMethodName(String daoMethodName) {
        this.daoMethodName = daoMethodName;
    }

    /**
     * Gets the dependency path.
     * <p>
     * </p>
     * 
     * @return the dependency path
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:15:14 PM
     */
    public String getDependencyPath() {
        return dependencyPath;
    }

    /**
     * Sets the dependency path.
     * <p>
     * </p>
     * 
     * @param dependencyPath
     *            the new dependency path
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:15:14 PM
     */
    public void setDependencyPath(String dependencyPath) {
        this.dependencyPath = dependencyPath;
    }

    /**
     * Gets the dependency level.
     * <p>
     * </p>
     * 
     * @return the dependency level
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:15:14 PM
     */
    public int getDependencyLevel() {
        return dependencyLevel;
    }

    /**
     * Sets the dependency level.
     * <p>
     * </p>
     * 
     * @param dependencyLevel
     *            the new dependency level
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:15:14 PM
     */
    public void setDependencyLevel(int dependencyLevel) {
        this.dependencyLevel = dependencyLevel;
    }

    /**
     * Gets the read write flag.
     * <p>
     * </p>
     * 
     * @return the read write flag
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:15:14 PM
     */
    public CRUD_FLAG getCrudFlag() {
        return crudFlag;
    }

    /**
     * Sets the read write flag.
     * <p>
     * </p>
     * 
     * @param readWriteFlag
     *            the new read write flag
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:15:14 PM
     */
    public void setCrudFlag(CRUD_FLAG crudFlag) {
        this.crudFlag = crudFlag;
    }
}
