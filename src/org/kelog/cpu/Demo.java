package org.kelog.cpu;

import org.kelog.cpu.core.Register;
import org.kelog.cpu.instruction.*;
import org.kelog.cpu.program.Program;

import static org.kelog.cpu.core.Controller.runProgram;

public class Demo {
    public static void main(String[] args) {
        
        Program program = new Program()
                .instruction(new AddImmediate(Register.R0, 5))
                .instruction(new AddImmediate(Register.R1, 1))
                .instruction(new AddImmediate(Register.R2, 1))
                .withLabel("loop_1", new CompareRegisters(Register.R0, Register.R2))
                .instruction(new JumpIfEqual("end"))
                .instruction(new MultiplyRegisters(Register.R1, Register.R0))
                .instruction(new AddImmediate(Register.R0, -1))
                .instruction(new Jump("loop_1"))
                .withLabel("end", new Exit());
        
        runProgram(program);
    }
}
