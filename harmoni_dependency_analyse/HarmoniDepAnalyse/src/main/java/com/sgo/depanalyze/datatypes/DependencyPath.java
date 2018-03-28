package com.sgo.depanalyze.datatypes;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class DependencyPath.
 * 
 * @author U065352 - SELÇUK GİRAY ÖZDAMAR
 * @since Dec 27, 2013 9:36:50 AM
 */
public class DependencyPath {
    /** The call stack. */
    private List<CallStackElement> callStack = null;

    /**
     * Instantiates a new dependency path.
     * 
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:36:50 AM
     */
    public DependencyPath() {
        this.callStack = new ArrayList<CallStackElement>();
    }

    /**
     * Instantiates a new dependency path.
     * 
     * @param elements
     *            the elements
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:36:50 AM
     */
    public DependencyPath(List<CallStackElement> elements) {
        if (elements == null) {
            throw new NullPointerException("elements is null");
        }
        this.callStack = new ArrayList<CallStackElement>(elements);
    }

    /**
     * Adds the call stack element.
     * 
     * @param callStackElement
     *            the call stack element
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Dec 27, 2013 9:36:50 AM
     */
    public void addCallStackElement(CallStackElement callStackElement) {
        this.callStack.add(callStackElement);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        if (callStack == null || callStack.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < callStack.size(); i++) {
            CallStackElement element = callStack.get(i);
            sb.append(element.toString());
            if (i != callStack.size() - 1) {
                sb.append(" >> \n");
            }
        }
        return sb.toString();
    }
}
