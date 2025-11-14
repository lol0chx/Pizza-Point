package com.PizzaPoint.ui;

import com.PizzaPoint.core.interfaces.Orderable;
import com.PizzaPoint.orders.Order;
import com.PizzaPoint.orders.Receipt;
import com.PizzaPoint.services.InputHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckOutScreen {
    static double  tendered;
    static double change;
    static String receiptId;

    public static String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        CheckOutScreen.receiptId = receiptId;
    }

    public static double getTendered() {
        return tendered;
    }

    public static double getChange() {
        return change;
    }

    private final Order order;

    public CheckOutScreen(Order order) {
        this.order = order;
    }

    public boolean checkOut() {
        // Reset static variables from previous transactions
        tendered = 0;
        change = 0;
        
        if (order.getItems().isEmpty()) {
            System.out.println("Your order is empty ");
            return false;
        }

        Receipt receipt = new Receipt(order);
        System.out.println("=======Receipt Preview========");
        System.out.println(receipt.generate());
        //get total of the order
        double total = order.getItems().stream()
                .mapToDouble(Orderable::calculatePrice)
                .sum();


        while (true) {
            int paymentChoice = InputHandler.getIntInput("Payment method: \n1: Cash \n2: Card \n0: Back\n", 0, 2);
            switch (paymentChoice) {
                case 1 -> {
                    handleCashPayment(total, receipt);
                    return completeCheckout(receipt);
                }
                case 2 -> {
                    handleCardPayment(total, receipt);
                    return completeCheckout(receipt);
                }
                case 0 -> {
                    return false;
                }
            }
        }
    }

    private boolean completeCheckout(Receipt receipt) {
        receiptId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        System.out.println("Checkout Complete");
        receipt.saveToFile(receiptId);
        order.clear();
        return true;
    }
    private void handleCardPayment(double total, Receipt receipt) {
        // Reset cash payment variables for card transactions
        tendered = 0;
        change = 0;
        
        System.out.println("\n--- Card Payment ---");
        System.out.printf("Total amount: $%.2f%n", total);

        // Get card number (16 digits)
        String cardNumber;
        String cardholderName = InputHandler.getStringInput("Enter cardholder name: ");
        while (true) {
            cardNumber = InputHandler.getCardNumberInput("Enter card Number");
            if (cardNumber.matches("\\d{16}")) {
                break;
            }
            System.out.println("Please enter a valid card number (must be 16 digits).");
        }

        // Get cardholder name


        int expiryMonth;
        while (true) {
            expiryMonth = InputHandler.getIntInput("Enter expiry month (01-12): ", 1, 12);
            if (expiryMonth >= 1 && expiryMonth <= 12) {
                break;
            }
            System.out.println("Please enter a valid month (01-12).");
        }
        // Get expiry year 2025-2035
        int currentYear = java.time.Year.now().getValue();
        int expiryYear;
        while (true) {
            expiryYear = InputHandler.getIntInput("Enter expiry year (YYYY): ", currentYear, currentYear + 15);
            if (expiryYear >= currentYear && expiryYear <= currentYear + 15) {
                break;
            }
            System.out.println("Please enter a valid expiry date");
        }
        // Get CVV (3 digits)
        String cvv;
        while (true) {
            cvv = InputHandler.getCVVInput("Enter CVV code (3 digits)");
            if (cvv.matches("\\d{3}")) {
                break;
            }
            System.out.println("Please enter a valid CVV (must be 3 digits).");
        }
        // (show last 4 digits only)
        String maskedCard = "**** **** **** " + cardNumber.substring(12);

        System.out.println("Card ending in " + cardNumber.substring(12) + " charged $" + String.format("%.2f", total));
        receipt.addNote(String.format("\nPaid by card: %s, \nCardholder: %s", maskedCard, cardholderName));
    }
    private void handleCashPayment(double total, Receipt receipt) {
         tendered = InputHandler.getDoubleInput(
                String.format("Total is $%.2f. Enter cash amount: \n", total));
        if (tendered < total) {
            System.out.println("Amount is less than total. Please enter again.");
            handleCashPayment(total, receipt);
            return;
        }
        change = tendered - total;
        System.out.printf("Change due: $%.2f%n", change);

    }
}
