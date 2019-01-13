package org.kelog.cpu.program;

import org.kelog.cpu.instruction.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Program {
    private final List<ProgramInstruction> instructions;
    
    private Program(List<ProgramInstruction> instructions) {
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
    
    public static class Builder {
        private final List<ProgramInstruction> instructions = new ArrayList<>();
        
        public Builder instruction(Instruction instruction) {
            return withLabel(null, instruction);
        }
        
        public Builder withLabel(String label, Instruction instruction) {
            instructions.add(new ProgramInstruction(label, instruction));
            return this;
        }
        
        public Program build() {
            return new Program(instructions);
        }
    }
    
    private static class ProgramInstruction {
        final String label;
        final Instruction instruction;
        
        ProgramInstruction(String label, Instruction instruction) {
            this.label = label;
            this.instruction = instruction;
        }
    }
}
