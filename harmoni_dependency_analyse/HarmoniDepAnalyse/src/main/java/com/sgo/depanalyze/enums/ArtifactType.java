package com.sgo.depanalyze.enums;

/**
 * The Enum ArtifactType.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 10:15:50 AM
 */
public enum ArtifactType {
    /** The back end. */
    HMN_BACK_END("BE"),
    /** The front end. */
    HMN_FRONT_END("FE"),
    /** The int bank. */
    INT_BANK("IB");
    /** The type. */
    private String type;

    /**
     * Instantiates a new artifact type.
     * 
     * @param type
     *            the type
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:50 AM
     */
    private ArtifactType(String type) {
        this.type = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return this.type;
    }
}
