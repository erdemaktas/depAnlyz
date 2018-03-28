package com.sgo.depanalyze.util;

import java.lang.reflect.Method;
import java.util.Stack;

/**
 * The Class DependencyTraceElement.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:53 AM
 */
public class DependencyTraceElement {
    /** The method. */
    private Method method = null;
    /** The ldc stack. */
    private Stack ldcStack = new Stack();

    /**
     * Instantiates a new dependency trace element.
     * 
     * @param method
     *            the method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public DependencyTraceElement(Method method) {
        super();
        this.method = method;
    }

    /**
     * Instantiates a new dependency trace element.
     * 
     * @param method
     *            the method
     * @param ldcStack
     *            the ldc stack
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public DependencyTraceElement(Method method, Stack ldcStack) {
        super();
        this.method = method;
        this.ldcStack = ldcStack;
    }

    /**
     * Gets the method.
     * <p>
     * </p>
     * 
     * @return the method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public Method getMethod() {
        return method;
    }

    /**
     * Sets the method.
     * <p>
     * </p>
     * 
     * @param method
     *            the new method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * Gets the ldc stack.
     * <p>
     * </p>
     * 
     * @return the ldc stack
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public Stack getLdcStack() {
        return ldcStack;
    }

    /**
     * Sets the ldc stack.
     * <p>
     * </p>
     * 
     * @param ldcStack
     *            the new ldc stack
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public void setLdcStack(Stack ldcStack) {
        this.ldcStack = ldcStack;
    }

    /**
     * Adds the ldc element.
     * 
     * @param ldcObj
     *            the ldc obj
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:53 AM
     */
    public void addLdcElement(Object ldcObj) {
        ldcStack.add(ldcObj);
    }
}
