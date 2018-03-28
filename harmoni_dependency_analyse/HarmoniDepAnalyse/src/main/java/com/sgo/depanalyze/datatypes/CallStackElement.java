package com.sgo.depanalyze.datatypes;

import java.lang.reflect.Method;

/**
 * The Class CallStackElement.
 *
 * @author U065352 - SELÇUK GİRAY ÖZDAMAR
 * @since Dec 27, 2013 9:36:12 AM
 */
public class CallStackElement {
    /** The method. */
    private Method method;
    /** The line number. */
    private int lineNumber;

    /**
     * The Constructor.
     *
     * @param method the method
     * @param lineNumber the line number
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:36:12 AM
     */
    public CallStackElement(Method method, int lineNumber) {
        if (method == null) {
            throw new IllegalArgumentException("Method is null");
        }
        this.method = method;
        this.lineNumber = lineNumber;
    }

    /**
     * Gets the method.
     *
     * @return the method
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:36:12 AM
     */
    public Method getMethod() {
        return method;
    }

    /**
     * Gets the line number.
     *
     * @return the line number
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:36:12 AM
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Gets the class name.
     *
     * @return the class name
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:36:12 AM
     */
    public String getClassName() {
        return method.getDeclaringClass().getName();
    }

    /**
     * Gets the class simple name.
     *
     * @return the class simple name
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:36:12 AM
     */
    public String getClassSimpleName() {
        return method.getDeclaringClass().getSimpleName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClassSimpleName() + "." + method.getName() + (lineNumber >= 0 ? "(line:" + lineNumber + ")" : ""));
        return sb.toString();
    }
    // @Override
    // public boolean equals(Object obj) {
    // if (obj == this) {
    // return true;
    // }
    // if (obj == null || obj.getClass() != this.getClass()) {
    // return false;
    // }
    //
    // CallStackElement guest = (CallStackElement) obj;
    // return method.equals(guest.getMethod());
    // }
}
