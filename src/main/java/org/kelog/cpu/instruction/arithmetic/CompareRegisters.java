package org.kelog.cpu.instruction.arithmetic;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Flag;
import org.kelog.cpu.core.Register;
import org.kelog.cpu.instruction.Instruction;

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
            state.setFlag(Flag.GREATER, false);
            state.setFlag(Flag.LESS, false);
        } else if (firstValue > secondValue) {
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
        return "cmp " + first.mnemonic() + ", " + second.mnemonic();
    }
}
