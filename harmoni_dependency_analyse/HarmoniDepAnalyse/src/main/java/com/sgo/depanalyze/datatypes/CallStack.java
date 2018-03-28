package com.sgo.depanalyze.datatypes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class CallStack.
 * 
 * @author U065352 - SELÇUK GİRAY ÖZDAMAR
 * @since Dec 27, 2013 9:35:57 AM
 */
public class CallStack {
    /** The call stack. */
    private List<CallStackElement> callStack = null;

    /**
     * Instantiates a new call stack.
     * 
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:35:57 AM
     */
    public CallStack() {
        this.callStack = new ArrayList<CallStackElement>();
    }

    /**
     * Instantiates a new call stack.
     * 
     * @param elements
     *            the elements
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:35:57 AM
     */
    public CallStack(List<CallStackElement> elements) {
        this.callStack = new ArrayList<CallStackElement>(elements);
        // this.callStack.addAll(elements);
    }

    /**
     * Adds the element.
     * 
     * @param callStackElement
     *            the call stack element
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:35:57 AM
     */
    public void addElement(CallStackElement callStackElement) {
        this.callStack.add(callStackElement);
    }

    /**
     * Gets the elements.
     * <p>
     * </p>
     * 
     * @return the elements
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:35:57 AM
     */
    public List<CallStackElement> getElements() {
        return this.callStack;
    }

    /**
     * Contains method.
     * 
     * @param method
     *            the method
     * @return true, if successful
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:35:57 AM
     */
    public boolean containsMethod(Method method) {
        return indexOf(method) >= 0;
    }

    /**
     * Index of.
     * 
     * @param o
     *            the o
     * @return the int
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:35:57 AM
     */
    private int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < callStack.size(); i++)
                if (callStack.get(i).getMethod() == null)
                    return i;
        } else {
            for (int i = 0; i < callStack.size(); i++)
                if (o.equals(callStack.get(i).getMethod()))
                    return i;
        }
        return -1;
    }
}
