package org.kelog.cpu;

import org.junit.Test;
import org.kelog.cpu.core.Controller;
import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Register;
import org.kelog.cpu.program.FileProgramBuilder;
import org.kelog.cpu.program.Program;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class FileBasedTests {
    
    @Test
    public void factorial() throws IOException {
        Program program = new FileProgramBuilder().loadFromString(readResource("factorial.txt"));
        CpuState finalState = Controller.runProgram(program);
        assertEquals(120, finalState.getRegister(Register.R1));
    }
    
    @Test
    public void fibonacci() throws IOException {
        Program program = new FileProgramBuilder().loadFromString(readResource("fibonacci.txt"));
        CpuState finalState = Controller.runProgram(program);
        assertEquals(89, finalState.getRegister(Register.R1));
    }
    
    private String readResource(String filename) {
        return new Scanner(this.getClass().getResourceAsStream('/' + filename), "UTF-8").useDelimiter("\\A").next();
    }
}
