package com.sgo.depanalyze.util.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.apache.log4j.Logger;

import com.sgo.depanalyze.datatypes.HarmoniJarEntries;
import com.sgo.depanalyze.enums.JarEntryHmnType;
import com.sgo.depanalyze.util.ClassUtils;
import com.sgo.depanalyze.util.Constants;
import com.sgo.depanalyze.util.DaoTableMappingList;
import com.sgo.depanalyze.util.DaoTableMappingProperties;
import com.sgo.depanalyze.util.FileSystemClassLoader;
import com.sgo.depanalyze.util.HbmMappingProperties;
import com.sgo.depanalyze.util.MavenPomProperties;
import com.sgo.depanalyze.util.intf.IHbmMappingParser;
import com.sgo.depanalyze.util.intf.IJarFileScanner;
import com.sgo.depanalyze.util.intf.IMavenPomParser;

/**
 * The Class BeJarFileScanner.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:53 AM
 */
public class HmnBackendJarScanner implements IJarFileScanner {
    /** The logger. */
    static Logger logger = Logger.getLogger(HmnBackendJarScanner.class);
    /** The clazz loader. */
    private ClassLoader clazzLoader;

    /**
     * Instantiates a new be jar file scanner.
     * 
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public HmnBackendJarScanner() {
    }

    /**
     * Instantiates a new be jar file scanner.
     * 
     * @param clazzLoader
     *            the clazz loader
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public HmnBackendJarScanner(ClassLoader clazzLoader) {
        this.clazzLoader = clazzLoader;
    }

    /**
     * Sets the class loader.
     * <p>
     * </p>
     * 
     * @param clazzLoader
     *            the new class loader
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public void setClassLoader(ClassLoader clazzLoader) {
        this.clazzLoader = clazzLoader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.IJarFileScanner#scanJar(java.lang.String)
     */
    public HarmoniJarEntries scanJar(String jarFile) throws RuntimeException {
        JarFile jar = null;
        try {
            jar = new JarFile(jarFile);
        } catch (IOException e) {
            throw new RuntimeException("IOException occured while creating JarFile object, file: " + jarFile, e);
        }
        return scanJar(jar);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IJarFileScanner#scanJar(java.net.URL)
     */
    public HarmoniJarEntries scanJar(URL url) throws RuntimeException {
        JarFile jar = null;
        try {
            jar = new JarFile(new File(url.toURI()));
        } catch (IOException e) {
            throw new RuntimeException("IOException occured while creating JarFile object, file: " + url.toString(), e);
        } catch (URISyntaxException e) {
            throw new RuntimeException("URISyntaxException occured while creating JarFile object, file: " + url.toString(), e);
        }
        return scanJar(jar);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.IJarFileScanner#scanJar(java.util.jar.JarFile)
     */
    @Override
    public HarmoniJarEntries scanJar(JarFile jarFile) {
        MavenPomProperties pomProperties = null;
        Map<JarEntryHmnType, List<Class<?>>> classes = new HashMap<JarEntryHmnType, List<Class<?>>>();
        List<Class<?>> interfaces = new ArrayList<Class<?>>();
        List<Class<?>> clazzes = new ArrayList<Class<?>>();
        /** key:dao class name, value:datamodel class name */
        Map<String, String> daoMappings = new LinkedHashMap<String, String>();
        /** key:datamodel class name */
        Map<String, HbmMappingProperties> hbmMappings = new LinkedHashMap<String, HbmMappingProperties>();
        /***/
        classes.put(JarEntryHmnType.SERVICE_INTF, interfaces);
        classes.put(JarEntryHmnType.SERVICE_CLASS, clazzes);
        // Getting the files into the jar
        Enumeration<? extends JarEntry> enumeration = jarFile.entries();
        // Iterates into the files in the jar file
        while (enumeration.hasMoreElements()) {
            ZipEntry zipEntry = enumeration.nextElement();
            // Is this a class?
            if (zipEntry.getName().endsWith(".class")) {
                // Relative path of file into the jar.
                String className = zipEntry.getName();
                // Complete class name
                className = className.replace(".class", "").replace("/", ".");
                try {
                    // Load class definition from JVM
                    // Class<?> clazz = this.clazzLoader.loadClass(className);
                    Class<?> clazz = FileSystemClassLoader.loadClass(className);
                    // Verify the type of the "class"
                    if (clazz.isInterface()) {
                        if (ClassUtils.isServiceIntf(clazz)) {
                            interfaces.add(clazz);
                        } else if (ClassUtils.isDaoIntf(clazz)) {
                            String dao = clazz.getName();
                            String datamodel = ClassUtils.getAnnotationValue(clazz, ClassUtils.getModelAnnotationClazz(), "name");
                            daoMappings.put(dao, datamodel);
                        }
                    } else if (clazz.isAnnotation()) {
                        // annotations.add(clazz);
                    } else if (clazz.isEnum()) {
                        // enums.add(clazz);
                    } else {
                        if (ClassUtils.isServiceImpl(clazz) || ServiceRegistry.getInstance().isServiceClass(clazz)) {
                            clazzes.add(clazz);
                        }
                    }
                    // } catch (java.lang.NoClassDefFoundError e) {
                    // logger.error("NoClassDefFoundError error occured: " + e.getMessage(), e);
                } catch (Exception e) {
                    logger.error("Exception occured: " + e.getMessage(), e);
                }
            } else if (zipEntry.getName().startsWith(Constants.HBM_ROOT_PATH) && zipEntry.getName().contains(Constants.HBM_XML_SUFFIX)) {
                try {
                    InputStream input = jarFile.getInputStream(zipEntry);
                    IHbmMappingParser hbmParser = new DefaultHbmMappingParser();
                    HbmMappingProperties hbmProps = hbmParser.parseHbm(input, zipEntry.getName());
                    if (hbmProps != null) {
                        hbmMappings.put(hbmProps.getClassName(), hbmProps);
                    }
                } catch (IOException e) {
                    logger.error("IOException error occured: " + e.getMessage(), e);
                }
            } else if (zipEntry.getName().contains("pom.xml")) {
                IMavenPomParser pomParser = new MavenPomParser();
                pomProperties = pomParser.parsePomFile(jarFile, zipEntry);
            }
        }
        DaoTableMappingList daoTableMappings = consolidateDaoMapping(daoMappings, hbmMappings);
        HarmoniJarEntries hmnJarEntries = new HarmoniJarEntries(interfaces, clazzes, daoTableMappings);
        hmnJarEntries.setPomProperties(pomProperties);
        return hmnJarEntries;
    }

    /**
     * Consolidate dao mapping.
     * 
     * @param daoMappings
     *            the dao mappings(key:dao class name, value:datamodel class name )
     * @param hbmMappings
     *            the hbm mappings (key:datamodel class name)
     * @return the dao table mapping list
     */
    private DaoTableMappingList consolidateDaoMapping(Map<String, String> daoMappings, Map<String, HbmMappingProperties> hbmMappings) {
        DaoTableMappingList result = new DaoTableMappingList();
        if (daoMappings == null || daoMappings.isEmpty() || hbmMappings == null || hbmMappings.isEmpty()) {
            return result;
        }
        for (Map.Entry<String, String> daoEntry : daoMappings.entrySet()) {
            String daoClassName = daoEntry.getKey();
            String datamodelClassName = daoEntry.getValue();
            if (hbmMappings.get(datamodelClassName) != null) {
                DaoTableMappingProperties properties = new DaoTableMappingProperties();
                properties.setDaoClassName(daoClassName);
                properties.setDatamodelClassName(datamodelClassName);
                String tableName = hbmMappings.get(datamodelClassName).getTableName();
                tableName = parseTableName(tableName);
                properties.setTableName(tableName);
                properties.setHbmFileName(hbmMappings.get(datamodelClassName).getEntryName());
                properties.setDataSource(hbmMappings.get(datamodelClassName).getDataSource());
                result.addMapping(properties);
            }
        }
        return result;
    }

    private String parseTableName(String tableName) {
        if (tableName == null || tableName.trim().isEmpty() || !tableName.contains(".")) {
            return tableName;
        }
        String[] splitArr = tableName.split("\\.");
        if (splitArr.length != 2) {
            return tableName;
        }
        return splitArr[1];
    }
}
