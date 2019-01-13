package org.kelog.cpu.program;

import org.kelog.cpu.instruction.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Program {
    List<ProgramInstruction> programInstructions = new ArrayList<>();
    
    public Program instruction(Instruction instruction) {
        programInstructions.add(new ProgramInstruction(instruction));
        return this;
    }
    
    public Program withLabel(String label, Instruction instruction) {
        programInstructions.add(new ProgramInstruction(label, instruction));
        return this;
    }
    
    public int getIdForLabel(String label) {
        for (int i = 0; i < programInstructions.size(); i++) {
            if (Objects.equals(programInstructions.get(i).label, label)) return i;
        }
        return -1;
    }
    
    public Instruction getInstruction(int number) {
        return programInstructions.get(number).instruction;
    }
}
