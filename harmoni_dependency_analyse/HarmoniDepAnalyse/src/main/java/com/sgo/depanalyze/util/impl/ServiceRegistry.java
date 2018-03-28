package com.sgo.depanalyze.util.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sgo.depanalyze.hibernate.dao.DataAccessUtil;
import com.sgo.depanalyze.util.ClassUtils;
import com.sgo.depanalyze.util.FileSystemClassLoader;
import com.sgo.depanalyze.util.intf.IServiceRegistry;

/**
 * The Class ServiceRegistry.
 * 
 * @author U065352 - SELÇUK GİRAY ÖZDAMAR
 * @since Feb 7, 2014 9:34:51 AM
 */
public class ServiceRegistry implements IServiceRegistry {
    /**
     * The service_hashmap. key:service class impl , value: component package prefix
     * */
    private HashMap<String, String> service_class_map = new HashMap<String, String>();
    /**
     * The service_interface_map. key:interface clazz, value: service class impl
     * */
    private HashMap<String, String> service_interface_map = new HashMap<String, String>();
    /** The logger. */
    private static Logger logger = Logger.getLogger(ServiceRegistry.class);

    /**
     * The Class SingletonHolder.
     * 
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 7, 2014 9:37:01 AM
     */
    static class SingletonHolder {
        /** The Constant INSTANCE. */
        static final IServiceRegistry INSTANCE = new ServiceRegistry();
    }

    /**
     * Instantiates a new service registry.
     * 
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 7, 2014 9:34:51 AM
     */
    private ServiceRegistry() {
        service_class_map.putAll(DataAccessUtil.getSysparServiceList());
        for (Map.Entry<String, String> entry : service_class_map.entrySet()) {
            try {
                List<Class<?>> interfaceList = findInterfaceOfService(entry.getKey(), entry.getValue());
                for (Class<?> intfClazz : interfaceList) {
                    service_interface_map.put(intfClazz.getName(), entry.getKey());
                }
            } catch (Exception e) {
                logger.warn("cannot build SYSPAR service, Exception: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Find interface of service.
     * 
     * @param className
     *            the clazz name
     * @param packagePrefix
     *            the package prefix
     * @return the list
     * @throws Exception
     *             the exception
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 7, 2014 11:01:45 AM
     */
    private List<Class<?>> findInterfaceOfService(String className, String packagePrefix) throws Exception {
        Class<?> clazz = FileSystemClassLoader.loadClass(className);
        List<Class<?>> interfaceList = ClassUtils.findImplementedInterfaces(clazz, packagePrefix);
        return interfaceList;
    }

    /**
     * Gets the single instance of ServiceRegistry.
     * 
     * @return single instance of ServiceRegistry
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 7, 2014 9:37:01 AM
     */
    public static IServiceRegistry getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IServiceRegistry#registerService(java.lang.String)
     */
    @Override
    public void registerService(String className) {
        //
        throw new RuntimeException("not implemented");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IServiceRegistry#isServiceClass(java.lang.String)
     */
    @Override
    public boolean isServiceClass(String className) {
        return service_class_map.containsKey(className);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IServiceRegistry#isServiceClass(java.lang.Class)
     */
    @Override
    public boolean isServiceClass(Class<?> clazz) {
        return isServiceClass(clazz.getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IServiceRegistry#isServiceInterface(java.lang.String)
     */
    @Override
    public boolean isServiceInterface(String className) {
        return service_interface_map.containsKey(className);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IServiceRegistry#isServiceInterface(java.lang.Class)
     */
    @Override
    public boolean isServiceInterface(Class<?> clazz) {
        return isServiceInterface(clazz.getName());
    }
}
