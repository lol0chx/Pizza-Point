package com.PizzaPoint.ui;

import com.PizzaPoint.orders.Order;


import com.PizzaPoint.util.InputHandler;

public class HomeScreen {



    public void startHome() {
        boolean running = true;
        while (running) {
            System.out.println("====== PizzaPoint =======");
            System.out.println("1) New order");
            System.out.println("0) exit");
            int choice = InputHandler.getIntInput("choose an option: ", 0, 1);
            switch (choice) {
                case 1 -> {
                    Order order = new Order();
                    new NewOrderScreen(order).startOrder();
                }
                case 0 -> running = false;
            }

        }
    }


}
