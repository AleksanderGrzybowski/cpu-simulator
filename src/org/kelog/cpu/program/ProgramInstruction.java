package org.kelog.cpu.program;

import org.kelog.cpu.instruction.Instruction;

public class ProgramInstruction {
    public String label;
    public Instruction instruction;
    
    public ProgramInstruction(String label, Instruction instruction) {
        this.label = label;
        this.instruction = instruction;
    }
    
    public ProgramInstruction( Instruction instruction) {
        this(null, instruction);
    }
}
