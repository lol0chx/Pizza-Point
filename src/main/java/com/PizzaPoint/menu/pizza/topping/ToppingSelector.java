package com.PizzaPoint.menu.pizza.topping;

import com.PizzaPoint.menu.pizza.Pizza;

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
