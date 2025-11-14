package com.PizzaPoint.ui;

import com.PizzaPoint.menu.GarlicKnots;
import com.PizzaPoint.orders.Order;
import com.PizzaPoint.services.InputHandler;

public class AddGarlicKnotsScreen {
    private final Order order;

    public AddGarlicKnotsScreen(Order order) {
        this.order = order;
    }

    public void start() {
        System.out.println("\n--- Garlic Knots ($5.00) ---");
        int confirm = InputHandler.getIntInput("Add Garlic Knots to order? 1. Yes  2. No\n", 1, 2);
        
        if (confirm == 1) {
            GarlicKnots garlicKnots = new GarlicKnots();
            order.addItem(garlicKnots);
            System.out.println("✅ Garlic Knots added to order!");
        }
    }
}
