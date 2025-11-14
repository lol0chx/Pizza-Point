package com.PizzaPoint.ui;

import com.PizzaPoint.orders.Order;
import com.PizzaPoint.orders.Receipt;
import com.PizzaPoint.services.InputHandler;

public class NewOrderScreen {


    private  final Order order;
    public NewOrderScreen(Order order) {
        this.order = order;
    }
    // Main order menu loop for adding items and managing the cart
    public void startOrder() {
        String orderName = InputHandler.getStringInput("Enter name for the order: ");
        order.setCustomerName(orderName);

        boolean ordering = true;
        while (ordering) {
            System.out.println("""
                                    🛒 ---- New Order ----:
                                    1: Add Custom Pizza
                                    2: Add Signature Pizza
                                    3: Add drink
                                    4: Add Garlic Knots
                                    5: View order
                                    6: Checkout
                                    7: Start new Order
                                    0: Back to home
                                   """);
            int selection = InputHandler.getIntInput("Choose an option: ", 0, 7);
            switch (selection) {
                case 1 -> new AddPizzaScreen(order).buildCustomPizza();
                case 2 -> new AddSignaturePizzaScreen(order).start();
                case 3 -> new AddDrinkScreen(order).start();
                case 4 -> new AddGarlicKnotsScreen(order).start();
                case 5 -> {
                    if (order.getItems().isEmpty()) {
                        System.out.println("🛒 Your order is empty please add to your order");
                    } else {
                        System.out.println(new Receipt(order).generate());
                        int removeChoice = InputHandler.getIntInput("Do you want to remove any item? \n1: Yes \n2: No\n", 1, 2);
                        if (removeChoice == 1) {
                            removeItemFromOrder();
                        }
                    }
                }
                case 6 -> {
                    boolean completed = new CheckOutScreen(order).checkOut();
                    if (completed) {
                        ordering = false;
                    }


                }
                case 7 -> {
                    order.clear();
                    orderName = InputHandler.getStringInput("Enter name for the order: ");
                    order.setCustomerName(orderName);
                }
                case 0 -> ordering = false;
            }
        }
    }
    
    // Displays items and allows user to remove one by number
    private void removeItemFromOrder() {
        System.out.println("\n🗑️ --- Remove item number? ---");
        for (int i = 0; i < order.getItems().size(); i++) {
            System.out.println("Item Number " + "[" + (i + 1) + "]");
        }
        
        int choice = InputHandler.getIntInput("Select item to remove (0 to cancel): ", 0, order.getItems().size());
        
        if (choice > 0) {
            var removedItem = order.getItems().get(choice - 1);
            order.removeItem(removedItem);
            System.out.println("✅ Item removed from order!");
        }
    }
}
