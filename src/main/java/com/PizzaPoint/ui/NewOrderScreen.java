package com.PizzaPoint.ui;

import com.PizzaPoint.orders.Order;
import com.PizzaPoint.orders.Receipt;
import com.PizzaPoint.ui.pizza.AddPizzaScreen;
import com.PizzaPoint.ui.pizza.AddSignaturePizzaScreen;
import com.PizzaPoint.util.InputHandler;

public class NewOrderScreen {


    private  final Order order;
    public NewOrderScreen(Order order) {
        this.order = order;
    }
    public void startOrder() {

        String orderName = InputHandler.getStringInput("Enter name for the order: ");
        order.setCustomerName(orderName);

        boolean ordering = true;
        while (ordering) {
            System.out.println("""
                                    ---- new order----:
                                    1: Add Custom Pizza
                                    2: Add Signature Pizza
                                    3: Add drink
                                    4: View order
                                    5: Checkout
                                    6: Start new Order
                                    0: Back to home
                                   """);
            int selection = InputHandler.getIntInput("Choose an option: ", 0, 6);
            switch (selection) {
                case 1 -> new AddPizzaScreen(order).buildCustomPizza();
                case 2 -> new AddSignaturePizzaScreen(order).start();
                case 3 -> new AddDrinkScreen(order).start();
                case 4 -> {
                    if (order.getItems().isEmpty()) System.out.println("Your order is empty please add to your order");
                    else  System.out.println(new Receipt(order).generate());
                }
                case 5 -> {
                    boolean completed = new CheckOutScreen(order).checkOut();
                    if (completed) {
                        ordering = false;
                    }


                }
                case 6 -> {
                    order.clear();
                    orderName = InputHandler.getStringInput("Enter name for the order: ");
                    order.setCustomerName(orderName);
                }
                case 0 -> ordering = false;
            }
        }
    }
}
