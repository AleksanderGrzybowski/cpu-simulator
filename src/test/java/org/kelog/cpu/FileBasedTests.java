package org.kelog.cpu;

import org.junit.Test;
import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Register;
import org.kelog.cpu.core.Runner;
import org.kelog.cpu.program.ProgramBuilder;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class FileBasedTests {
    
    private ProgramBuilder builder = new ProgramBuilder();
    private Runner runner = Runner.withLogging(false);
    
    @Test
    public void factorial() {
        CpuState finalState = runner.runToCompletion(builder.fromSource(readResource("factorial.txt")));
        assertEquals(120, finalState.getRegister(Register.R1));
    }
    
    @Test
    public void fibonacci() {
        CpuState finalState = runner.runToCompletion(builder.fromSource(readResource("fibonacci.txt")));
        assertEquals(89, finalState.getRegister(Register.R1));
    }
    
    private String readResource(String filename) {
        return new Scanner(this.getClass().getResourceAsStream('/' + filename), "UTF-8").useDelimiter("\\A").next();
    }
}
