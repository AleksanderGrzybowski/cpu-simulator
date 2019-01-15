package org.kelog.cpu;

import org.kelog.cpu.program.Program;
import org.kelog.cpu.program.ProgramBuilder;

import java.util.Scanner;

class TestUtils {
    
    static Program loadProgram(String filename) {
        String content = new Scanner(ProgramBuilder.class.getResourceAsStream('/' + filename), "UTF-8").useDelimiter("\\A").next();
        return new ProgramBuilder().fromSource(content);
    }
}
