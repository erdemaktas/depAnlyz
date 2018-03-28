package com.sgo.depanalyze.util.intf;

import java.util.List;

import com.sgo.depanalyze.datatypes.ClassLevelDependencyList;
import com.sgo.depanalyze.enums.ArtifactType;
import com.sgo.depanalyze.util.DaoTableMappingList;
import com.sgo.depanalyze.util.MavenPomProperties;

/**
 * The Interface IDependencyPersister.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:53 AM
 */
public interface IDependencyPersister {
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
     * @since Nov 11, 2013 9:46:53 AM
     */
    Long saveArtifact(String fileName, ArtifactType artifactType, Long executionId);

    /**
     * Save artifact.
     * 
     * @param fileName
     *            the artifact name
     * @param artifactType
     *            the artifact type
     * @param executionId
     *            the execution id
     * @param pomProperties
     *            the pom properties
     * @return the long
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:30:02 PM
     */
    Long saveArtifact(String fileName, ArtifactType artifactType, Long executionId, MavenPomProperties pomProperties);

    /**
     * Save dao hbm table mappings.
     * 
     * @param executionId
     *            the execution id
     * @param artifactId
     *            the artifact id
     * @param daoHbmTableMappings
     *            the dao hbm table mappings
     * @return the long
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:46:39 PM
     */
    void saveDaoHbmTableMappings(Long executionId, Long artifactId, DaoTableMappingList daoHbmTableMappings);

    /**
     * Save dependency list.
     * 
     * @param dependencyList
     *            the dependency list
     * @param artifactId
     *            the artifact id
     * @param artifactType
     *            the artifact type
     * @param executionId
     *            the execution id
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    void saveDependencyList(List<ClassLevelDependencyList> dependencyList, Long artifactId, ArtifactType artifactType, Long executionId);
}
