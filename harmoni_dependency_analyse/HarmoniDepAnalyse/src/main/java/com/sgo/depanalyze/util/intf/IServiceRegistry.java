package com.sgo.depanalyze.util.intf;

// TODO: Auto-generated Javadoc
/**
 * The Interface IServiceRegistry.
 * 
 * @author U065352 - SELÇUK GİRAY ÖZDAMAR
 * @since Feb 7, 2014 9:23:56 AM
 */
public interface IServiceRegistry {
    /**
     * Register service.
     * 
     * @param className
     *            the class name
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 7, 2014 9:23:56 AM
     */
    void registerService(String className);

    /**
     * Checks if is service class.
     * 
     * @param className
     *            the class name
     * @return true, if is service class
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 7, 2014 9:23:56 AM
     */
    boolean isServiceClass(String className);

    /**
     * Checks if is service class.
     * 
     * @param clazz
     *            the clazz
     * @return true, if is service class
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 7, 2014 9:23:56 AM
     */
    boolean isServiceClass(Class<?> clazz);
    
    /**
     * Checks if is service interface.
     *
     * @param clazzName the clazz name
     * @return true, if is service interface
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 7, 2014 11:00:52 AM
     */
    boolean isServiceInterface(String clazzName);

   
    /**
     * Checks if is service interface.
     *
     * @param clazz the clazz
     * @return true, if is service interface
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 7, 2014 11:00:52 AM
     */
    boolean isServiceInterface(Class<?> clazz);
}
