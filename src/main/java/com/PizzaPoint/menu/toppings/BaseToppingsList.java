package com.PizzaPoint.menu.toppings;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseToppingsList {
    public static final Map<String, BaseTopping> BASE_TOPPINGS = new LinkedHashMap<>();
    static {
        // Sauces
        BASE_TOPPINGS.put("Marinara", new BaseTopping("Marinara Sauce", 1.0));
        BASE_TOPPINGS.put("Alfredo", new BaseTopping("Alfredo Sauce", 1.0));


        // Cheeses
        BASE_TOPPINGS.put("Mozzarella", new BaseTopping("Mozzarella", 1.5));
        BASE_TOPPINGS.put("Parmesan", new BaseTopping("Parmesan", 1.5));
    }
}
