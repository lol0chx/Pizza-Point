package com.PizzaPoint.services;

import com.PizzaPoint.core.enums.CheeseType;
import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.core.enums.SauceType;
import com.PizzaPoint.core.enums.ToppingCategory;
import com.PizzaPoint.menu.pizza.topping.ToppingMenu;
import com.PizzaPoint.menu.pizza.topping.ToppingOption;

import java.util.ArrayList;
import java.util.List;

public class PizzaCustomizer {
    
    public static PizzaSize chooseSize() {
        String prompt = """
                select size:
                1: Small
                2: Medium
                3: Large
                """;
        int choice = InputHandler.getIntInput(prompt, 1, 3);
        return switch (choice) {
            case 1 -> PizzaSize.SMALL;
            case 2 -> PizzaSize.MEDIUM;
            default -> PizzaSize.LARGE;
        };
    }
    
    public static List<ToppingOption> chooseToppings(PizzaSize pizzaSize) {
        List<ToppingOption> selected = new ArrayList<>();
        List<ToppingOption> allToppings = new ArrayList<>(ToppingMenu.getAllToppings().values());
        double multiplier = pizzaSize != null ? pizzaSize.getToppingMultiplier() : 1.0;

        int wantTopping = InputHandler.getIntInput("Do you want toppings? 1. Yes  2. No\n", 1, 2);
        if (wantTopping == 2) return selected;
        
        boolean done = false;
        while (!done) {
            int typeChoice = InputHandler.getIntInput("Choose topping type: 1. Veg  2. Meat  0. Done\n", 0, 2);
            if (typeChoice == 0) break;
            
            ToppingCategory selectedCategory = (typeChoice == 1) ? ToppingCategory.VEG : ToppingCategory.MEAT;
            
            List<ToppingOption> filtered = allToppings.stream()
                    .filter(t -> t.getCategory() == selectedCategory)
                    .toList();
            
            boolean categoryDone = false;
            while (!categoryDone) {
                System.out.println("Available " + selectedCategory + " toppings:");
                for (int i = 0; i < filtered.size(); i++) {
                    ToppingOption t = filtered.get(i);
                    double adjustedPrice = t.getPrice() * multiplier;
                    System.out.printf("%d: %s $%.2f \n", i + 1, t.getName(), adjustedPrice);
                }
                System.out.println("0) Done with this category");
                
                int toppingChoice = InputHandler.getIntInput("Your choice: ", 0, filtered.size());
                if (toppingChoice == 0) {
                    categoryDone = true;
                } else if (toppingChoice > 0 && toppingChoice <= filtered.size()) {
                    ToppingOption chosen = filtered.get(toppingChoice - 1);
                    String portionMessage = String.format("How many portions of %s (1-10)?\n", chosen.getName());
                    int portionCount = InputHandler.getIntInput(portionMessage, 1, 10);

                    double adjustedPrice = chosen.getPrice() * multiplier;
                    
                    // Add the topping 'portionCount' times
                    for (int i = 0; i < portionCount; i++) {
                        selected.add(chosen);
                    }

                    System.out.printf("%d X %s added ($%.2f X %d)\n", portionCount, chosen.getName(), adjustedPrice, portionCount);
                } else {
                    System.out.println("Invalid choice");
                }
            }
        }
        return selected;
    }

    public static CrustType chooseCrust(PizzaSize size) {
        double stuffedCrustPrice = CrustType.STUFFED.getExtraCost() * size.getToppingMultiplier();
        String prompt = String.format("""
                Select Crust:
                1: Stuffed Crust ($%.2f more)
                2: Thin Crust
                3: Thick Crust
                4: Pan Crust
                5: Regular Crust
                """, stuffedCrustPrice);
        int choice = InputHandler.getIntInput(prompt, 1, 5);
        return switch (choice) {
            case 1 -> CrustType.STUFFED;
            case 2 -> CrustType.THIN;
            case 3 -> CrustType.THICK;
            case 4 -> CrustType.PAN;
            default -> CrustType.REGULAR;
        };
    }

    public static SauceType chooseSauce() {
        String prompt = """
                select Sauce:
                1: Alfredo
                2: BBQ
                3: Pesto
                4: Marinara
                """;
        int choice = InputHandler.getIntInput(prompt, 1, 4);
        return switch (choice) {
            case 1 -> SauceType.ALFREDO;
            case 2 -> SauceType.BBQ;
            case 3 -> SauceType.PESTO;
            default -> SauceType.MARINARA;
        };
    }

    public static CheeseType chooseCheese() {
        String prompt = """
                select Cheese:
                1: Vegan
                2: Cheddar
                3: Parmesan
                4: Mozzarella
                """;
        int choice = InputHandler.getIntInput(prompt, 1, 4);
        return switch (choice) {
            case 1 -> CheeseType.VEGAN;
            case 2 -> CheeseType.CHEDDAR;
            case 3 -> CheeseType.PARMESAN;
            default -> CheeseType.MOZZARELLA;
        };
    }
}
