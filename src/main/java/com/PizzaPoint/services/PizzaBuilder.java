package com.PizzaPoint.services;

import com.PizzaPoint.core.enums.CheeseType;
import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.core.enums.SauceType;
import com.PizzaPoint.menu.pizza.Pizza;

public class PizzaBuilder {
    private final Pizza pizza;

    // Initializes pizza with base attributes for building
    public PizzaBuilder(double basePrice, PizzaSize size, CrustType crust, SauceType sauce, CheeseType cheese) {
        pizza = new Pizza(basePrice, size, crust, sauce, cheese);
    }

    public PizzaBuilder setSize(PizzaSize size) {
        pizza.setSize(size);
        return this;
    }

    public PizzaBuilder setCrust(CrustType crust) {
        pizza.setCrust(crust);
        return this;
    }

    public Pizza build() {

        return pizza;
    }
}

