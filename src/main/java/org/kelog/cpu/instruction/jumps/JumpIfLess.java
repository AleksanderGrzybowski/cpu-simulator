package org.kelog.cpu.instruction.jumps;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Flag;

public class JumpIfLess extends ConditionalJump {
    
    public JumpIfLess(String label) {
        super(label);
    }
    
    @Override
    public boolean shouldJump(CpuState state) {
        return state.getFlag(Flag.LESS);
    }
    
    @Override
    public String getBaseMnemonic() {
        return "jl";
    }
}
