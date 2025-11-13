package com.PizzaPoint.ui;

import com.PizzaPoint.orders.Order;

import com.PizzaPoint.orders.Receipt;
import com.PizzaPoint.ui.AddDrinkScreen;
import com.PizzaPoint.ui.pizza.AddPizzaScreen;
import com.PizzaPoint.util.InputHandler;

public class HomeScreen {
    private  final Order order;

    public HomeScreen(Order order) {
        this.order = order;
    }
    public void startHome() {
        boolean running = true;
        while (running) {
            System.out.println("====== PizzaPoint =======");
            System.out.println("1) New order");
            System.out.println("0) exit");
            int choice = InputHandler.getIntInput("choose an option: ", 0, 1);
            switch (choice) {
                case 1 -> runOrder();
                case 0 -> running = false;
            }

        }
    }

    private void runOrder() {

        String orderName = InputHandler.getStringInput("Enter name for the order: ");
        order.setCustomerName(orderName);

        boolean ordering = true;
        while (ordering) {
            System.out.println("""
                                    ---- new order----:
                                    1: Add Pizza
                                    2: Add drink
                                    3: View order
                                    4: Checkout
                                    5: Start new Order
                                    0: Back to home
                                   """);
            int selection = InputHandler.getIntInput("Choose an option: ", 0, 5);
            switch (selection) {
                case 1 -> new AddPizzaScreen(order).start();
                case 2 -> new AddDrinkScreen(order).start();
                case 3 -> {
                    if (order.getItems().isEmpty()) System.out.println("Your order is empty please add to your order");
                    else  System.out.println(new Receipt(order).generate());
                }
                case 4 -> {
                    boolean completed = new CheckOutScreen(order).checkOut();
                    if (completed) {
                        ordering = false;
                    }


                }
                case 5 -> {
                    order.clear();
                    orderName = InputHandler.getStringInput("Enter name for the order: ");
                    order.setCustomerName(orderName);
                }
                case 0 -> ordering = false;
            }
        }
    }
}
