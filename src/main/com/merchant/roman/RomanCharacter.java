package com.merchant.roman;

public enum RomanCharacter{
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);

    public int value;

    RomanCharacter(int value){
        this.value = value;
    }
}