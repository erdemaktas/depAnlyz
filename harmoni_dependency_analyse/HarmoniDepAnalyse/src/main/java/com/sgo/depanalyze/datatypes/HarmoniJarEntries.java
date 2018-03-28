package com.sgo.depanalyze.datatypes;

import java.util.List;

import com.sgo.depanalyze.util.DaoTableMappingList;
import com.sgo.depanalyze.util.MavenPomProperties;

/**
 * The Class HarmoniJarEntries.
 * 
 * @author U065352 - SELÇUK GİRAY ÖZDAMAR
 * @since Feb 6, 2014 3:09:45 PM
 */
public class HarmoniJarEntries {
    /** The service interfaces. */
    private List<Class<?>> serviceInterfaces;
    /** The service clazzes. */
    private List<Class<?>> serviceClazzes;
    /** The dao table mapping list. */
    private DaoTableMappingList daoTableMappingList;
    /** The pom properties. */
    private MavenPomProperties pomProperties;

    /**
     * Instantiates a new harmoni jar entries.
     * 
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:09:45 PM
     */
    public HarmoniJarEntries() {
        super();
    }

    /**
     * Instantiates a new harmoni jar entries.
     * 
     * @param serviceInterfaces
     *            the service interfaces
     * @param serviceClazzes
     *            the service clazzes
     * @param daoTableMappingList
     *            the dao table mapping list
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:09:45 PM
     */
    public HarmoniJarEntries(List<Class<?>> serviceInterfaces, List<Class<?>> serviceClazzes, DaoTableMappingList daoTableMappingList) {
        super();
        this.serviceInterfaces = serviceInterfaces;
        this.serviceClazzes = serviceClazzes;
        this.daoTableMappingList = daoTableMappingList;
    }

    /**
     * Gets the service interfaces.
     * <p>
     * </p>
     * 
     * @return the service interfaces
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:09:45 PM
     */
    public List<Class<?>> getServiceInterfaces() {
        return serviceInterfaces;
    }

    /**
     * Sets the service interfaces.
     * <p>
     * </p>
     * 
     * @param serviceInterfaces
     *            the new service interfaces
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:09:45 PM
     */
    public void setServiceInterfaces(List<Class<?>> serviceInterfaces) {
        this.serviceInterfaces = serviceInterfaces;
    }

    /**
     * Gets the service clazzes.
     * <p>
     * </p>
     * 
     * @return the service clazzes
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:09:45 PM
     */
    public List<Class<?>> getServiceClazzes() {
        return serviceClazzes;
    }

    /**
     * Sets the service clazzes.
     * <p>
     * </p>
     * 
     * @param serviceClazzes
     *            the new service clazzes
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:09:45 PM
     */
    public void setServiceClazzes(List<Class<?>> serviceClazzes) {
        this.serviceClazzes = serviceClazzes;
    }

    /**
     * Sets the dao table mapping list.
     * <p>
     * </p>
     * 
     * @param daoTableMappingList
     *            the new dao table mapping list
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:09:45 PM
     */
    public void setDaoTableMappingList(DaoTableMappingList daoTableMappingList) {
        this.daoTableMappingList = daoTableMappingList;
    }

    /**
     * Gets the dao table mapping list.
     * <p>
     * </p>
     * 
     * @return the dao table mapping list
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:09:45 PM
     */
    public DaoTableMappingList getDaoTableMappingList() {
        return daoTableMappingList;
    }

    /**
     * Sets the pom properties.
     * <p>
     * </p>
     * 
     * @param pomProperties
     *            the new pom properties
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:09:45 PM
     */
    public void setPomProperties(MavenPomProperties pomProperties) {
        this.pomProperties = pomProperties;
    }

    /**
     * Gets the pom properties.
     * <p>
     * </p>
     * 
     * @return the pom properties
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 6, 2014 3:09:45 PM
     */
    public MavenPomProperties getPomProperties() {
        return pomProperties;
    }
}
