package org.kelog.cpu.program;

import org.kelog.cpu.instruction.Instruction;

import java.util.ArrayList;
import java.util.List;

public class InlineProgramBuilder {
    private final List<ProgramInstruction> instructions = new ArrayList<>();
    
    public InlineProgramBuilder instruction(Instruction instruction) {
        return withLabel(null, instruction);
    }
    
    public InlineProgramBuilder withLabel(String label, Instruction instruction) {
        instructions.add(new ProgramInstruction(label, instruction));
        return this;
    }
    
    public Program build() {
        return new Program(instructions);
    }
}
