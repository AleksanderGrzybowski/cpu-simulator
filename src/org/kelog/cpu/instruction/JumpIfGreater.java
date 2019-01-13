package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Flag;

import static java.util.Objects.requireNonNull;

public class JumpIfGreater extends Instruction {
    
    private final String label;
    
    public JumpIfGreater(String label) {
        requireNonNull(label);
        this.label = label;
    }
    
    @Override
    public void execute(CpuState state) {
        if (state.getFlag(Flag.GREATER)) {
            state.jumpToLabel(label);
        } else {
            state.nextInstruction();
        }
    }
}
