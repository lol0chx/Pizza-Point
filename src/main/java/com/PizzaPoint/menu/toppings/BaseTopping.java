package com.PizzaPoint.menu.toppings;

public class BaseTopping extends ToppingOption {
    private boolean includedFirst = true;

    public BaseTopping(String name, double extraPrice) {
        super(name, extraPrice);
    }

    @Override
    public double getPrice() {
        //to make the first choice free for sauce and cheese
        if (includedFirst) {
            includedFirst = false;
            return 0;
        }
        return getExtraPrice();
    }
    @Override
    public String toString() {
        return getName() + " (base)";
    }

}
