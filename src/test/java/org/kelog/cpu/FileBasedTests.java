package org.kelog.cpu;

import org.junit.Test;
import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Register;
import org.kelog.cpu.core.Runner;
import org.kelog.cpu.program.ProgramBuilder;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class FileBasedTests {
    
    private ProgramBuilder builder = new ProgramBuilder();
    private Runner runner = Runner.withLogging(true);
    
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
    
    @Test
    public void fillmemory() {
        CpuState finalState = runner.runToCompletion(builder.fromSource(readResource("fillmemory.txt")));
        for (int i = 0; i < 16; i++) {
            assertEquals(i, finalState.getMemoryAt(i));
        }
    }
    
    @Test
    public void selectionsort() {
        CpuState finalState = runner.runToCompletion(
                builder.fromSource(readResource("selectionsort.txt")),
                Stream.of(13, 9, 2, 12, 15, 4, 0, 10, 7, 11, 1, 14, 6, 8, 3, 5).map(i -> i * 10).collect(Collectors.toList())
        );
        for (int i = 0; i < 16; i++) {
            assertEquals(i*10, finalState.getMemoryAt(i));
        }
    }
    
    private String readResource(String filename) {
        return new Scanner(this.getClass().getResourceAsStream('/' + filename), "UTF-8").useDelimiter("\\A").next();
    }
}
