package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Register;

public class AddRegisters extends Instruction {
    
    private final Register target, source;
    
    public AddRegisters(Register target, Register source) {
        this.target = target;
        this.source = source;
    }
    
    @Override
    public void execute(CpuState state) {
        state.setRegister(target, state.getRegister(target) + state.getRegister(source));
        state.nextInstruction();
    }
    
    @Override
    public String toMnemonic() {
        return "add " + target.mnemonic() + ", " + source.mnemonic();
    }
}
