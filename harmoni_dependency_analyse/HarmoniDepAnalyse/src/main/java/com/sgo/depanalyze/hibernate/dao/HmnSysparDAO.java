/**
 * @author U079754 A. Emre Zorlu
 * @since Nov 24, 2016
 * @return HmnSysparDAO
 * 
 */
package com.sgo.depanalyze.hibernate.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sgo.depanalyze.util.Constants;
import com.sgo.depanalyze.util.SystemProperties;

/**
 * @author U079754 A. Emre Zorlu
 * @since Nov 24, 2016
 */
public class HmnSysparDAO {
    /** The jdbc template. */
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Execute sql.
     * 
     * @param sql
     *            the sql
     * @return the list
     * @throws DataAccessException
     *             the data access exception
     * @author U079754 A. Emre Zorlu
     * @since Nov 24, 2016
     */
    public List<Map<String, Object>> executeSQL(String sql) throws DataAccessException {
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        return result;
    }

    /**
     * Gets the syspar service list.
     * 
     * @return the syspar service list
     * @throws DataAccessException
     *             the data access exception
     * @author U079754 A. Emre Zorlu
     * @since Nov 24, 2016
     */
    public Map<String, String> getSysparServiceList() throws DataAccessException {
        String sysparQuery = SystemProperties.getParameterValue(Constants.PRM_SYSPAR_SERVICE_REG_SQL);
        List<Map<String, Object>> sqlResultSet = executeSQL(sysparQuery);
        Map<String, String> resultMap = new HashMap<String, String>();
        for (Map<String, Object> columns : sqlResultSet) {
            String packagePrefix = String.valueOf(columns.get("PACKAGE_PREFIX"));
            String className = String.valueOf(columns.get("CLASSNAME"));
            resultMap.put(className, packagePrefix);
        }
        return resultMap;
    }
}
