package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Flag;

import static java.util.Objects.requireNonNull;

public class JumpIfEqual extends Instruction {
    
    private final String label;
    
    public JumpIfEqual(String label) {
        requireNonNull(label);
        this.label = label;
    }
    
    @Override
    public void execute(CpuState state) {
        if (state.getFlag(Flag.EQUAL)) {
            state.jumpToLabel(label);
        } else {
            state.nextInstruction();
        }
    }
    
    @Override
    public String toMnemonic() {
        return "je " + label;
    }
}
