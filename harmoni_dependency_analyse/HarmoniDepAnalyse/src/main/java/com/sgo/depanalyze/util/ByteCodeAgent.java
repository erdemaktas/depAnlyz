package com.sgo.depanalyze.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.log4j.Logger;

import com.sgo.depanalyze.datatypes.InstructionWithLineNumber;
import com.sgo.depanalyze.util.intf.IByteCodeAgent;

/**
 * The Class ByteCodeAgent.
 * 
 * @author U065352 - SELÇUK GİRAY ÖZDAMAR
 * @since Feb 18, 2014 9:02:29 PM
 */
public class ByteCodeAgent implements IByteCodeAgent {
    /** The java clazz. */
    private JavaClass javaClazz = null;
    /** The class gen. */
    private ClassGen theClassGen = null;
    /** The c pool. */
    private ConstantPoolGen theCPool = null;
    /** The logger. */
    static Logger logger = Logger.getLogger(ByteCodeAgent.class);

    /**
     * Instantiates a new byte code agent.
     * 
     * @param javaClazz
     *            the java clazz
     * @author U065352 - SELÇUK GİRAY ÖZDAMAR
     * @since Feb 18, 2014 9:02:29 PM
     */
    public ByteCodeAgent(JavaClass javaClazz) {
        if (javaClazz == null) {
            throw new NullPointerException("JavaClass cannot be null");
        }
        this.javaClazz = javaClazz;
        this.theClassGen = new ClassGen(this.javaClazz); // Initialize with existing class.
        this.theCPool = this.theClassGen.getConstantPool();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IByteCodeAgent#getBcelMethod(java.lang.reflect.Method)
     */
    @Override
    public Method getBcelMethod(java.lang.reflect.Method method) {
        return javaClazz.getMethod(method);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IByteCodeAgent#getBcelMethod(java.lang.String, java.lang.String)
     */
    @Override
    public Method getBcelMethod(String name, String signature) {
        return theClassGen.containsMethod(name, signature);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IByteCodeAgent#getBcelMethods()
     */
    @Override
    public List<Method> getBcelMethods() {
        return Arrays.asList(javaClazz.getMethods());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IByteCodeAgent#getInstructions(org.apache.bcel.classfile.Method)
     */
    @Override
    public List<Instruction> getInstructions(Method bcelMethod) {
        MethodGen methodGen = new MethodGen(bcelMethod, theClassGen.getClassName(), theCPool);
        InstructionList instList = methodGen.getInstructionList();
        if (instList == null || instList.getInstructions() == null || instList.getInstructions().length == 0) {
            return Collections.emptyList();
        }
        return Arrays.asList(instList.getInstructions());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IByteCodeAgent#getInstructionsWithLineNumbers(org.apache.bcel.classfile.Method)
     */
    @Override
    public List<InstructionWithLineNumber> getInstructionsWithLineNumbers(Method bcelMethod) {
        MethodGen methodGen = new MethodGen(bcelMethod, theClassGen.getClassName(), theCPool);
        InstructionList instructionList = methodGen.getInstructionList();
        if (instructionList == null || instructionList.getInstructionHandles() == null || instructionList.getInstructionHandles().length == 0) {
            return Collections.emptyList();
        }
        InstructionHandle[] instructionHandles = instructionList.getInstructionHandles();
        List<InstructionWithLineNumber> result = new ArrayList<InstructionWithLineNumber>(instructionHandles.length);
        LineNumberTable lineNumberTable = methodGen.getLineNumberTable(theCPool);
        for (org.apache.bcel.generic.InstructionHandle instructionHandle : instructionHandles) {
            result.add(new InstructionWithLineNumber(instructionHandle, lineNumberTable));
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sgo.depanalyze.util.intf.IByteCodeAgent#getLineNumberTable(org.apache.bcel.classfile.Method)
     */
    @Override
    public LineNumberTable getLineNumberTable(Method bcelMethod) {
        MethodGen methodGen = new MethodGen(bcelMethod, theClassGen.getClassName(), theCPool);
        return methodGen.getLineNumberTable(theCPool);
    }

    @Override
    public JavaClass getJavaClazz() {
        return this.javaClazz;
    }

    @Override
    public ClassGen getClassGen() {
        return this.theClassGen;
    }

    @Override
    public ConstantPoolGen getConstantPoolGen() {
        return this.theCPool;
    }

    @Override
    public ConstantPool getConstantPool() {
        return this.theCPool.getConstantPool();
    }
}
