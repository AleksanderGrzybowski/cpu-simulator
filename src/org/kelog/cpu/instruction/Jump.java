package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;

import static java.util.Objects.requireNonNull;

public class Jump extends Instruction {
    
    private final String label;
    
    public Jump(String label) {
        requireNonNull(label);
        this.label = label;
    }
    
    @Override
    public void execute(CpuState state) {
        state.jumpToLabel(label);
    }
    
    @Override
    public String toMnemonic() {
        return "jmp " + label;
    }
}
