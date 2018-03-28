package com.sgo.depanalyze.util.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.PUTSTATIC;
import org.apache.bcel.generic.ReferenceType;
import org.apache.log4j.Logger;

import com.sgo.depanalyze.datatypes.CallStackElement;
import com.sgo.depanalyze.datatypes.ClassLevelDependencyList;
import com.sgo.depanalyze.datatypes.DependencyList;
import com.sgo.depanalyze.datatypes.DependencyPath;
import com.sgo.depanalyze.datatypes.DependencyScanResult;
import com.sgo.depanalyze.datatypes.GlobalVariableUsage;
import com.sgo.depanalyze.datatypes.HarmoniJarEntries;
import com.sgo.depanalyze.datatypes.InstructionWithLineNumber;
import com.sgo.depanalyze.datatypes.MethodDependencies;
import com.sgo.depanalyze.datatypes.ServiceDependency;
import com.sgo.depanalyze.util.ByteCodeRepository;
import com.sgo.depanalyze.util.ByteCodeUtils;
import com.sgo.depanalyze.util.ClassUtils;
import com.sgo.depanalyze.util.FileSystemClassLoader;
import com.sgo.depanalyze.util.intf.IByteCodeAgent;
import com.sgo.depanalyze.util.intf.IByteCodeRepository;
import com.sgo.depanalyze.util.intf.IDependencyScanner;
import com.sgo.depanalyze.util.intf.IJarFileScanner;

/**
 * The Class FrontendDependencyScanner.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:54 AM
 */
public class FrontendDependencyScanner implements IDependencyScanner {
    //
    /** The logger. */
    private static Logger logger = Logger.getLogger(FrontendDependencyScanner.class);
    /** The byte code repository. */
    private IByteCodeRepository byteCodeRepository = ByteCodeRepository.getInstance();

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.IDependencyScanner#scanDependencies(java.lang.Class)
     */
    @Override
    public ClassLevelDependencyList scanDependencies(Class<?> sourceClazz) {
        if (sourceClazz == null) {
            logger.error("scanDependencies is ignoring for null 'sourceClazz' argument");
            return new ClassLevelDependencyList();
        }
        // /***/
        // if (Modifier.isAbstract(sourceClazz.getModifiers())) {
        // logger.warn("scanDependencies is ignoring for null abstract class:" + sourceClazz.getName());
        // return dependencyResult;
        // }
        /***/
        Method[] declaredMethods = null;
        try {
            declaredMethods = sourceClazz.getDeclaredMethods();
        } catch (Exception e) {
            String errLog = String.format("Exception, cannot get declared methods (class:%s)", sourceClazz.getName());
            logger.error(errLog, e);
        } catch (Throwable e) {
            String errLog = String.format("Error, cannot get declared methods (class:%s)", sourceClazz.getName());
            logger.error(errLog, e);
        }
        /***/
        if (declaredMethods == null || declaredMethods.length == 0) {
            logger.warn("scanDependencies will return with empty dependency list, no declared method  found in class:" + sourceClazz.getName());
            return new ClassLevelDependencyList(sourceClazz);
        }
        // Map<Method, DependencyList> dependencyMapByMethod = new LinkedHashMap<Method, DependencyList>();
        List<MethodDependencies> dependencyListByMethod = new ArrayList<MethodDependencies>();
        // for each service method implementation, run scanDependencies for method
        for (Method implMethod : declaredMethods) {
            DependencyList methodDependencyResult = scanDependencies(implMethod);
            if (!methodDependencyResult.getServiceDependencyList().isEmpty()) {
                // dependencyMapByMethod.put(implMethod, methodDependencyResult);
                dependencyListByMethod.add(new MethodDependencies(null, implMethod, methodDependencyResult));
            }
            logger.debug(String.format("%d Service dependency found in %s.%s()", methodDependencyResult.getServiceDependencyList().size(), sourceClazz.getName(), implMethod.getName()));
        }
        ClassLevelDependencyList clazzLevelDependency = new ClassLevelDependencyList();
        clazzLevelDependency.setImplementationClazz(sourceClazz);
        // clazzLevelDependency.setDependencyMapByMethod(dependencyMapByMethod);
        clazzLevelDependency.setDependencyListByMethod(dependencyListByMethod);
        return clazzLevelDependency;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.IDependencyScanner#scanDependencies(java.lang.reflect.Method)
     */
    @Override
    public DependencyList scanDependencies(Method method) {
        return scanDependencies(method, 1, new ArrayList<Method>());
    }

    /**
     * Scan dependencies.
     * 
     * @param implementationMethod
     *            the implementation method
     * @param dependencyLevel
     *            the dependency level
     * @param callStack
     *            the call stack
     * @return the dependency list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    private DependencyList scanDependencies(Method implementationMethod, int dependencyLevel, List<Method> callStack) {
        DependencyList dependencyResult = new DependencyList();
        if (implementationMethod == null) {
            logger.error("scanDependencies is ignoring for null 'method' argument");
            return dependencyResult;
        }
        /***/
        if (Modifier.isAbstract(implementationMethod.getModifiers())) {
            logger.error("scanDependencies is ignoring byte code scan for abstract method: " + implementationMethod.toString());
            return dependencyResult;
        }
        /***/
        Class<?> sourceClazz = implementationMethod.getDeclaringClass();
        if (sourceClazz == null) {
            logger.error("scanDependencies is ignoring byte code scan, 'DeclaringClass' is null for " + implementationMethod.toString());
            return dependencyResult;
        }
        /** 18.02.2014 */
        IByteCodeAgent byteCodeAgent = null;
        try {
            byteCodeAgent = this.byteCodeRepository.loadByteCodeAgent(sourceClazz);
        } catch (ClassNotFoundException e) {
            logger.error("scanDependencies failed for class: " + sourceClazz.getName() + " and method:" + implementationMethod.getName(), e);
            return dependencyResult;
        }
        /** 18.02.2014 */
        /***/
        org.apache.bcel.classfile.Method bcelMethod = byteCodeAgent.getBcelMethod(implementationMethod);
        ConstantPoolGen theConstPoolGen = byteCodeAgent.getConstantPoolGen();
        if (bcelMethod == null) {
            logger.error("scanDependencies is ignoring byte code scan, cannot get 'bcelMethod' for " + implementationMethod.toString());
            return dependencyResult;
        }
        /***/
        List<InstructionWithLineNumber> instructionList = byteCodeAgent.getInstructionsWithLineNumbers(bcelMethod);
        if (instructionList == null || instructionList.isEmpty()) {
            logger.warn("scanDependencies will return with empty dependency list, no instruction found in method:" + implementationMethod.toString());
            return dependencyResult;
        }
        for (int i = 0; i < instructionList.size(); i++) {
            Instruction instruction = instructionList.get(i).getInstruction();
            int sourceLine = instructionList.get(i).getSourceLine();
            if (instruction instanceof PUTSTATIC && !Modifier.isSynchronized(implementationMethod.getModifiers())) {
                PUTSTATIC ps = (PUTSTATIC)instruction;
                GlobalVariableUsage globalVarUsage = new GlobalVariableUsage();
                globalVarUsage.setUsageType(GlobalVariableUsage.UsageType.PUTSTATIC);
                globalVarUsage.setFieldName(ps.getFieldName(theConstPoolGen));
                globalVarUsage.setSignature(ps.getSignature(theConstPoolGen));
                globalVarUsage.setReferenceType(ps.getReferenceType(theConstPoolGen).toString());
                globalVarUsage.setDependencyLevel(dependencyLevel);
                DependencyPath dependencyPath = new DependencyPath();
                dependencyPath.addCallStackElement(new CallStackElement(implementationMethod, sourceLine));
                globalVarUsage.setDependencyPath(dependencyPath.toString());
                dependencyResult.addGlobalVariableUsage(globalVarUsage);
            } else if (instruction instanceof PUTFIELD) {
                PUTFIELD pf = (PUTFIELD)instruction;
                String fieldRefType = pf.getReferenceType(theConstPoolGen).toString();
                boolean anomaliFound = false;
                if (fieldRefType.equals(implementationMethod.getDeclaringClass().getName())
                        && (ClassUtils.isPageController(implementationMethod.getDeclaringClass()) || ClassUtils.isConversationController(implementationMethod.getDeclaringClass()))) {
                    anomaliFound = true;
                }
                if (anomaliFound) {
                    GlobalVariableUsage globalVarUsage = new GlobalVariableUsage();
                    globalVarUsage.setUsageType(GlobalVariableUsage.UsageType.PUTFIELD);
                    globalVarUsage.setFieldName(pf.getFieldName(theConstPoolGen));
                    globalVarUsage.setSignature(pf.getSignature(theConstPoolGen));
                    globalVarUsage.setReferenceType(fieldRefType);
                    globalVarUsage.setDependencyLevel(dependencyLevel);
                    DependencyPath dependencyPath = new DependencyPath();
                    dependencyPath.addCallStackElement(new CallStackElement(implementationMethod, sourceLine));
                    globalVarUsage.setDependencyPath(dependencyPath.toString());
                    dependencyResult.addGlobalVariableUsage(globalVarUsage);
                }
            }  else if (ByteCodeUtils.isServiceIntfCall(sourceClazz, instruction, theConstPoolGen)) {
                INVOKEINTERFACE invokeIntf = (INVOKEINTERFACE) instruction;
                ReferenceType refType = invokeIntf.getReferenceType(theConstPoolGen);
                String invokeType = refType.toString();
                String invokeMethodName = invokeIntf.getMethodName(theConstPoolGen);
                // Type invokeReturnType = invokeIntf.getReturnType(bcelUtil.getConstantPool());
                // Type[] argTypes = invokeIntf.getArgumentTypes(bcelUtil.getConstantPool());
                String targetSignature = invokeIntf.getSignature(theConstPoolGen);
                DependencyPath dependencyPath = new DependencyPath();
                dependencyPath.addCallStackElement(new CallStackElement(implementationMethod, sourceLine));
                ServiceDependency serviceDep = new ServiceDependency(invokeType, invokeMethodName, targetSignature, dependencyPath.toString(), dependencyLevel);
                dependencyResult.addDependency(serviceDep);
                logger.debug(String.format("%s.%s() calling service interface: %s.%s()", sourceClazz.getName(), implementationMethod.getName(), invokeType, invokeMethodName));
            }
        }
        return dependencyResult;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.IDependencyScanner#scanDependencies(java.util.jar.JarFile)
     */
    @Override
    public DependencyScanResult scanDependencies(JarFile jarFile) {
        List<ClassLevelDependencyList> dependencyResult = new ArrayList<ClassLevelDependencyList>();
        //
        IJarFileScanner jarScanner = new HmnBackendJarScanner(FileSystemClassLoader.getClassLoader());
        HarmoniJarEntries jarEntries = jarScanner.scanJar(jarFile);
        for (Class<?> serviceClazz : jarEntries.getServiceClazzes()) {
            ClassLevelDependencyList clazzLevelDependencies = scanDependencies(serviceClazz);
            if (clazzLevelDependencies != null && clazzLevelDependencies.getDependencyListByMethod() != null && !clazzLevelDependencies.getDependencyListByMethod().isEmpty()) {
                dependencyResult.add(clazzLevelDependencies);
            }
        }
        DependencyScanResult result = new DependencyScanResult();
        result.setClassLevelDependencies(dependencyResult);
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.IDependencyScanner#scanDependencies(java.lang.String)
     */
    @Override
    public DependencyScanResult scanDependencies(String jarFile) {
        //
        logger.info("scanning jar file:" + jarFile);
        IJarFileScanner jarScanner = new HmnFrontendJarScanner(FileSystemClassLoader.getClassLoader());
        HarmoniJarEntries jarEntries = jarScanner.scanJar(jarFile);
        //
        List<ClassLevelDependencyList> dependencyResult = new ArrayList<ClassLevelDependencyList>();
        //
        for (Class<?> sourceClazz : jarEntries.getServiceClazzes()) {
            ClassLevelDependencyList clazzLevelDependencies = scanDependencies(sourceClazz);
            if (clazzLevelDependencies != null && clazzLevelDependencies.getDependencyListByMethod() != null && !clazzLevelDependencies.getDependencyListByMethod().isEmpty()) {
                dependencyResult.add(clazzLevelDependencies);
            }
        }
        DependencyScanResult result = new DependencyScanResult();
        result.setPomProperties(jarEntries.getPomProperties());
        result.setClassLevelDependencies(dependencyResult);
        return result;
    }

    /**
     * Builds the dep path.
     * 
     * @param callStack
     *            the call stack
     * @return the string
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    private String buildDepPath(List<Method> callStack) {
        if (callStack == null || callStack.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < callStack.size(); i++) {
            Method m = callStack.get(i);
            sb.append(String.format("%s.%s()", m.getDeclaringClass().getSimpleName(), m.getName()));
            if (i != callStack.size() - 1) {
                sb.append(" >> ");
            }
        }
        return sb.toString();
    }
}
