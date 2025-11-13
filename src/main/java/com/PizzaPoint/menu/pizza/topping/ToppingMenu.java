package com.PizzaPoint.menu.pizza.topping;

import com.PizzaPoint.core.enums.ToppingCategory;

import java.util.LinkedHashMap;
import java.util.Map;

public class ToppingMenu {
  private static final Map<String, ToppingOption> ALL_TOPPINGS = new LinkedHashMap<>();
    static {

        //meat toppings
        ALL_TOPPINGS.put("Pepperoni", new ToppingOption("Pepperoni", 1.50, ToppingCategory.MEAT));
        ALL_TOPPINGS.put("Beef", new ToppingOption("Beef", 2.00, ToppingCategory.MEAT));
        ALL_TOPPINGS.put("Italian Sausage", new ToppingOption("Italian Sausage", 1.75, ToppingCategory.MEAT));
        ALL_TOPPINGS.put("Ham", new ToppingOption("Ham", 1.50, ToppingCategory.MEAT));
        ALL_TOPPINGS.put("Bacon", new ToppingOption("Bacon", 2.00, ToppingCategory.MEAT));
        ALL_TOPPINGS.put("Chicken", new ToppingOption("Chicken", 2.25, ToppingCategory.MEAT));
        ALL_TOPPINGS.put("Salami", new ToppingOption("Salami", 1.75, ToppingCategory.MEAT));
        ALL_TOPPINGS.put("Anchovies", new ToppingOption("Anchovies", 2.50, ToppingCategory.MEAT));

        //cheese and sauce
        ALL_TOPPINGS.put("Extra Cheese", new ToppingOption("Extra Cheese", 2.00, ToppingCategory.EXTRA));
        ALL_TOPPINGS.put("Extra Sauce", new ToppingOption("Extra Sauce", 1.00, ToppingCategory.EXTRA));

        // veg toppings
        ALL_TOPPINGS.put("Mushrooms", new ToppingOption("Mushrooms", 1.00, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Bell Peppers", new ToppingOption("Bell Peppers", 0.75, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Onions", new ToppingOption("Onions", 0.50, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Olives", new ToppingOption("Olives", 1.00, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Tomatoes", new ToppingOption("Tomatoes", 0.75, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Spinach", new ToppingOption("Spinach", 1.00, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Jalapeños", new ToppingOption("Jalapeños", 0.75, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Pineapple", new ToppingOption("Pineapple", 1.00, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Basil", new ToppingOption("Basil", 0.75, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Garlic", new ToppingOption("Garlic", 0.50, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Artichokes", new ToppingOption("Artichokes", 1.50, ToppingCategory.VEG));
        ALL_TOPPINGS.put("Sun-dried Tomatoes", new ToppingOption("Sun-dried Tomatoes", 1.25, ToppingCategory.VEG));
    }
    public static ToppingOption getToppingByName(String name) {
        return ALL_TOPPINGS.get(name);
    }

    public static Map<String, ToppingOption> getAllToppings() {
        return ALL_TOPPINGS;
    }
}
