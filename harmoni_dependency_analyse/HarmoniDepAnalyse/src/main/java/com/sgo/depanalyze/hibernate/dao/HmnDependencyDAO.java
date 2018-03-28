/*
 * @author U079754 A. Emre Zorlu
 * @date Jun 1, 2016
 */
package com.sgo.depanalyze.hibernate.dao;

import java.security.InvalidParameterException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.StringUtils;

import com.sgo.depanalyze.hibernate.datamodel.HmnDepArtifact;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepDaoDependency;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepDatamodel;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepExecution;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepFeDependency;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepGlobalVariableUse;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepHbmTables;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepNqDependency;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepPlsqlDependency;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepServiceClass;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepServiceDependency;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepServiceInterface;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepServiceMethod;
import com.sgo.depanalyze.hibernate.datamodel.HmnDepServiceSuperClass;
import com.sgo.depanalyze.util.Constants;
import com.sgo.depanalyze.util.SystemProperties;
import com.sgo.depanalyze.util.mapper.ArtifactMapper;

/**
 * The Class HmnDepServiceInterfaceDAO.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 10:14:23 AM
 */
public class HmnDependencyDAO {
    // private static final String SYSPAR_SERVICE_REG_SQL =
    // "SELECT DISTINCT S.CLASSNAME, C.PACKAGE_PREFIX FROM SYSPAR.HMN_INF_SERVICE_REGISTRY S, SYSPAR.HMN_INF_COMPONENTS C WHERE S.COMPONENTOID = C.OID";
    //
    /** The Hibernate Template. */
    private HibernateTemplate template;
    //
    /** The jdbc template. */
    private JdbcTemplate jdbcTemplate;
    //
    /** The logger. */
    private static Logger logger = Logger.getLogger(HmnDependencyDAO.class);

    /**
     * Sets the session factory.
     * <p>
     * </p>
     * 
     * @param factory
     *            the new session factory
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:23 AM
     */
    public void setSessionFactory(SessionFactory factory) {
        template = new HibernateTemplate(factory);
    }

    /**
     * Sets the data source.
     * <p>
     * </p>
     * 
     * @param dataSource
     *            the new data source
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:23 AM
     */
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Save.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:44:34 AM
     */
    public Long save(HmnDepExecution datamodel) throws DataAccessException {
        return saveDataModel(datamodel);
    }

    /**
     * Save.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:44:34 AM
     */
    public Long save(HmnDepServiceInterface datamodel) throws DataAccessException {
        return saveDataModel(datamodel);
    }

    /**
     * Save.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 2:26:00 PM
     */
    public Long save(HmnDepServiceSuperClass datamodel) throws DataAccessException {
        return saveDataModel(datamodel);
    }

    /**
     * Save.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:44:34 AM
     */
    public Long save(HmnDepServiceClass datamodel) throws DataAccessException {
        return saveDataModel(datamodel);
    }

    /**
     * Save.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:44:34 AM
     */
    public Long save(HmnDepServiceMethod datamodel) throws DataAccessException {
        return saveDataModel(datamodel);
    }

    /**
     * Save.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:44:34 AM
     */
    public Long save(HmnDepArtifact datamodel) throws DataAccessException {
        return saveDataModel(datamodel);
    }

    /**
     * Save.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:44:34 AM
     */
    public Long save(HmnDepFeDependency datamodel) throws DataAccessException {
        return saveDataModel(datamodel);
    }

    /**
     * Save.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:44:34 AM
     */
    public Long save(HmnDepNqDependency datamodel) throws DataAccessException {
        return saveDataModel(datamodel);
    }

    /**
     * Save.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 5:08:05 PM
     */
    public Long save(HmnDepDaoDependency datamodel) throws DataAccessException {
        return saveDataModel(datamodel);
    }

    /**
     * Save.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:44:34 AM
     */
    public Long save(HmnDepPlsqlDependency datamodel) throws DataAccessException {
        return saveDataModel(datamodel);
    }

    /**
     * Save.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:44:34 AM
     */
    public Long save(HmnDepServiceDependency datamodel) throws DataAccessException {
        return saveDataModel(datamodel);
    }

    /**
     * Save.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:40:03 PM
     */
    public Long save(HmnDepHbmTables datamodel) throws DataAccessException {
        return saveDataModel(datamodel);
    }

    /**
     * Back up with artifact id.
     * 
     * @param artifactId
     *            the artifact id
     * @throws DataAccessException
     *             the data access exception
     * @author U079754 A. Emre Zorlu
     * @since Jun 8, 2016
     */
    public void backUpWithArtifactId(Long artifactId) throws DataAccessException {
        Map<String, String> backupScripts = new LinkedHashMap<String, String>();
        backupScripts.put(Constants.BACKUP_FE_DEPENDENCY_QUERY, SystemProperties.getParameterValue(Constants.BACKUP_FE_DEPENDENCY_QUERY));
        backupScripts.put(Constants.BACKUP_SERVICE_DEPENDENCY_QUERY, SystemProperties.getParameterValue(Constants.BACKUP_SERVICE_DEPENDENCY_QUERY));
        backupScripts.put(Constants.BACKUP_ARTIFACTS_QUERY, SystemProperties.getParameterValue(Constants.BACKUP_ARTIFACTS_QUERY));
        backupScripts.put(Constants.BACKUP_NQ_DEPENDENCIES_QUERY, SystemProperties.getParameterValue(Constants.BACKUP_NQ_DEPENDENCIES_QUERY));
        backupScripts.put(Constants.BACKUP_SERVICE_CLASSES_QUERY, SystemProperties.getParameterValue(Constants.BACKUP_SERVICE_CLASSES_QUERY));
        backupScripts.put(Constants.BACKUP_SERVICE_INTERFACES_QUERY, SystemProperties.getParameterValue(Constants.BACKUP_SERVICE_INTERFACES_QUERY));
        backupScripts.put(Constants.BACKUP_SERVICE_METHODS_QUERY, SystemProperties.getParameterValue(Constants.BACKUP_SERVICE_METHODS_QUERY));
        backupScripts.put(Constants.BACKUP_PLSQL_DEPENDENCIES_QUERY, SystemProperties.getParameterValue(Constants.BACKUP_PLSQL_DEPENDENCIES_QUERY));
        backupScripts.put(Constants.BACKUP_SERVICE_SUPERCLASSES_QUERY, SystemProperties.getParameterValue(Constants.BACKUP_SERVICE_SUPERCLASSES_QUERY));
        for (Map.Entry<String, String> entry : backupScripts.entrySet()) {
            String scriptName = entry.getKey();
            String sqlScript = entry.getValue();
            if (StringUtils.hasText(sqlScript)) {
                try {
                    int row = jdbcTemplate.update(sqlScript, new Object[] { artifactId });
                    logger.info(String.format("Backup script (%s) executed with artifactID : %s, %d rows effected.", scriptName, artifactId.toString(), row));
                } catch (DataAccessException e) {
                    logger.error(String.format("Cannot execute backup script with artifactid : %s (%s) : %s", artifactId, scriptName, sqlScript), e);
                }
            }
        }
    }

    /**
     * Delete all with artifact id.
     * 
     * @param artifactId
     *            the artifact id
     * @throws DataAccessException
     *             the data access exception
     * @author U079754 A. Emre Zorlu
     * @since Jun 8, 2016
     */
    public void deleteAllWithArtifactId(Long artifactId) throws DataAccessException {
        Map<String, String> deleteScripts = new LinkedHashMap<String, String>();
        deleteScripts.put(Constants.DELETE_ARTIFACTS_QUERY, SystemProperties.getParameterValue(Constants.DELETE_ARTIFACTS_QUERY));
        deleteScripts.put(Constants.DELETE_DAO_DEPENDENCY_QUERY, SystemProperties.getParameterValue(Constants.DELETE_DAO_DEPENDENCY_QUERY));
        deleteScripts.put(Constants.DELETE_FE_DEPENDENCY_QUERY, SystemProperties.getParameterValue(Constants.DELETE_FE_DEPENDENCY_QUERY));
        deleteScripts.put(Constants.DELETE_NQ_DEPENDENCIES_QUERY, SystemProperties.getParameterValue(Constants.DELETE_NQ_DEPENDENCIES_QUERY));
        deleteScripts.put(Constants.DELETE_PLSQL_DEPENDENCIES_QUERY, SystemProperties.getParameterValue(Constants.DELETE_PLSQL_DEPENDENCIES_QUERY));
        deleteScripts.put(Constants.DELETE_SERVICE_CLASSES_QUERY, SystemProperties.getParameterValue(Constants.DELETE_SERVICE_CLASSES_QUERY));
        deleteScripts.put(Constants.DELETE_SERVICE_DEPENDENCY_QUERY, SystemProperties.getParameterValue(Constants.DELETE_SERVICE_DEPENDENCY_QUERY));
        deleteScripts.put(Constants.DELETE_SERVICE_INTERFACES_QUERY, SystemProperties.getParameterValue(Constants.DELETE_SERVICE_INTERFACES_QUERY));
        deleteScripts.put(Constants.DELETE_SERVICE_METHODS_QUERY, SystemProperties.getParameterValue(Constants.DELETE_SERVICE_METHODS_QUERY));
        deleteScripts.put(Constants.DELETE_SERVICE_SUPERCLASSES_QUERY, SystemProperties.getParameterValue(Constants.DELETE_SERVICE_SUPERCLASSES_QUERY));
        deleteScripts.put(Constants.DELETE_GLOBAL_VARIABLE_USE_QUERY, SystemProperties.getParameterValue(Constants.DELETE_GLOBAL_VARIABLE_USE_QUERY));
        for (Map.Entry<String, String> entry : deleteScripts.entrySet()) {
            String scriptName = entry.getKey();
            String sqlScript = entry.getValue();
            if (StringUtils.hasText(sqlScript)) {
                try {
                    int row = jdbcTemplate.update(sqlScript, new Object[] { artifactId });
                    logger.info(String.format("DELETE script (%s) executed with artifactID : %s, %d rows effected.", scriptName, artifactId.toString(), row));
                } catch (DataAccessException e) {
                    logger.error(String.format("Cannot execute backup script with artifactid : %s (%s) : %s", artifactId, scriptName, sqlScript), e);
                }
            }
        }
    }

    /**
     * Save data model.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:44:34 AM
     */
    protected Long saveDataModel(HmnDepDatamodel datamodel) throws DataAccessException {
        Long id = null;
        try {
            template.save(datamodel);
            template.flush();
            id = datamodel.getId();
        } catch (DataAccessException e) {
            logger.error("Cannot save datamodel", e);
            throw e;
        }
        return id;
    }

    /**
     * Updates data model.
     * 
     * @param datamodel
     *            the datamodel
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:37:49 AM
     */
    public void updateDataModel(HmnDepDatamodel datamodel) throws DataAccessException {
        if (datamodel == null) {
            logger.error("Cannot update dataModel, null datamodel");
            throw new InvalidParameterException("null datamodel");
        }
        try {
            template.update(datamodel);
            template.flush();
        } catch (DataAccessException e) {
            logger.error("Cannot save datamodel", e);
            throw e;
        }
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
    public List<Map<String, Object>> executeSQL(String sql) throws DataAccessException {
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        return result;
    }

    /**
     * Find execution by id.
     * 
     * @param executionId
     *            the execution id
     * @return the hmn dep execution
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:37:49 AM
     */
    public HmnDepExecution findExecutionById(Long executionId) throws DataAccessException {
        logger.debug("getting HmnDepExecution instance with id: " + String.valueOf(executionId));
        try {
            HmnDepExecution instance = template.get(HmnDepExecution.class, executionId);
            if (instance == null) {
                logger.debug("get successful, no HmnDepExecution instance found");
            } else {
                logger.debug("get successful, HmnDepExecution instance found");
            }
            return instance;
        } catch (RuntimeException re) {
            logger.error("get HmnDepExecution failed", re);
            throw re;
        }
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
    public HmnDepArtifact findArtifactByExecutionId(Long executionId, String artifactId) throws DataAccessException {
        logger.debug("getting HmnDepArtifact instance with executionId: " + String.valueOf(executionId) + " artifactId:" + artifactId);
        try {
            logger.debug("finding HmnDepParameter instance by paramName");
            HmnDepArtifact artifactSearch = new HmnDepArtifact();
            artifactSearch.setArtifactId(artifactId);
            artifactSearch.setExecutionId(executionId);
            List results = template.findByExample(artifactSearch);
            logger.debug("find by example successful, result size: " + results.size());
            HmnDepArtifact instance = results != null && !results.isEmpty() ? ((HmnDepArtifact) results.get(0)) : null;
            if (instance == null) {
                logger.debug("get successful, no instance found");
            } else {
                logger.debug("get successful, instance found");
            }
            return instance;
        } catch (RuntimeException re) {
            logger.error("find by example failed", re);
            throw re;
        }
    }

    /**
     * Find artifact by file name and environment.
     * 
     * @param artifactId
     *            the artifact id
     * @param environment
     *            the environment
     * @return the hmn dep artifact
     * @author U079754 A. Emre Zorlu
     * @since Jun 6, 2016
     * 
     */
    public HmnDepArtifact findArtifactByFileNameAndEnvironment(String artifactId) {
        logger.debug("getting HmnDepArtifact instance with artifactId:" + artifactId);
        HmnDepArtifact artifact = null;
        try {
            artifact = (HmnDepArtifact) jdbcTemplate.queryForObject(SystemProperties.getParameterValue(Constants.QUERY_FIND_ARTIFACT), new ArtifactMapper(), new Object[] { artifactId,
                    SystemProperties.getEnvironment() });
        } catch (EmptyResultDataAccessException e) {
            logger.error("findArtifactByFileNameAndEnvironment - no data found (EmptyResultDataAccessException) Artifact_id: " + artifactId + " Environment: " + SystemProperties.getEnvironment()/*
                                                                                                                                                                                                   * ,
                                                                                                                                                                                                   * e
                                                                                                                                                                                                   */);
            return null;
        }
        return artifact;
    }

    public void TEST() {
        jdbcTemplate.update(SystemProperties.getParameterValue(Constants.DELETE_ARTIFACTS_QUERY), new Object[] { Long.valueOf("85597"), "test-20160607-1625", "HMN_CLS_Common_Internal" });
        logger.info("TEST : \n " + SystemProperties.getParameterValue(Constants.DELETE_ARTIFACTS_QUERY));
    }

    public void TEST1() {
        String fileName = "HMN_CLS_Common_Internal", environment = "UAT";
        HmnDepArtifact instance = (HmnDepArtifact) jdbcTemplate.queryForObject(SystemProperties.getParameterValue(Constants.PRM_TEST_QUERY), new ArtifactMapper(),
                new Object[] { fileName, environment });
        if (instance == null) {
            logger.info("NO RESULT, No Instance Found Named : " + fileName);
        } else {
            logger.info("SUCCESSFULL artifact_id: " + instance.getArtifactId() + " id : " + instance.getId());
        }
    }

    /**
     * Save.
     * 
     * @param datamodel
     *            the datamodel
     * @return the long
     * @throws DataAccessException
     *             the data access exception
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 23, 2016
     */
    public Long save(HmnDepGlobalVariableUse datamodel) throws DataAccessException {
        return saveDataModel(datamodel);
    }
}
