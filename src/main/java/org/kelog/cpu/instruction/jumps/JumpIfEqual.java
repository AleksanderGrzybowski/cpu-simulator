package org.kelog.cpu.instruction.jumps;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Flag;

public class JumpIfEqual extends ConditionalJump {
    
    public JumpIfEqual(String label) {
        super(label);
    }
    
    @Override
    public boolean shouldJump(CpuState state) {
        return state.getFlag(Flag.EQUAL);
    }
    
    @Override
    public String getBaseMnemonic() {
        return "je";
    }
}
