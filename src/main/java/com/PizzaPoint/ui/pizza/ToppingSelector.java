package com.PizzaPoint.ui.pizza;

import com.PizzaPoint.menu.Pizza;
import com.PizzaPoint.menu.topping.ToppingOption;

import java.util.List;

public class ToppingSelector {
    private final Pizza pizza;

    public ToppingSelector(Pizza pizza) {
        this.pizza = pizza;
    }

    public void addTopping(ToppingOption topping) {
        pizza.add(topping);
    }
    public void removeTopping(ToppingOption topping) {
        pizza.remove(topping);
    }
    public void addMultiple(List<ToppingOption> toppings) {
        toppings.forEach(this::addTopping);
    }
}
