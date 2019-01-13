package org.kelog.cpu.core;

import org.kelog.cpu.program.Program;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

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
        return flags.get(flag);
    }
    
    public void setFlag(Flag flag, boolean state) {
        flags.put(flag, state);
    }
    
    public int getRegister(Register register) {
        return registers.get(register);
    }
    
    public void setRegister(Register register, int value) {
        registers.put(register, value);
    }
    
    public int getNextInstructionNumber() {
        return nextInstructionNumber;
    }
    
    public void jumpToLabel(String label) {
        nextInstructionNumber = program.getIdForLabel(label);
    }
    
    private <T, U> Map<T, U> makeMap(T[] values, U defaultValue) {
        return Arrays.stream(values).collect(toMap(k -> k, v -> defaultValue));
    }
    
    @Override
    public String toString() {
        String r = registers.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(e -> e.getKey() + ": " + String.format("%4d", e.getValue()))
                .collect(Collectors.joining(" | "));
        return "Cycle: " + String.format("%4d", cycleCount) + " | Instruction: " +String.format("%4d", nextInstructionNumber) + " | "+  r;
    }
    
    public void incrementCycleCount() {
        cycleCount++;
    }
}
