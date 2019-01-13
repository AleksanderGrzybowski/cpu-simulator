package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;

public abstract class Instruction {
    abstract public void execute(CpuState state);
    
    abstract public String toMnemonic();
    
    public void executeAndIncrementCycle(CpuState state) {
        execute(state);
        state.incrementCycleCount();
    }
}
