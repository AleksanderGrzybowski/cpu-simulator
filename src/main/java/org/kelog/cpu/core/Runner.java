package org.kelog.cpu.core;

import org.kelog.cpu.instruction.Instruction;
import org.kelog.cpu.program.Program;

public class Runner {
    
    private final boolean loggingEnabled;
    
    private Runner(boolean debug) {
        this.loggingEnabled = debug;
    }
    
    public static Runner withLogging(boolean logging) {
        return new Runner(logging);
    }
    
    public CpuState runToCompletion(Program program) {
        CpuState state = new CpuState(program);
        if (loggingEnabled) {
            System.out.println("Initial state:");
            System.out.println(state);
        }
        
        while (!state.getFlag(Flag.EXIT)) {
            Instruction instruction = program.getInstruction(state.getNextInstructionNumber());
            
            if (loggingEnabled) {
                System.out.println("Running " + instruction.toMnemonic());
            }
            
            instruction.executeAndIncrementCycle(state);
            
            if (loggingEnabled) {
                System.out.println(state);
            }
        }
        
        return state;
    }
}
