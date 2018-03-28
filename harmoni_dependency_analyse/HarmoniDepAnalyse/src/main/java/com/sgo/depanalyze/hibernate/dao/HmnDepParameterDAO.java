package com.sgo.depanalyze.hibernate.dao;

// Generated Nov 6, 2013 1:49:00 PM by Hibernate Tools 3.4.0.CR1
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sgo.depanalyze.hibernate.datamodel.HmnDepParameter;

/**
 * Home object for domain model class DepHmnParameters.
 * 
 * @see .DepHmnParameters
 * @author Hibernate Tools
 */
public class HmnDepParameterDAO {
    /** The logger. */
    private static Logger logger = Logger.getLogger(HmnDepParameterDAO.class);
    /** The template. */
    private HibernateTemplate template;

    /**
     * Sets the session factory.
     * <p>
     * </p>
     * 
     * @param factory
     *            the new session factory
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:22 AM
     */
    public void setSessionFactory(SessionFactory factory) {
        this.template = new HibernateTemplate(factory);
    }

    /**
     * Find by id.
     * 
     * @param id
     *            the id
     * @return the hmn dep parameter
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:22 AM
     */
    public HmnDepParameter findById(long id) {
        logger.debug("getting HmnDepParameter instance with id: " + id);
        try {
            List results = template.findByExample(new HmnDepParameter(id, null, null, null));
            HmnDepParameter instance = results != null && !results.isEmpty() ? ((HmnDepParameter) results.get(0)) : null;
            if (instance == null) {
                logger.debug("get successful, no instance found");
            } else {
                logger.debug("get successful, instance found");
            }
            return instance;
        } catch (RuntimeException re) {
            logger.error("get failed", re);
            throw re;
        }
    }

    /**
     * Find by param name.
     * 
     * @param paramName
     *            the param name
     * @return the list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 10:14:22 AM
     */
    public List findByParamName(String paramName) {
        logger.debug("finding HmnDepParameter instance by paramName");
        try {
            List results = template.findByExample(new HmnDepParameter(null, paramName, null, null));
            logger.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            logger.error("find by example failed", re);
            throw re;
        }
    }

    /**
     * Find all parameters.
     * 
     * @return the list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @param environment 
     * @since Nov 11, 2013 10:14:22 AM
     */
    public List findAllParameters(String environment) {
        logger.debug("finding all HmnDepParameter ");
        try {
            List results = template.findByExample(new HmnDepParameter(null, null, null, environment));
            // List results = template.find("from HmnDepParameter");
            logger.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            logger.error("find by query failed", re);
            throw re;
        }
    }
}
