package com.sgo.depanalyze.util;

/**
 * The Class DaoTableMappingProperties.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Dec 4, 2013 4:16:24 PM
 */
public class DaoTableMappingProperties {
    /** The dao class name. */
    private String daoClassName;
    /** The datamodel class name. */
    private String datamodelClassName;
    /** The table name. */
    private String tableName;
    /** The hbm file name. */
    private String hbmFileName;
    /** The data source. */
    private String dataSource;

    /**
     * Gets the dao class name.
     * <p>
     * </p>
     * 
     * @return the dao class name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 4:16:24 PM
     */
    public String getDaoClassName() {
        return daoClassName;
    }

    /**
     * Sets the dao class name.
     * <p>
     * </p>
     * 
     * @param daoClassName
     *            the new dao class name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 4:16:24 PM
     */
    public void setDaoClassName(String daoClassName) {
        this.daoClassName = daoClassName;
    }

    /**
     * Gets the datamodel class name.
     * <p>
     * </p>
     * 
     * @return the datamodel class name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 4:16:24 PM
     */
    public String getDatamodelClassName() {
        return datamodelClassName;
    }

    /**
     * Sets the datamodel class name.
     * <p>
     * </p>
     * 
     * @param datamodelClassName
     *            the new datamodel class name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 4:16:24 PM
     */
    public void setDatamodelClassName(String datamodelClassName) {
        this.datamodelClassName = datamodelClassName;
    }

    /**
     * Gets the table name.
     * <p>
     * </p>
     * 
     * @return the table name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 4:16:24 PM
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the table name.
     * <p>
     * </p>
     * 
     * @param tableName
     *            the new table name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 4:16:24 PM
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Gets the hbm file name.
     * <p>
     * </p>
     * 
     * @return the hbm file name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 4:16:24 PM
     */
    public String getHbmFileName() {
        return hbmFileName;
    }

    /**
     * Sets the hbm file name.
     * <p>
     * </p>
     * 
     * @param hbmFileName
     *            the new hbm file name
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 4:16:24 PM
     */
    public void setHbmFileName(String hbmFileName) {
        this.hbmFileName = hbmFileName;
    }

    /**
     * Sets the data source.
     * <p>
     * </p>
     * 
     * @param dataSource
     *            the new data source
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 4:16:24 PM
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Gets the data source.
     * <p>
     * </p>
     * 
     * @return the data source
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 4:16:24 PM
     */
    public String getDataSource() {
        return dataSource;
    }
}
