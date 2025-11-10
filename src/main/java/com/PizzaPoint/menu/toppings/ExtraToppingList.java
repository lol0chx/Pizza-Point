package com.PizzaPoint.menu.toppings;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExtraToppingList {
    public static final Map<String, ExtraTopping> EXTRA_TOPPINGS = new LinkedHashMap<>();
    static {
        // Meats
        EXTRA_TOPPINGS.put("Pepperoni", new ExtraTopping("Pepperoni", 2.0));
        EXTRA_TOPPINGS.put("Beef", new ExtraTopping("Beef", 2.0));
        EXTRA_TOPPINGS.put("Italian Sausage", new ExtraTopping("Italian Sausage", 2.0));

        // Veggies
        EXTRA_TOPPINGS.put("Bell Pepper", new ExtraTopping("Bell Pepper", 1.5));
        EXTRA_TOPPINGS.put("Mushrooms", new ExtraTopping("Mushrooms", 1.5));
        EXTRA_TOPPINGS.put("Onions", new ExtraTopping("Onions", 1.0));
    }
}
