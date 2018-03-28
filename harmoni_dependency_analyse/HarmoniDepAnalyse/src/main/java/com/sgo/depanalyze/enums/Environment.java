/**
 * @author U079754 A. Emre Zorlu
 * @since Jun 14, 2016
 * @return Environment
 * 
 */
package com.sgo.depanalyze.enums;

/**
 * @author U079754 A. Emre Zorlu
 * @since Jun 14, 2016
 */
public enum Environment {
    LIVE("LIVE"), HOTFIX("HOTFIX"), UAT("UAT"), TEST("TEST"), DEV("DEV");
    /** The type. */
    private String type;

    /**
     * @author U079754 A. Emre Zorlu
     * @since Jun 14, 2016
     * @param type
     */
    private Environment(String type) {
        this.type = type;
    }

    /**
     * @author U079754 A. Emre Zorlu
     * @since Jun 14, 2016
     * @return the type
     */
    public String getType() {
        return type;
    }
}
