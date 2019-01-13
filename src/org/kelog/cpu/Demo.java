package org.kelog.cpu;

import org.kelog.cpu.core.CpuState;
import org.kelog.cpu.core.Flag;
import org.kelog.cpu.core.Register;
import org.kelog.cpu.instruction.*;
import org.kelog.cpu.program.Program;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        
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
        
        CpuState state = new CpuState(program);
        
        while (!state.getFlag(Flag.EXIT)) {
            program.getInstruction(state.getNextInstructionNumber()).executeAndIncrementCycle(state);
            System.out.println(state);
            Thread.sleep(100);
        }
    }
}
