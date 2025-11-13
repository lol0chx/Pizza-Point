package com.PizzaPoint.ui;

import com.PizzaPoint.menu.pizza.side.Side;
import com.PizzaPoint.menu.pizza.side.SideMenu;
import com.PizzaPoint.orders.Order;
import com.PizzaPoint.util.InputHandler;

import java.util.ArrayList;
import java.util.List;

public class AddSideScreen {
    private final Order order;

    public AddSideScreen(Order order) {
        this.order = order;
    }

    public void start() {
        List<Side> allSides = new ArrayList<>(SideMenu.getAllSides().values());
        System.out.println("\n--- Select Sides (Free) ---");

        for (int i = 0; i < allSides.size(); i++) {
            System.out.printf("%d: %s\n", i + 1, allSides.get(i).getName());
        }

        int choice = InputHandler.getIntInput("Choose a side (0 to skip): ", 0, allSides.size());

        if (choice > 0) {
            Side selected = allSides.get(choice - 1);
            order.addItem(selected);
            System.out.println("✅ " + selected.getName() + " added!");
        }
    }
}
