package org.kelog.cpu.core;

import org.kelog.cpu.instruction.Instruction;
import org.kelog.cpu.program.Program;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.unmodifiableList;

public class Environment {
    
    private final boolean shouldLog;
    private final List<Integer> initialMemory;
    private final Program program;
    
    private Environment(boolean shouldLog, List<Integer> initialMemory, Program program) {
        this.shouldLog = shouldLog;
        this.initialMemory = unmodifiableList(initialMemory);
        this.program = program;
    }
    
    public CpuState runToCompletion() {
        CpuState state = new CpuState.Builder()
                .withProgram(program)
                .withInitialMemory(initialMemory)
                .build();
        
        if (shouldLog) {
            System.out.println("Initial state:");
            System.out.println(state);
        }
        
        while (!state.getFlag(Flag.EXIT)) {
            Instruction instruction = program.getInstruction(state.getNextInstructionNumber());
            
            if (shouldLog) {
                System.out.println("Running " + instruction.toMnemonic());
            }
            
            instruction.executeAndIncrementCycle(state);
            
            if (shouldLog) {
                System.out.println(state);
            }
        }
        
        return state;
    }
    
    public static class Builder {
        private boolean shouldLog = true;
        private List<Integer> initialMemory = IntStream.generate(() -> 0).limit(16).boxed().collect(Collectors.toList());
        private Program program;
        
        public Builder withLogging(boolean shouldLog) {
            this.shouldLog = shouldLog;
            return this;
        }
        
        public Builder withProgram(Program program) {
            this.program = program;
            return this;
        }
        
        public Builder withInitialMemory(List<Integer> initialMemory) {
            this.initialMemory = initialMemory;
            return this;
        }
        
        public Environment build() {
            Objects.requireNonNull(program);
            Objects.requireNonNull(initialMemory);
            return new Environment(shouldLog, initialMemory, program);
        }
    }
}
