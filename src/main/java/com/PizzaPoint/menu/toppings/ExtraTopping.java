package com.PizzaPoint.menu.toppings;

public class ExtraTopping extends ToppingOption {
    public ExtraTopping(String name, double price) {
        super(name, price);
    }

    @Override
    public double getPrice() {
        //it always costs extra
        return getExtraPrice();
    }

    @Override
    public String toString() {
        return getName() + " (extra)";
    }
}
