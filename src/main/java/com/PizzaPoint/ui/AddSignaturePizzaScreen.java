package com.PizzaPoint.ui;

import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.core.enums.SauceType;
import com.PizzaPoint.menu.pizza.SignaturePizza;
import com.PizzaPoint.menu.pizza.SignaturePizzaMenu;
import com.PizzaPoint.menu.pizza.topping.ToppingOption;
import com.PizzaPoint.menu.pizza.topping.ToppingSelector;
import com.PizzaPoint.orders.Order;
import com.PizzaPoint.services.InputHandler;
import com.PizzaPoint.services.PizzaCustomizer;

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
        size = PizzaCustomizer.chooseSize();
        // Build the signature pizza with default toppings
        SignaturePizza pizza = SignaturePizzaMenu.create(selectedName, size);
        
        // Ask if they want to customize
        int customize = InputHandler.getIntInput("Do you want to customize this pizza? 1. Yes  2. No\n", 1, 2);
        
        if (customize == 1) {
            customizeSignaturePizza(pizza);
        }
        // Ask for sides
        int wantSides = InputHandler.getIntInput("Do you want to add sides? 1. Yes  2. No\n", 1, 2);
        if (wantSides == 1) {
            new AddSideScreen(pizza).start();
        }
        
        order.addItem(pizza);
        System.out.println("✅ " + pizza.getName() + " added!");
    }

    private void customizeSignaturePizza(SignaturePizza pizza) {
        boolean done = false;
        
        while (!done) {
            System.out.println("\n--- Customize " + pizza.getName() + " ---");
            System.out.println("\nCrust: " + pizza.getCrust());
            System.out.println("Sauce: " + pizza.getSauce());
            System.out.println("Current toppings:");
            pizza.getToppingsMap().forEach((topping, count) -> 
                System.out.println("  - " + topping.getName() + (count > 1 ? " x" + count : ""))
            );
            System.out.println("\n1: Change Crust");
            System.out.println("2: Change Sauce");
            System.out.println("3: Add Toppings");
            System.out.println("4: Remove Toppings");
            System.out.println("0: Done Customizing");
            
            int choice = InputHandler.getIntInput("Your choice: ", 0, 4);
            
            switch (choice) {
                case 1 -> changeCrust(pizza);
                case 2 -> changeSauce(pizza);
                case 3 -> addToppingsToSignature(pizza);
                case 4 -> removeToppingsFromSignature(pizza);
                case 0 -> done = true;
            }
        }
    }

    private void changeCrust(SignaturePizza pizza) {
        CrustType newCrust = PizzaCustomizer.chooseCrust(pizza.getSize());
        pizza.setCrust(newCrust);
        System.out.println("✅ Crust changed to " + newCrust);
    }

    private void changeSauce(SignaturePizza pizza) {
        SauceType newSauce = PizzaCustomizer.chooseSauce();
        pizza.setSauce(newSauce);
        System.out.println("✅ Sauce changed to " + newSauce);
    }

    private void addToppingsToSignature(SignaturePizza pizza) {
        System.out.println("\nAdding toppings to " + pizza.getName());
        List<ToppingOption> newToppings = PizzaCustomizer.chooseToppings(pizza.getSize());
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
}
