package com.PizzaPoint.ui.pizza;

import com.PizzaPoint.core.enums.*;
import com.PizzaPoint.menu.Pizza;
import com.PizzaPoint.menu.topping.ToppingMenu;
import com.PizzaPoint.menu.topping.ToppingOption;
import com.PizzaPoint.orders.Order;
import com.PizzaPoint.util.InputHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddPizzaScreen {
    private final Order order;
    private final Scanner scanner = new Scanner(System.in);

    public AddPizzaScreen(Order order) {
        this.order = order;
    }

    public void start() {
        System.out.println("🍕 Build your pizza!");
        //choose size and crust
        PizzaSize size = chooseSize();
        CrustType crust = chooseCrust();
        SauceType sauce = chooseSauce();
        CheeseType cheese = chooseCheese();


        List<ToppingOption> toppings = chooseToppings();

        double basePrice = size.getBasePrice();
        Pizza pizza = new PizzaBuilder(basePrice, size, crust, sauce, cheese).build();
        new ToppingSelector(pizza).addMultiple(toppings);
        order.addItem(pizza);
        System.out.println("✅ your Pizza is added!");
        System.out.println(pizza.displayCustomization());
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

    private CrustType chooseCrust() {
        String prompt = """
                Select Crust:
                1: Stuffed Crust ($%.2f more)
                2: Thin Crust
                3: Thick Crust
                4: Pan Crust
                5: Regular Crust
                """;
        int choice = InputHandler.getIntInput(prompt, 1, 4);
        return switch (choice) {
            case 1 -> CrustType.STUFFED;
            case 2 -> CrustType.THIN;
            case 3 -> CrustType.THICK;
            case 4 -> CrustType.PAN;
            default -> CrustType.REGULAR;
        };
    }
    private SauceType chooseSauce() {
        String prompt ="""
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

    private CheeseType chooseCheese() {
        String prompt ="""
                select Cheese:
                1: Vegan
                2: Cheddar
                3: Parmesan
                4: Mozzarella
                """;
        int choice = InputHandler.getIntInput(prompt, 1, 3);
        return switch (choice) {
            case 1 -> CheeseType.VEGAN;
            case 2 -> CheeseType.CHEDDAR;
            case 3 -> CheeseType.PARMESAN;
            default -> CheeseType.MOZZARELLA;
        };

    }
    private List<ToppingOption> chooseToppings() {
        List<ToppingOption> selected = new ArrayList<>();
        List<ToppingOption> allToppings = new ArrayList<>(ToppingMenu.getAllToppings().values());

        int wantTopping = InputHandler.getIntInput("Do you want toppings? 1. Yes  2. No", 1, 2);
        if (wantTopping == 2) return selected;
        boolean done = false;
        while (!done) {
            int typeChoice = InputHandler.getIntInput("Choose topping type: 1. Veg  2. Meat  0. Done", 0, 2);
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
                    System.out.printf("%d: %s $%.2f\n", i + 1, t.getName(), t.getPrice());
                }
                System.out.println("0) Done with this category");
                int toppingChoice = InputHandler.getIntInput("Your choice: ", 1, filtered.size());
                if (toppingChoice == 0) categoryDone = true;
                else if (toppingChoice > 0 && toppingChoice <= filtered.size()) {
                    selected.add(filtered.get(toppingChoice - 1));
                    System.out.println(filtered.get(toppingChoice - 1).getName() + " added");
                } else {
                    System.out.println("Invalid choice");
                }
            }
        }
        return selected;
    }
}

