package org.kelog.cpu.core;

import org.kelog.cpu.program.Program;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class CpuState {
    
    private final Program program;
    private final Map<Register, Integer> registers = Utils.makeMap(Register.values(), 0);
    private final Map<Flag, Boolean> flags = Utils.makeMap(Flag.values(), false);
    private final int[] memory;
    
    private int nextInstructionNumber = 0;
    private int cycleCount = 0;
    
    private CpuState(Program program, int[] initialMemory) {
        this.program = program;
        this.memory = initialMemory;
    }
    
    int getNextInstructionNumber() {
        return nextInstructionNumber;
    }
    
    public void nextInstruction() {
        nextInstructionNumber++;
    }
    
    public boolean getFlag(Flag flag) {
        requireNonNull(flag);
        return flags.get(flag);
    }
    
    public void setFlag(Flag flag, boolean state) {
        requireNonNull(flag);
        flags.put(flag, state);
    }
    
    public int getRegister(Register register) {
        requireNonNull(register);
        return registers.get(register);
    }
    
    public void setRegister(Register register, int value) {
        requireNonNull(register);
        registers.put(register, value);
    }
    
    public int getMemoryAt(int cellNumber) {
        if (cellNumber >= memory.length) {
            throw new AssertionError();
        }
        return memory[cellNumber];
    }
    
    public void setMemory(int cellNumber, int value) {
        if (cellNumber >= memory.length) {
            throw new AssertionError();
        }
        memory[cellNumber] = value;
    }
    
    public void jumpToLabel(String label) {
        requireNonNull(label);
        nextInstructionNumber = program.getLabelInstructionNumber(label);
    }
    
    public void cycleExecuted() {
        cycleCount++;
    }
    
    @Override
    public String toString() {
        String registerLabels = Arrays.stream(Register.values()).map(r -> r.name().toLowerCase()).collect(joining("    "));
        String registerValues = Arrays.stream(Register.values())
                .map(r -> Utils.formatNumber(registers.get(r)))
                .collect(joining("  "));
        
        String memoryLabels = IntStream.range(0, memory.length).mapToObj(Utils::formatNumber).collect(joining("  "));
        String memoryValues = Arrays.stream(memory).mapToObj(Utils::formatNumber).collect(joining("  "));
        
        String flagValues = Arrays.stream(Flag.values()).map(f -> f.name() + "=" + flags.get(f)).collect(joining(" "));
        
        return "-----------------------------------------\n" +
                "Cycle: " + Utils.formatNumber(cycleCount) + "\n" +
                "Instr: " + Utils.formatNumber(nextInstructionNumber) + "\n" +
                "Flags: " + flagValues + "\n" +
                "Registers:  " + registerLabels + "\n          " + registerValues + "\n\n" +
                "Memory:   " + memoryLabels + "\n          " + memoryValues +
                "\n-----------------------------------------";
    }
    
    static class Builder {
        private Program program;
        private List<Integer> initialMemory;
        
        Builder withProgram(Program program) {
            this.program = program;
            return this;
        }
        
        Builder withInitialMemory(List<Integer> initialMemory) {
            this.initialMemory = initialMemory;
            return this;
        }
        
        CpuState build() {
            int[] memory = new int[initialMemory.size()];
            for (int i = 0; i < initialMemory.size(); i++) {
                memory[i] = initialMemory.get(i);
            }
            
            return new CpuState(program, memory);
        }
    }
}
