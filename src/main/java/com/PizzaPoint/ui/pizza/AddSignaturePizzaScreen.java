package com.PizzaPoint.ui.pizza;

import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.menu.pizza.SignaturePizza;
import com.PizzaPoint.menu.pizza.SignaturePizzaMenu;
import com.PizzaPoint.core.enums.ToppingCategory;
import com.PizzaPoint.menu.pizza.topping.ToppingMenu;
import com.PizzaPoint.menu.pizza.topping.ToppingOption;
import com.PizzaPoint.menu.pizza.topping.ToppingSelector;
import com.PizzaPoint.orders.Order;
import com.PizzaPoint.util.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class AddSignaturePizzaScreen {
    private final Order order;
    private PizzaSize size;

    public AddSignaturePizzaScreen(Order order) {
        this.order = order;
    }

    public void start() {
        // Show signature pizzas
        List<String> names = SignaturePizzaMenu.getSignaturePizzaNames();
        
        System.out.println("\n--- Signature Pizzas ---");
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            System.out.printf("%d: %s - %s\n", i + 1, name, SignaturePizzaMenu.getDescription(name));
        }
        
        int choice = InputHandler.getIntInput("Choose signature pizza: ", 1, names.size());
        String selectedName = names.get(choice - 1);
        
        // Choose size
        size = chooseSize();
        
        // Build the signature pizza with default toppings
        SignaturePizza pizza = SignaturePizzaMenu.create(selectedName, size);
        
        // Ask if they want to customize
        int customize = InputHandler.getIntInput("Do you want to customize this pizza? 1. Yes  2. No\n", 1, 2);
        
        if (customize == 1) {
            customizeSignaturePizza(pizza);
        }
        
        order.addItem(pizza);
        System.out.println("✅ " + pizza.getName() + " added!");
    }

    private PizzaSize chooseSize() {
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

    private void customizeSignaturePizza(SignaturePizza pizza) {
        boolean done = false;
        
        while (!done) {
            System.out.println("\n--- Customize " + pizza.getName() + " ---");
            System.out.println("Current toppings:");
            pizza.getToppingsMap().forEach((topping, count) -> 
                System.out.println("  - " + topping.getName() + (count > 1 ? " x" + count : ""))
            );
            
            System.out.println("\n1: Add Toppings");
            System.out.println("2: Remove Toppings");
            System.out.println("0: Done Customizing");
            
            int choice = InputHandler.getIntInput("Your choice: ", 0, 2);
            
            switch (choice) {
                case 1 -> addToppingsToSignature(pizza);
                case 2 -> removeToppingsFromSignature(pizza);
                case 0 -> done = true;
            }
        }
    }

    private void addToppingsToSignature(SignaturePizza pizza) {
        System.out.println("\nAdding toppings to " + pizza.getName());
        List<ToppingOption> newToppings = chooseToppings(pizza.getSize());
        new ToppingSelector(pizza).addMultiple(newToppings);
    }

    private void removeToppingsFromSignature(SignaturePizza pizza) {
        if (pizza.getToppingsMap().isEmpty()) {
            System.out.println("No toppings to remove!");
            return;
        }
        
        boolean done = false;
        while (!done) {
            // Rebuild the list each time to reflect current state
            List<ToppingOption> currentToppings = new ArrayList<>(pizza.getToppingsMap().keySet());
            
            if (currentToppings.isEmpty()) {
                System.out.println("All toppings removed!");
                break;
            }
            
            System.out.println("\n--- Remove Toppings ---");
            for (int i = 0; i < currentToppings.size(); i++) {
                ToppingOption topping = currentToppings.get(i);
                int count = pizza.getToppingsMap().get(topping);
                System.out.printf("%d: %s%s\n", i + 1, topping.getName(), 
                    count > 1 ? " (x" + count + ")" : "");
            }
            System.out.println("0: Done removing");
            
            int choice = InputHandler.getIntInput("Remove which topping? ", 0, currentToppings.size());
            
            if (choice == 0) {
                done = true;
            } else {
                ToppingOption toRemove = currentToppings.get(choice - 1);
                pizza.remove(toRemove);
                System.out.println("✅ Removed one " + toRemove.getName());
            }
        }
    }

    private List<ToppingOption> chooseToppings(PizzaSize pizzaSize) {
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
}
