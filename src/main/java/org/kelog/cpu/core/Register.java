package org.kelog.cpu.core;

public enum Register {
    R0, R1, R2, R3, R4, R5, R6, R7;
    
    public String mnemonic() {
        return toString().toLowerCase();
    }
}
