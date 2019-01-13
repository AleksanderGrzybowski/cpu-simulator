package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Register;

public class MoveImmediate extends Instruction {
    
    private final Register target;
    private final int value;
    
    public MoveImmediate(Register target, int value) {
        this.target = target;
        this.value = value;
    }
    
    @Override
    public void execute(CpuState state) {
        state.setRegister(target, value);
        state.nextInstruction();
    }
    
    @Override
    public String toMnemonic() {
        return "mov " + target.mnemonic() + ", " + value;
    }
}
