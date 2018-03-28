package com.sgo.depanalyze.ftp;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.log4j.Logger;

import com.sgo.depanalyze.datatypes.DependencyScanResult;
import com.sgo.depanalyze.enums.ArtifactType;
import com.sgo.depanalyze.util.FileSystemUtils;
import com.sgo.depanalyze.util.impl.BackendDependencyScanner;
import com.sgo.depanalyze.util.impl.DefaultDependencyPersister;
import com.sgo.depanalyze.util.impl.FrontendDependencyScanner;
import com.sgo.depanalyze.util.intf.IDependencyPersister;
import com.sgo.depanalyze.util.intf.IDependencyScanner;

public class FtpFileWorkerThread implements Runnable {
    //
    /** The logger. */
    static Logger logger = Logger.getLogger(FtpFileWorkerThread.class);

    /**
     * Instantiates a new worker thread.
     * 
     * @param jarFile
     *            the jar file
     * @param artifactType
     *            the artifact type
     * @param executionId
     *            the execution id
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    public FtpFileWorkerThread(FTPClient ftpClient,FTPFile ftpFile) {
    }

    /** The ftp client. */
    protected FTPClient ftpClient = null;
    protected FTPFile ftpFile = null;
    protected String localPath;
    protected String remotePath;
    
    private void downloadFiles() {
        logger.info("STARTED downloading jars from remotePath: " + remotePath + " to localPath:" + localPath);
        // change current directory
        try {
            logger.debug("File is " + ftpFile.getName());
            // get output stream
            OutputStream output = new FileOutputStream(localPath + ftpFile.getName());
            // get the file from the remote system
            if (ftpClient.retrieveFile(ftpFile.getName(), output)) {
                logger.info(ftpFile.getName() + " retrieved successfully.");
            } else {
                logger.warn(ftpFile.getName() + " retrieve failed.");
            }
            // close output stream
            output.close();
        } catch (Exception e) {
            logger.error("Exception occured while downloading Back-End Jars, " + e.getMessage(), e);
        }
        logger.info("FINISHED downloading jars from remotePath: " + remotePath + " to localPath:" + localPath);
    }

    /**
     * Process command.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    private void processCommand() {
        downloadFiles();
    }

    /*
     * (non-Javadoc) O
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        try {
//            logger.info(Thread.currentThread().getName() + " started. jarFile = " + jarFile);
//            processCommand();
//            logger.info(Thread.currentThread().getName() + " finished. jarFile = " + jarFile);
        } catch (Exception e) {
            // } catch (Throwable e) {
//            logger.error(String.format("Exception occured on Thread:%s  jarFile:%s", Thread.currentThread().getName(), jarFile), e);
        }
    }


}
