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
        state.setFlag(Flag.EQUAL, state.getRegister(this.second) == state.getRegister(this.first));
        state.nextInstruction();
    }
}
