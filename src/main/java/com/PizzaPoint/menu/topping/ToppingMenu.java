package com.PizzaPoint.menu.topping;

import com.PizzaPoint.core.enums.ToppingCategory;

import java.util.LinkedHashMap;
import java.util.Map;

public class ToppingMenu {
  private static final Map<String, ToppingOption> ALL_TOPPINGS = new LinkedHashMap<>();
    static {

        //meat toppings
        ALL_TOPPINGS.put("Pepperoni", new ToppingOption("Pepperoni", 2.0, ToppingCategory.MEAT)); // extra charge
        ALL_TOPPINGS.put("Beef", new ToppingOption("Beef", 2.5, ToppingCategory.MEAT));
        ALL_TOPPINGS.put("Italian Sausage", new ToppingOption("Italian Sausage", 2.5, ToppingCategory.MEAT));

        // veg toppings
        ALL_TOPPINGS.put("Mushrooms", new ToppingOption("Mushrooms", 1.0, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Bell Peppers", new ToppingOption("Bell Peppers", 0.8, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Onions", new ToppingOption("Onions", 0.5, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Olives", new ToppingOption("Olives", 0.7, ToppingCategory.VEG));
    }
    public static ToppingOption getToppingByName(String name) {
        return ALL_TOPPINGS.get(name);
    }

    public static Map<String, ToppingOption> getAllToppings() {
        return ALL_TOPPINGS;
    }
}
