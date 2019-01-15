package org.kelog.cpu;

import org.junit.Test;
import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Environment;
import org.kelog.cpu.core.Register;

import static org.junit.Assert.assertEquals;
import static org.kelog.cpu.TestUtils.loadProgram;

public class FileBasedTests {
    
    private Environment.Builder environmentBuilder = new Environment.Builder().withLogging(false);
    
    @Test
    public void factorial() {
        CpuState finalState = runToCompletion("factorial.txt");
        assertEquals(120, finalState.getRegister(Register.R1));
    }
    
    
    @Test
    public void fibonacci() {
        CpuState finalState = runToCompletion("fibonacci.txt");
        assertEquals(89, finalState.getRegister(Register.R1));
    }
    
    @Test
    public void fillmemory() {
        CpuState finalState = runToCompletion("fillmemory.txt");
        for (int i = 0; i < 16; i++) {
            assertEquals(i, finalState.getMemoryAt(i));
        }
    }
    
    private CpuState runToCompletion(String s) {
        return environmentBuilder.withProgram(loadProgram(s)).build().runToCompletion();
    }
}
