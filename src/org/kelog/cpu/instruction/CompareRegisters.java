package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Flag;
import org.kelog.cpu.core.Register;

public class CompareRegisters extends Instruction {
    
    private final Register first, second;
    
    public CompareRegisters(Register first, Register second) {
        this.first = first;
        this.second = second;
    }
    
    @Override
    public void execute(CpuState state) {
        int firstValue = state.getRegister(first);
        int secondValue = state.getRegister(second);
        
        if (firstValue == secondValue) {
            state.setFlag(Flag.EQUAL, true);
        } else if (firstValue > secondValue) {
            state.setFlag(Flag.GREATER, true);
        } else {
            state.setFlag(Flag.LESS, true);
        }
        
        state.nextInstruction();
    }
}
