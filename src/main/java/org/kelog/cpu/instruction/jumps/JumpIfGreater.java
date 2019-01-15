package org.kelog.cpu.instruction.jumps;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Flag;

public class JumpIfGreater extends ConditionalJump {
    
    public JumpIfGreater(String label) {
        super(label);
    }
    
    @Override
    public boolean shouldJump(CpuState state) {
        return state.getFlag(Flag.GREATER);
    }
    
    @Override
    public String getBaseMnemonic() {
        return "jg";
    }
}
