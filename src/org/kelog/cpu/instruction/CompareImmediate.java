package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Flag;
import org.kelog.cpu.core.Register;

public class CompareImmediate extends Instruction {
    
    private final Register first;
    private final int value;
    
    public CompareImmediate(Register first, int value) {
        this.first = first;
        this.value = value;
    }
    
    @Override
    public void execute(CpuState state) {
        if (value == state.getRegister(this.first)) {
            state.setFlag(Flag.EQUAL, true);
        } else if (value > state.getRegister(this.first)) {
            state.setFlag(Flag.GREATER, true);
        } else {
            state.setFlag(Flag.LESS, true);
        }
    }
}
