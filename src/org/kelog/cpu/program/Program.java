package org.kelog.cpu.program;

import org.kelog.cpu.instruction.Instruction;

import java.util.List;
import java.util.Objects;

public class Program {
    private final List<ProgramInstruction> instructions;
    
    Program(List<ProgramInstruction> instructions) {
        this.instructions = instructions;
    }
    
    public int getLabelInstructionNumber(String label) {
        for (int number = 0; number < instructions.size(); number++) {
            if (Objects.equals(instructions.get(number).label, label)) {
                return number;
            }
        }
        throw new AssertionError("No such label " + label);
    }
    
    public Instruction getInstruction(int number) {
        return instructions.get(number).instruction;
    }
    
}
