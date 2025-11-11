package com.PizzaPoint.core.enums;



public enum  PizzaSize {
    //define all pizza sizes
    SMALL(8,10.99),
    MEDIUM(12, 14.99),
    LARGE(16, 18.99);

    private final int diameter;
    private final double basePrice;

    PizzaSize(int diameter, double basePrice) {
        this.diameter = diameter;
        this.basePrice = basePrice;
    }

    public int getDiameter() {
        return diameter;
    }

    public double getBasePrice() {
        return basePrice;
    }

}
