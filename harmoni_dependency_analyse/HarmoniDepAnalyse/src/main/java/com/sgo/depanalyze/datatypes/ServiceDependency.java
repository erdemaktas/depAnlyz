package com.sgo.depanalyze.datatypes;

/**
 * The Class ServiceDependency.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:53 AM
 */
public class ServiceDependency {
    /** The target service. */
    private String targetService;
    /** The target method. */
    private String targetMethod;
    /** The target signature. */
    private String targetSignature;
    /** The dependency path. */
    private String dependencyPath;
    /** The dependency level. */
    private int dependencyLevel;

    /**
     * Instantiates a new service dependency.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public ServiceDependency() {
        super();
    }

    /**
     * Instantiates a new service dependency.
     * 
     * @param targetService
     *            the target service
     * @param targetMethod
     *            the target method
     * @param targetSignature
     *            the target signature
     * @param dependencyPath
     *            the dependency path
     * @param dependencyLevel
     *            the dependency level
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public ServiceDependency(String targetService, String targetMethod, String targetSignature, String dependencyPath, int dependencyLevel) {
        super();
        this.targetService = targetService;
        this.targetMethod = targetMethod;
        this.targetSignature = targetSignature;
        this.dependencyPath = dependencyPath;
        this.dependencyLevel = dependencyLevel;
    }

    /**
     * Gets the target service.
     * <p>
     * </p>
     * 
     * @return the target service
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public String getTargetService() {
        return targetService;
    }

    /**
     * Sets the target service.
     * <p>
     * </p>
     * 
     * @param targetService
     *            the new target service
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public void setTargetService(String targetService) {
        this.targetService = targetService;
    }

    /**
     * Gets the target method.
     * <p>
     * </p>
     * 
     * @return the target method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public String getTargetMethod() {
        return targetMethod;
    }

    /**
     * Sets the target method.
     * <p>
     * </p>
     * 
     * @param targetMethod
     *            the new target method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    /**
     * Gets the target signature.
     * <p>
     * </p>
     * 
     * @return the target signature
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public String getTargetSignature() {
        return targetSignature;
    }

    /**
     * Sets the target signature.
     * <p>
     * </p>
     * 
     * @param targetSignature
     *            the new target signature
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public void setTargetSignature(String targetSignature) {
        this.targetSignature = targetSignature;
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
