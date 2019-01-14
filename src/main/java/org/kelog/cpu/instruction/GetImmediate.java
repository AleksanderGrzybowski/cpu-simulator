package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Register;

public class GetImmediate extends Instruction {
    
    private final Register target;
    private final int cellNumber;
    
    public GetImmediate(Register target, int cellNumber) {
        this.target = target;
        this.cellNumber = cellNumber;
    }
    
    @Override
    public void execute(CpuState state) {
        state.setRegister(target, state.getMemoryAt(cellNumber));
        state.nextInstruction();
    }
    
    @Override
    public String toMnemonic() {
        return "get " + target.mnemonic() + ", " + cellNumber;
    }
}
