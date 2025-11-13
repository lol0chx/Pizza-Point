package com.PizzaPoint.menu.drink;

import java.util.Arrays;
import java.util.List;

public class DrinkMenu {
    public static final List<String> DRINKS = Arrays.asList(
            "Coke",
            "Sprite",
            "Water",
            "Fanta"
    );

    public static boolean isAvailable(String name) {
        return DRINKS.contains(name);
    }

    public static List<String> getAllDrinks() {
        return DRINKS;
    }
}
