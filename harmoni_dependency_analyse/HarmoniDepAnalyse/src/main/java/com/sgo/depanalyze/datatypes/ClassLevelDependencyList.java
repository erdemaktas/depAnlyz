package com.sgo.depanalyze.datatypes;

import java.util.List;

/**
 * The Class ClassLevelDependencyList.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 10:18:25 AM
 */
public class ClassLevelDependencyList {
    /** The implementation clazz. */
    private Class<?> implementationClazz;
    /** The implemented interfaces. */
    private List<Class<?>> implementedInterfaces;
    /** The super classes. */
    private List<Class<?>> superClasses;
    /** The dependency map by method. */
//    private Map<Method, DependencyList> dependencyMapByMethod;
    /** The dependency list by method. */
    private List<MethodDependencies> dependencyListByMethod;

    /**
     * Instantiates a new class level dependency list.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    public ClassLevelDependencyList() {
        super();
    }

    /**
     * Instantiates a new class level dependency list.
     * 
     * @param implementationClazz
     *            the implementation clazz
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since 30.Haz.2014 04:35:32
     */
    public ClassLevelDependencyList(Class<?> implementationClazz) {
        super();
        this.implementationClazz = implementationClazz;
    }

    // /**
    // * Instantiates a new class level dependency list.
    // *
    // * @param implementationClazz
    // * the implementation clazz
    // * @param implementedInterfaces
    // * the implemented interfaces
    // * @param dependencyMapByMethod
    // * the dependency map by method
    // * @author U065352-Selçuk Giray ÖZDAMAR
    // * @since Nov 11, 2013 10:18:25 AM
    // */
    // public ClassLevelDependencyList(Class<?> implementationClazz, List<Class<?>> implementedInterfaces, Map<Method,
    // DependencyList> dependencyMapByMethod) {
    // super();
    // this.implementationClazz = implementationClazz;
    // this.implementedInterfaces = implementedInterfaces;
    // this.dependencyMapByMethod = dependencyMapByMethod;
    // }

    public ClassLevelDependencyList(Class<?> implementationClazz, List<Class<?>> implementedInterfaces, List<MethodDependencies> dependencyListByMethod) {
        super();
        this.implementationClazz = implementationClazz;
        this.implementedInterfaces = implementedInterfaces;
        this.dependencyListByMethod = dependencyListByMethod;
    }

    /**
     * Gets the implementation clazz.
     * <p>
     * </p>
     * 
     * @return the implementation clazz
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    public Class<?> getImplementationClazz() {
        return implementationClazz;
    }

    /**
     * Sets the implementation clazz.
     * <p>
     * </p>
     * 
     * @param implementationClazz
     *            the new implementation clazz
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
    public void setImplementationClazz(Class<?> implementationClazz) {
        this.implementationClazz = implementationClazz;
    }

    /**
     * Gets the dependency map by method.
     * <p>
     * </p>
     * 
     * @return the dependency map by method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
//    public Map<Method, DependencyList> getDependencyMapByMethod() {
//        return dependencyMapByMethod;
//    }

    /**
     * Sets the dependency map by method.
     * 
     * @param dependencyMapByMethod
     *            the dependency map by method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:18:25 AM
     */
//    public void setDependencyMapByMethod(Map<Method, DependencyList> dependencyMapByMethod) {
//        this.dependencyMapByMethod = dependencyMapByMethod;
//    }

    /**
     * Sets the implemented interfaces.
     * <p>
     * </p>
     * 
     * @param implementedInterfaces
     *            the new implemented interfaces
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 1:52:39 PM
     */
    public void setImplementedInterfaces(List<Class<?>> implementedInterfaces) {
        this.implementedInterfaces = implementedInterfaces;
    }

    /**
     * Gets the implemented interfaces.
     * <p>
     * </p>
     * 
     * @return the implemented interfaces
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 1:52:39 PM
     */
    public List<Class<?>> getImplementedInterfaces() {
        return implementedInterfaces;
    }

    /**
     * Sets the super classes.
     * <p>
     * </p>
     * 
     * @param superClasses
     *            the new super classes
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 1:52:39 PM
     */
    public void setSuperClasses(List<Class<?>> superClasses) {
        this.superClasses = superClasses;
    }

    /**
     * Gets the super classes.
     * <p>
     * </p>
     * 
     * @return the super classes
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 1:52:39 PM
     */
    public List<Class<?>> getSuperClasses() {
        return superClasses;
    }

    /**
     * Gets the dependency list by method.
     * <p>
     * </p>
     * 
     * @return the dependency list by method
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since 30.Haz.2014 04:32:25
     */
    public List<MethodDependencies> getDependencyListByMethod() {
        return dependencyListByMethod;
    }

    /**
     * Sets the dependency list by method.
     * <p>
     * </p>
     * 
     * @param dependencyListByMethod
     *            the new dependency list by method
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since 30.Haz.2014 04:32:25
     */
    public void setDependencyListByMethod(List<MethodDependencies> dependencyListByMethod) {
        this.dependencyListByMethod = dependencyListByMethod;
    }
}
