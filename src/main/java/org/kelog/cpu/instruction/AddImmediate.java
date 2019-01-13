package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Register;

public class AddImmediate extends Instruction {
    
    private final Register target;
    private final int value;
    
    public AddImmediate(Register target, int value) {
        this.target = target;
        this.value = value;
    }
    
    @Override
    public void execute(CpuState state) {
        state.setRegister(target, state.getRegister(target) + value);
        state.nextInstruction();
    }
    
    @Override
    public String toMnemonic() {
        return "add " + target.mnemonic() + ", " + value;
    }
}