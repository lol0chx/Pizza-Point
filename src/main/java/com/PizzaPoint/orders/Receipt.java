package com.PizzaPoint.orders;

import com.PizzaPoint.core.interfaces.Orderable;
import com.PizzaPoint.menu.Pizza;
import com.PizzaPoint.menu.topping.ToppingOption;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Receipt {
    private final Order order;

    public Receipt (Order order) {
        this.order = order;
    }
    public String generate() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("----- Receipt -----\n");

        List<Orderable> items = order.getItems();
        for (Orderable item : items) {
            if (item instanceof Pizza pizza) {
                receipt.append(pizza.getName())
                        .append(" - Base: $").append(pizza.getBasePrice()).append("\n");
                receipt.append("Size: ").append(pizza.getSize()).append("\n");
                receipt.append("Crust: ").append(pizza.getCrust()).append("\n");

                Map<ToppingOption, Integer> toppings = pizza.getToppingsMap();
                if (toppings.isEmpty()) {
                    receipt.append("Toppings: None\n");
                } else {
                    receipt.append("Toppings: ");
                    toppings.forEach((topping, count) -> {
                        receipt.append(topping.getName());
                        if (count > 1) receipt.append(" x").append(count);
                        receipt.append(", ");
                    });
                    // Remove last comma
                    receipt.setLength(receipt.length() - 2);
                    receipt.append("\n");
                }

                receipt.append("Subtotal: $").append(pizza.calculatePrice()).append("\n\n");
            }
            // Later: handle drinks, desserts, etc.
        }

        double total = PriceCalculator.calculateTotal(items);
        receipt.append("------------------\n");
        receipt.append("Total: $").append(total).append("\n");

        return receipt.toString();
    }
    public void saveToFile(String filename) {
        String content = generate();
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
            System.out.println("Receipt saved to " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save receipt: " + e.getMessage());
        }
    }

}
