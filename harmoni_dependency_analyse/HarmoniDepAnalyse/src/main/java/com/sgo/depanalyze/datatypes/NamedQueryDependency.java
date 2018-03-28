package com.sgo.depanalyze.datatypes;

/**
 * The Class NamedQueryDependency.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:54 AM
 */
public class NamedQueryDependency {
    /** The query name. */
    private String queryName;
    /** The dependency path. */
    private String dependencyPath;
    /** The dependency level. */
    private int dependencyLevel;

    /**
     * Instantiates a new named query dependency.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public NamedQueryDependency() {
        super();
    }

    /**
     * Instantiates a new named query dependency.
     * 
     * @param queryName
     *            the query name
     * @param dependencyPath
     *            the dependency path
     * @param dependencyLevel
     *            the dependency level
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public NamedQueryDependency(String queryName, String dependencyPath, int dependencyLevel) {
        super();
        this.setQueryName(queryName);
        this.dependencyPath = dependencyPath;
        this.dependencyLevel = dependencyLevel;
    }

    /**
     * Gets the dependency path.
     * <p>
     * </p>
     * 
     * @return the dependency path
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
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
     * @since Nov 11, 2013 9:46:54 AM
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
     * @since Nov 11, 2013 9:46:54 AM
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
     * @since Nov 11, 2013 9:46:54 AM
     */
    public void setDependencyLevel(int dependencyLevel) {
        this.dependencyLevel = dependencyLevel;
    }

    /**
     * Sets the query name.
     * <p>
     * </p>
     * 
     * @param queryName
     *            the new query name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    /**
     * Gets the query name.
     * <p>
     * </p>
     * 
     * @return the query name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public String getQueryName() {
        return queryName;
    }
}
