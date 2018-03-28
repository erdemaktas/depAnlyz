package com.sgo.depanalyze.datatypes;

/**
 * The Class PlsqlDependency.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:53 AM
 */
public class PlsqlDependency {
    /** The package name. */
    private String packageName;
    /** The procedure name. */
    private String procedureName;
    /** The dependency path. */
    private String dependencyPath;
    /** The dependency level. */
    private int dependencyLevel;

    /**
     * Instantiates a new plsql dependency.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public PlsqlDependency() {
        super();
    }

    /**
     * Instantiates a new plsql dependency.
     * 
     * @param packageName
     *            the package name
     * @param procedureName
     *            the procedure name
     * @param dependencyPath
     *            the dependency path
     * @param dependencyLevel
     *            the dependency level
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public PlsqlDependency(String packageName, String procedureName, String dependencyPath, int dependencyLevel) {
        super();
        this.packageName = packageName;
        this.procedureName = procedureName;
        this.dependencyPath = dependencyPath;
        this.dependencyLevel = dependencyLevel;
    }

    /**
     * Gets the package name.
     * <p>
     * </p>
     * 
     * @return the package name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets the package name.
     * <p>
     * </p>
     * 
     * @param packageName
     *            the new package name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Gets the procedure name.
     * <p>
     * </p>
     * 
     * @return the procedure name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public String getProcedureName() {
        return procedureName;
    }

    /**
     * Sets the procedure name.
     * <p>
     * </p>
     * 
     * @param procedureName
     *            the new procedure name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    /**
     * Gets the dependency path.
     * <p>
     * </p>
     * 
     * @return the dependency path
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
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
     * @since Nov 11, 2013 9:46:53 AM
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
     * @since Nov 11, 2013 9:46:53 AM
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
     * @since Nov 11, 2013 9:46:53 AM
     */
    public void setDependencyLevel(int dependencyLevel) {
        this.dependencyLevel = dependencyLevel;
    }
}
