package com.PizzaPoint.ui;

import com.PizzaPoint.core.enums.*;
import com.PizzaPoint.menu.pizza.Pizza;
import com.PizzaPoint.menu.pizza.topping.ToppingMenu;
import com.PizzaPoint.menu.pizza.topping.ToppingOption;
import com.PizzaPoint.menu.pizza.topping.ToppingSelector;
import com.PizzaPoint.orders.Order;
import com.PizzaPoint.services.InputHandler;
import com.PizzaPoint.services.PizzaBuilder;
import com.PizzaPoint.services.PizzaCustomizer;
import java.util.List;

public class AddPizzaScreen {
    private final Order order;
    //declare outside to use on methods so i can display price of topping based on size
    PizzaSize size;

    public AddPizzaScreen(Order order) {

        this.order = order;
    }

    // Guides user through custom pizza creation with size, toppings, and sides
    public void buildCustomPizza() {
        System.out.println("\n🍕 Build your pizza!");
        size = PizzaCustomizer.chooseSize();
        CrustType crust = PizzaCustomizer.chooseCrust(size);
        SauceType sauce = PizzaCustomizer.chooseSauce();
        
        // Ask for extra sauce
        int wantExtraSauce = InputHandler.getIntInput("Do you want extra sauce? 1. Yes  2. No\n", 1, 2);
        boolean extraSauce = (wantExtraSauce == 1);
        CheeseType cheese = PizzaCustomizer.chooseCheese();
        // Ask for extra cheese
        int wantExtraCheese = InputHandler.getIntInput("Do you want extra cheese? 1. Yes  2. No\n", 1, 2);
        boolean extraCheese = (wantExtraCheese == 1);

        List<ToppingOption> toppings = PizzaCustomizer.chooseToppings(size);

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
}

