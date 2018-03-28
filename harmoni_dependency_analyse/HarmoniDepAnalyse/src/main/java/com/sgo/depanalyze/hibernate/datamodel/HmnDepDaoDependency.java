/*
 * @author U079754 A. Emre Zorlu
 * @date Jun 7, 2016
 */
package com.sgo.depanalyze.hibernate.datamodel;

// TODO: Auto-generated Javadoc
// Generated Dec 4, 2013 5:05:49 PM by Hibernate Tools 3.4.0.CR1
/**
 * HmndepDaoDependencies generated by hbm2java.
 */
public class HmnDepDaoDependency extends HmnDepDatamodel implements java.io.Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8440653111650675321L;
    /** The execution id. */
    private Long executionId;
    /** The service class. */
    private String serviceClass;
    /** The service method. */
    private String serviceMethod;
    /** The dao name. */
    private String daoName;
    /** The dao method. */
    private String daoMethod;
    /** The crud flag. */
    private String crudFlag;
    /** The dependency level. */
    private Integer dependencyLevel;
    /** The dependency path. */
    private String dependencyPath;
    /** The artifact id. */
    private Long artifactId;

    /**
     * Instantiates a new hmn dep dao dependency.
     */
    public HmnDepDaoDependency() {
    }

    /**
     * Instantiates a new hmn dep dao dependency.
     * 
     * @param id
     *            the id
     * @param executionId
     *            the execution id
     * @param serviceClass
     *            the service class
     * @param serviceMethod
     *            the service method
     * @param daoName
     *            the dao name
     * @param daoMethod
     *            the dao method
     * @param crudFlag
     *            the crud flag
     * @param dependencyLevel
     *            the dependency level
     * @param artifactId
     *            the artifact id
     */
    public HmnDepDaoDependency(Long id, Long executionId, String serviceClass, String serviceMethod, String daoName, String daoMethod, String crudFlag, Integer dependencyLevel, Long artifactId) {
        super(id);
        this.executionId = executionId;
        this.serviceClass = serviceClass;
        this.serviceMethod = serviceMethod;
        this.daoName = daoName;
        this.daoMethod = daoMethod;
        this.crudFlag = crudFlag;
        this.dependencyLevel = dependencyLevel;
        this.artifactId = artifactId;
    }

    /**
     * Instantiates a new hmn dep dao dependency.
     * 
     * @param id
     *            the id
     * @param executionId
     *            the execution id
     * @param serviceClass
     *            the service class
     * @param serviceMethod
     *            the service method
     * @param daoName
     *            the dao name
     * @param daoMethod
     *            the dao method
     * @param crudFlag
     *            the crud flag
     * @param dependencyLevel
     *            the dependency level
     * @param dependencyPath
     *            the dependency path
     * @param artifactId
     *            the artifact id
     */
    public HmnDepDaoDependency(Long id, Long executionId, String serviceClass, String serviceMethod, String daoName, String daoMethod, String crudFlag, Integer dependencyLevel, String dependencyPath,
            Long artifactId) {
        super(id);
        this.executionId = executionId;
        this.serviceClass = serviceClass;
        this.serviceMethod = serviceMethod;
        this.daoName = daoName;
        this.daoMethod = daoMethod;
        this.crudFlag = crudFlag;
        this.dependencyLevel = dependencyLevel;
        this.dependencyPath = dependencyPath;
        this.artifactId = artifactId;
    }

    /**
     * Gets the execution id.
     * 
     * @return the execution id
     */
    public Long getExecutionId() {
        return this.executionId;
    }

    /**
     * Sets the execution id.
     * 
     * @param executionId
     *            the new execution id
     */
    public void setExecutionId(Long executionId) {
        this.executionId = executionId;
    }

    /**
     * Gets the service class.
     * 
     * @return the service class
     */
    public String getServiceClass() {
        return this.serviceClass;
    }

    /**
     * Sets the service class.
     * 
     * @param serviceClass
     *            the new service class
     */
    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    /**
     * Gets the service method.
     * 
     * @return the service method
     */
    public String getServiceMethod() {
        return this.serviceMethod;
    }

    /**
     * Sets the service method.
     * 
     * @param serviceMethod
     *            the new service method
     */
    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    /**
     * Gets the dao name.
     * 
     * @return the dao name
     */
    public String getDaoName() {
        return this.daoName;
    }

    /**
     * Sets the dao name.
     * 
     * @param daoName
     *            the new dao name
     */
    public void setDaoName(String daoName) {
        this.daoName = daoName;
    }

    /**
     * Gets the dao method.
     * 
     * @return the dao method
     */
    public String getDaoMethod() {
        return this.daoMethod;
    }

    /**
     * Sets the dao method.
     * 
     * @param daoMethod
     *            the new dao method
     */
    public void setDaoMethod(String daoMethod) {
        this.daoMethod = daoMethod;
    }

    /**
     * Gets the dependency level.
     * 
     * @return the dependency level
     */
    public Integer getDependencyLevel() {
        return this.dependencyLevel;
    }

    /**
     * Sets the dependency level.
     * 
     * @param dependencyLevel
     *            the new dependency level
     */
    public void setDependencyLevel(Integer dependencyLevel) {
        this.dependencyLevel = dependencyLevel;
    }

    /**
     * Gets the dependency path.
     * 
     * @return the dependency path
     */
    public String getDependencyPath() {
        return this.dependencyPath;
    }

    /**
     * Sets the dependency path.
     * 
     * @param dependencyPath
     *            the new dependency path
     */
    public void setDependencyPath(String dependencyPath) {
        this.dependencyPath = dependencyPath;
    }

    /**
     * Sets the crud flag.
     * 
     * @param crudFlag
     *            the new crud flag
     */
    public void setCrudFlag(String crudFlag) {
        this.crudFlag = crudFlag;
    }

    /**
     * Gets the crud flag.
     * 
     * @return the crud flag
     */
    public String getCrudFlag() {
        return crudFlag;
    }

    /**
     * Sets the artifact id.
     * 
     * @param artifactId
     *            the artifactId to set
     * @author U079754 A. Emre Zorlu
     * @since Jun 7, 2016
     */
    public void setArtifactId(Long artifactId) {
        this.artifactId = artifactId;
    }

    /**
     * Gets the artifact id.
     * 
     * @return the artifactId
     * @author U079754 A. Emre Zorlu
     * @since Jun 7, 2016
     */
    public Long getArtifactId() {
        return artifactId;
    }
}
