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
            
            state.setFlag(Flag.GREATER, false);
            state.setFlag(Flag.LESS, false);
        } else if (value > state.getRegister(this.first)) {
            state.setFlag(Flag.GREATER, true);
            
            state.setFlag(Flag.EQUAL, false);
            state.setFlag(Flag.LESS, false);
        } else {
            state.setFlag(Flag.LESS, true);
            
            state.setFlag(Flag.GREATER, false);
            state.setFlag(Flag.EQUAL, false);
        }
        state.nextInstruction();
    }
    
    @Override
    public String toMnemonic() {
        return "cmp " + first.mnemonic() + ", " + value;
    }
}
