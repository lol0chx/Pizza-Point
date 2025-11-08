package com.PizzaPoint.core.enums;

public enum PizzaSize {
    //define all pizza sizes
    SMALL(8),
    MEDIUM(12),
    LARGE(16);

    private final int diameter;

    PizzaSize(int diameter) {
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }
}
