package org.kelog.cpu.core;

import org.kelog.cpu.program.Program;

public class Controller {
    
    public static void runProgram(Program program) {
        CpuState state = new CpuState(program);
    
        while (!state.getFlag(Flag.EXIT)) {
            program.getInstruction(state.getNextInstructionNumber()).executeAndIncrementCycle(state);
            System.out.println(state);
            sleep();
        }
    }
    
    private static void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
