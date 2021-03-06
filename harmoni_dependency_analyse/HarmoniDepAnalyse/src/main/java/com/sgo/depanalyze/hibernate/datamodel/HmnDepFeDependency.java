package com.sgo.depanalyze.hibernate.datamodel;

// Generated Nov 3, 2013 2:47:11 PM by Hibernate Tools 3.4.0.CR1
/**
 * DepHmnFeDepend generated by hbm2java.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 10:14:07 AM
 */
public class HmnDepFeDependency extends HmnDepDatamodel implements java.io.Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 3060776008463853574L;
    /** The execution id. */
    private Long executionId;
    /** The artifact id. */
    private Long artifactId;
    /** The source class. */
    private String sourceClass;
    /** The source method. */
    private String sourceMethod;
    /** The source signature. */
    private String sourceSignature;
    /** The target service. */
    private String targetService;
    /** The target method. */
    private String targetMethod;
    /** The target signature. */
    private String targetSignature;
    /** The dep level. */
    private Integer depLevel;
    /** The dep path. */
    private String depPath;

    /**
     * Instantiates a new hmn dep fe dependency.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public HmnDepFeDependency() {
        super();
    }

    /**
     * Instantiates a new hmn dep fe dependency.
     * 
     * @param id
     *            the id
     * @param executionId
     *            the execution id
     * @param artifactId
     *            the artifact id
     * @param sourceClass
     *            the source class
     * @param sourceMethod
     *            the source method
     * @param sourceSignature
     *            the source signature
     * @param targetService
     *            the target service
     * @param targetMethod
     *            the target method
     * @param targetSignature
     *            the target signature
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 19, 2013 4:38:32 PM
     */
    public HmnDepFeDependency(Long id, Long executionId, Long artifactId, String sourceClass, String sourceMethod, String sourceSignature, String targetService, String targetMethod,
            String targetSignature) {
        super(id);
        this.executionId = executionId;
        this.artifactId = artifactId;
        this.sourceClass = sourceClass;
        this.sourceMethod = sourceMethod;
        this.sourceSignature = sourceSignature;
        this.targetService = targetService;
        this.targetMethod = targetMethod;
        this.targetSignature = targetSignature;
    }

    /**
     * Gets the artifact id.
     * <p>
     * </p>
     * 
     * @return the artifact id
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
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
     * @since Nov 11, 2013 10:14:07 AM
     */
    public void setArtifactId(Long artifactId) {
        this.artifactId = artifactId;
    }

    /**
     * Gets the source class.
     * <p>
     * </p>
     * 
     * @return the source class
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public String getSourceClass() {
        return this.sourceClass;
    }

    /**
     * Sets the source class.
     * <p>
     * </p>
     * 
     * @param sourceClass
     *            the new source class
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public void setSourceClass(String sourceClass) {
        this.sourceClass = sourceClass;
    }

    /**
     * Gets the source method.
     * <p>
     * </p>
     * 
     * @return the source method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public String getSourceMethod() {
        return this.sourceMethod;
    }

    /**
     * Sets the source method.
     * <p>
     * </p>
     * 
     * @param sourceMethod
     *            the new source method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public void setSourceMethod(String sourceMethod) {
        this.sourceMethod = sourceMethod;
    }

    /**
     * Gets the source signature.
     * <p>
     * </p>
     * 
     * @return the source signature
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public String getSourceSignature() {
        return this.sourceSignature;
    }

    /**
     * Sets the source signature.
     * <p>
     * </p>
     * 
     * @param sourceSignature
     *            the new source signature
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public void setSourceSignature(String sourceSignature) {
        this.sourceSignature = sourceSignature;
    }

    /**
     * Gets the target service.
     * <p>
     * </p>
     * 
     * @return the target service
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public String getTargetService() {
        return this.targetService;
    }

    /**
     * Sets the target service.
     * <p>
     * </p>
     * 
     * @param targetService
     *            the new target service
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
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
     * @since Nov 11, 2013 10:14:07 AM
     */
    public String getTargetMethod() {
        return this.targetMethod;
    }

    /**
     * Sets the target method.
     * <p>
     * </p>
     * 
     * @param targetMethod
     *            the new target method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
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
     * @since Nov 11, 2013 10:14:07 AM
     */
    public String getTargetSignature() {
        return this.targetSignature;
    }

    /**
     * Sets the target signature.
     * <p>
     * </p>
     * 
     * @param targetSignature
     *            the new target signature
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public void setTargetSignature(String targetSignature) {
        this.targetSignature = targetSignature;
    }

    /**
     * Sets the execution id.
     * <p>
     * </p>
     * 
     * @param executionId
     *            the new execution id
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 19, 2013 4:38:33 PM
     */
    public void setExecutionId(Long executionId) {
        this.executionId = executionId;
    }

    /**
     * Gets the execution id.
     * <p>
     * </p>
     * 
     * @return the execution id
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 19, 2013 4:38:33 PM
     */
    public Long getExecutionId() {
        return executionId;
    }

    /**
     * Gets the dep level.
     * <p>
     * </p>
     * 
     * @return the dep level
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Mar 4, 2014 4:23:18 PM
     */
    public Integer getDepLevel() {
        return depLevel;
    }

    /**
     * Sets the dep level.
     * <p>
     * </p>
     * 
     * @param depLevel
     *            the new dep level
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Mar 4, 2014 4:23:18 PM
     */
    public void setDepLevel(Integer depLevel) {
        this.depLevel = depLevel;
    }

    /**
     * Gets the dep path.
     * <p>
     * </p>
     * 
     * @return the dep path
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Mar 4, 2014 4:23:18 PM
     */
    public String getDepPath() {
        return depPath;
    }

    /**
     * Sets the dep path.
     * <p>
     * </p>
     * 
     * @param depPath
     *            the new dep path
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Mar 4, 2014 4:23:18 PM
     */
    public void setDepPath(String depPath) {
        this.depPath = depPath;
    }
}
