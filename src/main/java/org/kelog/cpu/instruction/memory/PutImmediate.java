package org.kelog.cpu.instruction.memory;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.instruction.Instruction;

public class PutImmediate extends Instruction {
    
    private final int cellNumber;
    private final int value;
    
    public PutImmediate(int cellNumber, int value) {
        this.cellNumber = cellNumber;
        this.value = value;
    }
    
    @Override
    public void execute(CpuState state) {
        state.setMemory(cellNumber, value);
        state.nextInstruction();
    }
    
    @Override
    public String toMnemonic() {
        return "put " + cellNumber + ", " + value;
    }
}
