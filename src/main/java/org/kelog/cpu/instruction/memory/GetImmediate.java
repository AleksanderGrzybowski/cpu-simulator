package org.kelog.cpu.instruction.memory;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Register;
import org.kelog.cpu.instruction.Instruction;

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
