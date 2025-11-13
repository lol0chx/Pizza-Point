package com.PizzaPoint.ui;

import com.PizzaPoint.core.enums.*;
import com.PizzaPoint.menu.pizza.Pizza;
import com.PizzaPoint.menu.pizza.topping.ToppingMenu;
import com.PizzaPoint.menu.pizza.topping.ToppingOption;
import com.PizzaPoint.menu.pizza.topping.ToppingSelector;
import com.PizzaPoint.orders.Order;
import com.PizzaPoint.services.InputHandler;
import com.PizzaPoint.menu.pizza.PizzaBuilder;
import java.util.ArrayList;
import java.util.List;

public class AddPizzaScreen {
    private final Order order;
    //declare outside to use on methods so i can display price of topping based on size
    PizzaSize size;

    public AddPizzaScreen(Order order) {

        this.order = order;
    }

    public void buildCustomPizza() {
        System.out.println("\n🍕 Build your pizza!");
        //choose size and crust
        size = chooseSize();
        CrustType crust = chooseCrust();
        SauceType sauce = chooseSauce();
        
        // Ask for extra sauce
        int wantExtraSauce = InputHandler.getIntInput("Do you want extra sauce? 1. Yes  2. No\n", 1, 2);
        boolean extraSauce = (wantExtraSauce == 1);
        CheeseType cheese = chooseCheese();
        // Ask for extra cheese
        int wantExtraCheese = InputHandler.getIntInput("Do you want extra cheese? 1. Yes  2. No\n", 1, 2);
        boolean extraCheese = (wantExtraCheese == 1);

        List<ToppingOption> toppings = chooseToppings(size);

        double basePrice = size.getBasePrice();
        Pizza pizza = new PizzaBuilder(basePrice, size, crust, sauce, cheese).build();
        
        // Add extras if selected
        ToppingSelector selector = new ToppingSelector(pizza);
        if (extraSauce) {
            ToppingOption sauceTopping = ToppingMenu.getAllToppings().get("Extra Sauce");
            if (sauceTopping != null) selector.addTopping(sauceTopping);
        }
        if (extraCheese) {
            ToppingOption cheeseTopping = ToppingMenu.getAllToppings().get("Extra Cheese");
            if (cheeseTopping != null) selector.addTopping(cheeseTopping);
        }
        
        selector.addMultiple(toppings);
        order.addItem(pizza);
        // ask for sides after everything is picked
        int wantSides = InputHandler.getIntInput("Do you want to add sides? 1. Yes  2. No\n", 1, 2);
        if (wantSides == 1) {
            new AddSideScreen(pizza).start();
        }

        System.out.println("✅ your Pizza is added!");
       // System.out.println(pizza.displayCustomization());
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
        double stuffedCrustPrice = CrustType.STUFFED.getExtraCost() * size.getToppingMultiplier();
        String prompt = String.format("""
                Select Crust:
                1: Stuffed Crust ($%.2f more)
                2: Thin Crust
                3: Thick Crust
                4: Pan Crust
                5: Regular Crust
                """, stuffedCrustPrice);
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
        int choice = InputHandler.getIntInput(prompt, 1, 4);
        return switch (choice) {
            case 1 -> CheeseType.VEGAN;
            case 2 -> CheeseType.CHEDDAR;
            case 3 -> CheeseType.PARMESAN;
            default -> CheeseType.MOZZARELLA;
        };
        //ask for extra cheese after cheese is selected
    }
    double multiplier;
    private List<ToppingOption> chooseToppings(PizzaSize pizzaSize) {
        List<ToppingOption> selected = new ArrayList<>();
        List<ToppingOption> allToppings = new ArrayList<>(ToppingMenu.getAllToppings().values());
         multiplier = pizzaSize != null ? pizzaSize.getToppingMultiplier() : 1.0;

        int wantTopping = InputHandler.getIntInput("Do you want toppings? 1. Yes  2. No\n", 1, 2);
        if (wantTopping == 2) return selected;
        boolean done = false;
        while (!done) {
            int typeChoice = InputHandler.getIntInput("Choose topping type: 1. Veg  2. Meat  0. Done\n", 0, 2);
            if (typeChoice == 0) break;
            ToppingCategory selectedCategory = (typeChoice == 1) ? ToppingCategory.VEG : ToppingCategory.MEAT;
            //show toppings list of selected size
            List<ToppingOption> filtered = allToppings.stream()
                    .filter(t -> t.getCategory() == selectedCategory)
                    .toList();
            boolean categoryDone = false;
            while (!categoryDone) {
                System.out.println("Available " + selectedCategory + " toppings:");
                for (int i = 0; i < filtered.size(); i++) {
                    ToppingOption t = filtered.get(i);
                    //adjust the price of the toppings according to size
                    double adjustedPrice = t.getPrice() * multiplier;
                    System.out.printf("%d: %s $%.2f \n", i + 1, t.getName(), adjustedPrice);
                }
                System.out.println("0) Done with this category");
                int toppingChoice = InputHandler.getIntInput("Your choice: ", 0, filtered.size());
                if (toppingChoice == 0) categoryDone = true;
                else if (toppingChoice > 0 && toppingChoice <= filtered.size()) {
                    ToppingOption chosen = filtered.get(toppingChoice - 1);
                    String portionMessage = String.format("how many portions of %s 1-10\n", chosen);
                    int portionCount = InputHandler.getIntInput(portionMessage,1, 10 );

                    double adjustedPrice = chosen.getPrice() * multiplier;
                    // Add the topping 'portionCount' times
                    for (int i = 0; i < portionCount; i++) {
                        selected.add(chosen);
                    }

                    System.out.printf("%d X %s added ($%.2f X %d )\n",portionCount, chosen.getName(), adjustedPrice,portionCount);
                } else {
                    System.out.println("Invalid choice");
                }
            }
        }
        return selected;
    }
}

