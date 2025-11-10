package com.PizzaPoint.menu.toppings;

public abstract class ToppingOption {
    private final String name;
    private final double extraPrice;

    public ToppingOption(String name, double extraPrice) {
        this.name = name;
        this.extraPrice = extraPrice;
    }

    public String getName() {
        return name;
    }

    public double getExtraPrice() {
        return extraPrice;
    }

    public abstract double getPrice();

    @Override
    public String toString() {
        return name;
    }
}
