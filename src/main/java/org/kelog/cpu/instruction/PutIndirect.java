package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Register;

public class PutIndirect extends Instruction {
    
    private final Register cellNumberTarget;
    private final Register source;
    
    public PutIndirect(Register cellNumberTarget, Register source) {
        this.cellNumberTarget = cellNumberTarget;
        this.source = source;
    }
    
    @Override
    public void execute(CpuState state) {
        state.setMemory(state.getRegister(cellNumberTarget), state.getRegister(source));
        state.nextInstruction();
    }
    
    @Override
    public String toMnemonic() {
        return "put " + cellNumberTarget.mnemonic() + ", " + source.mnemonic();
    }
}
