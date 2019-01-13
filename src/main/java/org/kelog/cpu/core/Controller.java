package org.kelog.cpu.core;

import org.kelog.cpu.instruction.Instruction;
import org.kelog.cpu.program.Program;

public class Controller {
    
    public static void runProgram(Program program) {
        CpuState state = new CpuState(program);
        System.out.println("Initial state:");
        System.out.println(state);
    
        while (!state.getFlag(Flag.EXIT)) {
            Instruction instruction = program.getInstruction(state.getNextInstructionNumber());
            System.out.println("Running " + instruction.toMnemonic());
            
            instruction.executeAndIncrementCycle(state);
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