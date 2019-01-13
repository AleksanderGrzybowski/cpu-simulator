package org.kelog.cpu.instruction;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Flag;

public class Exit extends Instruction {
    
    @Override
    public void execute(CpuState state) {
        state.setFlag(Flag.EXIT, true);
    }
}
