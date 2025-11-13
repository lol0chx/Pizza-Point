package com.PizzaPoint.menu.pizza;

import com.PizzaPoint.core.enums.*;

import java.util.*;

public class SignaturePizzaMenu {
    
    public static SignaturePizza createMeatLovers(PizzaSize size) {
        return new SignaturePizza(
            "Meat Lovers",
            "Loaded with pepperoni, beef, bacon, and sausage",
            size,
            CrustType.REGULAR,
            SauceType.MARINARA,
            CheeseType.MOZZARELLA,
            Arrays.asList("Pepperoni", "Beef", "Bacon", "Italian Sausage")
        );
    }
    
    public static SignaturePizza createVeggieSupreme(PizzaSize size) {
        return new SignaturePizza(
            "Veggie Supreme",
            "Fresh veggies: mushrooms, peppers, onions, and olives",
            size,
            CrustType.REGULAR,
            SauceType.MARINARA,
            CheeseType.MOZZARELLA,
            Arrays.asList("Mushrooms", "Bell Peppers", "Onions", "Olives")
        );
    }
    
    public static SignaturePizza createHawaiian(PizzaSize size) {
        return new SignaturePizza(
            "Hawaiian",
            "Ham and pineapple on marinara",
            size,
            CrustType.REGULAR,
            SauceType.MARINARA,
            CheeseType.MOZZARELLA,
            Arrays.asList("Ham", "Pineapple")
        );
    }
    
    public static SignaturePizza createBBQChicken(PizzaSize size) {
        return new SignaturePizza(
            "BBQ Chicken",
            "Grilled chicken with BBQ sauce, onions, and bell peppers",
            size,
            CrustType.REGULAR,
            SauceType.BBQ,
            CheeseType.CHEDDAR,
            Arrays.asList("Chicken", "Onions", "Bell Peppers")
        );
    }
    
    public static SignaturePizza createMargherita(PizzaSize size) {
        return new SignaturePizza(
            "Margherita",
            "Classic: tomatoes, basil, and extra mozzarella",
            size,
            CrustType.REGULAR,
            SauceType.MARINARA,
            CheeseType.MOZZARELLA,
            Arrays.asList("Tomatoes", "Basil", "Extra Cheese")
        );
    }
    
    public static List<String> getSignaturePizzaNames() {
        return Arrays.asList("Meat Lovers", "Veggie Supreme", "Hawaiian", "BBQ Chicken", "Margherita");
    }
    
    public static String getDescription(String name) {
        return switch (name) {
            case "Meat Lovers" -> "Loaded with pepperoni, beef, bacon, and sausage";
            case "Veggie Supreme" -> "Fresh veggies: mushrooms, peppers, onions, and olives";
            case "Hawaiian" -> "Ham and pineapple on marinara";
            case "BBQ Chicken" -> "Grilled chicken with BBQ sauce, onions, and bell peppers";
            case "Margherita" -> "Classic: tomatoes, basil, and extra mozzarella";
            default -> "";
        };
    }
    
    public static SignaturePizza create(String name, PizzaSize size) {
        return switch (name) {
            case "Meat Lovers" -> createMeatLovers(size);
            case "Veggie Supreme" -> createVeggieSupreme(size);
            case "Hawaiian" -> createHawaiian(size);
            case "BBQ Chicken" -> createBBQChicken(size);
            case "Margherita" -> createMargherita(size);
            default -> null;
        };
    }
}
