package com.sgo.depanalyze.datatypes;

import java.lang.reflect.Method;

/**
 * The Class MethodDependencies.
 * 
 * @author U065352 - SELÇUK GİRAY ÖZDAMAR
 * @since 30.Haz.2014 04:27:26
 */
public class MethodDependencies {
    private Method interfaceMethod;
    private Method implementationMethod;
    private DependencyList dependencyList;
    private String digest;
    
    public MethodDependencies() {
        super();
    }

    public MethodDependencies(Method interfaceMethod, Method implementationMethod, DependencyList dependencyList) {
        super();
        this.interfaceMethod = interfaceMethod;
        this.implementationMethod = implementationMethod;
        this.dependencyList = dependencyList;
    }

    public Method getInterfaceMethod() {
        return interfaceMethod;
    }

    public void setInterfaceMethod(Method interfaceMethod) {
        this.interfaceMethod = interfaceMethod;
    }

    public Method getImplementationMethod() {
        return implementationMethod;
    }

    public void setImplementationMethod(Method implementationMethod) {
        this.implementationMethod = implementationMethod;
    }

    public DependencyList getDependencyList() {
        return dependencyList;
    }

    public void setDependencyList(DependencyList dependencyList) {
        this.dependencyList = dependencyList;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDigest() {
        return digest;
    }
}
