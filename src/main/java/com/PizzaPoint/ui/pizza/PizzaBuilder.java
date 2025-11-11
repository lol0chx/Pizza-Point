package com.PizzaPoint.ui.pizza;

import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.menu.Pizza;

public class PizzaBuilder {
    private final Pizza pizza;

    //name is custom pizza by default
    public PizzaBuilder (double basePrice, PizzaSize size, CrustType crust) {
        pizza = new Pizza(basePrice, size, crust);
    }

    public PizzaBuilder setSize(PizzaSize size) {
        pizza.setSize(size);
        return  this;
    }

    public PizzaBuilder setCrust(CrustType crust) {
        pizza.setCrust(crust);
        return this;
    }

    public Pizza build() {
        return pizza;
    }
}
