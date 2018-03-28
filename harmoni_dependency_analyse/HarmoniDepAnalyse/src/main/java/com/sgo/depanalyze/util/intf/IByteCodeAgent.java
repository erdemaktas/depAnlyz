package com.sgo.depanalyze.util.intf;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;

import com.sgo.depanalyze.datatypes.InstructionWithLineNumber;

public interface IByteCodeAgent {
    JavaClass getJavaClazz();

    ClassGen getClassGen();

    ConstantPoolGen getConstantPoolGen();

    ConstantPool getConstantPool();

    org.apache.bcel.classfile.Method getBcelMethod(Method method);

    org.apache.bcel.classfile.Method getBcelMethod(String name, String signature);

    List<org.apache.bcel.classfile.Method> getBcelMethods();

    List<org.apache.bcel.generic.Instruction> getInstructions(org.apache.bcel.classfile.Method bcelMethod);

    List<InstructionWithLineNumber> getInstructionsWithLineNumbers(org.apache.bcel.classfile.Method bcelMethod);

    org.apache.bcel.classfile.LineNumberTable getLineNumberTable(org.apache.bcel.classfile.Method bcelMethod);
}
