package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;

public class Jump extends Instruction {
    
    private final String label;
    
    public Jump(String label) {
        this.label = label;
    }
    
    @Override
    public void execute(CpuState state) {
        state.jumpToLabel(label);
    }
}
