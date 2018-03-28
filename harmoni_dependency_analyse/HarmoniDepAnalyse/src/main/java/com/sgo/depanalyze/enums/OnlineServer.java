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
public enum OnlineServer {
    SERVER_1("SERVER_1"), SERVER_2("SERVER_2");
    /** The type. */
    private String server;

    /**
     * @author U079754 A. Emre Zorlu
     * @since Jul 24, 2016
     * @param server
     *            the server to set
     */
    private OnlineServer(String server) {
        this.server = server;
    }

    /**
     * @author U079754 A. Emre Zorlu
     * @since Jul 24, 2016
     * @return the server
     */
    public String getServer() {
        return server;
    }
}
