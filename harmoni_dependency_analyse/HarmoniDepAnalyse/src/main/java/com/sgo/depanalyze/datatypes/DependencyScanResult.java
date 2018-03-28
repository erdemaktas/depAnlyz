package com.sgo.depanalyze.datatypes;

import java.util.List;

import com.sgo.depanalyze.util.DaoTableMappingList;
import com.sgo.depanalyze.util.MavenPomProperties;

/**
 * The Class DependencyScanResult.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Dec 4, 2013 3:57:25 PM
 */
public class DependencyScanResult {
    private MavenPomProperties pomProperties;
    /** The dao table mapping list. */
    private DaoTableMappingList daoTableMappingList;
    /** The class level dependencies. */
    private List<ClassLevelDependencyList> classLevelDependencies;

    /**
     * Instantiates a new dependency scan result.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:57:25 PM
     */
    public DependencyScanResult() {
        super();
    }

    /**
     * Instantiates a new dependency scan result.
     * 
     * @param daoTableMappingList
     *            the dao table mapping list
     * @param classLevelDependencies
     *            the class level dependencies
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:57:25 PM
     */
    public DependencyScanResult(DaoTableMappingList daoTableMappingList, List<ClassLevelDependencyList> classLevelDependencies) {
        super();
        this.daoTableMappingList = daoTableMappingList;
        this.classLevelDependencies = classLevelDependencies;
    }

    /**
     * Gets the dao table mapping list.
     * <p>
     * </p>
     * 
     * @return the dao table mapping list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:57:25 PM
     */
    public DaoTableMappingList getDaoTableMappingList() {
        return daoTableMappingList;
    }

    /**
     * Sets the dao table mapping list.
     * <p>
     * </p>
     * 
     * @param daoTableMappingList
     *            the new dao table mapping list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:57:25 PM
     */
    public void setDaoTableMappingList(DaoTableMappingList daoTableMappingList) {
        this.daoTableMappingList = daoTableMappingList;
    }

    /**
     * Gets the class level dependencies.
     * <p>
     * </p>
     * 
     * @return the class level dependencies
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:57:25 PM
     */
    public List<ClassLevelDependencyList> getClassLevelDependencies() {
        return classLevelDependencies;
    }

    /**
     * Sets the class level dependencies.
     * <p>
     * </p>
     * 
     * @param classLevelDependencies
     *            the new class level dependencies
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 3:57:25 PM
     */
    public void setClassLevelDependencies(List<ClassLevelDependencyList> classLevelDependencies) {
        this.classLevelDependencies = classLevelDependencies;
    }

    public void setPomProperties(MavenPomProperties pomProperties) {
        this.pomProperties = pomProperties;
    }

    public MavenPomProperties getPomProperties() {
        return pomProperties;
    }
}
