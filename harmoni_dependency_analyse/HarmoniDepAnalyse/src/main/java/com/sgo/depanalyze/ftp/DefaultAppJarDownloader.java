package com.sgo.depanalyze.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.log4j.Logger;

/**
 * The Class DefaultAppJarDownloader.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 10:15:56 AM
 */
public class DefaultAppJarDownloader extends AbstractAppJarDownloader {
    /** The logger. */
    static Logger logger = Logger.getLogger(DefaultAppJarDownloader.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.ftp.AbstractAppJarDownloader#downloadBackEndJars()
     */
    @Override
    public void downloadBackEndJars() {
        logger.info("STARTED downloading Back-End application jars");
        try {
            String remotePath = getBeLibsRemotePathname();
            String localPath = getBeLibsLocalPathName();
            FTPFileFilter filter = new FTPFileFilter() {
                @Override
                public boolean accept(FTPFile ftpFile) {
                    return (ftpFile.isFile() && ftpFile.getName().endsWith(".jar"));
                }
            };
            downloadFiles(remotePath, filter, localPath, true);
        } catch (Exception e) {
            logger.error("Exception occured while downloading Back-End Jars, " + e.getMessage(), e);
        }
        logger.info("FINISHED downloading Back-End application jars");
        
        // INF download
        logger.info("STARTED downloading Back-End INF jars");
        try {
            String remotePath = getBeInfRemotePathName();
            String localPath = getBeInfLocalPathName();
            FTPFileFilter filter = new FTPFileFilter() {
                @Override
                public boolean accept(FTPFile ftpFile) {
                    return (ftpFile.isFile() && ftpFile.getName().endsWith(".jar"));
                }
            };
            downloadFiles(remotePath, filter, localPath, true);
        } catch (Exception e) {
            logger.error("Exception occured while downloading Back-End INF Jars, " + e.getMessage(), e);
        }
        logger.info("FINISHED downloading Back-End INF jars");
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.ftp.AbstractAppJarDownloader#downloadFrontEndJars()
     */
    @Override
    public void downloadFrontEndJars() {
        logger.info("STARTED downloading Front-End application jars");
        try {
            String remotePath = getFeLibsRemotePathname();
            String localPath = getFeLibsLocalPathName();
            FTPFileFilter filter = new FTPFileFilter() {
                @Override
                public boolean accept(FTPFile ftpFile) {
                    return (ftpFile.isFile() && ftpFile.getName().endsWith(".jar"));
                }
            };
            downloadFiles(remotePath, filter, localPath, true);
        } catch (Exception e) {
            logger.error("Exception occured while downloading Back-End Jars, " + e.getMessage(), e);
        }
        logger.info("FINISHED downloading Front-End application jars");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.ftp.AbstractAppJarDownloader#downloadThirdPartyJars()
     */
    @Override
    public void downloadThirdPartyJars() {
        logger.info("STARTED downloading ThirdParty jars");
        try {
            String remotePath = getThirdPartyLibsRemotePathname();
            String localPath = getThirdPartyLocalPathName();
            FTPFileFilter filter = new FTPFileFilter() {
                @Override
                public boolean accept(FTPFile ftpFile) {
                    return (ftpFile.isFile() && ftpFile.getName().endsWith(".jar"));
                }
            };
            downloadFiles(remotePath, filter, localPath, true);
        } catch (Exception e) {
            logger.error("Exception occured while downloading ThirdParty Jars, " + e.getMessage(), e);
        }
        logger.info("FINISHED downloading ThirdParty jars");
    }

    /**
     * Clean directory.
     * 
     * @param directory
     *            the directory
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    private void cleanDirectory(String directory) throws IOException {
        try {
            FileUtils.cleanDirectory(new File(directory));
        } catch (IOException e) {
            logger.error(String.format("IOException (%s) occured while cleaning local directory:'%s'", e.getMessage(), directory), e);
            throw e;
        }
    }

    /**
     * Creates the directory.
     * 
     * @param directory
     *            the directory
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:15:56 AM
     */
    private void createDirectory(String directory) throws IOException {
        File theDir = new File(directory);
        // if the directory does not exist, create it
        if (!theDir.exists()) {
            logger.info("creating local directory: " + directory);
            boolean result = theDir.mkdirs(); // creates whole path automatically
            if (!result) {
                logger.warn("Failed to create local directory: " + directory);
            }
        }
    }

    /**
     * Download files.
     * 
     * @param remotePath
     *            the remote path
     * @param filter
     *            the filter
     * @param localPath
     *            the local path
     * @param cleanLocalPath
     *            the clean local path
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 3, 2013 9:11:02 AM
     */
    private void downloadFiles(String remotePath, FTPFileFilter filter, String localPath, boolean cleanLocalPath) {
        logger.info("STARTED downloading jars from remotePath: " + remotePath + " to localPath:" + localPath);
        // change current directory
        try {
            ftpClient.changeWorkingDirectory(remotePath);
            logger.debug("Current directory is " + ftpClient.printWorkingDirectory());
            FTPFile[] files = ftpClient.listFiles(remotePath, filter);
            localPath = localPath.endsWith("/") ? localPath : (localPath + "/");
            //
            createDirectory(localPath);
            //
            if (cleanLocalPath) {
                cleanDirectory(localPath);
            }
            //
            for (FTPFile file : files) {
                logger.debug("File is " + file.getName());
                // get output stream
                OutputStream output = new FileOutputStream(localPath + file.getName());
                // get the file from the remote system
                if (ftpClient.retrieveFile(file.getName(), output)) {
                    logger.info(file.getName() + " retrieved successfully.");
                } else {
                    logger.warn(file.getName() + " retrieve failed.");
                }
                // close output stream
                output.close();
            }
        } catch (Exception e) {
            logger.error("Exception occured while downloading Back-End Jars, " + e.getMessage(), e);
        }
        logger.info("FINISHED downloading jars from remotePath: " + remotePath + " to localPath:" + localPath);
    }
}
