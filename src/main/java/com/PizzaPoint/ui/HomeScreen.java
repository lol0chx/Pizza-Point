package com.PizzaPoint.ui;
import com.PizzaPoint.orders.Order;
import com.PizzaPoint.services.InputHandler;
public class HomeScreen {

    public void startHome() {
        boolean running = true;
        while (running) {
            System.out.println("🍕 PIZZAPOINT 🍕");
            System.out.println("1: 🛒 New Order");
            System.out.println("0: 🚪 Exit");
            int choice = InputHandler.getIntInput("Choose an option: ", 0, 1);
            switch (choice) {
                case 1 -> {
                    Order order = new Order();
                    new NewOrderScreen(order).startOrder();
                }
                case 0 -> {
                    System.out.println("Thank you! 🍕 See you soon! 👋");
                    running = false;
                }
            }

        }
    }
}
