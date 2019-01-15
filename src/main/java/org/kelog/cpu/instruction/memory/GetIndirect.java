package org.kelog.cpu.instruction.memory;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Register;
import org.kelog.cpu.instruction.Instruction;

public class GetIndirect extends Instruction {
    
    private final Register target, sourceIndex;
    
    public GetIndirect(Register target, Register sourceIndex) {
        this.target = target;
        this.sourceIndex = sourceIndex;
    }
    
    @Override
    public void execute(CpuState state) {
        state.setRegister(target, state.getMemoryAt(state.getRegister(sourceIndex)));
        state.nextInstruction();
    }
    
    @Override
    public String toMnemonic() {
        return "get " + target.mnemonic() + ", " + sourceIndex.mnemonic();
    }
}
