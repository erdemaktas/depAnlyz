package com.sgo.depanalyze.util.impl;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.reflections.Reflections;
import org.springframework.util.StringUtils;

import com.sgo.depanalyze.concurrent.WorkerThread;
import com.sgo.depanalyze.datatypes.DependencyScanResult;
import com.sgo.depanalyze.enums.ArtifactType;
import com.sgo.depanalyze.enums.DependencyType;
import com.sgo.depanalyze.enums.YesNoCondition;
import com.sgo.depanalyze.ftp.AppJarDownloaderFactory;
import com.sgo.depanalyze.google_reflections.HmnReflections;
import com.sgo.depanalyze.hibernate.dao.DataAccessUtil;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepArtifact;
import com.sgo.depanalyze.util.AlarmLogger;
import com.sgo.depanalyze.util.Constants;
import com.sgo.depanalyze.util.FileSystemClassLoader;
import com.sgo.depanalyze.util.FileSystemUtils;
import com.sgo.depanalyze.util.MavenPomProperties;
import com.sgo.depanalyze.util.SystemProperties;
import com.sgo.depanalyze.util.intf.IDependencyAnalyzeRunner;
import com.sgo.depanalyze.util.intf.IDependencyPersister;
import com.sgo.depanalyze.util.intf.IDependencyScanner;

/**
 * The Class DependencyAnalyzeRunner.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 15, 2013 5:36:05 PM
 */
public final class DependencyAnalyzeRunner implements IDependencyAnalyzeRunner {
    private static final String JAVAX_SERVLET_API_MAVEN_REPO_PATH = "http://repo1.maven.org/maven2/javax/servlet/servlet-api/2.5/servlet-api-2.5.jar";
    private static final String JAVAX_MAIL_API_MAVEN_REPO_PATH = "http://repo1.maven.org/maven2/javax/mail/mail/1.4.7/mail-1.4.7.jar";
    /** The command line. */
    // private CommandLine commandLine = null;
    /** The beinf local path. */
    private String BEINF_LOCAL_PATH = "d:/temp_selcuk/be_inf_jars";
    /** The thirdparty local path. */
    private String THIRDPARTY_LOCAL_PATH = "d:/temp_selcuk/thirdparty";
    /** The belibs local path. */
    private String BELIBS_LOCAL_PATH = "d:/temp_selcuk/be_test_jars";
    /** The felibs local path. */
    private String FELIBS_LOCAL_PATH = "d:/temp_selcuk/fe_test_jars";
    /** The intbank local path. */
    private String INTBANK_LOCAL_PATH = "d:/HarmoniDependencyAnalyze/test_jars/intbank_jars";
    /** The Constant APP_CONTEXT_FILE. */
    private final String APP_CONTEXT_FILE = "applicationContext.xml";
    /** The logger. */
    private static Logger logger = Logger.getLogger(DependencyAnalyzeRunner.class);
    /** The backend dependency scanner. */
    private IDependencyScanner backendDependencyScanner;
    /** The environment. */
    private String environment;

    /**
     * Instantiates a new dependency analyze runner.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @param environment
     * @since Nov 15, 2013 5:36:05 PM
     */
    public DependencyAnalyzeRunner(String environment) {
        this.environment = environment;
    }

    // /**
    // * Instantiates a new dependency analyze runner.
    // *
    // * @param arguments
    // * the arguments
    // * @author U065352-Selçuk Giray ÖZDAMAR
    // * @since Nov 15, 2013 5:36:05 PM
    // */
    // public DependencyAnalyzeRunner(String[] arguments) {
    // this.commandLine = parseCommandLine(arguments);
    // }
    /**
     * Parses the command line.
     * 
     * @param args
     *            the args
     * @return the command line
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 15, 2013 5:36:05 PM
     */
    private CommandLine parseCommandLine(String[] args) {
        final CommandLineParser parser = new BasicParser();
        CommandLine commandLine = null;
        try {
            final Options options = new Options();
            options.addOption("environment", true, "environment option");
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            logger.error("ParseException occured in parseCommandLine with command line arguments:" + args.toString(), e);
        }
        return commandLine;
    }

    /**
     * Start ftp download.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 15, 2013 5:36:05 PM
     */
    private void startFtpDownload() {
        if (isFtpDownloadEnabled()) {
            AppJarDownloaderFactory.createDefaultAppJarDownloader().downloadAppJars();
        }
    }

    /**
     * Inits the classpath folders.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    private void initClassPathFolders() {
        String paramValue = "";
        paramValue = SystemProperties.getParameterValue(Constants.PRM_BELIBS_LOCAL_PATH);
        if (StringUtils.hasText(paramValue)) {
            BELIBS_LOCAL_PATH = paramValue;
        }
        //
        paramValue = SystemProperties.getParameterValue(Constants.PRM_BEINF_LOCAL_PATH);
        if (StringUtils.hasText(paramValue)) {
            BEINF_LOCAL_PATH = paramValue;
        }
        //
        paramValue = SystemProperties.getParameterValue(Constants.PRM_FELIBS_LOCAL_PATH);
        if (StringUtils.hasText(paramValue)) {
            FELIBS_LOCAL_PATH = paramValue;
        }
        //
        paramValue = SystemProperties.getParameterValue(Constants.PRM_THIRDPARTY_LOCAL_PATH);
        if (StringUtils.hasText(paramValue)) {
            THIRDPARTY_LOCAL_PATH = paramValue;
        }
    }

    /**
     * Class load jar files.
     * 
     * @return the map
     * @throws RuntimeException
     *             the runtime exception
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 15, 2013 5:36:05 PM
     */
    private Map<ArtifactType, List<String>> classLoadJarFiles() throws RuntimeException {
        Map<ArtifactType, List<String>> resultMap = new HashMap<ArtifactType, List<String>>();
        //
        List<String> thirdPartyList = FileSystemUtils.listFiles(THIRDPARTY_LOCAL_PATH);
        FileSystemClassLoader.addJarFile(thirdPartyList);
        /***/
        try {
            /** load servlet-api from maven repository */
            FileSystemClassLoader.addURL(new URL(JAVAX_SERVLET_API_MAVEN_REPO_PATH));
            /** load mail from maven repository */
            FileSystemClassLoader.addURL(new URL(JAVAX_MAIL_API_MAVEN_REPO_PATH));
        } catch (MalformedURLException e) {
            throw new RuntimeException("MalformedURLException occured for url", e);
        }
        // List<String> intBankFileList= Arrays.asList(
        // "http://falez11:8081/nexus/service/local/repositories/inhouse-snapshots/content/com/ykb/adc/adc-accounts-internal/SNAPSHOT/adc-accounts-internal-SNAPSHOT.jar",
        // "http://falez11:8081/nexus/service/local/repositories/inhouse-snapshots/content/com/ykb/adc/adc-accounts-model/SNAPSHOT/adc-accounts-model-SNAPSHOT.jar",
        // "http://falez11:8081/nexus/service/local/repositories/inhouse-snapshots/content/com/ykb/adc/adc-accounts-interface/SNAPSHOT/adc-accounts-interface-SNAPSHOT.jar",
        // "http://falez11:8081/nexus/service/local/repositories/inhouse-snapshots/content/com/ykb/adc/adc-commons/SNAPSHOT/adc-commons-SNAPSHOT.jar",
        // "http://falez11:8081/nexus/service/local/repositories/inhouse-snapshots/content/com/ykb/adc/adc-ws-accounts/SNAPSHOT/adc-ws-accounts-SNAPSHOT.jar"
        // );
        // try {
        // for(String url :intBankFileList){
        // /** load from maven repository */
        // FileSystemClassLoader.addURL(new URL(url));
        // }
        // } catch (MalformedURLException e) {
        // throw new RuntimeException("MalformedURLException occured for url", e);
        // }
        //
        List<String> infJarList = FileSystemUtils.listFiles(BEINF_LOCAL_PATH);
        FileSystemClassLoader.addJarFile(infJarList);
        //
        List<String> beJarList = FileSystemUtils.listFiles(BELIBS_LOCAL_PATH);
        FileSystemClassLoader.addJarFile(beJarList);
        resultMap.put(ArtifactType.HMN_BACK_END, beJarList);
        //
        List<String> feJarList = FileSystemUtils.listFiles(FELIBS_LOCAL_PATH);
        FileSystemClassLoader.addJarFile(feJarList);
        resultMap.put(ArtifactType.HMN_FRONT_END, feJarList);
        // internet bankacılığı jar listesi
        // List<String> intbankJarList = FileSystemUtils.listFiles(INTBANK_LOCAL_PATH);
        // FileSystemClassLoader.addJarFile(intbankJarList);
        // resultMap.put(ArtifactType.INT_BANK, intbankJarList);
        return resultMap;
    }

    /**
     * Verify clazz loader.
     * 
     * @throws ClassNotFoundException
     *             the class not found exception
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    private void verifyClazzLoader() throws ClassNotFoundException {
        // check classloader is ok
        // check class can be found ok
        ClassLoader cl = FileSystemClassLoader.getClassLoader();
        assert (cl != null);
        //
        URLClassLoader urlClazzLoader = (URLClassLoader) cl;
        assert (urlClazzLoader != null);
        //
        URL[] urls = urlClazzLoader.getURLs();
        assert (urls != null && urls.length > 0);
        // check class can be found ok
        String clazzName = "com.ykb.hmn.limitriskmanagement.common.service.intf.ILrmExchangeRateService";
        Class<?> c1 = Class.forName(clazzName, true, FileSystemClassLoader.getClassLoader());
        assert (c1 != null);
        //
        clazzName = "com.ykb.hmn.limitriskmanagement.common.service.LrmExchangeRateService";
        Class<?> c2 = Class.forName(clazzName, true, FileSystemClassLoader.getClassLoader());
        assert (c2 != null);
        //
        clazzName = "com.ykb.hmn.dlc.export.service.intf.IDlcSwiftMessageDetailService"; // "com.ykb.hmn.dlc.swift.service.intf.IDlcSwiftMessageDetailService";
        Class<?> c3 = Class.forName(clazzName, true, FileSystemClassLoader.getClassLoader());
        assert (c3 != null);
        // clazzName = "com.ykb.adc.accounts.services.AccountsService";
        // Class<?> c4 = Class.forName(clazzName, true, FileSystemClassLoader.getClassLoader());
        // assert (c4 != null);
    }

    /**
     * Checks if is backup enabled.
     * 
     * @return true, if is backup enabled
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 15, 2013 2:48:38 PM
     */
    private boolean isBackupEnabled() {
        return !(YesNoCondition.NO.toString().equals(SystemProperties.getParameterValue(Constants.PRM_BACKUP_ENABLE)));
    }

    /**
     * Checks if is ftp download enabled.
     * 
     * @return true, if is ftp download enabled
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    private boolean isFtpDownloadEnabled() {
        return YesNoCondition.YES.toString().equals(SystemProperties.getParameterValue(Constants.PRM_FTP_UPDATE_ENABLE));
    }

    /**
     * Checks if is run parallel.
     * 
     * @return true, if is run parallel
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    private boolean isRunParallel() {
        return YesNoCondition.YES.toString().equals(SystemProperties.getParameterValue(Constants.PRM_RUN_PARALLEL));
    }

    /**
     * Run dependency analyze.
     * 
     * @return true, if successful
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 15, 2013 5:36:05 PM
     */
    @Override
    public boolean runDependencyAnalyze() {
        /** create new dependency analyze execution record */
        Long executionId = createExecutionRecord();
        /** download jars from ftp */
        startFtpDownload();
        /** initialize classpath folder paths */
        initClassPathFolders();
        /** */
        Map<ArtifactType, List<String>> jarFileMap = null;
        /** load jars from classpath folders into classLoader */
        try {
            jarFileMap = classLoadJarFiles();
        } catch (Exception e) {
            logger.error("Exception occured in runDependencyAnalyze: " + e.getMessage(), e);
            // throw new RuntimeException("context", e);
            return false;
        }
        /** */
        try {
            verifyClazzLoader();
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException occured in runDependencyAnalyze:" + e.getMessage(), e);
            return false;
        }
        /** inialize google reflections */
        @SuppressWarnings({ "static-access", "unused" })
        Reflections reflections = HmnReflections.getInstance().getReflections();
        //
        // backupHistory();
        // //
        // DataAccessUtil.deleteAll();
        //
        if (isRunParallel()) {
            executionId = runParallel(jarFileMap, executionId);
        } else {
            executionId = runWithSingleThread(jarFileMap, executionId);
        }
        //
        logDependencyResults();
        // update finish date
        if (!DataAccessUtil.updateExecutionFinishDate(executionId, new Date())) {
            logger.warn("Cannot update execution finish time");
        }
        //
        logger.info(String.format("Dependency analyze FINISHED successfully (execution id:%d)", executionId));
        return true;
    }

    /**
     * Run parallel.
     * 
     * @param beJarList
     *            the be jar list
     * @param feJarList
     *            the fe jar list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    private Long runParallel(Map<ArtifactType, List<String>> jarFileMap, Long executionId) {
        logger.info(String.format("Dependency analyze starting in parallel execution mode (execution id:%d)", executionId));
        //
        List<String> beJarList = jarFileMap.get(ArtifactType.HMN_BACK_END);
        List<String> feJarList = jarFileMap.get(ArtifactType.HMN_FRONT_END);
        //
        int feThreadCount = Integer.valueOf(SystemProperties.getParameterValue(Constants.PRM_FE_SCAN_THREAD_COUNT));
        int beThreadCount = Integer.valueOf(SystemProperties.getParameterValue(Constants.PRM_BE_SCAN_THREAD_COUNT));
        // ExecutorService fe_executor = Executors.newFixedThreadPool(feThreadCount);
        // // FRONT_END jobs
        // for (String jarFile : feJarList) {
        // String artifactName = FileSystemUtils.getBaseName(jarFile);
        // if (artifactName.startsWith(Constants.FE_JAR_PREFIX)) {
        // Runnable worker = new WorkerThread(jarFile, ArtifactType.FRONT_END, executionId);
        // fe_executor.execute(worker);
        // }
        // }
        // fe_executor.shutdown();
        // /***/
        // // BACK_END jobs
        //
        // ExecutorService be_executor = Executors.newFixedThreadPool(beThreadCount);
        // for (String jarFile : beJarList) {
        // String artifactName = FileSystemUtils.getBaseName(jarFile);
        // if (artifactName.startsWith(Constants.BE_JAR_PREFIX)) {
        // Runnable worker = new WorkerThread(jarFile, ArtifactType.BACK_END, executionId);
        // be_executor.execute(worker);
        // }
        // }
        // be_executor.shutdown();
        // try {
        // be_executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        // fe_executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        // } catch (InterruptedException e) {
        // logger.error("InterruptedException occured:" + e.getMessage(), e);
        // }
        // // while (!fe_executor.isTerminated() && !be_executor.isTerminated()) {
        // // }
        /***/
        List<Runnable> commandList = new ArrayList<Runnable>(feJarList.size() + beJarList.size());
        for (String jarFile : feJarList) {
            String artifactName = FileSystemUtils.getBaseName(jarFile);
            if (artifactName.startsWith(Constants.FE_JAR_PREFIX)) {
                commandList.add(new WorkerThread(jarFile, ArtifactType.HMN_FRONT_END, executionId, environment));
            }
        }
        for (String jarFile : beJarList) {
            String artifactName = FileSystemUtils.getBaseName(jarFile);
            if (artifactName.startsWith(Constants.BE_JAR_PREFIX)) {
                // TODO: remove temporary test if for DLC component
                // if (artifactName.startsWith("HMN_DLC_")) {
                // continue;
                // }
                commandList.add(new WorkerThread(jarFile, ArtifactType.HMN_BACK_END, executionId, environment));
            }
        }
        int numberOfThreads = beThreadCount + feThreadCount;
        ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);
        for (Runnable command : commandList) {
            pool.execute(command);
        }
        shutdownAndAwaitTermination(pool);
        // pool.shutdown();
        // while (!pool.isTerminated()) {
        // }
        /***/
        logger.info(String.format("Dependency analyze parallel execution finished all threads (execution id: %d)", executionId));
        //
        return executionId;
    }

    private void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        logger.info("ExecutorService shutdown successfully");
        try {
            // Wait a while for existing tasks to terminate
            boolean awaitTerminationResult = pool.awaitTermination(30, TimeUnit.MINUTES);
            if (pool instanceof ThreadPoolExecutor) {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) pool;
                logger.info(String.format("ExecutorService (ActiveCount:%d) (CompletedTaskCount:%d)", threadPoolExecutor.getActiveCount(), threadPoolExecutor.getCompletedTaskCount()));
            }
            if (!awaitTerminationResult) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(30, TimeUnit.MINUTES)) {
                    logger.error("Pool did not terminate");
                }
            }
            logger.info("ExecutorService awaitTermination finished as :" + awaitTerminationResult);
        } catch (InterruptedException e) {
            logger.error("ExecutorService shutdownAndAwaitTermination has InterruptedException: " + e.getMessage(), e);
            if (pool instanceof ThreadPoolExecutor) {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) pool;
                logger.error(String.format("ExecutorService (ActiveCount:%d) (CompletedTaskCount:%d)", threadPoolExecutor.getActiveCount(), threadPoolExecutor.getCompletedTaskCount()));
            }
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Creates the execution record.
     * 
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    private Long createExecutionRecord() {
        String hostName = null;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.warn("UnknownHostException: " + e.getMessage(), e);
        }
        Long executionId = DataAccessUtil.createExecution(environment, new Date(), hostName);
        return executionId;
    }

    /**
     * Run with single thread.
     * 
     * @param beJarList
     *            the be jar list
     * @param feJarList
     *            the fe jar list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @return
     * @since Nov 11, 2013 10:18:25 AM
     */
    private Long runWithSingleThread(Map<ArtifactType, List<String>> jarFileMap, Long executionId) {
        logger.info(String.format("Dependency analyze starting in single thread execution mode (execution id:%d)", executionId));
        //
        List<String> beJarList = jarFileMap.get(ArtifactType.HMN_BACK_END);
        List<String> feJarList = jarFileMap.get(ArtifactType.HMN_FRONT_END);
        List<String> intBankJarList = jarFileMap.get(ArtifactType.INT_BANK);
        // ***** FRONT_END jar scan block *******
        IDependencyPersister dbPersister = new DefaultDependencyPersister();
        IDependencyScanner feDepScannner = new FrontendDependencyScanner();
        if (CollectionUtils.isNotEmpty(feJarList)) {
            for (String jarFile : feJarList) {
                String fileName = FileSystemUtils.getBaseName(jarFile);
                if (!fileName.startsWith("HMN_NLS_Com")) {
                    // if (!fileName.startsWith(Constants.FE_JAR_PREFIX)) {
                    continue;
                }
                MavenPomProperties pomProperties = MavenPomPropertyResolver.resolvePomProperties(jarFile);
                HmnDepArtifact previousArtifact = DataAccessUtil.findArtifactByFileNameAndEnvironment(pomProperties.getArtifactId());
                if (previousArtifact != null && pomProperties.getVersion().equals(previousArtifact.getArtifactVersion())) {
                    // aynı version daha once taranmış, pas geç
                    continue;
                }
                // List<ClassLevelDependencyList> jarDependencyResult = feDepScannner.scanDependencies(jarFile);
                DependencyScanResult jarDependencyResult = feDepScannner.scanDependencies(jarFile);
                if (previousArtifact != null) {
                    // bu artifact ilk defa geliyor
                    DataAccessUtil.backUpAndDelete(previousArtifact.getId());
                }
                Long artifactId = dbPersister.saveArtifact(fileName, ArtifactType.HMN_FRONT_END, executionId, jarDependencyResult.getPomProperties());
                dbPersister.saveDependencyList(jarDependencyResult.getClassLevelDependencies(), artifactId, ArtifactType.HMN_FRONT_END, executionId);
            }
        }
        // ***** BACK_END jar scan block *******
        IDependencyScanner beDepScannner = new BackendDependencyScanner();
        if (CollectionUtils.isNotEmpty(beJarList)) {
            for (String jarFile : beJarList) {
                String fileName = FileSystemUtils.getBaseName(jarFile);
                // if (!artifactName.startsWith("HMN_TKD_LoanCalculator")) {
                if (!fileName.startsWith("HMN_INV_Common_")) {
                    // if (!artifactName.startsWith(Constants.BE_JAR_PREFIX)) {
                    continue;
                }
                MavenPomProperties pomProperties = MavenPomPropertyResolver.resolvePomProperties(jarFile);
                //
                HmnDepArtifact previousArtifact = null;
                try {
                    previousArtifact = DataAccessUtil.findArtifactByFileNameAndEnvironment(pomProperties.getArtifactId());
                } catch (Exception e) {
                    logger.error("previous artifact not found:" + jarFile, e);
                }
                //
                if (previousArtifact != null && pomProperties.getVersion().equals(previousArtifact.getArtifactVersion())) {
                    // aynı version daha once taranmış, pas geç
                    continue;
                }
                DependencyScanResult jarDependencyResult = beDepScannner.scanDependencies(jarFile);
                if (previousArtifact != null) {
                    // bu artifact ilk defa geliyor
                    DataAccessUtil.backUpAndDelete(previousArtifact.getId());
                }
                Long artifactId = dbPersister.saveArtifact(fileName, ArtifactType.HMN_BACK_END, executionId, jarDependencyResult.getPomProperties());
                // dbPersister.saveDaoHbmTableMappings(executionId, artifactId, daoHbmTableMappings);
                dbPersister.saveDaoHbmTableMappings(executionId, artifactId, jarDependencyResult.getDaoTableMappingList());
                // List<ClassLevelDependencyList> jarDependencyResult = beDepScannner.scanDependencies(jarFile);
                dbPersister.saveDependencyList(jarDependencyResult.getClassLevelDependencies(), artifactId, ArtifactType.HMN_BACK_END, executionId);
            }
        }
        // ***** INT_Bank jar scan block *******
        IDependencyScanner defaultDepScannner = new DefaultAppDependencyScanner();
        if (CollectionUtils.isNotEmpty(intBankJarList)) {
            for (String jarFile : intBankJarList) {
                String fileName = FileSystemUtils.getBaseName(jarFile);
                if (!fileName.startsWith("adc") && !fileName.startsWith("ngi")) {
                    continue;
                }
                MavenPomProperties pomProperties = MavenPomPropertyResolver.resolvePomProperties(jarFile);
                HmnDepArtifact previousArtifact = DataAccessUtil.findArtifactByFileNameAndEnvironment(pomProperties.getArtifactId());
                //
                if (previousArtifact != null && pomProperties.getVersion().equals(previousArtifact.getArtifactVersion())) {
                    // aynı version daha once taranmış, pas geç
                    continue;
                }
                DependencyScanResult jarDependencyResult = defaultDepScannner.scanDependencies(jarFile);
                if (previousArtifact != null) {
                    // bu artifact ilk defa geliyor
                    DataAccessUtil.backUpAndDelete(previousArtifact.getId());
                }
                Long artifactId = dbPersister.saveArtifact(fileName, ArtifactType.INT_BANK, executionId, jarDependencyResult.getPomProperties());
                dbPersister.saveDependencyList(jarDependencyResult.getClassLevelDependencies(), artifactId, ArtifactType.INT_BANK, executionId);
            }
        }
        // List<String> intBankFileList = Arrays.asList(
        // "http://falez11:8081/nexus/service/local/repositories/inhouse-snapshots/content/com/ykb/adc/adc-accounts-internal/SNAPSHOT/adc-accounts-internal-SNAPSHOT.jar",
        // "http://falez11:8081/nexus/service/local/repositories/inhouse-snapshots/content/com/ykb/adc/adc-accounts-model/SNAPSHOT/adc-accounts-model-SNAPSHOT.jar",
        // "http://falez11:8081/nexus/service/local/repositories/inhouse-snapshots/content/com/ykb/adc/adc-accounts-interface/SNAPSHOT/adc-accounts-interface-SNAPSHOT.jar");
        /*
         * for (String path : intBankFileList) { JarFile jarfile = null; try { final URL jarUrl = new URL("jar:" + path
         * + "!/"); // Get the jar file JarURLConnection conn = (JarURLConnection) jarUrl.openConnection(); jarfile =
         * conn.getJarFile(); } catch (Exception e) { logger.error(e.getMessage(), e); } String fileName =
         * FileSystemUtils.getBaseName(path); DependencyScanResult jarDependencyResult =
         * defaultDepScannner.scanDependencies(jarfile); Long artifactId = dbPersister.saveArtifact(fileName,
         * ArtifactType.HMN_FRONT_END, executionId, jarDependencyResult.getPomProperties());
         * dbPersister.saveDependencyList(jarDependencyResult.getClassLevelDependencies(), artifactId,
         * ArtifactType.HMN_FRONT_END, executionId); }
         */
        return executionId;
    }

    /**
     * Gets the dependency count.
     * <p>
     * listedeki 1.eleman=newCount, 2.eleman=historyCount
     * </p>
     * 
     * @param dependencyType
     *            the dependency type
     * @return the dependency count <br>
     *         listedeki 1.eleman=newCount, 2.eleman=historyCount
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 27, 2013 6:30:34 PM
     */
    private List<Long> getDependencyCount(DependencyType dependencyType) {
        String sqlParamName = null;
        switch (dependencyType) {
        case SERVICE_DEPENDENCY:
            sqlParamName = Constants.PRM_QUERY_COMPARE_HISTORY_SERVICE_DEP;
            break;
        case FE_DEPENDENCY:
            sqlParamName = Constants.PRM_QUERY_COMPARE_HISTORY_FE_DEP;
            break;
        case NQ_DEPENDENCY:
            sqlParamName = Constants.PRM_QUERY_COMPARE_HISTORY_NQ_DEP;
            break;
        case PLSQL_DEPENDENCY:
            sqlParamName = Constants.PRM_QUERY_COMPARE_HISTORY_PLSQL_DEP;
            break;
        default:
            break;
        }
        String sql = SystemProperties.getParameterValue(sqlParamName);
        List<Map<String, Object>> resultMap = DataAccessUtil.executeSQL(sql);
        //
        Long historyCount = ((Number) resultMap.get(0).get("HISTORY_COUNT")).longValue();
        Long newCount = ((Number) resultMap.get(0).get("DEPENDENCY_COUNT")).longValue();
        return Arrays.asList(newCount, historyCount);
    }

    /**
     * Log dependency results.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 3, 2013 9:11:37 AM
     */
    private void logDependencyResults() {
        logger.info("Dependency Analyzer logging number of dependency results");
        //
        List<Long> depCounts = getDependencyCount(DependencyType.SERVICE_DEPENDENCY);
        Long newCount = depCounts.get(0);
        Long historyCount = depCounts.get(1);
        if (newCount.compareTo(historyCount) < 0) {
            AlarmLogger.logError(String.format("Number of Service-Service dependencies found:%d, previous(%d)", newCount, historyCount));
        }
        logger.info(String.format("Number of Service-Service dependencies found:%d, previous(%d)", newCount, historyCount));
        //
        depCounts = getDependencyCount(DependencyType.FE_DEPENDENCY);
        newCount = depCounts.get(0);
        historyCount = depCounts.get(1);
        if (newCount.compareTo(historyCount) < 0) {
            AlarmLogger.logError(String.format("Number of FrontEnd-Service dependencies found:%d, previous(%d)", newCount, historyCount));
        }
        logger.info(String.format("Number of FrontEnd-Service dependencies found:%d, previous(%d)", newCount, historyCount));
        //
        depCounts = getDependencyCount(DependencyType.NQ_DEPENDENCY);
        newCount = depCounts.get(0);
        historyCount = depCounts.get(1);
        if (newCount.compareTo(historyCount) < 0) {
            AlarmLogger.logError(String.format("Number of Service-NamedQuery dependencies found:%d, previous(%d)", newCount, historyCount));
        }
        logger.info(String.format("Number of Service-NamedQuery dependencies found:%d, previous(%d)", newCount, historyCount));
        //
        depCounts = getDependencyCount(DependencyType.PLSQL_DEPENDENCY);
        newCount = depCounts.get(0);
        historyCount = depCounts.get(1);
        if (newCount.compareTo(historyCount) < 0) {
            AlarmLogger.logError(String.format("Number of Service-PLSQL dependencies found:%d, previous(%d)", newCount, historyCount));
        }
        logger.info(String.format("Number of Service-PLSQL dependencies found:%d, previous(%d)", newCount, historyCount));
    }
}
