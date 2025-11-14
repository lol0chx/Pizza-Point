package com.PizzaPoint.menu;

public class GarlicKnots extends MenuItem {
    private static final double FIXED_PRICE = 5.00;

    public GarlicKnots() {
        super("Garlic Knots", FIXED_PRICE);
    }

    @Override
    public double calculatePrice() {
        return FIXED_PRICE;
    }

    @Override
    public String toString() {
        return String.format("%s - $%.2f", getName(), calculatePrice());
    }
}
