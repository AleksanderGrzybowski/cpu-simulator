package org.kelog.cpu.program;

import org.kelog.cpu.core.Register;
import org.kelog.cpu.instruction.Exit;
import org.kelog.cpu.instruction.MoveImmediate;
import org.kelog.cpu.instruction.MoveRegisters;
import org.kelog.cpu.instruction.arithmetic.*;
import org.kelog.cpu.instruction.jumps.Jump;
import org.kelog.cpu.instruction.jumps.JumpIfEqual;
import org.kelog.cpu.instruction.jumps.JumpIfGreater;
import org.kelog.cpu.instruction.jumps.JumpIfLess;
import org.kelog.cpu.instruction.memory.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

public class ProgramBuilder {
    
    public Program fromSource(String sourceContent) {
        return new Program(parse(
                Arrays.stream(sourceContent.split("\n")).collect(toList())
        ));
    }
    
    private List<ProgramInstruction> parse(List<String> lines) {
        return lines.stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .map(this::toProgramInstruction)
                .collect(toList());
    }
    
    private ProgramInstruction toProgramInstruction(String line) {
        String label, instruction;
        
        if (line.contains(":")) {
            int labelDelimiterIndex = line.indexOf(":");
            
            label = line.substring(0, labelDelimiterIndex);
            instruction = line.substring(labelDelimiterIndex + 1).trim();
        } else {
            label = null;
            instruction = line;
        }
        
        String mnemonic, parameters;
        
        if (!instruction.contains(" ")) { // no-arg operation
            mnemonic = instruction;
            parameters = "";
        } else {
            int mnemonicDelimiterIndex = instruction.indexOf(" ");
            
            mnemonic = instruction.substring(0, mnemonicDelimiterIndex);
            parameters = instruction.substring(mnemonicDelimiterIndex + 1).trim();
        }
        
        String firstArg, secondArg;
        
        if (parameters.contains(",")) {
            int argumentDelimiterIndex = parameters.indexOf(",");
            
            firstArg = parameters.substring(0, argumentDelimiterIndex).trim();
            secondArg = parameters.substring(argumentDelimiterIndex + 1).trim();
        } else {
            firstArg = parameters;
            secondArg = null;
        }
        
        // 0-argument operations
        
        if (mnemonic.equals("exit")) {
            return new ProgramInstruction(label, new Exit());
        }
        
        // 1-argument operations
        
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
        
        // 2-argument operations
        Objects.requireNonNull(secondArg);
        
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
        
        if (mnemonic.equals("get")) {
            if (secondArg.startsWith("r")) {
                return new ProgramInstruction(label, new GetIndirect(toRegister(firstArg), toRegister(secondArg)));
            } else {
                return new ProgramInstruction(label, new GetImmediate(toRegister(firstArg), parseInt(secondArg)));
            }
        }
        
        if (mnemonic.equals("put")) {
            if (firstArg.startsWith("r") && secondArg.startsWith("r")) {
                return new ProgramInstruction(label, new PutIndirect(toRegister(firstArg), toRegister(secondArg)));
            } else if (secondArg.startsWith("r")) {
                return new ProgramInstruction(label, new PutRegister(parseInt(firstArg), toRegister(secondArg)));
            } else {
                return new ProgramInstruction(label, new PutImmediate(parseInt(firstArg), parseInt(secondArg)));
            }
        }
        
        throw new AssertionError("Unknown instruction " + mnemonic);
    }
    
    private Register toRegister(String register) {
        return Register.valueOf(register.toUpperCase());
    }
}
