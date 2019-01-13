package org.kelog.cpu;

import org.kelog.cpu.core.Register;
import org.kelog.cpu.instruction.*;
import org.kelog.cpu.program.Program;

import static org.kelog.cpu.core.Controller.runProgram;
import static org.kelog.cpu.core.Register.*;

@SuppressWarnings("unused")
public class Demo {
    public static void main(String[] args) {
        fibonacci();
    }
    
    static void factorial() {
        Program program = new Program.Builder()
                .instruction(new AddImmediate(R0, 5))
                .instruction(new AddImmediate(R1, 1))
                .instruction(new AddImmediate(Register.R2, 1))
                .withLabel("loop_1", new CompareRegisters(R0, Register.R2))
                .instruction(new JumpIfEqual("end"))
                .instruction(new MultiplyRegisters(R1, R0))
                .instruction(new AddImmediate(R0, -1))
                .instruction(new Jump("loop_1"))
                .withLabel("end", new Exit())
                .build();
        
        runProgram(program);
        
    }
    
    private static void fibonacci() {
        Program program = new Program.Builder()
                .instruction(new MoveImmediate(R0, 0))
                .instruction(new MoveImmediate(R1, 1))
                .instruction(new MoveImmediate(R6, 0))
                .instruction(new AddImmediate(R7, 10))
                
                .withLabel("loop_1", new CompareRegisters(R7, R6))
                .instruction(new JumpIfEqual("end"))
                .instruction(new MoveRegisters(R2, R1))
                .instruction(new AddRegisters(R2, R0))
                .instruction(new MoveRegisters(R0, R1))
                .instruction(new MoveRegisters(R1, R2))
                .instruction(new AddImmediate(R7, -1))
                .instruction(new Jump("loop_1"))
                .withLabel("end", new Exit())
                .build();
        
        runProgram(program);
        
    }
}
