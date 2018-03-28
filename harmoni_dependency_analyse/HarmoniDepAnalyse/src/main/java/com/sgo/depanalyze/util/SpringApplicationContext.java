package com.sgo.depanalyze.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * The Class SpringApplicationContext.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:54 AM
 */
public class SpringApplicationContext implements ApplicationContextAware {
    /** The context. */
    private static ApplicationContext CONTEXT;

    /**
     * This method is called from within the ApplicationContext once it is done starting up, it will stick a reference
     * to itself into this bean.
     * 
     * @param context
     *            a reference to the ApplicationContext.
     * @throws BeansException
     *             the beans exception
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        CONTEXT = context;
    }

    /**
     * This is about the same as context.getBean("beanName"), except it has its own static handle to the Spring context,
     * so calling this method statically will give access to the beans by name in the Spring application context. As in
     * the context.getBean("beanName") call, the caller must cast to the appropriate target class. If the bean does not
     * exist, then a Runtime error will be thrown.
     * 
     * @param beanName
     *            the name of the bean to get.
     * @return an Object reference to the named bean.
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static Object getBean(String beanName) {
        return CONTEXT.getBean(beanName);
    }
}
