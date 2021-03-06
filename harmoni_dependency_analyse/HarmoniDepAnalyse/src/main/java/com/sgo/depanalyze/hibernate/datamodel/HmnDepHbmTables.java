package com.sgo.depanalyze.hibernate.datamodel;

/**
 * HmndepHbmTables generated by hbm2java.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Dec 4, 2013 3:37:44 PM
 */
public class HmnDepHbmTables extends HmnDepDatamodel implements java.io.Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4865611489073610084L;
    /** The execution id. */
    private Long executionId;
    /** The artifact id. */
    private Long artifactId;
    /** The dao class. */
    private String daoClass;
    /** The model class. */
    private String modelClass;
    /** The table name. */
    private String tableName;
    /** The datasource. */
    private String datasource;
    /** The hbm file. */
    private String hbmFile;

    /**
     * Instantiates a new hmn dep hbm tables.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public HmnDepHbmTables() {
    }

    /**
     * Instantiates a new hmn dep hbm tables.
     * 
     * @param id
     *            the id
     * @param executionId
     *            the execution id
     * @param artifactId
     *            the artifact id
     * @param daoClass
     *            the dao class
     * @param modelClass
     *            the model class
     * @param tableName
     *            the table name
     * @param datasource
     *            the datasource
     * @param hbmFile
     *            the hbm file
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public HmnDepHbmTables(Long id, Long executionId, Long artifactId, String daoClass, String modelClass, String tableName, String datasource, String hbmFile) {
        super(id);
        this.executionId = executionId;
        this.artifactId = artifactId;
        this.daoClass = daoClass;
        this.modelClass = modelClass;
        this.tableName = tableName;
        this.datasource = datasource;
        this.hbmFile = hbmFile;
    }

    /**
     * Gets the execution id.
     * <p>
     * </p>
     * 
     * @return the execution id
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public Long getExecutionId() {
        return this.executionId;
    }

    /**
     * Sets the execution id.
     * <p>
     * </p>
     * 
     * @param executionId
     *            the new execution id
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public void setExecutionId(Long executionId) {
        this.executionId = executionId;
    }

    /**
     * Gets the artifact id.
     * <p>
     * </p>
     * 
     * @return the artifact id
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public Long getArtifactId() {
        return this.artifactId;
    }

    /**
     * Sets the artifact id.
     * <p>
     * </p>
     * 
     * @param artifactId
     *            the new artifact id
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public void setArtifactId(Long artifactId) {
        this.artifactId = artifactId;
    }

    /**
     * Gets the dao class.
     * <p>
     * </p>
     * 
     * @return the dao class
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public String getDaoClass() {
        return this.daoClass;
    }

    /**
     * Sets the dao class.
     * <p>
     * </p>
     * 
     * @param daoClass
     *            the new dao class
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public void setDaoClass(String daoClass) {
        this.daoClass = daoClass;
    }

    /**
     * Gets the model class.
     * <p>
     * </p>
     * 
     * @return the model class
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public String getModelClass() {
        return this.modelClass;
    }

    /**
     * Sets the model class.
     * <p>
     * </p>
     * 
     * @param modelClass
     *            the new model class
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public void setModelClass(String modelClass) {
        this.modelClass = modelClass;
    }

    /**
     * Gets the table name.
     * <p>
     * </p>
     * 
     * @return the table name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Sets the table name.
     * <p>
     * </p>
     * 
     * @param tableName
     *            the new table name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Gets the datasource.
     * <p>
     * </p>
     * 
     * @return the datasource
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public String getDatasource() {
        return this.datasource;
    }

    /**
     * Sets the datasource.
     * <p>
     * </p>
     * 
     * @param datasource
     *            the new datasource
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    /**
     * Gets the hbm file.
     * <p>
     * </p>
     * 
     * @return the hbm file
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public String getHbmFile() {
        return this.hbmFile;
    }

    /**
     * Sets the hbm file.
     * <p>
     * </p>
     * 
     * @param hbmFile
     *            the new hbm file
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:37:44 PM
     */
    public void setHbmFile(String hbmFile) {
        this.hbmFile = hbmFile;
    }
}
