package com.PizzaPoint.menu.topping;

import java.util.LinkedHashMap;
import java.util.Map;

public class ToppingMenu {
  private static final Map<String, ToppingOption> ALL_TOPPINGS = new LinkedHashMap<>();
    static {
        // Base toppings (first one free, extras cost extra)
        ALL_TOPPINGS.put("Mozzarella", new ToppingOption("Mozzarella", 1.0, true));
        ALL_TOPPINGS.put("Marinara Sauce", new ToppingOption("Marinara Sauce", 0.5, true));
        ALL_TOPPINGS.put("Parmesan", new ToppingOption("Parmesan", 0.5, true));

        //meat toppings
        ALL_TOPPINGS.put("Pepperoni", new ToppingOption("Pepperoni", 2.0, false)); // extra charge
        ALL_TOPPINGS.put("Beef", new ToppingOption("Beef", 2.5, false));
        ALL_TOPPINGS.put("Italian Sausage", new ToppingOption("Italian Sausage", 2.5, false));

        // veg toppings
        ALL_TOPPINGS.put("Mushrooms", new ToppingOption("Mushrooms", 1.0, false));
        ALL_TOPPINGS.put("Bell Peppers", new ToppingOption("Bell Peppers", 0.8, false));
        ALL_TOPPINGS.put("Onions", new ToppingOption("Onions", 0.5, false));
        ALL_TOPPINGS.put("Olives", new ToppingOption("Olives", 0.7, false));
    }
    public static ToppingOption getToppingByName(String name) {
        return ALL_TOPPINGS.get(name);
    }

    public static Map<String, ToppingOption> getAllToppings() {
        return ALL_TOPPINGS;
    }
}
