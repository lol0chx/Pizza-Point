package com.PizzaPoint.ui;

import com.PizzaPoint.core.enums.*;
import com.PizzaPoint.menu.drink.Drink;
import com.PizzaPoint.menu.drink.DrinkMenu;
import com.PizzaPoint.menu.pizza.Pizza;
import com.PizzaPoint.menu.pizza.PizzaBuilder;
import com.PizzaPoint.menu.pizza.topping.ToppingOption;
import com.PizzaPoint.menu.pizza.topping.ToppingSelector;
import com.PizzaPoint.orders.Order;
import com.PizzaPoint.util.InputHandler;

import java.util.List;

public class AddDrinkScreen {

    private final Order order;
    //declare outside to use on methods so i can display price of topping based on size
    DrinkSize size;
    public void start() {
        System.out.println("Choose your Drink Size");
        //choose size and crust
        size = chooseSize();
        String drinkName = chooseDrink();
        Drink drink = new Drink(drinkName,size);
        order.addItem(drink);
        System.out.println("Your Drink is Added");



    }
    public AddDrinkScreen(Order order) {

        this.order = order;
    }

    private String chooseDrink() {
        List<String> drinks = DrinkMenu.DRINKS;
        System.out.println("Pick a drink");
        for (int i =0; i<drinks.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, drinks.get(i));
        }
        int choice = InputHandler.getIntInput("Your choice: ", 1,drinks.size());
        return drinks.get(choice -1);
    }
    private DrinkSize chooseSize() {
        String prompt = """
                select size:
                1: Small
                2: Medium
                3: Large
                """;
        int choice = InputHandler.getIntInput(prompt, 1, 3);
        return switch (choice) {
            case 1 -> DrinkSize.SMALL;
            case 2 -> DrinkSize.MEDIUM;
            default -> DrinkSize.LARGE;
        };
    }
}
