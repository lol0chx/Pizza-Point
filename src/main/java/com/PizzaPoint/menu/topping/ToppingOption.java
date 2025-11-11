package com.PizzaPoint.menu.topping;

public class ToppingOption {
    private final String name;
    private final double price;
    private final boolean isBase;//true for base toppings

    public ToppingOption(String name, double price, boolean isBase) {
        this.name = name;
        this.price = price;
        this.isBase = isBase;
    }

    public String getName() {

        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBase() {
        return isBase;
    }

    @Override
    public String toString() {

        return name + (isBase ? " (base)" : "") + (price > 0 ? " ($" + price + " extra)" : "");
    }
}
