package com.PizzaPoint.ui;
import com.PizzaPoint.orders.Order;
import com.PizzaPoint.services.InputHandler;
public class HomeScreen {

    public void startHome() {
        boolean running = true;
        while (running) {
            System.out.println("====== PizzaPoint =======");
            System.out.println("1: New order");
            System.out.println("2: Ledger");
            System.out.println("0) exit");
            int choice = InputHandler.getIntInput("choose an option: ", 0, 2);
            switch (choice) {
                case 1 -> {
                    Order order = new Order();
                    new NewOrderScreen(order).startOrder();
                }
                //case 2 -> LedgerScreen;
                case 0 -> running = false;
            }

        }
    }


}
