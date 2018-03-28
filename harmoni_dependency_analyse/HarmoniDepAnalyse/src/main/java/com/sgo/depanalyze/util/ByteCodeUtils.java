package com.sgo.depanalyze.util;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.generic.ArrayType;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.InvokeInstruction;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.ObjectType;
import org.apache.log4j.Logger;

import com.sgo.depanalyze.datatypes.InstructionWithLineNumber;
import com.sgo.depanalyze.util.impl.ServiceRegistry;

/**
 * The Class ByteCodeUtils.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 * @since Nov 11, 2013 9:46:54 AM
 */
public class ByteCodeUtils {
    /** The clazz. */
    private Class<?> clazz = null;
    /** The java clazz. */
    private JavaClass javaClazz = null;
    /** The class gen. */
    private ClassGen theClassGen = null;
    /** The c pool. */
    private ConstantPoolGen theCPool = null;
    /** The logger. */
    static Logger logger = Logger.getLogger(ByteCodeUtils.class);

    /**
     * Gets the constant pool.
     * <p>
     * </p>
     * 
     * @return the constant pool
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public ConstantPoolGen getConstantPool() {
        return theCPool;
    }

    /**
     * Instantiates a new byte code utils.
     * 
     * @param clazz
     *            the clazz
     * @throws ClassNotFoundException
     *             the class not found exception
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public ByteCodeUtils(Class<?> clazz) throws ClassNotFoundException {
        this.clazz = clazz;
        // Repository.clearCache();
        this.javaClazz = Repository.lookupClass(clazz);
        this.theClassGen = new ClassGen(this.javaClazz); // Initialize with existing class.
        this.theCPool = this.theClassGen.getConstantPool();
    }

    /**
     * Gets the method.
     * <p>
     * </p>
     * 
     * @param method
     *            the arg0
     * @return the method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public org.apache.bcel.classfile.Method getMethod(Method method) {
        // if(method.getName().contains("updateCUForm")){
        // int x = 0;
        // }
        return javaClazz.getMethod(method);
    }

    /**
     * Gets the method with name and signature
     * <p>
     * </p>
     * 
     * @param name
     *            the name
     * @param signature
     *            the signature
     * @return the method
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Dec 10, 2013 5:58:50 PM
     */
    public org.apache.bcel.classfile.Method getMethod(String name, String signature) {
        return theClassGen.containsMethod(name, signature);
    }

    /**
     * Gets the methods.
     * <p>
     * </p>
     * 
     * @return the methods
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public List<org.apache.bcel.classfile.Method> getMethods() {
        return Arrays.asList(javaClazz.getMethods());
    }

    /**
     * Gets the method instructions.
     * <p>
     * </p>
     * 
     * @param method
     *            the method
     * @return the method instructions
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public List<Instruction> getMethodInstructions(org.apache.bcel.classfile.Method method) {
        MethodGen methodGen = new MethodGen(method, theClassGen.getClassName(), theCPool);
        InstructionList instList = methodGen.getInstructionList();
        if (instList == null || instList.getInstructions() == null || instList.getInstructions().length == 0) {
            return null;
        }
        return Arrays.asList(instList.getInstructions());
    }

    /**
     * Gets the instructions.
     * 
     * @param method
     *            the method
     * @return the instructions
     */
    public List<InstructionWithLineNumber> getInstructionsWithLineNumbers(org.apache.bcel.classfile.Method method) {
        MethodGen methodGen = new MethodGen(method, theClassGen.getClassName(), theCPool);
        InstructionList instructionList = methodGen.getInstructionList();
        if (instructionList == null || instructionList.getInstructionHandles() == null || instructionList.getInstructionHandles().length == 0) {
            return null;
        }
        InstructionHandle[] instructionHandles = instructionList.getInstructionHandles();
        List<InstructionWithLineNumber> result = new ArrayList<InstructionWithLineNumber>(instructionHandles.length);
        LineNumberTable lineNumberTable = methodGen.getLineNumberTable(theCPool);
        for (org.apache.bcel.generic.InstructionHandle instructionHandle : instructionHandles) {
            result.add(new InstructionWithLineNumber(instructionHandle, lineNumberTable));
        }
        return result;
    }

    public LineNumberTable getLineNumberTable(org.apache.bcel.classfile.Method method) {
        MethodGen methodGen = new MethodGen(method, theClassGen.getClassName(), theCPool);
        return methodGen.getLineNumberTable(theCPool);
    }

    /**
     * Gets the method signature.
     * <p>
     * </p>
     * 
     * @param method
     *            the method
     * @return the method signature
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public String getMethodSignature(org.apache.bcel.classfile.Method method) {
        return method.getSignature();
    }

    /**
     * Checks if is service intf call.
     * 
     * @param i
     *            the i
     * @return true, if is service intf call
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public boolean isServiceIntfCall(Instruction i) {
        return isServiceIntfCall(clazz, i, theCPool);
        /*
         * if (i instanceof INVOKEINTERFACE) { INVOKEINTERFACE invokeIntf = (INVOKEINTERFACE) i; ReferenceType refType =
         * invokeIntf.getReferenceType(theCPool); String invokeType = refType.toString(); try { Class<?> iServiceClazz =
         * ClassUtils.getIServiceClass(clazz.getClassLoader()); Class<?> invokeClazz = Class.forName(invokeType, true,
         * clazz.getClassLoader()); return (invokeClazz.isInterface() && iServiceClazz.isAssignableFrom(invokeClazz));
         * // org.apache.bcel.generic.Type t = Type.getType(iServiceKlass); // // if (refType.isCastableTo(t)) { // // }
         * } catch (Throwable e) { Logger.getLogger(ByteCodeUtils.class).error(String.format(
         * "isServiceIntfCall failed for instruction:%s, and class:%s", i.getName(), clazz.getName()), e); } } return
         * false;
         */
    }

    /**
     * Checks if is service intf call.
     * 
     * @param sourceClazz
     *            the source clazz
     * @param inst
     *            the i
     * @param constantPool
     *            the constant pool
     * @return true, if is service intf call
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static boolean isServiceIntfCall(Class<?> sourceClazz, Instruction inst, ConstantPoolGen constantPool) {
        boolean result = false;
        if (inst instanceof InvokeInstruction) {
            InvokeInstruction invokeInst = (InvokeInstruction) inst;
            String invokeType = invokeInst.getReferenceType(constantPool).toString();
            try {
                // Class<?> invokeClazz = Class.forName(invokeType, true, sourceClazz.getClassLoader());
                Class<?> invokeClazz = FileSystemClassLoader.loadClass(invokeType);
                return ClassUtils.isServiceIntf(invokeClazz) || ServiceRegistry.getInstance().isServiceInterface(invokeClazz);
                // TODO @SGO: 24.12.2013 comment edildi
                // Class<?> class_IService = ClassUtils.getIServiceClazz();
                // return (invokeClazz.isInterface() && class_IService.isAssignableFrom(invokeClazz));
                /***/
                // org.apache.bcel.generic.Type t = Type.getType(iServiceKlass);
                // // if (refType.isCastableTo(t)) {
                // // }
            } catch (Exception e) {
                String errLog = String.format("Exception, instruction: %s, and class:%s", inst.toString(constantPool.getConstantPool()), sourceClazz.getName());
                logger.error(errLog, e);
            }
        }
        return result;
    }

    /**
     * Type to class.
     * 
     * @param type
     *            the type
     * @return the class
     * @throws ClassNotFoundException
     *             the class not found exception
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static Class<?> typeToClass(org.apache.bcel.generic.Type type) throws ClassNotFoundException {
        Class<?> argType = null;
        if (type instanceof ObjectType) {
            argType = FileSystemClassLoader.loadClass(((ObjectType) type).getClassName());
        } else if (type instanceof ArrayType) {
            Class<?> elementType = FileSystemClassLoader.loadClass(((ArrayType) type).getBasicType().toString());
            argType = Array.newInstance(elementType, 0).getClass();
        } else {
            argType = FileSystemClassLoader.loadClass(type.toString());
        }
        return argType;
    }

    /**
     * Types to class arr.
     * 
     * @param types
     *            the types
     * @return the class[]
     * @throws ClassNotFoundException
     *             the class not found exception
     * @author U065352-Selçuk Giray ÖZDAMAR
     * @since Nov 11, 2013 9:46:54 AM
     */
    public static Class<?>[] typesToClassArr(org.apache.bcel.generic.Type[] types) throws ClassNotFoundException {
        if (types == null || types.length == 0) {
            return null;
        }
        Class<?>[] clazzArr = new Class<?>[types.length];
        for (int index = 0; index < types.length; index++) {
            org.apache.bcel.generic.Type t = types[index];
            Class<?> argType = typeToClass(t);
            clazzArr[index] = argType;
        }
        return clazzArr;
    }
}
