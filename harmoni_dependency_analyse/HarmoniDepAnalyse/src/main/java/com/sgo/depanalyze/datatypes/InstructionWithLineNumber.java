package com.sgo.depanalyze.datatypes;

import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;

/**
 * The Class InstructionWithLineNumber.
 * 
 * @author U065352-Selçuk Giray ÖZDAMAR
 */
public class InstructionWithLineNumber {
    /** The instruction handle. */
    private InstructionHandle instructionHandle;
    /** The source line number. */
    private int sourceLine;


    /**
     * The Constructor.
     *
     * @param instructionHandle the instruction handle
     * @param lineNumberTable the line number table
     */
    public InstructionWithLineNumber(InstructionHandle instructionHandle, LineNumberTable lineNumberTable) {
        this.instructionHandle = instructionHandle;
        this.sourceLine = extractLineNumber(lineNumberTable);
    }

    /**
     * Gets the instruction handle.
     * 
     * @return the instruction handle
     */
    public InstructionHandle getInstructionHandle() {
        return instructionHandle;
    }

    /**
     * Gets the instruction.
     * 
     * @return the instruction
     */
    public Instruction getInstruction() {
        return instructionHandle.getInstruction();
    }

    /**
     * Gets the line number.
     * 
     * @return the line number
     */
    public int getSourceLine() {
        return sourceLine;
    }

    // /**
    // * Extract line number.
    // *
    // * @return the int
    // */
    // private int extractLineNumber() {
    // int position = instructionHandle.getPosition();
    // InstructionTargeter[] instTargeters = instructionHandle.getTargeters();
    // if (instTargeters == null || instTargeters.length == 0) {
    // return -1;
    // }
    // for (InstructionTargeter targeter : instTargeters) {
    // if (targeter instanceof LineNumberGen) {
    // return ((LineNumberGen) targeter).getLineNumber().getLineNumber();
    // }
    // }
    // return -1;
    // }
    private int extractLineNumber(LineNumberTable lineNumberTable) {
        return lineNumberTable.getSourceLine(instructionHandle.getPosition());
    }
}
