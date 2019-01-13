package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Register;

public class MoveRegisters extends Instruction {
    
    private final Register target, source;
    
    public MoveRegisters(Register target, Register source) {
        this.target = target;
        this.source = source;
    }
    
    @Override
    public void execute(CpuState state) {
        state.setRegister(target, state.getRegister(source));
        state.nextInstruction();
    }
    
    @Override
    public String toMnemonic() {
        return "mov " + target.mnemonic() + ", " + source.mnemonic();
    }
}
