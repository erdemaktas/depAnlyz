package com.sgo.depanalyze.util.impl;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sgo.depanalyze.datatypes.ClassLevelDependencyList;
import com.sgo.depanalyze.datatypes.DependencyList;
import com.sgo.depanalyze.datatypes.GlobalVariableUsage;
import com.sgo.depanalyze.datatypes.HbmDaoDependency;
import com.sgo.depanalyze.datatypes.MethodDependencies;
import com.sgo.depanalyze.datatypes.NamedQueryDependency;
import com.sgo.depanalyze.datatypes.PlsqlDependency;
import com.sgo.depanalyze.datatypes.ServiceDependency;
import com.sgo.depanalyze.enums.ArtifactType;
import com.sgo.depanalyze.hibernate.dao.DataAccessUtil;
import com.sgo.depanalyze.util.DaoTableMappingList;
import com.sgo.depanalyze.util.DaoTableMappingProperties;
import com.sgo.depanalyze.util.MavenPomProperties;
import com.sgo.depanalyze.util.intf.IDependencyPersister;

/**
 * The Class DefaultDependencyPersister.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:53 AM
 */
public class DefaultDependencyPersister implements IDependencyPersister {
    /** The logger. */
    static Logger logger = Logger.getLogger(DefaultDependencyPersister.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.IDependencyPersister#saveArtifact(java.lang.String,
     * com.sgo.depanalyze.enums.ArtifactType, java.lang.Long)
     */
    @Override
    public Long saveArtifact(String fileName, ArtifactType artifactType, Long executionId) {
        logger.info("saving artifact with fileName:" + fileName);
        Long artifactId = DataAccessUtil.saveArtifact(fileName, artifactType, executionId);
        return artifactId;
    }

    /**
     * Save service class.
     * 
     * @param clazz
     *            the clazz
     * @param artifactId
     *            the artifact id
     * @param executionId
     *            the execution id
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 3, 2013 9:11:37 AM
     */
    private Long saveServiceClass(Long executionId, Class<?> clazz, Long artifactId) throws RuntimeException {
        Long serviceClassId = null;
        try {
            logger.debug(String.format("saving BACK-END service implementation: %s", clazz.getName()));
            serviceClassId = DataAccessUtil.saveServiceImpl(clazz.getName(), artifactId, executionId);
        } catch (DataAccessException e) {
            logger.error("DataAccessException, saveServiceClass failed for class: " + clazz.getName(), e);
            throw new RuntimeException("saveServiceClass failed for class: " + clazz.getName(), e);
        } catch (Exception e) {
            logger.error("Exception, saveServiceClass failed for class: " + clazz.getName(), e);
            throw new RuntimeException("saveServiceClass failed for class: " + clazz.getName(), e);
        }
        return serviceClassId;
    }

    /**
     * Save implemented interfaces.
     * 
     * @param clazz
     *            the clazz
     * @param implementedInterfaces
     *            the implemented interfaces
     * @param executionId
     *            the service class id
     * @param executionId
     *            the execution id
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @param artifactId
     * @since Dec 3, 2013 9:11:37 AM
     */
    private void saveImplementedInterfaces(Long executionId, Class<?> serviceClazz, List<Class<?>> implementedInterfaces, Long artifactId) {
        if (CollectionUtils.isEmpty(implementedInterfaces)) {
            return;
        }
        for (Class<?> intfClazz : implementedInterfaces) {
            try {
                logger.debug(String.format("saving BACK-END service interface: %s", intfClazz.getName()));
                DataAccessUtil.saveServiceInterface(executionId, serviceClazz, intfClazz, artifactId);
            } catch (DataAccessException e) {
                logger.error(String.format("DataAccessException, saveServiceInterface failed for class: %s, inteface: %s", serviceClazz.getName(), intfClazz.getName()), e);
                throw new RuntimeException("saveImplementedInterfaces failed for class: " + serviceClazz.getName(), e);
            } catch (Exception e) {
                logger.error(String.format("Exception, saveServiceInterface failed for class: %s, inteface: %s", serviceClazz.getName(), intfClazz.getName()), e);
                throw new RuntimeException("saveImplementedInterfaces failed for class: " + serviceClazz.getName(), e);
            }
        }
    }

    /**
     * Save super classes.
     * 
     * @param clazz
     *            the clazz
     * @param superClasses
     *            the super classes
     * @param executionId
     *            the execution id
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 3, 2013 9:11:37 AM
     */
    private void saveSuperClasses(Long executionId, Class<?> clazz, List<Class<?>> superClasses, Long artifactId) {
        for (Class<?> superClazz : superClasses) {
            try {
                logger.debug(String.format("saving BACK_END service superClass: %s", superClazz.getName()));
                @SuppressWarnings("unused")
                Long hmnDepServiceIntfId = DataAccessUtil.saveServiceSuperClass(clazz.getName(), superClazz.getName(), executionId, artifactId);
            } catch (DataAccessException e) {
                logger.error(String.format("DataAccessException, saveSuperClasses failed for class: %s, inteface: %s", clazz.getName(), superClazz.getName()), e);
                throw new RuntimeException("saveServiceClass failed for class: " + clazz.getName(), e);
            } catch (Exception e) {
                logger.error(String.format("Exception, saveSuperClasses failed for class: %s, inteface: %s", clazz.getName(), superClazz.getName()), e);
                throw new RuntimeException("saveServiceClass failed for class: " + clazz.getName(), e);
            }
        }
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
     * @author Selçuk Giray ÖZDAMAR
     * @param artifactId
     * @since May 25, 2016
     */
    private void saveServiceMethod(Method implMethod, Method intfMethod, String digest, Class<?> serviceClazz, Long executionId, Long artifactId) {
        try {
            DataAccessUtil.saveServiceMethod(implMethod, intfMethod, digest, serviceClazz, executionId, artifactId);
        } catch (DataAccessException e) {
            String errLog = String.format("DataAccessException, saveServiceMethod failed (class:%s, method:%s)", serviceClazz.getName(), implMethod.getName());
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        } catch (Exception e) {
            String errLog = String.format("Exception, saveServiceMethod failed (class:%s, method:%s)", serviceClazz.getName(), implMethod.getName());
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        }
    }
    
    private void saveGlobalVariableUse(Long executionId, Long artifactId, Method implMethod, GlobalVariableUsage globalVariableUsage) {
        try {
            DataAccessUtil.saveGlobalVariableUse(executionId, artifactId, implMethod.getDeclaringClass().getName(), implMethod.getName(), 
                    globalVariableUsage.getUsageType().name(),globalVariableUsage.getFieldName(), globalVariableUsage.getFieldType(), globalVariableUsage.getReferenceType(), globalVariableUsage.getDependencyLevel(), globalVariableUsage.getDependencyPath());
        } catch (DataAccessException e) {
            String errLog = String.format("DataAccessException, saveGlobalVariableUse failed (class:%s, method:%s)", implMethod.getDeclaringClass().getName(), implMethod.getName());
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        } catch (Exception e) {
            String errLog = String.format("Exception, saveGlobalVariableUse failed (class:%s, method:%s)", implMethod.getDeclaringClass().getName(), implMethod.getName());
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        }
    }
    

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.IDependencyPersister#saveDependencyList(java.util.List, java.lang.Long,
     * com.sgo.depanalyze.enums.ArtifactType, java.lang.Long)
     */
    @SuppressWarnings("unused")
    @Override
    public void saveDependencyList(List<ClassLevelDependencyList> dependencyList, Long artifactId, ArtifactType artifactType, Long executionId) {
        if (dependencyList == null || dependencyList.isEmpty()) {
            logger.warn("saveDependenyList method is ignoring null/empty list");
            return;
        }
        for (ClassLevelDependencyList clazzLevelDependencies : dependencyList) {
            Class<?> clazz = clazzLevelDependencies.getImplementationClazz();
            List<Class<?>> implementedInterfaces = clazzLevelDependencies.getImplementedInterfaces();
            //
            List<Class<?>> superClasses = clazzLevelDependencies.getSuperClasses();
            Long serviceClassId = null;
            if (ArtifactType.HMN_BACK_END == artifactType) {
                // save service class
                serviceClassId = saveServiceClass(executionId, clazz, artifactId);
                // save interfaces of service
                saveImplementedInterfaces(executionId, clazz, implementedInterfaces, artifactId);
                // save superclasses of service
                saveSuperClasses(executionId, clazz, superClasses, artifactId);
            }
            //
            // Map<Method, DependencyList> dependenciesByMethod = clazzLevelDependencies.getDependencyMapByMethod();
            List<MethodDependencies> dependencyListByMethod = clazzLevelDependencies.getDependencyListByMethod();
            logger.info(String.format("saving dependencies for [%s] class:%s", artifactType.toString(), clazz.getName()));
            for (MethodDependencies methodDependencies : dependencyListByMethod) {
                Method implementationMethod = methodDependencies.getImplementationMethod();
                DependencyList methodDependencyList = methodDependencies.getDependencyList();
                
                List<GlobalVariableUsage> globalList = methodDependencyList.getGlobalUsageList();
                if (globalList != null && !globalList.isEmpty()) {
                    for (GlobalVariableUsage globalVariableUsage : globalList) {
                        saveGlobalVariableUse(executionId, artifactId, implementationMethod, globalVariableUsage);
                    }
                }
                // for (Map.Entry<Method, DependencyList> depEntry : dependenciesByMethod.entrySet()) {
                // Method method = depEntry.getKey();
                // DependencyList methodDependencies = depEntry.getValue();
                if (ArtifactType.HMN_BACK_END == artifactType) {
                    saveServiceMethod(implementationMethod, methodDependencies.getInterfaceMethod(), methodDependencies.getDigest(), clazz, executionId, artifactId);
                    //
                    logger.debug(String.format("saving BACK_END Service-Service dependency for class:%s, method:%s", clazz.getName(), implementationMethod.getName()));
                    for (ServiceDependency serviceDep : methodDependencyList.getServiceDependencyList()) {
                        try {
                            Long id = DataAccessUtil.saveServiceDependency(executionId, artifactId, clazz.getName(), implementationMethod.getName(), implementationMethod.toString(),
                                    serviceDep.getTargetService(), serviceDep.getTargetMethod(), serviceDep.getTargetSignature(), serviceDep.getDependencyLevel(), serviceDep.getDependencyPath());
                        } catch (DataAccessException e) {
                            String errLog = String.format("DataAccessException, saveServiceDependency failed (class:%s, method:%s)", clazz.getName(), implementationMethod.getName());
                            logger.error(errLog, e);
                            throw new RuntimeException(errLog, e);
                        } catch (Exception e) {
                            String errLog = String.format("Exception, saveServiceDependency failed (class:%s, method:%s)", clazz.getName(), implementationMethod.getName());
                            logger.error(errLog, e);
                            throw new RuntimeException(errLog, e);
                        }
                    }
                    logger.debug(String.format("saving BACK_END Service-Plsql dependency for class:%s, method:%s", clazz.getName(), implementationMethod.getName()));
                    for (PlsqlDependency plsqlDep : methodDependencyList.getPlsqlDependencyList()) {
                        try {
                            Long id = DataAccessUtil.savePlsqlDependency(clazz.getName(), /***/
                            implementationMethod.getName(), /***/
                            plsqlDep.getPackageName(), /***/
                            plsqlDep.getProcedureName(), /***/
                            plsqlDep.getDependencyLevel(), /***/
                            plsqlDep.getDependencyPath(), /***/
                            executionId, /***/
                            artifactId);
                        } catch (DataAccessException e) {
                            String errLog = String.format("DataAccessException, savePlsqlDependency failed (class:%s, method:%s)", clazz.getName(), implementationMethod.getName());
                            logger.error(errLog, e);
                            throw new RuntimeException(errLog, e);
                        } catch (Exception e) {
                            String errLog = String.format("Exception, savePlsqlDependency failed (class:%s, method:%s)", clazz.getName(), implementationMethod.getName());
                            logger.error(errLog, e);
                            throw new RuntimeException(errLog, e);
                        }
                    }
                    logger.debug(String.format("saving BACK_END Service-NamedQuery dependency for class:%s, method:%s", clazz.getName(), implementationMethod.getName()));
                    for (NamedQueryDependency nqDep : methodDependencyList.getNqDependencyList()) {
                        try {
                            Long id = DataAccessUtil.saveNamedQueryDependency(clazz.getName(), /***/
                            implementationMethod.getName(), /***/
                            nqDep.getQueryName(), /***/
                            nqDep.getDependencyLevel(), /***/
                            nqDep.getDependencyPath(), /***/
                            executionId, /***/
                            artifactId);
                        } catch (DataAccessException e) {
                            String errLog = String.format("DataAccessException, saveNamedQueryDependency failed (class:%s, method:%s)", clazz.getName(), implementationMethod.getName());
                            logger.error(errLog, e);
                            throw new RuntimeException(errLog, e);
                        } catch (Exception e) {
                            String errLog = String.format("Exception, saveNamedQueryDependency failed (class:%s, method:%s)", clazz.getName(), implementationMethod.getName());
                            logger.error(errLog, e);
                            throw new RuntimeException(errLog, e);
                        }
                    }
                    logger.debug(String.format("saving BACK_END Service-DAO dependency for class:%s, method:%s", clazz.getName(), implementationMethod.getName()));
                    for (HbmDaoDependency daoDep : methodDependencyList.getHbmDaoDependencyList()) {
                        try {
                            Long id = DataAccessUtil.saveDaoDependency(executionId, clazz.getName(), implementationMethod.getName(), daoDep.getDaoClassName(), daoDep.getDaoMethodName(),
                                    daoDep.getCrudFlag(), daoDep.getDependencyLevel(), daoDep.getDependencyPath(), artifactId);
                        } catch (DataAccessException e) {
                            String errLog = String.format("DataAccessException, saveDaoDependency failed (class:%s, method:%s)", clazz.getName(), implementationMethod.getName());
                            logger.error(errLog, e);
                            throw new RuntimeException(errLog, e);
                        } catch (Exception e) {
                            String errLog = String.format("Exception, saveDaoDependency failed (class:%s, method:%s)", clazz.getName(), implementationMethod.getName());
                            logger.error(errLog, e);
                            throw new RuntimeException(errLog, e);
                        }
                    }
                } else {
                    logger.debug(String.format("saving FRONT_END Service-Service dependency for class:%s, method:%s", clazz.getName(), implementationMethod.getName()));
                    for (ServiceDependency serviceDep : methodDependencyList.getServiceDependencyList()) {
                        try {
                            Long id = DataAccessUtil.saveFeDependency(executionId, artifactId, clazz.getName(), implementationMethod.getName(), implementationMethod.toString(),
                                    serviceDep.getTargetService(), serviceDep.getTargetMethod(), serviceDep.getTargetSignature(), serviceDep.getDependencyLevel(), serviceDep.getDependencyPath());
                        } catch (DataAccessException e) {
                            String errLog = String.format("DataAccessException, saveFeDependency failed (class:%s, method:%s)", clazz.getName(), implementationMethod.getName());
                            logger.error(errLog, e);
                            throw new RuntimeException(errLog, e);
                        } catch (Exception e) {
                            String errLog = String.format("Exception, saveFeDependency failed (class:%s, method:%s)", clazz.getName(), implementationMethod.getName());
                            logger.error(errLog, e);
                            throw new RuntimeException(errLog, e);
                        }
                    }
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IDependencyPersister#saveDaoHbmTableMappings(java.lang.Long, java.lang.Long,
     * com.sgo.depanalyze.util.DaoTableMappingList)
     */
    @Override
    public void saveDaoHbmTableMappings(Long executionId, Long artifactId, DaoTableMappingList daoHbmTableMappings) {
        if (daoHbmTableMappings == null) {
            return;
        }
        List<DaoTableMappingProperties> mappingList = daoHbmTableMappings.getMappingList();
        if (mappingList == null || mappingList.isEmpty()) {
            return;
        }
        for (DaoTableMappingProperties mappingProperties : daoHbmTableMappings.getMappingList()) {
            DataAccessUtil.saveDaoHbmTableMapping(executionId, artifactId, mappingProperties.getDaoClassName(), mappingProperties.getDatamodelClassName(), mappingProperties.getTableName(),
                    mappingProperties.getDataSource(), mappingProperties.getHbmFileName());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IDependencyPersister#saveArtifact(java.lang.String,
     * com.sgo.depanalyze.enums.ArtifactType, java.lang.Long, com.sgo.depanalyze.util.MavenPomProperties)
     */
    @Override
    public Long saveArtifact(String fileName, ArtifactType artifactType, Long executionId, MavenPomProperties pomProperties) {
        logger.info("saving artifact with fileName:" + fileName);
        Long artifactId = DataAccessUtil.saveArtifact(fileName, artifactType, executionId, pomProperties);
        return artifactId;
    }
}
