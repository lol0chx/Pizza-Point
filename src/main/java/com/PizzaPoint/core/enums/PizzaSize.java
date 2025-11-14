package com.PizzaPoint.core.enums;
public enum  PizzaSize {
    //define all pizza sizes
    SMALL(8,10.99, 1.00),
    MEDIUM(12, 14.99, 1.5),
    LARGE(16, 18.99, 2.0);

    private final int diameter;
    private final double basePrice;
    private final double toppingMultiplier;

    PizzaSize(int diameter, double basePrice, double toppingMultiplier) {
        this.diameter = diameter;
        this.basePrice = basePrice;
        this.toppingMultiplier = toppingMultiplier;
    }

    public int getDiameter() {

        return diameter;
    }

    public double getBasePrice() {

        return basePrice;
    }

    public double getToppingMultiplier() {

        return toppingMultiplier;
    }

    public double applyToppingPrice(double baseToppingPrice) {

        return baseToppingPrice * toppingMultiplier;
    }

}
