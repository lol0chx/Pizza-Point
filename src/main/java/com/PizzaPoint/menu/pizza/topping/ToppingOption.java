package com.PizzaPoint.menu.pizza.topping;

import com.PizzaPoint.core.enums.ToppingCategory;

public class ToppingOption {
    private final String name;
    private final double price;
    private final ToppingCategory category;

    public ToppingOption(String name, double price, ToppingCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;

    }

    public String getName() {

        return name;
    }

    public double getPrice() {
        return price;
    }
    public ToppingCategory getCategory() {
        return category;
    }


    @Override
    public String toString() {

        return name + (price > 0 ? " ($" + price + " extra)" : "");
    }
}
