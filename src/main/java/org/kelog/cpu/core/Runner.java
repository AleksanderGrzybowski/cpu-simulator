package org.kelog.cpu.core;

import org.kelog.cpu.instruction.Instruction;
import org.kelog.cpu.program.Program;

import java.util.List;

import static java.util.Collections.emptyList;

public class Runner {
    
    private final boolean loggingEnabled;
    
    private Runner(boolean debug) {
        this.loggingEnabled = debug;
    }
    
    public static Runner withLogging(boolean logging) {
        return new Runner(logging);
    }
    
    public CpuState runToCompletion(Program program, List<Integer> initialMemory) {
        CpuState state = new CpuState(program);
        for (int i = 0; i < initialMemory.size(); i++) {
            state.setMemory(i, initialMemory.get(i));
        }
        
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
    
    public CpuState runToCompletion(Program program) {
        return runToCompletion(program, emptyList());
    }
}
