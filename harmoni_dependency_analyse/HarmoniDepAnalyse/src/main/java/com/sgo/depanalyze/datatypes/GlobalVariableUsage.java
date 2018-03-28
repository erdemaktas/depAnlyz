package com.sgo.depanalyze.datatypes;

/**
 * The Class GlobalVariableUsage.
 * 
 * @author Selçuk Giray ÖZDAMAR
 * @since Jun 23, 2016
 */
public class GlobalVariableUsage {
    /**
     * The Enum UsageType.
     * 
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public static enum UsageType {
        /** The putstatic. */
        PUTSTATIC,
        /** The putfield. */
        PUTFIELD
    };

    /** The usage type. */
    private UsageType usageType; //
    /** The field name. */
    private String fieldName;
    /** The signature. */
    private String fieldType;
    /** The reference type. */
    private String referenceType;
    /** The dependency path. */
    private String dependencyPath;
    /** The dependency level. */
    private int dependencyLevel;

    /**
     * Gets the usage type.
     * 
     * @return the usage type
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public UsageType getUsageType() {
        return usageType;
    }

    /**
     * Sets the usage type.
     * 
     * @param usageType
     *            the new usage type
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

    /**
     * Gets the field name.
     * 
     * @return the field name
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets the field name.
     * 
     * @param fieldName
     *            the new field name
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Gets the signature.
     * 
     * @return the signature
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * Sets the signature.
     * 
     * @param signature
     *            the new signature
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public void setSignature(String signature) {
        this.fieldType = signature;
    }

    /**
     * Gets the reference type.
     * 
     * @return the reference type
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public String getReferenceType() {
        return referenceType;
    }

    /**
     * Sets the reference type.
     * 
     * @param referenceType
     *            the new reference type
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    /**
     * Gets the dependency path.
     * 
     * @return the dependency path
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public String getDependencyPath() {
        return dependencyPath;
    }

    /**
     * Sets the dependency path.
     * 
     * @param dependencyPath
     *            the new dependency path
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public void setDependencyPath(String dependencyPath) {
        this.dependencyPath = dependencyPath;
    }

    /**
     * Gets the dependency level.
     * 
     * @return the dependency level
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public int getDependencyLevel() {
        return dependencyLevel;
    }

    /**
     * Sets the dependency level.
     * 
     * @param dependencyLevel
     *            the new dependency level
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public void setDependencyLevel(int dependencyLevel) {
        this.dependencyLevel = dependencyLevel;
    }
}
