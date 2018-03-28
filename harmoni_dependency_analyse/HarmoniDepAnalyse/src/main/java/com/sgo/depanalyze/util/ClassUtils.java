package com.sgo.depanalyze.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sgo.depanalyze.google_reflections.HmnReflections;

/**
 * The Class ClassUtils.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:53 AM
 */
public abstract class ClassUtils {
    //
    /** The class_ i service. */
    static Class<?> class_IService = null;
    /** The class_ abstract service. */
    static Class<?> class_AbstractService = null;
    /** The class_ core dto. */
    static Class<?> class_CoreDTO = null;
    /** The class_ core dao. */
    static Class<?> class_CoreDAO = null;
    /** The class_ i model. */
    static Class<?> class_IModel = null;
    /** The class IDao. */
    static Class<?> class_IDao = null;
    // CNL classes
    /** The class_ i legacy service locator. */
    static Class<?> class_ILegacyServiceLocator = null;
    /** The class_ isql query. */
    static Class<?> class_ISQLQuery = null;
    /** The class_ model_ annotation. */
    static Class<? extends Annotation> class_Model_Annotation = null;
    static Class<?> class_PageController = null;
    static Class<?> class_ConversationController = null;
    // com.ykb.hmn.fw.page.controller.PageController
    // com.ykb.hmn.fw.core.conversation.ConversationController
    //
    /** The logger. */
    static Logger logger = Logger.getLogger(ClassUtils.class);
    static {
        try {
            class_IService = FileSystemClassLoader.loadClass(Constants.CLASS_ISERVICE);
        } catch (ClassNotFoundException e) {
            String errLog = "cannot initialize class: " + Constants.CLASS_ISERVICE;
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        }
        //
        try {
            class_AbstractService = FileSystemClassLoader.loadClass(Constants.CLASS_ABSTRACTSERVICE);
        } catch (ClassNotFoundException e) {
            String errLog = "cannot initialize class: " + Constants.CLASS_ABSTRACTSERVICE;
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        }
        //
        try {
            class_CoreDTO = FileSystemClassLoader.loadClass(Constants.CLASS_COREDTO);
        } catch (ClassNotFoundException e) {
            String errLog = "cannot initialize class: " + Constants.CLASS_COREDTO;
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        }
        //
        try {
            class_CoreDAO = FileSystemClassLoader.loadClass(Constants.CLASS_COREDAO);
        } catch (ClassNotFoundException e) {
            String errLog = "cannot initialize class: " + Constants.CLASS_COREDAO;
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        }
        //
        try {
            class_IModel = FileSystemClassLoader.loadClass(Constants.CLASS_IMODEL);
        } catch (ClassNotFoundException e) {
            String errLog = "cannot initialize class: " + Constants.CLASS_IMODEL;
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        }
        //
        try {
            class_ILegacyServiceLocator = FileSystemClassLoader.loadClass(Constants.CLASS_ILEGACYSERVICELOCATOR);
        } catch (ClassNotFoundException e) {
            String errLog = "cannot initialize class: " + Constants.CLASS_ILEGACYSERVICELOCATOR;
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        }
        //
        try {
            class_ISQLQuery = FileSystemClassLoader.loadClass(Constants.CLASS_ISQLQUERY);
        } catch (ClassNotFoundException e) {
            String errLog = "cannot initialize class: " + Constants.CLASS_ISQLQUERY;
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        }
        //
        try {
            class_IDao = FileSystemClassLoader.loadClass(Constants.CLASS_IDAO);
        } catch (ClassNotFoundException e) {
            String errLog = "cannot initialize class: " + Constants.CLASS_IDAO;
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        }
        //
        try {
            class_Model_Annotation = (Class<? extends Annotation>) FileSystemClassLoader.loadClass(Constants.CLASS_MODEL_ANNOTATION);
        } catch (ClassNotFoundException e) {
            String errLog = "cannot initialize class: " + Constants.CLASS_MODEL_ANNOTATION;
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        }
        //
        try {
            class_PageController = FileSystemClassLoader.loadClass(Constants.CLASS_PAGECONTROLLER);
        } catch (ClassNotFoundException e) {
            String errLog = "cannot initialize class: " + Constants.CLASS_PAGECONTROLLER;
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        }
        //
        try {
            class_ConversationController = FileSystemClassLoader.loadClass(Constants.CLASS_CONVERSATIONCONTROLLER);
        } catch (ClassNotFoundException e) {
            String errLog = "cannot initialize class: " + Constants.CLASS_CONVERSATIONCONTROLLER;
            logger.error(errLog, e);
            throw new RuntimeException(errLog, e);
        }
    }

    /**
     * Gets the i service clazz.
     * <p>
     * </p>
     * 
     * @return the i service clazz
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static Class<?> getIServiceClazz() {
        return class_IService;
    }

    /**
     * Gets the abstract service clazz.
     * <p>
     * </p>
     * 
     * @return the abstract service clazz
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static Class<?> getAbstractServiceClazz() {
        return class_AbstractService;
    }

    /**
     * Gets the core dto clazz.
     * <p>
     * </p>
     * 
     * @return the core dto clazz
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static Class<?> getCoreDTOClazz() {
        return class_CoreDTO;
    }

    /**
     * Gets the core dao clazz.
     * <p>
     * </p>
     * 
     * @return the core dao clazz
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static Class<?> getCoreDAOClazz() {
        return class_CoreDAO;
    }

    /**
     * Gets the i model clazz.
     * <p>
     * </p>
     * 
     * @return the i model clazz
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static Class<?> getIModelClazz() {
        return class_IModel;
    }

    /**
     * Gets the i legacy service locator clazz.
     * <p>
     * </p>
     * 
     * @return the i legacy service locator clazz
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static Class<?> getILegacyServiceLocatorClazz() {
        return class_ILegacyServiceLocator;
    }

    /**
     * Gets the iSQL query clazz.
     * <p>
     * </p>
     * 
     * @return the iSQL query clazz
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static Class<?> getISQLQueryClazz() {
        return class_ISQLQuery;
    }

    /**
     * Gets the model annotation clazz.
     * <p>
     * </p>
     * 
     * @return the model annotation clazz
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 2:39:23 PM
     */
    public static Class<? extends Annotation> getModelAnnotationClazz() {
        return class_Model_Annotation;
    }

    /**
     * Gets the i dao clazz.
     * <p>
     * </p>
     * 
     * @return the i dao clazz
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 4, 2013 1:52:16 PM
     */
    public static Class<?> getIDaoClazz() {
        return class_IDao;
    }

    /**
     * Checks if is service intf.
     * 
     * @param clazz
     *            the clazz
     * @return true, if is service intf
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static boolean isServiceIntf(Class<?> clazz) {
        if (!clazz.isInterface() || /***/
        isDaoIntf(clazz) || /***/
        isDataModelIntf(clazz) || /***/
        isDTOClazz(clazz)/***/
        // || !clazz.getName().startsWith(Constants.YKB_CLASS_PREFIX)
        ) {
            return false;
        }
        if (class_IService.isAssignableFrom(clazz)) {
            // service interface extends IService interface
            return true;
        }
        // find implementors
        Set<?> implementors = HmnReflections.getReflections().getSubTypesOf(clazz);
        if (implementors != null && !implementors.isEmpty()) {
            for (Object o : implementors) {
                Class<?> implClazz = (Class<?>) o;
                return isServiceImpl(implClazz);
            }
        }
        return false;
    }

    /**
     * Checks if is data model intf.
     * 
     * @param clazz
     *            the clazz
     * @return true, if is data model intf
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static boolean isDataModelIntf(Class<?> clazz) {
        return (clazz.isInterface() && class_IModel.isAssignableFrom(clazz));
    }

    /**
     * Checks if is dao intf.
     * 
     * @param clazz
     *            the clazz
     * @return true, if is dao intf
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static boolean isDaoIntf(Class<?> clazz) {
        // boolean result = (clazz.isInterface() && class_CoreDAO.isAssignableFrom(clazz));
        boolean result = (clazz.isInterface() && class_IDao.isAssignableFrom(clazz));
        // if (result) {
        // logger.trace(clazz.getName() + "  is DAO interface");
        // }
        return result;
    }

    /**
     * Checks if is dTO clazz.
     * 
     * @param clazz
     *            the clazz
     * @return true, if is dTO clazz
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static boolean isDTOClazz(Class<?> clazz) {
        return (class_CoreDTO.isAssignableFrom(clazz));
    }

    /**
     * check if given class implements any backend service interface.
     * 
     * @param clazz
     *            the clazz
     * @return true, if is service impl
     * @throws ClassNotFoundException
     *             the class not found exception
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static boolean isServiceImpl(Class<?> clazz) {
        boolean result = false;
        if ((class_AbstractService.equals(clazz.getSuperclass()) || class_IService.isAssignableFrom(clazz)) && clazz.getName().startsWith(Constants.HMN_CLASS_PREFIX)
                && !Modifier.isAbstract(clazz.getModifiers())) {
            result = true;
        }
        return result;
    }

    /**
     * Find service interface.
     * 
     * @param clazz
     *            the clazz
     * @return the class
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static Class<?> findServiceInterface(Class<?> clazz) {
        for (Class<?> intf : clazz.getInterfaces()) {
            String intfName = intf.getName();
            if (!Constants.CLASS_ISERVICE.equals(intfName) && intfName.startsWith(Constants.HMN_CLASS_PREFIX) && isServiceIntf(intf)) {
                return intf;
            }
        }
        return null;
    }

    /**
     * Find service methods.
     * 
     * @param iServiceIntf
     *            the i service intf
     * @return the list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static List<Method> findServiceMethods(Class<?> iServiceIntf) {
        List<Method> methodList = new ArrayList<Method>();
        Method[] methods = iServiceIntf.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (Modifier.isPublic(method.getModifiers())
            /* && (method.getExceptionTypes()!=null && method.getExceptionTypes()[0].equals(HmnServiceException.class)) */
            ) {
                methodList.add(method);
            }
        }
        return methodList;
    }

    /**
     * Checks for throw hmn service exception.
     * 
     * @param method
     *            the method
     * @return true, if successful
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static boolean hasThrowHmnServiceException(Method method) {
        return (method.getExceptionTypes() != null && /** */
        method.getExceptionTypes().length == 1 && /** */
        method.getExceptionTypes()[0].getName().equals(Constants.CLASS_HMNSERVICEEXCEPTION));
    }

    /**
     * getImplementationMethod.
     * 
     * @param interfaceMethod
     *            the interface method whose implementation you're looking for
     * @param concreteClass
     *            the concrete class
     * @return the implementation method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static Method getImplementationMethod(Method interfaceMethod, Class<?> concreteClass) throws RuntimeException {
        return getClazzMethod(concreteClass, interfaceMethod.getName(), interfaceMethod.getParameterTypes());
    }

    /**
     * Gets the clazz method.
     * <p>
     * </p>
     * 
     * @param clazz
     *            the clazz
     * @param methodName
     *            the method name
     * @param parameterTypes
     *            the parameter types
     * @return the clazz method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static Method getClazzMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) throws RuntimeException {
        Method method = _getClazzMethod(clazz, methodName, parameterTypes);
        if (method != null) {
            return method;
        }
        Class<?> superClazz = clazz.getSuperclass();
        while (method == null && superClazz != null && superClazz != java.lang.Object.class) {
            method = _getClazzMethod(superClazz, methodName, parameterTypes);
            if (method == null) {
                superClazz = superClazz.getSuperclass();
            }
        }
        // if (method == null && clazz.getSuperclass() != null && clazz.getSuperclass() != java.lang.Object.class) {
        // method = _getClazzMethod(clazz.getSuperclass(), methodName, parameterTypes);
        // }
        if (method == null) {
            logger.error("Cannot find method: " + clazz.getName() + "." + methodName + ClassUtils.argumentTypesToString(parameterTypes));
        }
        return method;
    }

    /**
     * _get clazz method.
     * 
     * @param clazz
     *            the clazz
     * @param methodName
     *            the method name
     * @param parameterTypes
     *            the parameter types
     * @return the method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    private static Method _getClazzMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) throws RuntimeException {
        Method method = null;
        try {
            if (parameterTypes != null && parameterTypes.length > 0) {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
            } else {
                method = clazz.getDeclaredMethod(methodName);
            }
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            String errLog = String.format("Cannot find declared method, NoSuchMethodException (method:%s , class:%s)", methodName, clazz.getName());
            logger.debug(errLog, e);
            // throw new RuntimeException(errLog, e);
        } catch (SecurityException e) {
            String errLog = String.format("Cannot find declared method, insufficient permissions, SecurityException (method:%s , class:%s)", methodName, clazz.getName());
            logger.debug(errLog, e);
            // throw new RuntimeException(errLog, e);
        } catch (Exception e) {
            String errLog = String.format("Cannot find declared method, Exception (method:%s, class:%s)", methodName, clazz.getName());
            logger.debug(errLog, e);
            // throw new RuntimeException(errLog, e);
        } catch (Throwable e) {
            String errLog = String.format("Cannot find declared method, Throwable (method:%s, class:%s)", methodName, clazz.getName());
            logger.debug(errLog, e);
            // throw new RuntimeException(errLog, e);
        }
        return method;
    }

    /**
     * Checks if is same method.
     * 
     * @param m1
     *            the m1
     * @param m2
     *            the m2
     * @return true, if is same method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static boolean isSameMethod(Method m1, Method m2) {
        if (m1 == null && m2 == null) {
            return true;
        }
        if ((m1 != null && m2 == null) || (m2 != null && m1 == null)) {
            return false;
        }
        if ((m1.getDeclaringClass() == m2.getDeclaringClass()) && (m1.getName() == m2.getName())) {
            if (!m1.getReturnType().equals(m2.getReturnType()))
                return false;
            /* Avoid unnecessary cloning */
            Class<?>[] params1 = m1.getParameterTypes();
            Class<?>[] params2 = m2.getParameterTypes();
            if (params1.length == params2.length) {
                for (int i = 0; i < params1.length; i++) {
                    if (params1[i] != params2[i])
                        return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Argument types to string.
     * 
     * @param argTypes
     *            the arg types
     * @return the string
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public static String argumentTypesToString(Class<?>[] argTypes) {
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        if (argTypes != null) {
            for (int i = 0; i < argTypes.length; i++) {
                if (i > 0) {
                    buf.append(", ");
                }
                Class<?> c = argTypes[i];
                buf.append((c == null) ? "null" : c.getName());
            }
        }
        buf.append(")");
        return buf.toString();
    }

    /**
     * Find implemented interfaces.
     * 
     * @param cl
     *            the cl
     * @return the list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 3, 2013 9:10:31 AM
     */
    public static List<Class<?>> findImplementedInterfaces(Class<?> cl) {
        Set<Class<?>> types = findImplementedTypes(cl);
        List<Class<?>> interfaceList = new ArrayList<Class<?>>();
        // post processing:
        for (Class<?> curr : types) {
            if (curr.isInterface()) {
                interfaceList.add(curr);
            }
        }
        return interfaceList;
    }

    /**
     * Find implemented interfaces
     * <p>
     * Sadece packagePrefix ile başlayan interface'leri döner
     * </p>
     * 
     * @param cl
     *            the cl
     * @param packagePrefix
     *            the package prefix
     * @return the list
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 7, 2014 10:41:58 AM
     */
    public static List<Class<?>> findImplementedInterfaces(Class<?> cl, String packagePrefix) {
        Set<Class<?>> types = findImplementedTypes(cl);
        List<Class<?>> interfaceList = new ArrayList<Class<?>>();
        // post processing:
        for (Class<?> curr : types) {
            if (curr.isInterface() && curr.getName().startsWith(packagePrefix)) {
                interfaceList.add(curr);
            }
        }
        return interfaceList;
    }

    /**
     * Find implemented types.
     * 
     * @param cl
     *            the cl
     * @return the sets the
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 3, 2013 9:10:31 AM
     */
    public static Set<Class<?>> findImplementedTypes(Class<?> cl) {
        // BFC algorithm
        Queue<Class<?>> queue = new LinkedList<Class<?>>();
        Set<Class<?>> types = new HashSet<Class<?>>();
        queue.add(cl);
        types.add(cl);
        // BFS:
        while (queue.isEmpty() == false) {
            Class<?> curr = queue.poll();
            Class<?>[] supers = curr.getInterfaces();
            for (Class<?> next : supers) {
                if (next != null && types.contains(next) == false) {
                    types.add(next);
                    queue.add(next);
                }
            }
            Class<?> next = curr.getSuperclass();
            if (next != null && types.contains(next) == false) {
                queue.add(next);
                types.add(next);
            }
        }
        return types;
    }

    /**
     * Find implemented interfaces.
     * 
     * @param className
     *            the clazz name
     * @return the list
     * @throws ClassNotFoundException
     *             the class not found exception
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 3, 2013 9:10:31 AM
     */
    public static List<Class<?>> findImplementedInterfaces(String className) throws ClassNotFoundException {
        Class<?> cl = FileSystemClassLoader.loadClass(className);
        return findImplementedInterfaces(cl);
    }

    /**
     * Find super classes.
     * 
     * @param cl
     *            the cl
     * @return the list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 3, 2013 9:10:31 AM
     */
    public static List<Class<?>> findSuperClasses(Class<?> cl) {
        Set<Class<?>> types = findImplementedTypes(cl);
        // remove class itself
        types.remove(cl);
        //
        types.remove(java.lang.Object.class);
        List<Class<?>> superClasses = new ArrayList<Class<?>>();
        // post processing:
        for (Class<?> curr : types) {
            if (!(curr.isInterface() || curr.isEnum() || curr.isAnnotation())) {
                superClasses.add(curr);
            }
        }
        return superClasses;
    }

    /**
     * Find super classes.
     * 
     * @param className
     *            the clazz name
     * @return the list
     * @throws ClassNotFoundException
     *             the class not found exception
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 3, 2013 9:10:31 AM
     */
    public static List<Class<?>> findSuperClasses(String className) throws ClassNotFoundException {
        Class<?> cl = FileSystemClassLoader.loadClass(className);
        return findSuperClasses(cl);
    }

    /**
     * Checks if is harmoni interface.
     * 
     * @param cl
     *            the cl
     * @return true, if is harmoni interface
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 3, 2013 9:10:31 AM
     */
    public static boolean isHarmoniInterface(Class<?> cl) {
        return cl.isInterface() && (cl.getName().startsWith(Constants.HMN_CLASS_PREFIX) || cl.getName().startsWith(Constants.HMN_CLASS_PREFIX_2));
    }

    /**
     * Gets the annotation value.
     * 
     * @param <T>
     *            the generic type
     * @param clazz
     *            the clazz
     * @param annotationClass
     *            the annotation class
     * @param element
     *            the element
     * @return the annotation value
     * @throws Exception
     *             the exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAnnotationValue(Class<?> clazz, Class<? extends Annotation> annotationClass, String element) throws Exception {
        Annotation annotation = clazz.getAnnotation(annotationClass);
        Method method = annotationClass.getMethod(element, (Class[]) null);
        if (annotation == null) {
            return ((T) method.getDefaultValue());
        }
        return ((T) method.invoke(annotation, (Object[]) null));
    }

    /**
     * Checks if is page controller.
     * 
     * @param clazz
     *            the clazz
     * @return true, if is page controller
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 27, 2016
     */
    public static boolean isPageController(Class<?> clazz) {
        return (class_PageController.isAssignableFrom(clazz));
    }

    /**
     * Checks if is conversation controller.
     * 
     * @param clazz
     *            the clazz
     * @return true, if is conversation controller
     * @author Selçuk Giray ÖZDAMAR
     * @since Jun 27, 2016
     */
    public static boolean isConversationController(Class<?> clazz) {
        return (class_ConversationController.isAssignableFrom(clazz));
    }
}
