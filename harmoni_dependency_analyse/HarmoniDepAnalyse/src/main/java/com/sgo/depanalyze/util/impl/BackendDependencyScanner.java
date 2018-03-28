package com.sgo.depanalyze.util.impl;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;

import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InvokeInstruction;
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.PUTSTATIC;
import org.apache.bcel.generic.ReferenceType;
import org.apache.bcel.generic.Type;
import org.apache.log4j.Logger;

import com.sgo.depanalyze.datatypes.CallStack;
import com.sgo.depanalyze.datatypes.CallStackElement;
import com.sgo.depanalyze.datatypes.ClassLevelDependencyList;
import com.sgo.depanalyze.datatypes.DependencyList;
import com.sgo.depanalyze.datatypes.DependencyPath;
import com.sgo.depanalyze.datatypes.DependencyScanResult;
import com.sgo.depanalyze.datatypes.GlobalVariableUsage;
import com.sgo.depanalyze.datatypes.HarmoniJarEntries;
import com.sgo.depanalyze.datatypes.HbmDaoDependency;
import com.sgo.depanalyze.datatypes.InstructionWithLineNumber;
import com.sgo.depanalyze.datatypes.MethodDependencies;
import com.sgo.depanalyze.datatypes.NamedQueryDependency;
import com.sgo.depanalyze.datatypes.PlsqlDependency;
import com.sgo.depanalyze.datatypes.ServiceDependency;
import com.sgo.depanalyze.enums.CRUD_FLAG;
import com.sgo.depanalyze.util.ByteCodeRepository;
import com.sgo.depanalyze.util.ByteCodeUtils;
import com.sgo.depanalyze.util.ClassUtils;
import com.sgo.depanalyze.util.Constants;
import com.sgo.depanalyze.util.FileSystemClassLoader;
import com.sgo.depanalyze.util.intf.IByteCodeAgent;
import com.sgo.depanalyze.util.intf.IByteCodeRepository;
import com.sgo.depanalyze.util.intf.IDependencyScanner;
import com.sgo.depanalyze.util.intf.IJarFileScanner;

/**
 * The Class BackendDependencyScanner.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:54 AM
 */
public class BackendDependencyScanner implements IDependencyScanner {
    //
    /** The logger. */
    private static Logger logger = Logger.getLogger(BackendDependencyScanner.class);
    /** The byte code repository. */
    private IByteCodeRepository byteCodeRepository = ByteCodeRepository.getInstance();

    public IByteCodeRepository getByteCodeRepository() {
        // if (byteCodeRepository == null) {
        // byteCodeRepository = ByteCodeRepository.getInstance();
        // }
        return byteCodeRepository;
    }

    public void setByteCodeRepository(IByteCodeRepository byteCodeRepository) {
        this.byteCodeRepository = byteCodeRepository;
    }

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
        /***/
        if (Modifier.isAbstract(sourceClazz.getModifiers())) {
            logger.warn("scanDependencies is ignoring for null abstract class:" + sourceClazz.getName());
            return new ClassLevelDependencyList(sourceClazz);
        }
        //
        List<Class<?>> implementedInterfaces = findImplementedHarmoniInterfaces(sourceClazz);
        List<Class<?>> superClasses = findSuperClasses(sourceClazz);
        List<Method> interfaceMethods = findAllInterfaceMethods(implementedInterfaces);
        //
        if (implementedInterfaces == null || implementedInterfaces.isEmpty()) {
            logger.warn("scanDependencies is ignoring class scan, no service interface found for class:" + sourceClazz.getName());
        }
        /***/
        // Map<Method, DependencyList> dependencyMapByMethod = new LinkedHashMap<Method, DependencyList>();
        List<MethodDependencies> dependencyListByMethod = new ArrayList<MethodDependencies>();
        // for each service method implementation, run scanDependencies for method
        for (Method interfaceMethod : interfaceMethods) {
            Method implementationMethod = null;
            try {
                implementationMethod = ClassUtils.getImplementationMethod(interfaceMethod, sourceClazz);
            } catch (RuntimeException e) {
                String logStr = String.format("RuntimeException, implementation method not found (method: %s, class: %s)", interfaceMethod.getName(), sourceClazz.getName());
                logger.error(logStr, e);
            }
            if (implementationMethod != null) {
                DependencyList methodDependResult = scanDependencies(implementationMethod);
                List<PlsqlDependency> plsqlDepList = methodDependResult.getPlsqlDependencyList();
                logger.debug(String.format("%d PL/SQL dependency found in %s.%s() service implementation", plsqlDepList.size(), sourceClazz.getName(), interfaceMethod.getName()));
                List<ServiceDependency> serviceDepList = methodDependResult.getServiceDependencyList();
                logger.debug(String.format("%d Service dependency found in %s.%s() service implementation", serviceDepList.size(), sourceClazz.getName(), interfaceMethod.getName()));
                List<NamedQueryDependency> nqDepList = methodDependResult.getNqDependencyList();
                logger.debug(String.format("%d NamedQuery dependency found in %s.%s() service implementation", nqDepList.size(), sourceClazz.getName(), interfaceMethod.getName()));
                // dependencyMapByMethod.put(implementationMethod, methodDependResult);
                // @SGO: 30.06.2014, method ve interface ilişkisi için değişiklik yapıldı
                MethodDependencies methodDependencies = new MethodDependencies(interfaceMethod, implementationMethod, methodDependResult);
                methodDependencies.setDigest(getMethodDigest(implementationMethod));
                dependencyListByMethod.add(methodDependencies);
            } else {
                String logStr = String.format("implementation method not found (method: %s, class: %s)", interfaceMethod.getName(), sourceClazz.getName());
                logger.warn(logStr);
            }
        }
        ClassLevelDependencyList clazzLevelDependency = new ClassLevelDependencyList();
        clazzLevelDependency.setImplementationClazz(sourceClazz);
        clazzLevelDependency.setImplementedInterfaces(implementedInterfaces);
        clazzLevelDependency.setSuperClasses(superClasses);
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
        DependencyList result = new DependencyList();
        try {
            result = scanDependencies(method, 1, new CallStack(), new ArrayList<Object>());
            // } catch (Throwable e) {
        } catch (Exception e) {
            logger.error(String.format("Exception in scanDependencies for %s.%s", method.getDeclaringClass().getName(), method.getName()), e);
        }
        return result;
    }

    private DependencyList scanDependencies(Constructor<?> ctor, int dependencyLevel, List<CallStackElement> callStack) {
        DependencyList dependencyResult = new DependencyList();
        if (ctor == null) {
            logger.error("scanDependencies is ignoring for null 'ctor' argument");
            return dependencyResult;
        }
        /***/
        if (Modifier.isAbstract(ctor.getModifiers())) {
            logger.error("scanDependencies is ignoring byte code scan for abstract constuctor: " + ctor.toString());
            return dependencyResult;
        }
        /***/
        Class<?> clazz = ctor.getDeclaringClass();
        if (clazz == null) {
            logger.error("scanDependencies is ignoring byte code scan, 'DeclaringClass' is null for " + ctor.toString());
            return dependencyResult;
        }
        return dependencyResult;
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
     * @param LDCStack
     *            the lDC stack
     * @return the dependency list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    private DependencyList scanDependencies(Method implementationMethod, int dependencyLevel, CallStack callStack, List<Object> LDCStack) {
        // if(implementationMethod.getDeclaringClass().getName().contains("com.ykb.hmn.limitriskmanagement.calculator.facade.LrmCalculatorFacade")
        // &&
        // implementationMethod.getName().contains("getProductComboInfo")){
        // @SuppressWarnings("unused")
        // int x = 0;
        // }
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
        Class<?> serviceClazz = implementationMethod.getDeclaringClass();
        if (serviceClazz == null) {
            logger.error("scanDependencies is ignoring byte code scan, 'DeclaringClass' is null for " + implementationMethod.toString());
            return dependencyResult;
        }
        /***/
        /** 18.02.2014 */
        IByteCodeAgent byteCodeAgent = getByteCodeAgent(serviceClazz);
        if (byteCodeAgent == null) {
            logger.error("scanDependencies ignoring byte code (byteCodeAgent not found) scan for class: " + serviceClazz.getName());
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
        // List<Instruction> instructionList = bcelUtil.getMethodInstructions(bcelMethod);
        List<InstructionWithLineNumber> instructionList = byteCodeAgent.getInstructionsWithLineNumbers(bcelMethod);
        if (instructionList == null || instructionList.isEmpty()) {
            logger.warn("scanDependencies will return with empty dependency list, no instruction found in method:" + implementationMethod.toString());
            return dependencyResult;
        }
        logger.trace("number of instructions:" + instructionList.size());
        for (int i = 0; i < instructionList.size(); i++) {
            int sourceLine = instructionList.get(i).getSourceLine();
            Instruction instruction = instructionList.get(i).getInstruction();
            // if (instruction instanceof InvokeInstruction) {
            // InvokeInstruction invokeInst = (InvokeInstruction) instruction;
            // String invokeTypeName = invokeInst.getReferenceType(constantPool).toString();
            // if (invokeTypeName.contains("IGeneralParametersService")) {
            // System.out.println("");
            // }
            // }
            /*
             * if (instruction instanceof InvokeInstruction) { InvokeInstruction invokeInst = (InvokeInstruction)
             * instruction; String invokeTypeName = invokeInst.getReferenceType(constantPool).toString(); String
             * invokeMethodName = invokeInst.getMethodName(constantPool); if("println".equals(invokeMethodName)){
             * System.out.println("println called in :"+implementationMethod.getDeclaringClass().getName()+"."+
             * implementationMethod.getName()); } else if("printStackTrace".equals(invokeMethodName)){
             * System.out.println("printStackTrace called in :"+implementationMethod.getDeclaringClass().getName()+"."+
             * implementationMethod.getName()); } }
             */
            if (instruction instanceof PUTSTATIC && !Modifier.isSynchronized(implementationMethod.getModifiers())) {
                PUTSTATIC ps = (PUTSTATIC)instruction;
//                logger.warn("PUTSTATIC:"+ps.getFieldName(theConstPoolGen) + "    signature: "+ps.getSignature(theConstPoolGen)+
//                        "  ReferenceType:"+ ps.getReferenceType(theConstPoolGen).getSignature());
                GlobalVariableUsage globalVarUsage = new GlobalVariableUsage();
                globalVarUsage.setUsageType(GlobalVariableUsage.UsageType.PUTSTATIC);
                globalVarUsage.setFieldName(ps.getFieldName(theConstPoolGen));
                globalVarUsage.setSignature(ps.getSignature(theConstPoolGen));
                globalVarUsage.setReferenceType(ps.getReferenceType(theConstPoolGen).toString());
                globalVarUsage.setDependencyLevel(dependencyLevel);
                DependencyPath dependencyPath = new DependencyPath(callStack.getElements());
                dependencyPath.addCallStackElement(new CallStackElement(implementationMethod, sourceLine));
                globalVarUsage.setDependencyPath(dependencyPath.toString());
                dependencyResult.addGlobalVariableUsage(globalVarUsage);
            } else if (instruction instanceof PUTFIELD && (dependencyLevel == 1 || Modifier.isStatic(implementationMethod.getModifiers()))) {
                boolean anomaliFound = false;
                PUTFIELD pf = (PUTFIELD)instruction;
                String fieldRefType = pf.getReferenceType(theConstPoolGen).toString();
                if (dependencyLevel == 1) {
                    // değişken servisin kendi değişkenidir
                    anomaliFound = fieldRefType.equals(implementationMethod.getDeclaringClass().getName());
                } /*else {
                    anomaliFound = Modifier.isStatic(implementationMethod.getModifiers());
                }*/
//                    logger.warn("PUTFIELD:"+pf.getFieldName(theConstPoolGen) + "    signature: "+pf.getSignature(theConstPoolGen)+
//                            "  ReferenceType:"+ pf.getReferenceType(theConstPoolGen).getSignature());
                if (anomaliFound) {
                    GlobalVariableUsage globalVarUsage = new GlobalVariableUsage();
                    globalVarUsage.setUsageType(GlobalVariableUsage.UsageType.PUTFIELD);
                    globalVarUsage.setFieldName(pf.getFieldName(theConstPoolGen));
                    globalVarUsage.setSignature(pf.getSignature(theConstPoolGen));
                    globalVarUsage.setReferenceType(fieldRefType);
                    globalVarUsage.setDependencyLevel(dependencyLevel);
                    DependencyPath dependencyPath = new DependencyPath(callStack.getElements());
                    dependencyPath.addCallStackElement(new CallStackElement(implementationMethod, sourceLine));
                    globalVarUsage.setDependencyPath(dependencyPath.toString());
                    dependencyResult.addGlobalVariableUsage(globalVarUsage);
                }
            } 
            if (isExecuteStoredProcInstruction(instruction, theConstPoolGen)) {
                Map<String, String> qeArgs = getExecuteStoredProcArgValues(instructionList, theConstPoolGen, i);
                /*
                 * Type[] argTypes = getArgumentTypes((InvokeInstruction) instruction, constantPool); Map<String,
                 * String> qeArgs = getExecuteStoredProcArgValues(LDCStack, argTypes.length == 3);
                 */
                if (qeArgs.get("proc") == null || qeArgs.get("proc").trim().isEmpty()) {
                    logger.warn(String.format("cannot find ExecuteStoredProc arguments for class:%s, method:%s", serviceClazz.getName(), implementationMethod.getName()));
                }
                DependencyPath dependencyPath = new DependencyPath(callStack.getElements());
                dependencyPath.addCallStackElement(new CallStackElement(implementationMethod, sourceLine));
                PlsqlDependency qeDep = new PlsqlDependency(qeArgs.get("pkg"), qeArgs.get("proc"), dependencyPath.toString(), dependencyLevel);
                dependencyResult.addDependency(qeDep);
                logger.debug(String.format("%s.%s() calling plsql package:%s proc:%s", serviceClazz.getName(), implementationMethod.getName(), qeDep.getPackageName(), qeDep.getProcedureName()));
            } else if (isNamedQueryInstruction(instruction, theConstPoolGen)) {
                String queryName = getNamedQueryArgValue(instructionList, theConstPoolGen, i);
                DependencyPath dependencyPath = new DependencyPath(callStack.getElements());
                dependencyPath.addCallStackElement(new CallStackElement(implementationMethod, sourceLine));
                NamedQueryDependency nqDep = new NamedQueryDependency(queryName, dependencyPath.toString(), dependencyLevel);
                dependencyResult.addDependency(nqDep);
                logger.debug(String.format("%s.%s() calling named query:%s", serviceClazz.getName(), implementationMethod.getName(), queryName));
            } else if (isDaoInstruction(instruction, theConstPoolGen)) {
                InvokeInstruction invokeIntst = (InvokeInstruction) instruction;
                String invokeType = invokeIntst.getReferenceType(theConstPoolGen).toString();
                String invokeMethodName = invokeIntst.getMethodName(theConstPoolGen);
                CRUD_FLAG crudFlag;
                if ("create".equals(invokeMethodName)) {
                    crudFlag = CRUD_FLAG.CREATE;
                } else if ("update".equals(invokeMethodName)) {
                    crudFlag = CRUD_FLAG.UPDATE;
                } else if ("delete".equals(invokeMethodName)) {
                    crudFlag = CRUD_FLAG.DELETE;
                } else {
                    crudFlag = CRUD_FLAG.READ;
                }
                DependencyPath dependencyPath = new DependencyPath(callStack.getElements());
                dependencyPath.addCallStackElement(new CallStackElement(implementationMethod, sourceLine));
                dependencyResult.addDependency(new HbmDaoDependency(invokeType, invokeMethodName, crudFlag, dependencyPath.toString(), dependencyLevel));
                logger.debug(String.format("%s.%s() calling DAO method: %s.%s", serviceClazz.getName(), implementationMethod.getName(), invokeType, invokeMethodName));
            } else if (ByteCodeUtils.isServiceIntfCall(serviceClazz, instruction, theConstPoolGen)) {
                InvokeInstruction invokeIntst = (InvokeInstruction) instruction;
                ReferenceType refType = invokeIntst.getReferenceType(theConstPoolGen);
                String invokeType = refType.toString();
                String invokeMethodName = invokeIntst.getMethodName(theConstPoolGen);
                // Type invokeReturnType = invokeIntf.getReturnType(constantPool);
                // Type[] argTypes = invokeIntf.getArgumentTypes(constantPool);
                String targetSignature = invokeIntst.getSignature(theConstPoolGen);
                DependencyPath dependencyPath = new DependencyPath(callStack.getElements());
                dependencyPath.addCallStackElement(new CallStackElement(implementationMethod, sourceLine));
                ServiceDependency serviceDep = new ServiceDependency(invokeType, invokeMethodName, targetSignature, dependencyPath.toString(), dependencyLevel);
                dependencyResult.addDependency(serviceDep);
                logger.debug(String.format("%s.%s() calling service interface: %s.%s()", serviceClazz.getName(), implementationMethod.getName(), invokeType, invokeMethodName));
            } else if (instruction instanceof org.apache.bcel.generic.LDC) {
                Object ldcValue = ((org.apache.bcel.generic.LDC) instruction).getValue(theConstPoolGen);
                LDCStack.add(ldcValue);
            } else {
                String invokeType = "";
                String invokeMethodName = "";
                Type[] invokeArgTypes = null;
                if (instruction instanceof InvokeInstruction) {
                    InvokeInstruction invokeInst = (InvokeInstruction) instruction;
                    ReferenceType refType = invokeInst.getReferenceType(theConstPoolGen);
                    invokeType = refType != null ? refType.toString() : "";
                    invokeMethodName = invokeInst.getMethodName(theConstPoolGen);
                    invokeArgTypes = invokeInst.getArgumentTypes(theConstPoolGen);
                }
                /*
                 * if(invokeType.contains("SgoUtil") && "<init>".equals(invokeMethodName)) { // TODO: handle constructor
                 * calls Class<?> invokeClazz = null; Class<?>[] parameterTypes = null; try { invokeClazz =
                 * FileSystemClassLoader.getClazz(invokeType); parameterTypes = (invokeArgTypes != null &&
                 * invokeArgTypes.length > 0) ? ByteCodeUtils.typesToClassArr(invokeArgTypes) : new Class[0]; } catch
                 * (ClassNotFoundException e) { String s = String.format(
                 * "scanDependencies is failed for class: %s method: %s ClassNotFoundException occured for InvokeInstruction(refType: %s, method:%s)"
                 * , serviceClazz.getName(), implementationMethod.getName(), invokeType, invokeMethodName);
                 * logger.error(s, e); } if (invokeClazz != null) { org.apache.bcel.classfile.Method m =
                 * bcelUtil.getMethod(invokeMethodName, signature); } }
                 */
                if (invokeType.startsWith(Constants.HMN_CLASS_PREFIX) && !(invokeType.startsWith(Constants.HMN_INF_CLASS_PREFIX) || invokeType.startsWith(Constants.HMN_INF_CLASS_PREFIX_2))
                        && !"<init>".equals(invokeMethodName)) {
                    // Harmoni class'ları analiz et, mimari class'ları analiz etme.
                    Class<?> invokeClazz = null;
                    Class<?>[] parameterTypes = null;
                    try {
                        invokeClazz = FileSystemClassLoader.loadClass(invokeType);
                        parameterTypes = ByteCodeUtils.typesToClassArr(invokeArgTypes);
                    } catch (ClassNotFoundException e) {
                        String s = String.format("scanDependencies is failed for class: %s method: %s ClassNotFoundException occured for InvokeInstruction(refType: %s, method:%s)",
                                serviceClazz.getName(), implementationMethod.getName(), invokeType, invokeMethodName);
                        logger.error(s, e);
                    }
                    /***/
                    Method invokedMethod = null;
                    if (invokeClazz != null && !(invokeClazz.isAnnotation() || invokeClazz.isEnum() || invokeClazz.isInterface())
                            && !(ClassUtils.isDaoIntf(invokeClazz) || ClassUtils.isDataModelIntf(invokeClazz) || ClassUtils.isDTOClazz(invokeClazz))) {
                        try {
                            invokedMethod = ClassUtils.getClazzMethod(invokeClazz, invokeMethodName, parameterTypes);
                        } catch (RuntimeException e) {
                            String logStr = String.format("RuntimeException, method not found (class: %s, method: %s, parameterTypes:%s)", invokeClazz.getName(), invokeMethodName,
                                    String.valueOf(parameterTypes));
                            logger.error(logStr, e);
                        }
                    } else {
                        logger.trace("ignoring instruction analyze for invoked class: " + invokeClazz);
                    }
                    /***/
                    if (invokedMethod != null && callStack.containsMethod(invokedMethod)) {
                        logger.info(String.format("scanDependencies is ignoring recursive instruction call (%s) in %s.%s()", invokedMethod.getName(), serviceClazz.getName(),
                                implementationMethod.getName()));
                    }
                    if (invokeClazz != null && invokedMethod != null && !callStack.containsMethod(invokedMethod) && !Modifier.isAbstract(invokedMethod.getModifiers())) {
                        // !callStack.contains(invokedMethod) kontrolü recursive çağrımları tekrar analiz etmemek için
                        // eklendi
                        CallStack newCallStack = new CallStack(callStack.getElements());
                        newCallStack.addElement(new CallStackElement(implementationMethod, sourceLine));
                        DependencyList invokeDepList = scanDependencies(invokedMethod, dependencyLevel + 1, newCallStack, LDCStack);
                        List<NamedQueryDependency> nqDepList = dependencyResult.getNqDependencyList();
                        nqDepList.addAll(invokeDepList.getNqDependencyList());
                        dependencyResult.setNqDependencyList(nqDepList);
                        //
                        List<PlsqlDependency> plsqlDepList = dependencyResult.getPlsqlDependencyList();
                        plsqlDepList.addAll(invokeDepList.getPlsqlDependencyList());
                        dependencyResult.setPlsqlDependencyList(plsqlDepList);
                        //
                        List<ServiceDependency> serviceDepList = dependencyResult.getServiceDependencyList();
                        serviceDepList.addAll(invokeDepList.getServiceDependencyList());
                        dependencyResult.setServiceDependencyList(serviceDepList);
                        //
                        List<HbmDaoDependency> hbmDaoDepList = dependencyResult.getHbmDaoDependencyList();
                        hbmDaoDepList.addAll(invokeDepList.getHbmDaoDependencyList());
                        dependencyResult.setHbmDaoDependencyList(hbmDaoDepList);
                        //
                        List<GlobalVariableUsage> globalUsageList = dependencyResult.getGlobalUsageList();
                        globalUsageList.addAll(invokeDepList.getGlobalUsageList());
                        dependencyResult.setGlobalUsageList(globalUsageList);
                    }
                }
            }
        }
        return dependencyResult;
    }

    /**
     * Checks if is execute stored proc instruction.
     * 
     * @param instruction
     *            the instruction
     * @param theCPool
     *            the the c pool
     * @return true, if is execute stored proc instruction
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    private boolean isExecuteStoredProcInstruction(Instruction instruction, ConstantPoolGen theCPool) {
        boolean result = false;
        if (instruction instanceof InvokeInstruction) {
            InvokeInstruction invokeInst = (InvokeInstruction) instruction;
            String invokeTypeName = invokeInst.getReferenceType(theCPool).toString();
            String invokeMethodName = invokeInst.getMethodName(theCPool);
            try {
                Class<?> invokeClazz = FileSystemClassLoader.loadClass(invokeTypeName);
                Class<?> class_ILegacyServiceLocator = ClassUtils.getILegacyServiceLocatorClazz();
                if (class_ILegacyServiceLocator.isAssignableFrom(invokeClazz)) {
                    return true;
                }
            } catch (ClassNotFoundException e) {
                logger.error("ClassNotFoundException in isExecuteStoredProcInstruction, invoked type: " + invokeTypeName, e);
            }
            Type[] argTypes = invokeInst.getArgumentTypes(theCPool);
            if (invokeTypeName.equals(Constants.CLASS_QUERYEXECUTOR) && invokeMethodName.equals("executeStoredProcedure") && argTypes != null && argTypes.length > 0) {
                result = true;
            }
        }
        return result;
    }

    private boolean isDaoInstruction(Instruction instruction, ConstantPoolGen theCPool) {
        boolean result = false;
        if (instruction instanceof InvokeInstruction) {
            InvokeInstruction invokeInst = (InvokeInstruction) instruction;
            String invokeTypeName = invokeInst.getReferenceType(theCPool).toString();
            try {
                Class<?> invokeClazz = FileSystemClassLoader.loadClass(invokeTypeName);
                return ClassUtils.isDaoIntf(invokeClazz);
            } catch (ClassNotFoundException e) {
                logger.error("ClassNotFoundException in isDaoInstruction, invoked type: " + invokeTypeName, e);
            }
        }
        return result;
    }

    /**
     * Checks if is named query instruction.
     * 
     * @param instruction
     *            the instruction
     * @param theCPool
     *            the the c pool
     * @return true, if is named query instruction
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    private boolean isNamedQueryInstruction(Instruction instruction, ConstantPoolGen theCPool) {
        boolean result = false;
        if (instruction instanceof InvokeInstruction) {
            InvokeInstruction invokeInst = (InvokeInstruction) instruction;
            String invokeTypeName = invokeInst.getReferenceType(theCPool).toString();
            String invokeMethodName = invokeInst.getMethodName(theCPool);
            try {
                Class<?> invokeClazz = FileSystemClassLoader.loadClass(invokeTypeName);
                Class<?> class_ISQLQuery = ClassUtils.getISQLQueryClazz();
                if (class_ISQLQuery.isAssignableFrom(invokeClazz)) {
                    return true;
                }
            } catch (ClassNotFoundException e) {
                logger.error("ClassNotFoundException in isNamedQueryInstruction, invoked type: " + invokeTypeName, e);
            }
            Type[] argTypes = invokeInst.getArgumentTypes(theCPool);
            if (invokeTypeName.equals(Constants.CLASS_QUERYEXECUTOR)
                    && ("listWithDynamicQuery".equals(invokeMethodName) || "executeUpdate".equals(invokeMethodName) || "list".equals(invokeMethodName)) && argTypes != null && argTypes.length > 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Gets the execute stored proc arg values.
     * <p>
     * </p>
     * 
     * @param ldcStack
     *            the ldc stack
     * @param isPackageCall
     *            the is package call
     * @return the execute stored proc arg values
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    private Map<String, String> getExecuteStoredProcArgValues(List<Object> ldcStack, boolean isPackageCall) {
        // TODO: Alttaki gibi alternatif kodlarda, stack'te 2 tane olmasına karşın, stack'deki en üstteki package
        // olmasına rağmen procedure'e bağlıyor
        /*
         * if (!StringUtils.hasText(packageName)) found = queryExecutor.executeStoredProcedure(procedureName,
         * transformer.prepare()); else { found = queryExecutor.executeStoredProcedure(procedureName, packageName,
         * transformer.prepare()); }
         */
        String pkgName = " ";
        String procName = " ";
        int index = ldcStack.size() - 1;
        boolean found = false;
        int str_LDC_count = 0;
        while (!found && index >= 0) {
            Object ldcValue = ldcStack.get(index);
            if (ldcValue instanceof String) {
                if (isPackageCall && str_LDC_count == 0) {
                    pkgName = ldcValue.toString();
                    str_LDC_count++;
                } else {
                    procName = ldcValue.toString();
                    found = true;
                }
            }
            index = index - 1;
        }
        Map<String, String> result = new HashMap<String, String>();
        result.put("pkg", pkgName.toUpperCase());
        result.put("proc", procName.toUpperCase());
        return result;
    }

    /**
     * Gets the execute stored proc arg values.
     * <p>
     * </p>
     * 
     * @param instructionList
     *            the instruction list
     * @param theCPool
     *            the the c pool
     * @param indexOfQueryExec
     *            the index of query exec
     * @return the execute stored proc arg values
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    private Map<String, String> getExecuteStoredProcArgValues(List<InstructionWithLineNumber> instructionList, ConstantPoolGen theCPool, int indexOfQueryExec) {
        Map<String, String> result = new HashMap<String, String>();
        InvokeInstruction invokeInst = (InvokeInstruction) instructionList.get(indexOfQueryExec).getInstruction();
        String invokeTypeName = invokeInst.getReferenceType(theCPool).toString();
        try {
            Class<?> invokeClazz = FileSystemClassLoader.loadClass(invokeTypeName);
            Class<?> class_ILegacyServiceLocator = ClassUtils.getILegacyServiceLocatorClazz();
            if (class_ILegacyServiceLocator.isAssignableFrom(invokeClazz)) {
                result.put("proc", invokeInst.getMethodName(theCPool).toUpperCase());
                if (invokeClazz.getDeclaringClass() != null && class_ILegacyServiceLocator.isAssignableFrom(invokeClazz.getDeclaringClass())) {
                    // invoking package + procedure
                    result.put("pkg", invokeClazz.getSimpleName().replaceFirst(Constants.Legacy_ServiceLocator_Pkg_Prefix, "").toUpperCase());
                }
                return result;
            }
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException in getExecuteStoredProcArgValues, invoked type: " + invokeTypeName, e);
        }
        Type[] argTypes = invokeInst.getArgumentTypes(theCPool);
        org.apache.bcel.generic.LDC ldcProc = null;
        org.apache.bcel.generic.LDC ldcPkg = null;
        boolean found = false;
        int index = indexOfQueryExec;
        // find first LDC command before executeStoredProcedure instruction
        while (!found && index > 0) {
            index = index - 1;
            if (instructionList.get(index).getInstruction() instanceof org.apache.bcel.generic.LDC) {
                found = true;
            }
        }
        if (found) {
            if (argTypes.length == 3 && argTypes[1].toString().equals(String.class.getName())) {
                // executeStoredProcedure(String procedureName, String packageName, Map<String, Object> inputParameters)
                if (instructionList.get(index).getInstruction() instanceof org.apache.bcel.generic.LDC) {
                    ldcPkg = (org.apache.bcel.generic.LDC) instructionList.get(index).getInstruction();
                }
                if (instructionList.get(index - 1).getInstruction() instanceof org.apache.bcel.generic.LDC) {
                    ldcProc = (org.apache.bcel.generic.LDC) instructionList.get(index - 1).getInstruction();
                }
            } else if (argTypes.length == 2 && argTypes[0].toString().equals(String.class.getName())) {
                // executeStoredProcedure(String procedureName, Map<String, Object> inputParameters)
                if (instructionList.get(index).getInstruction() instanceof org.apache.bcel.generic.LDC) {
                    ldcProc = (org.apache.bcel.generic.LDC) instructionList.get(index).getInstruction();
                }
            }
        }
        /*
         * if (argTypes.length == 3 && argTypes[1].toString().equals(String.class.getName())) { //
         * executeStoredProcedure(String procedureName, String packageName, Map<String, Object> inputParameters) if
         * (instructionList.get(indexOfQueryExec - 3) instanceof org.apache.bcel.generic.LDC) { ldcProc =
         * (org.apache.bcel.generic.LDC) instructionList.get(indexOfQueryExec - 3); } if
         * (instructionList.get(indexOfQueryExec - 2) instanceof org.apache.bcel.generic.LDC) { ldcPkg =
         * (org.apache.bcel.generic.LDC) instructionList.get(indexOfQueryExec - 2); } } else if (argTypes.length == 2 &&
         * argTypes[0].toString().equals(String.class.getName())) {ş // executeStoredProcedure(String procedureName,
         * Map<String, Object> inputParameters) if (instructionList.get(indexOfQueryExec - 2) instanceof
         * org.apache.bcel.generic.LDC) { ldcProc = (org.apache.bcel.generic.LDC) instructionList.get(indexOfQueryExec -
         * 2); } }
         */
        String pkgName = ldcPkg != null ? ldcPkg.getValue(theCPool).toString() : " ";
        String procName = ldcProc != null ? ldcProc.getValue(theCPool).toString() : " ";
        if ((pkgName == null || pkgName.trim().isEmpty()) && procName != null && procName.contains(".")) {
            String[] splitArr = procName.split("\\.");
            pkgName = splitArr[0];
            procName = splitArr[1];
        }
        result.put("pkg", pkgName.toUpperCase());
        result.put("proc", procName.toUpperCase());
        return result;
    }

    /**
     * Gets the named query arg value.
     * <p>
     * </p>
     * 
     * @param instructionList
     *            the instruction list
     * @param theCPool
     *            the the c pool
     * @param indexOfQueryExec
     *            the index of query exec
     * @return the named query arg value
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    private String getNamedQueryArgValue(List<InstructionWithLineNumber> instructionList, ConstantPoolGen theCPool, int indexOfQueryExec) {
        InvokeInstruction invokeInst = (InvokeInstruction) instructionList.get(indexOfQueryExec).getInstruction();
        String invokeTypeName = invokeInst.getReferenceType(theCPool).toString();
        try {
            Class<?> invokeClazz = FileSystemClassLoader.loadClass(invokeTypeName);
            Class<?> class_ISQLQuery = ClassUtils.getISQLQueryClazz();
            if (class_ISQLQuery.isAssignableFrom(invokeClazz)) {
                return invokeInst.getMethodName(theCPool);
            }
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException in getNamedQueryArgValue, invoked type: " + invokeTypeName, e);
        }
        // INVOKEVIRTUAL invokeVirt = (INVOKEVIRTUAL) instructionList.get(indexOfQueryExec);
        // Type[] argTypes = invokeVirt.getArgumentTypes(theCPool);
        org.apache.bcel.generic.LDC ldcNamedQuery = null;
        boolean found = false;
        int index = indexOfQueryExec;
        // find previous LDC command before "list" or "listWithDynamicQuery" instruction
        while (!found && index > 0) {
            index = index - 1;
            if (instructionList.get(index).getInstruction() instanceof org.apache.bcel.generic.LDC) {
                found = true;
            }
        }
        if (found && instructionList.get(index).getInstruction() instanceof org.apache.bcel.generic.LDC) {
            ldcNamedQuery = (org.apache.bcel.generic.LDC) instructionList.get(index).getInstruction();
        }
        String nqName = ldcNamedQuery != null ? ldcNamedQuery.getValue(theCPool).toString() : " ";
        return nqName;
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
        result.setPomProperties(jarEntries.getPomProperties());
        result.setClassLevelDependencies(dependencyResult);
        result.setDaoTableMappingList(jarEntries.getDaoTableMappingList());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.IDependencyScanner#scanDependencies(java.lang.String)
     */
    @Override
    public DependencyScanResult scanDependencies(String jarFile) {
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
        result.setPomProperties(jarEntries.getPomProperties());
        result.setClassLevelDependencies(dependencyResult);
        result.setDaoTableMappingList(jarEntries.getDaoTableMappingList());
        return result;
    }

    /**
     * Find implemented harmoni interfaces.
     * 
     * @param cl
     *            the cl
     * @return the list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 12:31:26 PM
     */
    private List<Class<?>> findImplementedHarmoniInterfaces(Class<?> cl) {
        List<Class<?>> result = new ArrayList<Class<?>>();
        List<Class<?>> implementedInterfaces = ClassUtils.findImplementedInterfaces(cl);
        if (!implementedInterfaces.isEmpty()) {
            for (Class<?> intfClazz : implementedInterfaces) {
                if (ClassUtils.isHarmoniInterface(intfClazz)) {
                    result.add(intfClazz);
                }
            }
        }
        return result;
    }

    /**
     * Find super classes.
     * 
     * @param cl
     *            the cl
     * @return the list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 3, 2013 9:11:37 AM
     */
    private List<Class<?>> findSuperClasses(Class<?> cl) {
        List<Class<?>> superClasses = ClassUtils.findSuperClasses(cl);
        return superClasses;
    }

    /**
     * Find all interface methods.
     * 
     * @param implementedInterfaces
     *            the implemented interfaces
     * @return the list
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 12:31:16 PM
     */
    private List<Method> findAllInterfaceMethods(List<Class<?>> implementedInterfaces) {
        List<Method> interfaceMethods = new ArrayList<Method>();
        if (implementedInterfaces.isEmpty()) {
            return interfaceMethods;
        }
        for (Class<?> intfClazz : implementedInterfaces) {
            interfaceMethods.addAll(Arrays.asList(intfClazz.getDeclaredMethods()));
        }
        return interfaceMethods;
    }
    
    /**
     * Gets the method digest.
     * 
     * @param method
     *            the method
     * @return the method digest
     * @author Selçuk Giray ÖZDAMAR
     * @since May 25, 2016
     */
    private String getMethodDigest(Method method) {
        Class<?> clazz = method.getDeclaringClass();
        IByteCodeAgent byteCodeAgent = getByteCodeAgent(clazz);
        org.apache.bcel.classfile.Method bcelMethod = byteCodeAgent.getBcelMethod(method);
        ConstantPoolGen theConstPoolGen = byteCodeAgent.getConstantPoolGen();
        List<InstructionWithLineNumber> instructionList = byteCodeAgent.getInstructionsWithLineNumbers(bcelMethod);
        //
        try {
            StringBuilder sbMethodContent = new StringBuilder(method.toString() + "\n");
            sbMethodContent.append(instructionListToStr(instructionList, theConstPoolGen));
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.update(sbMethodContent.toString().getBytes());
            md.update(sbMethodContent.toString().getBytes("UTF-8"));   // must specify "UTF-8" encoding
            byte[] mdbytes = md.digest();
            // convert the byte to hex format
            StringBuffer sb = new StringBuffer("");
//            for (int i = 0; i < mdbytes.length; i++) {
//                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
//            }
//            return sb.toString();            
            // Use Base64 encoding here -->
            String hashed = org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(mdbytes);           
            return hashed;
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException getMethodDigest failed for class: " + clazz.getName() + " and method:" + method.getName(), e);
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException getMethodDigest failed for class: " + clazz.getName() + " and method:" + method.getName(), e);
        }
        return "";
    }

    private static String instructionListToStr(List<InstructionWithLineNumber> instructionList, ConstantPoolGen theConstPoolGen) {
        if (instructionList == null || instructionList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("");
        for (InstructionWithLineNumber instruction : instructionList) {
            sb.append(instruction.getInstruction().toString(theConstPoolGen.getConstantPool()) + "\n");
        }
        return sb.toString();
    }
    
    /**
     * Gets the byte code agent.
     * 
     * @param clazz
     *            the clazz
     * @return the byte code agent
     * @author Selçuk Giray ÖZDAMAR
     * @since May 25, 2016
     */
    private IByteCodeAgent getByteCodeAgent(Class<?> clazz) {
        if (clazz == null) {
            logger.error("getByteCodeAgent failed. 'clazz' is null");
            return null;
        }
        /***/
        /** 18.02.2014 */
        IByteCodeAgent byteCodeAgent = null;
        try {
            byteCodeAgent = this.byteCodeRepository.loadByteCodeAgent(clazz);
        } catch (ClassNotFoundException e) {
            logger.error("getByteCodeAgent failed for class: " + clazz.getName(), e);
        }
        return byteCodeAgent;
    }
    
    
    
}