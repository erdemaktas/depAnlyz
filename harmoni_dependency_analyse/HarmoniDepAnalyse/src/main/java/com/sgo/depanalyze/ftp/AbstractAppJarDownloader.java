/*
 * @author U079754 A. Emre Zorlu
 * @date Jun 15, 2016
 */
package com.sgo.depanalyze.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * The Class AbstractAppJarDownloader.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 10:15:56 AM
 */
public abstract class AbstractAppJarDownloader {
    /** The host name. */
    private String beHostName = "opchrmntst2";
    /** The fe host name. */
    private String feHostName = "opchrmntst2";
    /** The be user name. */
    protected String beUserName = "loguser";
    /** The be password. */
    protected String bePassword = "harmoni";
    /** The fe user name. */
    protected String feUserName = "loguser";
    /** The fe password. */
    protected String fePassword = "harmoni";
    // remote paths
    /** The be libs remote pathname. */
    protected String beLibsRemotePathname = "/Shared_Libraries/be/";
    /** The be inf remote pathname. */
    protected String beInfRemotePathname = "/Shared_Libraries/be/";
    /** The fe libs remote pathname. */
    protected String feLibsRemotePathname = "/Shared_Libraries/be/";
    /** The third party remote pathname. */
    protected String thirdPartyRemotePathname = "/Shared_Libraries/be/";
    // local paths
    /** The be lib local path name. */
    protected String beLibLocalPathName = "AppJars/be_jars/";
    /** The be inf local path name. */
    protected String beInfLocalPathName = "AppJars/fe_jars/";
    /** The fe libs local path name. */
    protected String feLibsLocalPathName = "AppJars/be_jars/";
    /** The third party local path name. */
    protected String thirdPartyLocalPathName = "AppJars/thirdparty_jars/";
    //
    /** The logger. */
    static Logger logger = Logger.getLogger(AbstractAppJarDownloader.class);
    //
    /** The ftp client. */
    protected FTPClient ftpClient = null;

    //
    /**
     * Download app jars.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public void downloadAppJars() {
        // FE JARS
        try {
            ftpClient = new FTPClient(); // new ftp client
            // try to connect
            ftpClient.connect(getFeHostName());
            // login to server
            if (!ftpClient.login(feUserName, fePassword)) {
                ftpClient.logout();
            }
            int reply = ftpClient.getReplyCode();
            // FTPReply stores a set of constants for FTP reply codes.
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            }
            // enter passive mode
            ftpClient.enterLocalPassiveMode();
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // get system name
            logger.info("Connected to FTP Remote system :" + ftpClient.getSystemType());
            //
            downloadFrontEndJars();
            //
        } catch (Exception e) {
            logger.error("Exception occured while downloading Application Jars, " + e.getMessage(), e);
        } finally {
            if (ftpClient.isAvailable() && ftpClient.isConnected()) {
                try {
                    ftpClient.logout();
                    ftpClient.disconnect();
                } catch (IOException e) {
                    logger.error("Exception occured while disconnecting from ftp server, " + e.getMessage(), e);
                }
            }
            logger.info("FINISHED downloading application Jars");
        }
        // BE & INF JARS
        try {
            ftpClient = new FTPClient(); // new ftp client
            // try to connect
            ftpClient.connect(getBeHostName());
            // login to server
            if (!ftpClient.login(beUserName, bePassword)) {
                ftpClient.logout();
            }
            int reply = ftpClient.getReplyCode();
            // FTPReply stores a set of constants for FTP reply codes.
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            }
            // enter passive mode
            ftpClient.enterLocalPassiveMode();
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // get system name
            logger.info("Connected to FTP Remote system :" + ftpClient.getSystemType());
            //
            downloadBackEndJars();
            //
            downloadThirdPartyJars();
        } catch (Exception e) {
            logger.error("Exception occured while downloading Application Jars, " + e.getMessage(), e);
        } finally {
            if (ftpClient.isAvailable() && ftpClient.isConnected()) {
                try {
                    ftpClient.logout();
                    ftpClient.disconnect();
                } catch (IOException e) {
                    logger.error("Exception occured while disconnecting from ftp server, " + e.getMessage(), e);
                }
            }
            logger.info("FINISHED downloading application Jars");
        }
    }

    /**
     * @author U079754 A. Emre Zorlu
     * @since Jun 29, 2016
     * @return the beUserName
     */
    public String getBeUserName() {
        return beUserName;
    }

    /**
     * @author U079754 A. Emre Zorlu
     * @since Jun 29, 2016
     * @param beUserName
     *            the beUserName to set
     */
    public void setBeUserName(String beUserName) {
        this.beUserName = beUserName;
    }

    /**
     * @author U079754 A. Emre Zorlu
     * @since Jun 29, 2016
     * @return the bePassword
     */
    public String getBePassword() {
        return bePassword;
    }

    /**
     * @author U079754 A. Emre Zorlu
     * @since Jun 29, 2016
     * @param bePassword
     *            the bePassword to set
     */
    public void setBePassword(String bePassword) {
        this.bePassword = bePassword;
    }

    /**
     * @author U079754 A. Emre Zorlu
     * @since Jun 29, 2016
     * @return the feUserName
     */
    public String getFeUserName() {
        return feUserName;
    }

    /**
     * @author U079754 A. Emre Zorlu
     * @since Jun 29, 2016
     * @param feUserName
     *            the feUserName to set
     */
    public void setFeUserName(String feUserName) {
        this.feUserName = feUserName;
    }

    /**
     * @author U079754 A. Emre Zorlu
     * @since Jun 29, 2016
     * @return the fePassword
     */
    public String getFePassword() {
        return fePassword;
    }

    /**
     * @author U079754 A. Emre Zorlu
     * @since Jun 29, 2016
     * @param fePassword
     *            the fePassword to set
     */
    public void setFePassword(String fePassword) {
        this.fePassword = fePassword;
    }

    /**
     * Download back end jars.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    protected abstract void downloadBackEndJars();

    /**
     * Download front end jars.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    protected abstract void downloadFrontEndJars();

    /**
     * Download third party jars.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    protected abstract void downloadThirdPartyJars();

    /**
     * Gets the be libs remote pathname.
     * <p>
     * </p>
     * 
     * @return the be libs remote pathname
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public String getBeLibsRemotePathname() {
        return beLibsRemotePathname;
    }

    /**
     * Sets the be libs remote pathname.
     * <p>
     * </p>
     * 
     * @param beLibs_remotePathname
     *            the new be libs remote pathname
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public void setBeLibsRemotePathname(String beLibs_remotePathname) {
        this.beLibsRemotePathname = beLibs_remotePathname;
    }

    /**
     * Gets the fe libs remote pathname.
     * <p>
     * </p>
     * 
     * @return the fe libs remote pathname
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public String getFeLibsRemotePathname() {
        return feLibsRemotePathname;
    }

    /**
     * Sets the fe libs remote pathname.
     * <p>
     * </p>
     * 
     * @param feLibs_remotePathname
     *            the new fe libs remote pathname
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public void setFeLibsRemotePathname(String feLibs_remotePathname) {
        this.feLibsRemotePathname = feLibs_remotePathname;
    }

    /**
     * Gets the third party libs remote pathname.
     * <p>
     * </p>
     * 
     * @return the third party libs remote pathname
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public String getThirdPartyLibsRemotePathname() {
        return thirdPartyRemotePathname;
    }

    /**
     * Sets the third party libs remote pathname.
     * <p>
     * </p>
     * 
     * @param thirdPartyLibs_remotePathname
     *            the new third party libs remote pathname
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public void setThirdPartyLibsRemotePathname(String thirdPartyLibs_remotePathname) {
        this.thirdPartyRemotePathname = thirdPartyLibs_remotePathname;
    }

    /**
     * Gets the be libs local path name.
     * <p>
     * </p>
     * 
     * @return the be libs local path name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public String getBeLibsLocalPathName() {
        return beLibLocalPathName;
    }

    /**
     * Sets the be libs local path name.
     * <p>
     * </p>
     * 
     * @param beLibs_localPathName
     *            the new be libs local path name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public void setBeLibsLocalPathName(String beLibs_localPathName) {
        this.beLibLocalPathName = beLibs_localPathName;
    }

    /**
     * Gets the be inf local path name.
     * <p>
     * </p>
     * 
     * @return the be inf local path name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public String getBeInfLocalPathName() {
        return beInfLocalPathName;
    }

    /**
     * Sets the be inf local path name.
     * <p>
     * </p>
     * 
     * @param beInf_localPathName
     *            the new be inf local path name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public void setBeInfLocalPathName(String beInf_localPathName) {
        this.beInfLocalPathName = beInf_localPathName;
    }

    /**
     * Gets the thirdparty local path name.
     * <p>
     * </p>
     * 
     * @return the thirdparty local path name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public String getThirdPartyLocalPathName() {
        return thirdPartyLocalPathName;
    }

    /**
     * Sets the thirdparty local path name.
     * <p>
     * </p>
     * 
     * @param thirdparty_localPathName
     *            the new thirdparty local path name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public void setThirdPartyLocalPathName(String thirdparty_localPathName) {
        this.thirdPartyLocalPathName = thirdparty_localPathName;
    }

    /**
     * Gets the fe libs local path name.
     * <p>
     * </p>
     * 
     * @return the fe libs local path name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public String getFeLibsLocalPathName() {
        return feLibsLocalPathName;
    }

    /**
     * Sets the fe libs local path name.
     * <p>
     * </p>
     * 
     * @param feLibs_localPathName
     *            the new fe libs local path name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public void setFeLibsLocalPathName(String feLibs_localPathName) {
        this.feLibsLocalPathName = feLibs_localPathName;
    }

    /**
     * Gets the be inf remote path name.
     * <p>
     * </p>
     * 
     * @return the be inf remote path name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public String getBeInfRemotePathName() {
        return beInfRemotePathname;
    }

    /**
     * Sets the be inf remote path name.
     * <p>
     * </p>
     * 
     * @param beInf_remotePathname
     *            the new be inf remote path name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    public void setBeInfRemotePathName(String beInf_remotePathname) {
        this.beInfRemotePathname = beInf_remotePathname;
    }

    /**
     * Sets the fe host name.
     * 
     * @param feHostName
     *            the feHostName to set
     * @author U079754 A. Emre Zorlu
     * @since Jun 15, 2016
     */
    public void setFeHostName(String feHostName) {
        this.feHostName = feHostName;
    }

    /**
     * Gets the fe host name.
     * 
     * @return the feHostName
     * @author U079754 A. Emre Zorlu
     * @since Jun 15, 2016
     */
    public String getFeHostName() {
        return feHostName;
    }

    /**
     * Sets the be host name.
     * 
     * @param beHostName
     *            the beHostName to set
     * @author U079754 A. Emre Zorlu
     * @since Jun 15, 2016
     */
    public void setBeHostName(String beHostName) {
        this.beHostName = beHostName;
    }

    /**
     * Gets the be host name.
     * 
     * @return the beHostName
     * @author U079754 A. Emre Zorlu
     * @since Jun 15, 2016
     */
    public String getBeHostName() {
        return beHostName;
    }
}
