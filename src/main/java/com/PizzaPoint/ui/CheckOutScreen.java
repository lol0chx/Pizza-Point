package com.PizzaPoint.ui;

import com.PizzaPoint.core.interfaces.Orderable;
import com.PizzaPoint.orders.Order;
import com.PizzaPoint.orders.Receipt;
import com.PizzaPoint.util.InputHandler;

public class CheckOutScreen {

    private final Order order;

    public CheckOutScreen(Order order) {
        this.order = order;
    }

    public void checkOut() {
        if (order.getItems().isEmpty()) {
            System.out.println("Your order is empty ");
            return;
        }

        Receipt receipt = new Receipt(order);
        System.out.println("=======Receipt Preview");
        System.out.println(receipt.generate());
        //get total of the order
        double total = order.getItems().stream()
                .mapToDouble(Orderable::calculatePrice)
                .sum();

        int paymentChoice = InputHandler.getIntInput("Payment method: 1: Cash \n 2: Card: ", 1, 2);
        switch (paymentChoice) {
            case 1 -> handleCashPayment(total, receipt);
            //case 2 -> handleCardPayment();
        }

        System.out.println("Checkout Complete");
        receipt.saveToFile("check");
        order.clear();
    }

    private void handleCashPayment(double total, Receipt receipt) {
        double tendered = InputHandler.getDoubleInput(
                String.format("Total is $%.2f. Enter cash amount: ", total));

        if (tendered < total) {
            System.out.println("Amount is less than total. Please enter again.");
            handleCashPayment(total, receipt);
            return;
        }

        double change = tendered - total;
        System.out.printf("Change due: $%.2f%n", change);
        receipt.addNote(String.format("Paid in cash: tendered $%.2f, change $%.2f.", tendered, change));

    }
}
