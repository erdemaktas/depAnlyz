/*
 * @author U079754 A. Emre Zorlu
 * @date Jun 1, 2016
 */
package com.sgo.depanalyze.hibernate.dao;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sgo.depanalyze.enums.ArtifactType;
import com.sgo.depanalyze.enums.CRUD_FLAG;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepArtifact;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepDaoDependency;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepExecution;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepFeDependency;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepGlobalVariableUse;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepHbmTables;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepNqDependency;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepParameter;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepPlsqlDependency;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepServiceClass;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepServiceDependency;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepServiceInterface;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepServiceMethod;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepServiceSuperClass;
import com.sgo.depanalyze.util.MavenPomProperties;
import com.sgo.depanalyze.util.SpringApplicationContext;

/**
 * The Class DataAccessUtil.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:54 AM
 */
public class DataAccessUtil {
    //
    /** The hmn service dao. */
    private static HmnDependencyDAO hmnDependencyDAO = (HmnDependencyDAO) SpringApplicationContext.getBean("hmnDependencyDAO");
    //
    /** The hmn parameters dao. */
    private static HmnDepParameterDAO hmnParametersDAO = (HmnDepParameterDAO) SpringApplicationContext.getBean("hmnDepParameterDAO");
    //
    /** The hmn syspar dao. */
    private static HmnSysparDAO hmnSysparDAO = (HmnSysparDAO) SpringApplicationContext.getBean("hmnSysparDAO");
    //
    /** The logger. */
    private static Logger logger = Logger.getLogger(DataAccessUtil.class);

    /**
     * Save artifact.
     * 
     * @param fileName
     *            the file name
     * @param artifactType
     *            the artifact type
     * @param executionId
     *            the execution id
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static Long saveArtifact(String fileName, ArtifactType artifactType, Long executionId) throws DataAccessException {
        HmnDepArtifact datamodel = new HmnDepArtifact(null, fileName, artifactType.toString(), executionId);
        return hmnDependencyDAO.save(datamodel);
    }

    /**
     * Save artifact with pom properties
     * 
     * @param fileName
     *            the file name
     * @param artifactType
     *            the artifact type
     * @param executionId
     *            the execution id
     * @param pomProperties
     *            the pom properties
     * @return the long
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:33:04 PM
     */
    public static Long saveArtifact(String fileName, ArtifactType artifactType, Long executionId, MavenPomProperties pomProperties) throws DataAccessException {
        HmnDepArtifact datamodel = new HmnDepArtifact(null, fileName, artifactType.toString(), executionId);
        if (pomProperties != null) {
            datamodel.setGroupId(pomProperties.getGroupId());
            datamodel.setArtifactId(pomProperties.getArtifactId());
            datamodel.setArtifactVersion(pomProperties.getVersion());
            datamodel.setArtifactDesc(pomProperties.getDescription());
        }
        return hmnDependencyDAO.save(datamodel);
    }

    /**
     * Save service ınterface.
     * 
     * @param executionId
     *            the service class ıd
     * @param interfaceName
     *            the interface name
     * @return the long
     * @throws DataAccessException
     *             the data access exception
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @param artifactId
     * @since 01.Tem.2014 01:47:14
     */
    public static Long saveServiceInterface(Long executionId, String className, String interfaceName, Long artifactId) throws DataAccessException {
        HmnDepServiceInterface datamodel = new HmnDepServiceInterface();
        datamodel.setExecutionId(executionId);
        datamodel.setClassName(className);
        datamodel.setInterfaceName(interfaceName);
        datamodel.setArtifactId(artifactId);
        return hmnDependencyDAO.save(datamodel);
    }

    /**
     * Save service ınterface.
     * 
     * @param executionId
     *            the execution ıd
     * @param serviceClazz
     *            the service clazz
     * @param interfaceClazz
     *            the interface clazz
     * @return the long
     * @throws DataAccessException
     *             the data access exception
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @param artifactId
     * @since 01.Tem.2014 03:03:01
     */
    public static Long saveServiceInterface(Long executionId, Class<?> serviceClazz, Class<?> interfaceClazz, Long artifactId) throws DataAccessException {
        return saveServiceInterface(executionId, serviceClazz.getName(), interfaceClazz.getName(), artifactId);
    }

    /**
     * Save service super class.
     * 
     * @param className
     *            the class name
     * @param superClassName
     *            the super class name
     * @param executionId
     *            the execution id
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 2:26:57 PM
     */
    public static Long saveServiceSuperClass(String className, String superClassName, Long executionId, Long artifactId) throws DataAccessException {
        return hmnDependencyDAO.save(new HmnDepServiceSuperClass(null, className, superClassName, executionId, artifactId));
    }

    /**
     * Save service impl.
     * 
     * @param implClazzName
     *            the impl clazz name
     * @param artifactId
     *            the artifact id
     * @param executionId
     *            the execution id
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static Long saveServiceImpl(String implClazzName, Long artifactId, Long executionId) throws DataAccessException {
        HmnDepServiceClass datamodel = new HmnDepServiceClass(null, implClazzName, artifactId, executionId);
        return hmnDependencyDAO.save(datamodel);
    }

    /**
     * Save service method.
     * 
     * @param serviceId
     *            the service ıd
     * @param implMethod
     *            the implementaion method
     * @param intfMethod
     *            the interface method
     * @return the long
     * @throws DataAccessException
     *             the data access exception
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @param className
     * @param executionId
     * @param artifactId
     * @param d
     * @since 30.Haz.2014 08:45:21
     */
    public static Long saveServiceMethod(Method implMethod, Method intfMethod, String digest, String className, Long executionId, Long artifactId) throws DataAccessException {
        HmnDepServiceMethod datamodel = new HmnDepServiceMethod();
        datamodel.setInterfaceName(intfMethod.getDeclaringClass().getName());
        datamodel.setMethodName(implMethod.getName());
        datamodel.setSignature(intfMethod.toString()/* implMethod.toString() */);
        datamodel.setDigest(digest);
        datamodel.setClassName(className);
        datamodel.setExecutionId(executionId);
        datamodel.setArtifactId(artifactId);
        return hmnDependencyDAO.save(datamodel);
    }

    /**
     * Save service method.
     * 
     * @param implMethod
     *            the impl method
     * @param intfMethod
     *            the intf method
     * @param digest
     *            the digest
     * @param serviceClazz
     *            the service clazz
     * @param executionId
     *            the execution id
     * @return the long
     * @throws DataAccessException
     *             the data access exception
     * @author Selçuk Giray ÖZDAMAR
     * @param artifactId
     * @since May 25, 2016
     */
    public static Long saveServiceMethod(Method implMethod, Method intfMethod, String digest, Class<?> serviceClazz, Long executionId, Long artifactId) throws DataAccessException {
        return saveServiceMethod(implMethod, intfMethod, digest, serviceClazz.getName(), executionId, artifactId);
    }

    /**
     * Save service to service dependency.
     * 
     * @param executionId
     *            the execution id
     * @param artifactId
     *            the artifact id
     * @param serviceClass
     *            the service class
     * @param serviceMethod
     *            the service method
     * @param serviceSignature
     *            the service signature
     * @param targetService
     *            the target service
     * @param targetMethod
     *            the target method
     * @param targetSignature
     *            the target signature
     * @param dependencyLevel
     *            the dep level
     * @param dependencyPath
     *            the dep path
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static Long saveServiceDependency(Long executionId, Long artifactId, String serviceClass, String serviceMethod, String serviceSignature, String targetService, String targetMethod,
            String targetSignature, int dependencyLevel, String dependencyPath) throws DataAccessException {
        HmnDepServiceDependency datamodel = new HmnDepServiceDependency();
        datamodel.setExecutionId(executionId);
        datamodel.setArtifactId(artifactId);
        datamodel.setDepLevel(dependencyLevel);
        datamodel.setDepPath(dependencyPath);
        datamodel.setSourceClass(serviceClass);
        datamodel.setSourceMethod(serviceMethod);
        datamodel.setSourceSignature(serviceSignature);
        datamodel.setTargetService(targetService);
        datamodel.setTargetMethod(targetMethod);
        datamodel.setTargetSignature(targetSignature);
        return hmnDependencyDAO.save(datamodel);
    }

    /**
     * Save dao dependency.
     * 
     * @param executionId
     *            the execution id
     * @param serviceClass
     *            the service class
     * @param serviceMethod
     *            the service method
     * @param daoName
     *            the dao name
     * @param daoMethod
     *            the dao method
     * @param dependencyLevel
     *            the dependency level
     * @param dependencyPath
     *            the dependency path
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 5:10:37 PM
     */
    public static Long saveDaoDependency(Long executionId, String serviceClass, String serviceMethod, String daoName, String daoMethod, CRUD_FLAG crudFlag, Integer dependencyLevel,
            String dependencyPath, Long artifactId) throws DataAccessException {
        HmnDepDaoDependency datamodel = new HmnDepDaoDependency();
        datamodel.setDaoMethod(daoMethod);
        datamodel.setDaoName(daoName);
        datamodel.setCrudFlag(crudFlag.toString());
        datamodel.setDependencyLevel(dependencyLevel);
        datamodel.setDependencyPath(dependencyPath);
        datamodel.setExecutionId(executionId);
        datamodel.setServiceClass(serviceClass);
        datamodel.setServiceMethod(serviceMethod);
        datamodel.setArtifactId(artifactId);
        return hmnDependencyDAO.save(datamodel);
    }

    /**
     * Save FrontEnd to service dependencyy.
     * 
     * @param executionId
     *            the execution id
     * @param artifactId
     *            the artifact id
     * @param sourceClass
     *            the source class
     * @param sourceMethod
     *            the source method
     * @param sourceSignature
     *            the source signature
     * @param targetService
     *            the target service
     * @param targetMethod
     *            the target service method
     * @param targetSignature
     *            the target service method signature
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static Long saveFeDependency(Long executionId, Long artifactId, String sourceClass, String sourceMethod, String sourceSignature, String targetService, String targetMethod,
            String targetSignature, int dependencyLevel, String dependencyPath) throws DataAccessException {
        HmnDepFeDependency datamodel = new HmnDepFeDependency(null, executionId, artifactId, sourceClass, sourceMethod, sourceSignature, targetService, targetMethod, targetSignature);
        datamodel.setDepLevel(dependencyLevel);
        datamodel.setDepPath(dependencyPath);
        datamodel.setArtifactId(artifactId);
        return hmnDependencyDAO.save(datamodel);
    }

    /**
     * Save plsql depend.
     * 
     * @param serviceClass
     *            the service class
     * @param serviceMethod
     *            the service method
     * @param packageName
     *            the package name
     * @param procName
     *            the proc name
     * @param depLevel
     *            the dep level
     * @param depPath
     *            the dep path
     * @param executionId
     *            the execution id
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static Long savePlsqlDependency(String serviceClass, String serviceMethod, String packageName, String procName, int depLevel, String depPath, Long executionId, Long artifactId)
            throws DataAccessException {
        HmnDepPlsqlDependency datamodel = new HmnDepPlsqlDependency(null, serviceClass, serviceMethod, packageName, procName, depLevel, depPath, executionId, artifactId);
        return hmnDependencyDAO.save(datamodel);
    }

    /**
     * Save named query depend.
     * 
     * @param serviceClass
     *            the service class
     * @param serviceMethod
     *            the service method
     * @param queryName
     *            the query name
     * @param depLevel
     *            the dep level
     * @param depPath
     *            the dep path
     * @param executionId
     *            the execution id
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static Long saveNamedQueryDependency(String serviceClass, String serviceMethod, String queryName, int depLevel, String depPath, Long executionId, Long artifactId)
            throws DataAccessException {
        HmnDepNqDependency datamodel = new HmnDepNqDependency(null, serviceClass, serviceMethod, queryName, depLevel, depPath, executionId, artifactId);
        return hmnDependencyDAO.save(datamodel);
    }

    /**
     * Delete all with artifact id.
     * 
     * @param artifactId
     *            the artifact id
     * @throws DataAccessException
     *             the data access exception
     * @author U079754 A. Emre Zorlu
     * @since May 27, 2016
     */
    public static void backUpAndDelete(Long artifactId) throws DataAccessException {
        hmnDependencyDAO.backUpWithArtifactId(artifactId);
        hmnDependencyDAO.deleteAllWithArtifactId(artifactId);
    }

    /**
     * Find parameter by name.
     * 
     * @param paramName
     *            the param name
     * @return the hmn dep parameter
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static HmnDepParameter findParameterByName(String paramName) throws DataAccessException {
        List paramList = hmnParametersDAO.findByParamName(paramName);
        return paramList != null && !paramList.isEmpty() ? ((HmnDepParameter) paramList.get(0)) : null;
    }

    /**
     * Find all parameters.
     * 
     * @return the list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @param environment
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static List findAllParameters(String environment) throws DataAccessException {
        List paramList = hmnParametersDAO.findAllParameters(environment);
        return paramList;
    }

    /**
     * Creates the execution.
     * 
     * @param executionDate
     *            the execution date
     * @param hostComputer
     *            the host computer
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @param environment
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static Long createExecution(String environment, Date executionDate, String hostComputer) throws DataAccessException {
        return hmnDependencyDAO.save(new HmnDepExecution(null, environment, executionDate, hostComputer));
    }

    /**
     * Update execution finish date.
     * 
     * @param executionId
     *            the execution id
     * @param finishDate
     *            the finish date
     * @return true, if update execution finish date
     */
    public static boolean updateExecutionFinishDate(Long executionId, Date finishDate) throws DataAccessException {
        HmnDepExecution executionDatamodel = hmnDependencyDAO.findExecutionById(executionId);
        if (executionDatamodel == null) {
            logger.warn("Cannot find HmnDepExecution datamodel with id:" + executionId);
            return false;
        }
        executionDatamodel.setFinishDate(finishDate);
        hmnDependencyDAO.updateDataModel(executionDatamodel);
        return true;
    }

    /**
     * Execute sql.
     * 
     * @param sql
     *            the sql
     * @return the list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 3, 2013 9:10:52 AM
     */
    public static List<Map<String, Object>> executeSQL(String sql) throws DataAccessException {
        return hmnDependencyDAO.executeSQL(sql);
    }

    /**
     * Save dao hbm table mapping.
     * 
     * @param executionId
     *            the execution id
     * @param artifactId
     *            the artifact id
     * @param daoClass
     *            the dao class
     * @param modelClass
     *            the model class
     * @param tableName
     *            the table name
     * @param datasource
     *            the datasource
     * @param hbmFile
     *            the hbm file
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:43:49 PM
     */
    public static Long saveDaoHbmTableMapping(Long executionId, Long artifactId, String daoClass, String modelClass, String tableName, String datasource, String hbmFile) throws DataAccessException {
        HmnDepHbmTables datamodel = new HmnDepHbmTables();
        datamodel.setExecutionId(executionId);
        datamodel.setArtifactId(artifactId);
        datamodel.setDaoClass(daoClass);
        datamodel.setModelClass(modelClass);
        datamodel.setTableName(tableName);
        datamodel.setDatasource(datasource);
        datamodel.setHbmFile(hbmFile);
        return hmnDependencyDAO.save(datamodel);
    }

    /**
     * Find artifact by execution id.
     * 
     * @param executionId
     *            the execution id
     * @param artifactId
     *            the artifact id
     * @return the hmn dep artifact
     * @throws DataAccessException
     *             the data access exception
     * @author Selçuk Giray ÖZDAMAR
     * @since May 27, 2016
     */
    public static HmnDepArtifact findArtifactByExecutionId(Long executionId, String artifactId) throws DataAccessException {
        return hmnDependencyDAO.findArtifactByExecutionId(executionId, artifactId);
    }

    /**
     * Find artifact by artifactId and environment.
     * 
     * @param artifactId
     *            the artifact id
     * @param environment
     *            the environment
     * @return the hmn dep artifact
     * @author U079754 A. Emre Zorlu
     * @since Jun 6, 2016
     */
    public static HmnDepArtifact findArtifactByFileNameAndEnvironment(String artifactId) {
        return hmnDependencyDAO.findArtifactByFileNameAndEnvironment(artifactId);
    }

    /**
     * @author U079754 A. Emre Zorlu
     * @since Jun 8, 2016
     */
    public static void test() {
        hmnDependencyDAO.TEST1();
    }

    /**
     * Save global variable use.
     * 
     * @param executionId
     *            the execution id
     * @param artifactId
     *            the artifact id
     * @param className
     *            the class name
     * @param methodName
     *            the method name
     * @param fieldName
     *            the field name
     * @param fieldType
     *            the field type
     * @param referenceType
     *            the reference type
     * @param dependencyLevel
     *            the dependency level
     * @param dependencyPath
     *            the dependency path
     * @return the long
     * @throws DataAccessException
     *             the data access exception
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public static Long saveGlobalVariableUse(Long executionId, Long artifactId, String className, String methodName, String usageType, String fieldName, String fieldType, String referenceType,
            Integer dependencyLevel, String dependencyPath) throws DataAccessException {
        HmnDepGlobalVariableUse datamodel = new HmnDepGlobalVariableUse();
        datamodel.setExecutionId(executionId);
        datamodel.setArtifactId(artifactId);
        datamodel.setClassName(className);
        datamodel.setMethodName(methodName);
        datamodel.setUsageType(usageType);
        datamodel.setFieldName(fieldName);
        datamodel.setFieldType(fieldType);
        datamodel.setReferenceType(referenceType);
        datamodel.setDepLevel(dependencyLevel);
        datamodel.setDepPath(dependencyPath);
        return hmnDependencyDAO.save(datamodel);
    }

    /**
     * Gets the syspar service list.
     * 
     * @return the syspar service list
     * @throws DataAccessException
     *             the data access exception
     * @author U079754 A. Emre Zorlu
     * @since Nov 24, 2016
     */
    public static Map<String, String> getSysparServiceList() throws DataAccessException {
        return hmnSysparDAO.getSysparServiceList();
    }
}
