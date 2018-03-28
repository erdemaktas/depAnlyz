package com.sgo.depanalyze.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Enum YesNoCondition.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 10:15:49 AM
 */
public enum YesNoCondition {
    /** The yes. */
    YES("Y"),
    /** The no. */
    NO("N"),
    /** The null. */
    NULL("");
    /** The value. */
    private String value;
    /** The Constant STR_TO_ENUM. */
    static final Map<String, YesNoCondition> STR_TO_ENUM = new ConcurrentHashMap<String, YesNoCondition>();
    static {
        for (YesNoCondition ert : values()) {
            STR_TO_ENUM.put(ert.value, ert);
        }
    }

    /**
     * Instantiates a new yes no condition.
     * 
     * @param value
     *            the value
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:49 AM
     */
    private YesNoCondition(String value) {
        this.value = value;
    }

    /**
     * Gets the value.
     * <p>
     * </p>
     * 
     * @return the value
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:49 AM
     */
    public String getValue() {
        return this.value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return this.value;
    }

    /**
     * From string.
     * 
     * @param value
     *            the value
     * @return the yes no condition
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:49 AM
     */
    public static YesNoCondition fromString(String value) {
        YesNoCondition result = (value != null) ? STR_TO_ENUM.get(value.toUpperCase()) : null;
        if (result == null) {
            throw new IllegalArgumentException("No enum const " + YesNoCondition.class + "@value." + value);
        }
        return result;
    }
}
