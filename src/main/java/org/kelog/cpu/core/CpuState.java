package org.kelog.cpu.core;

import org.kelog.cpu.program.Program;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;

public class CpuState {
    
    private final Program program;
    private final Map<Register, Integer> registers = makeMap(Register.values(), 0);
    private final Map<Flag, Boolean> flags = makeMap(Flag.values(), false);
    
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
    
    int getNextInstructionNumber() {
        return nextInstructionNumber;
    }
    
    public void jumpToLabel(String label) {
        requireNonNull(label);
        nextInstructionNumber = program.getLabelInstructionNumber(label);
    }
    
    @Override
    public String toString() {
        String r = registers.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(e -> e.getKey() + ": " + formatNumber(e.getValue()))
                .collect(Collectors.joining(" | "));
        return "Cycle: " + formatNumber(cycleCount) + " | Instruction: " + formatNumber(nextInstructionNumber) + " | " + r;
    }
    
    public void cycleExecuted() {
        cycleCount++;
    }
    
    private static String formatNumber(int cycleCount) {
        return String.format("%4d", cycleCount);
    }
    
    private <T, U> Map<T, U> makeMap(T[] values, U defaultValue) {
        return Arrays.stream(values).collect(toMap(k -> k, v -> defaultValue));
    }
}
