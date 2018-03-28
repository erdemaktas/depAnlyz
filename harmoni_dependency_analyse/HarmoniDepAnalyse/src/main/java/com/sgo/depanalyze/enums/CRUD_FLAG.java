package com.sgo.depanalyze.enums;

/**
 * The Enum CRUD_FLAG.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Dec 4, 2013 8:55:43 PM
 */
public enum CRUD_FLAG {
    /** The create. */
    CREATE("C"),
    /** The read. */
    READ("R"),
    /** The update. */
    UPDATE("U"),
    /** The delete. */
    DELETE("D");
    /** The type. */
    private String flag;

    /**
     * Instantiates a new crud flag.
     * 
     * @param type
     *            the type
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 8:55:43 PM
     */
    private CRUD_FLAG(String type) {
        this.flag = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return this.flag;
    }
}
