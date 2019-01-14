package org.kelog.cpu.core;

import org.kelog.cpu.program.Program;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

public class CpuState {
    
    private final Program program;
    private final Map<Register, Integer> registers = makeMap(Register.values(), 0);
    private final Map<Flag, Boolean> flags = makeMap(Flag.values(), false);
    private final int[] memory = new int[16];
    
    private int nextInstructionNumber = 0;
    private int cycleCount = 0;
    
    public CpuState(Program program) {
        this.program = program;
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
    
    int getNextInstructionNumber() {
        return nextInstructionNumber;
    }
    
    public void jumpToLabel(String label) {
        requireNonNull(label);
        nextInstructionNumber = program.getLabelInstructionNumber(label);
    }
    
    @Override
    public String toString() {
        String registerLabels = Arrays.stream(Register.values()).map(r -> r.name().toLowerCase()).collect(joining("    "));
        String registerValues = Arrays.stream(Register.values())
                .map(r -> formatNumber(registers.get(r)))
                .collect(joining("  "));
        
        String memoryLabels = IntStream.range(0, memory.length).mapToObj(CpuState::formatNumber).collect(joining("  "));
        String memoryValues = Arrays.stream(memory).mapToObj(CpuState::formatNumber).collect(joining("  "));
        
        return "-----------------------------------------\n" +
                "Cycle: " + formatNumber(cycleCount) + "\n" +
                "Instr: " + formatNumber(nextInstructionNumber) + "\n" +
                "Registers:  " + registerLabels + "\n          " + registerValues + "\n\n" +
                "Memory:   " + memoryLabels + "\n          " + memoryValues +
                "\n-----------------------------------------";
    }
    
    public void cycleExecuted() {
        cycleCount++;
    }
    
    private static String formatNumber(int number) {
        return String.format("%4d", number);
    }
    
    private <T, U> Map<T, U> makeMap(T[] values, U defaultValue) {
        return Arrays.stream(values).collect(toMap(k -> k, v -> defaultValue));
    }
}
