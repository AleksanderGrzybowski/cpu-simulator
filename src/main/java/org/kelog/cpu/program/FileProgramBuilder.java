package org.kelog.cpu.program;

import org.kelog.cpu.core.Register;
import org.kelog.cpu.instruction.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

public class FileProgramBuilder {
    
    public Program loadFromFile(String filename) throws IOException {
        return new Program(parse(Files.readAllLines(Paths.get(filename))));
    }
    
    public Program loadFromString(String content) throws IOException {
        return new Program(parse(Arrays.stream(content.split("\n")).collect(toList())));
    }
    
    private List<ProgramInstruction> parse(List<String> inputLines) throws IOException {
        List<String> lines = inputLines.stream()
                .filter(line -> line.trim().length() != 0)
                .map(String::trim)
                .collect(toList());
        
        return lines.stream()
                .map(line -> toProgramInstruction(line))
                .collect(toList());
    }
    
    private ProgramInstruction toProgramInstruction(String line) {
        String label = null;
        if (line.contains(":")) {
            label = line.substring(0, line.indexOf(":"));
            line = line.substring(line.indexOf(":") + 1);
            line = line.trim();
        }
    
        String mnemonic, parameters;
        mnemonic = parameters = null;
        parameters = "";
        
        if (!line.contains(" ")) { // no-arg operation
            mnemonic = line;
        } else {
            mnemonic = line.substring(0, line.indexOf(" "));
            parameters = line.substring(line.indexOf(" ") + 1).trim();
        }
        
        String firstArg, secondArg;
        firstArg = secondArg = null;
        
        if (parameters.contains(",")) {
            firstArg = parameters.substring(0, parameters.indexOf(",")).trim();
            secondArg = parameters.substring(parameters.indexOf(",") + 1).trim();
        } else {
            firstArg = parameters;
        }
        
        if (mnemonic.equals("add")) {
            if (secondArg.startsWith("r")) {
                return new ProgramInstruction(label, new AddRegisters(toRegister(firstArg), toRegister(secondArg)));
            } else {
                return new ProgramInstruction(label, new AddImmediate(toRegister(firstArg), parseInt(secondArg)));
            }
        }
        
        if (mnemonic.equals("mul")) {
            if (secondArg.startsWith("r")) {
                return new ProgramInstruction(label, new MultiplyRegisters(toRegister(firstArg), toRegister(secondArg)));
            } else {
                return new ProgramInstruction(label, new MultiplyImmediate(toRegister(firstArg), parseInt(secondArg)));
            }
        }
        
        if (mnemonic.equals("mov")) {
            if (secondArg.startsWith("r")) {
                return new ProgramInstruction(label, new MoveRegisters(toRegister(firstArg), toRegister(secondArg)));
            } else {
                return new ProgramInstruction(label, new MoveImmediate(toRegister(firstArg), parseInt(secondArg)));
            }
        }
        
        if (mnemonic.equals("cmp")) {
            if (secondArg.startsWith("r")) {
                return new ProgramInstruction(label, new CompareRegisters(toRegister(firstArg), toRegister(secondArg)));
            } else {
                return new ProgramInstruction(label, new CompareImmediate(toRegister(firstArg), parseInt(secondArg)));
            }
        }
        
        if (mnemonic.equals("jmp")) {
            return new ProgramInstruction(label, new Jump(firstArg));
        }
        
        if (mnemonic.equals("je")) {
            return new ProgramInstruction(label, new JumpIfEqual(firstArg));
        }
        
        if (mnemonic.equals("jg")) {
            return new ProgramInstruction(label, new JumpIfGreater(firstArg));
        }
        
        if (mnemonic.equals("jl")) {
            return new ProgramInstruction(label, new JumpIfLess(firstArg));
        }
        
        if (mnemonic.equals("exit")) {
            return new ProgramInstruction(label, new Exit());
        }
        
        throw new AssertionError("Unknown instruction " + mnemonic);
    }
    
    private Register toRegister(String register) {
        return Register.valueOf(register.toUpperCase());
    }
}
