/*
 * @author U079754 A. Emre Zorlu
 * @date Jun 6, 2016
 */
package com.sgo.depanalyze.concurrent;

import org.apache.log4j.Logger;

import com.sgo.depanalyze.datatypes.DependencyScanResult;
import com.sgo.depanalyze.enums.ArtifactType;
import com.sgo.depanalyze.hibernate.dao.DataAccessUtil;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepArtifact;
import com.sgo.depanalyze.util.FileSystemUtils;
import com.sgo.depanalyze.util.MavenPomProperties;
import com.sgo.depanalyze.util.impl.BackendDependencyScanner;
import com.sgo.depanalyze.util.impl.DefaultDependencyPersister;
import com.sgo.depanalyze.util.impl.FrontendDependencyScanner;
import com.sgo.depanalyze.util.impl.MavenPomPropertyResolver;
import com.sgo.depanalyze.util.intf.IDependencyPersister;
import com.sgo.depanalyze.util.intf.IDependencyScanner;

/**
 * The Class WorkerThread.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 10:18:25 AM
 */
public class WorkerThread implements Runnable {
    /** The jar file. */
    private String jarFile = null;
    /** The artifact type. */
    private ArtifactType artifactType = null;
    /** The execution id. */
    private Long executionId = null;
    /** The environment. */
    private String environment = null;
    /** The dependency scanner. */
    private IDependencyScanner dependencyScanner = null;
    //
    /** The logger. */
    static Logger logger = Logger.getLogger(WorkerThread.class);

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
    public WorkerThread(String jarFile, ArtifactType artifactType, Long executionId) {
        this.jarFile = jarFile;
        this.artifactType = artifactType;
        this.executionId = executionId;
        if (ArtifactType.HMN_BACK_END == artifactType) {
            dependencyScanner = new BackendDependencyScanner();
        } else if (ArtifactType.HMN_FRONT_END == artifactType) {
            dependencyScanner = new FrontendDependencyScanner();
        }
    }

    /**
     * Instantiates a new worker thread.
     * 
     * @param jarFile
     *            the jar file
     * @param artifactType
     *            the artifact type
     * @param executionId
     *            the execution id
     * @param environment
     *            the environment
     * @author U079754 A. Emre Zorlu
     * @since Jun 6, 2016
     */
    public WorkerThread(String jarFile, ArtifactType artifactType, Long executionId, String environment) {
        this.jarFile = jarFile;
        this.artifactType = artifactType;
        this.executionId = executionId;
        this.environment = environment;
        if (ArtifactType.HMN_BACK_END == artifactType) {
            dependencyScanner = new BackendDependencyScanner();
        } else if (ArtifactType.HMN_FRONT_END == artifactType) {
            dependencyScanner = new FrontendDependencyScanner();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        try {
            logger.info(Thread.currentThread().getName() + " started. jarFile = " + jarFile);
            processCommand();
            logger.info(Thread.currentThread().getName() + " finished. jarFile = " + jarFile);
        } catch (Exception e) {
            // } catch (Throwable e) {
            logger.error(String.format("Exception occured on Thread:%s  jarFile:%s", Thread.currentThread().getName(), jarFile), e);
        }
    }

    /**
     * Process new command.
     * 
     * @author U079754 A. Emre Zorlu
     * @since Jun 6, 2016
     */
    private void processCommand() {
        String fileName = FileSystemUtils.getBaseName(jarFile);
        //
        MavenPomProperties pomProperties = MavenPomPropertyResolver.resolvePomProperties(jarFile);
        //
        if (ArtifactType.HMN_BACK_END == artifactType) {
            logger.info("artifact id:  " + pomProperties.getArtifactId() + " version : " + pomProperties.getVersion());
        }
        HmnDepArtifact previousArtifact = DataAccessUtil.findArtifactByFileNameAndEnvironment(pomProperties.getArtifactId());
        //
        if (previousArtifact != null && pomProperties.getVersion().equals(previousArtifact.getArtifactVersion())) {
            return;
        }
        //
        DependencyScanResult jarDependencyResult = dependencyScanner.scanDependencies(jarFile);
        //
        if (previousArtifact != null) {
            DataAccessUtil.backUpAndDelete(previousArtifact.getId());
        }
        IDependencyPersister dependencyPersister = new DefaultDependencyPersister();
        Long artifactId = dependencyPersister.saveArtifact(fileName, artifactType, executionId, jarDependencyResult.getPomProperties());
        //
        if (ArtifactType.HMN_BACK_END.equals(artifactType)) {
            dependencyPersister.saveDaoHbmTableMappings(executionId, artifactId, jarDependencyResult.getDaoTableMappingList());
        }
        //
        dependencyPersister.saveDependencyList(jarDependencyResult.getClassLevelDependencies(), artifactId, artifactType, executionId);
    }
}
