package org.kelog.cpu.instruction.memory;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Register;
import org.kelog.cpu.instruction.Instruction;

public class PutRegister extends Instruction {
    
    private final int cellNumber;
    private final Register source;
    
    public PutRegister(int cellNumber, Register source) {
        this.cellNumber = cellNumber;
        this.source = source;
    }
    
    @Override
    public void execute(CpuState state) {
        state.setMemory(cellNumber, state.getRegister(source));
        state.nextInstruction();
    }
    
    @Override
    public String toMnemonic() {
        return "put " + cellNumber + ", " + source.mnemonic();
    }
}
