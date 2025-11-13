package com.PizzaPoint.core.enums;

public enum DrinkSize {
    SMALL(1.00),
    MEDIUM(1.49),
    LARGE(1.99);


    private final double price;

    DrinkSize(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
}
