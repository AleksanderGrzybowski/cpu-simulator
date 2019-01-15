package org.kelog.cpu.instruction.jumps;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.instruction.Instruction;

import static java.util.Objects.requireNonNull;

public abstract class ConditionalJump extends Instruction {
    
    private final String label;
    
    ConditionalJump(String label) {
        requireNonNull(label);
        this.label = label;
    }
    
    public abstract boolean shouldJump(CpuState state);
    public abstract String getBaseMnemonic();
    
    @Override
    public void execute(CpuState state) {
        if (shouldJump(state)) {
            state.jumpToLabel(label);
        } else {
            state.nextInstruction();
        }
    }
    
    @Override
    public String toMnemonic() {
        return getBaseMnemonic() + " " + label;
    }
}
