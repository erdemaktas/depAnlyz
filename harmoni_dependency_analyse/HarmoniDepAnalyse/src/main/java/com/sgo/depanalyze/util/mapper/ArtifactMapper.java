/**
 * @author U079754 A. Emre Zorlu
 * @since Jun 8, 2016
 * @return ArtifactMapper
 * 
 */
package com.sgo.depanalyze.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sgo.depanalyze.hibernate.datamodel.HmnDepArtifact;

/**
 * The Class ArtifactMapper.
 * 
 * @author U079754 A. Emre Zorlu
 * @since Jun 8, 2016
 */
public class ArtifactMapper implements RowMapper<HmnDepArtifact> {
    /**
     * Map row.
     * 
     * @param rs
     *            the rs
     * @param rowNum
     *            the row num
     * @return the hmn dep artifact
     * @throws SQLException
     *             the sQL exception
     * @author U079754 A. Emre Zorlu
     * @since Jun 8, 2016
     */
    @Override
    public HmnDepArtifact mapRow(ResultSet rs, int rowNum) throws SQLException {
        HmnDepArtifact artifact = new HmnDepArtifact();
        artifact.setId(rs.getLong("ID"));
        artifact.setArtifactId(rs.getString("ARTIFACT_ID"));
        artifact.setArtifactDesc(rs.getString("ARTIFACT_DESC"));
        artifact.setArtifactType(rs.getString("ARTIFACT_TYPE"));
        artifact.setArtifactVersion(rs.getString("ARTIFACT_VERSION"));
        artifact.setExecutionId(rs.getLong("EXECUTION_ID"));
        artifact.setFileName(rs.getString("FILE_NAME"));
        artifact.setGroupId(rs.getString("GROUP_ID"));
        return artifact;
    }
}
