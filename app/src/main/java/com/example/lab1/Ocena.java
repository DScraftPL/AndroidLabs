package com.example.lab1;

public enum Ocena {
    OCENA2(2),
    OCENA3(3),
    OCENA35(3.5),
    OCENA4(4),
    OCENA45(4.5),
    OCENA5(5);

    private final double value;

    Ocena(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
