package org.kelog.cpu.program;

import org.kelog.cpu.instruction.Instruction;

class ProgramInstruction {
    final String label;
    final Instruction instruction;
    
    ProgramInstruction(String label, Instruction instruction) {
        this.label = label;
        this.instruction = instruction;
    }
}
