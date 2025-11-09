package com.PizzaPoint.core.enums;

import com.PizzaPoint.core.interfaces.CustomOption;

public enum PizzaSize implements CustomOption {
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
