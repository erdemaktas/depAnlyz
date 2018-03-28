/**
 * @author U079754 A. Emre Zorlu
 * @since Jul 24, 2016
 * @return OnlineServer
 * 
 */
package com.sgo.depanalyze.enums;

/**
 * @author U079754 A. Emre Zorlu
 * @since Jul 24, 2016
 */
public enum ServerStatus {
    ONLINE("UP"), OFFLINE("DOWN");
    /** The type. */
    private String status;

    /**
     * Instantiates a new server status.
     * 
     * @param status
     *            the status
     * @author U079754 A. Emre Zorlu
     * @since Jul 24, 2016
     */
    private ServerStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the server status.
     * 
     * @return the server
     * @author U079754 A. Emre Zorlu
     * @since Jul 24, 2016
     */
    public String getServerStatus() {
        return status;
    }
}
