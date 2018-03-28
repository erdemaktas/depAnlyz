package com.sgo.depanalyze.hibernate.datamodel;

// Generated Oct 24, 2013 9:13:46 AM by Hibernate Tools 3.4.0.CR1
/**
 * DepHmnArtifacts generated by hbm2java.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 10:14:07 AM
 */
public class HmnDepArtifact extends HmnDepDatamodel implements java.io.Serializable {
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2353066641486866935L;
    /** The artifact name. */
    private String fileName;
    /** The artifact type. */
    private String artifactType;
    /** The execution id. */
    private Long executionId;
    /** The group id. */
    private String groupId;
    /** The artifact id. */
    private String artifactId;
    /** The version. */
    private String artifactVersion;
    /** The description. */
    private String artifactDesc;

    /**
     * Instantiates a new hmn dep artifact.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public HmnDepArtifact() {
        super();
    }

    /**
     * Instantiates a new hmn dep artifact.
     * 
     * @param id
     *            the id
     * @param artifactName
     *            the artifact name
     * @param artifactType
     *            the artifact type
     * @param executionId
     *            the execution id
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 20, 2013 8:58:13 AM
     */
    public HmnDepArtifact(Long id, String artifactName, String artifactType, Long executionId) {
        super(id);
        this.fileName = artifactName;
        this.artifactType = artifactType;
        this.executionId = executionId;
    }

    /**
     * Gets the artifact name.
     * <p>
     * </p>
     * 
     * @return the artifact name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Sets the artifact name.
     * <p>
     * </p>
     * 
     * @param fileName
     *            the new artifact name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the artifact type.
     * <p>
     * </p>
     * 
     * @return the artifact type
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public String getArtifactType() {
        return artifactType;
    }

    /**
     * Sets the artifact type.
     * <p>
     * </p>
     * 
     * @param artifactType
     *            the new artifact type
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:07 AM
     */
    public void setArtifactType(String artifactType) {
        this.artifactType = artifactType;
    }

    /**
     * Sets the execution id.
     * <p>
     * </p>
     * 
     * @param executionId
     *            the new execution id
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 19, 2013 4:40:56 PM
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
     * @since Nov 19, 2013 4:40:56 PM
     */
    public Long getExecutionId() {
        return executionId;
    }

    /**
     * Gets the group id.
     * <p>
     * </p>
     * 
     * @return the group id
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:31:43 PM
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Sets the group id.
     * <p>
     * </p>
     * 
     * @param groupId
     *            the new group id
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:31:43 PM
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets the artifact id.
     * <p>
     * </p>
     * 
     * @return the artifact id
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:31:43 PM
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Sets the artifact id.
     * <p>
     * </p>
     * 
     * @param artifactId
     *            the new artifact id
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:31:43 PM
     */
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    /**
     * Gets the artifact version.
     * <p>
     * </p>
     * 
     * @return the artifact version
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:31:43 PM
     */
    public String getArtifactVersion() {
        return artifactVersion;
    }

    /**
     * Sets the artifact version.
     * <p>
     * </p>
     * 
     * @param artifactVersion
     *            the new artifact version
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:31:43 PM
     */
    public void setArtifactVersion(String artifactVersion) {
        this.artifactVersion = artifactVersion;
    }

    /**
     * Gets the artifact desc.
     * <p>
     * </p>
     * 
     * @return the artifact desc
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:31:43 PM
     */
    public String getArtifactDesc() {
        return artifactDesc;
    }

    /**
     * Sets the artifact desc.
     * <p>
     * </p>
     * 
     * @param artifactDesc
     *            the new artifact desc
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:31:43 PM
     */
    public void setArtifactDesc(String artifactDesc) {
        this.artifactDesc = artifactDesc;
    }
}