package com.PizzaPoint.util;

import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    // Get an integer input within a range
    public static int getIntInput(String prompt, int min, int max) {
        int choice;
        while (true) {
            System.out.print(prompt);
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.println("Please enter a choice between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }


    public static String getCVVInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String cvv = scanner.nextLine().trim();
            if (cvv.matches("\\d{3}")) {
                return cvv;
            }
            System.out.println("Please enter a valid CVV (must be 3 digits).");
        }
    }

    public static String getStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty() && value.matches("[a-zA-Z\\s]+")) {
                return value;
            }
            System.out.println("Please enter a valid Input.");
        }
    }
    public static String getCardNumberInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String cardNumber = scanner.nextLine().trim();
            cardNumber = cardNumber.replaceAll("[\\s-]", ""); // Remove spaces and dashes
            if (cardNumber.matches("\\d{16}")) {
                return cardNumber;
            }
            System.out.println("Please enter a valid card number (must be 16 digits).");
        }
    }

    // Get a choice from a list of options
    public static <T> T getListInput(String prompt, List<T> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        int choice = getIntInput(prompt, 1, options.size());
        return options.get(choice - 1);
    }

    // Get any double input
    public static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (e.g., 12.50).");
            }
        }
    }
}
