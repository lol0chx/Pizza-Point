package com.PizzaPoint.orders;

import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.core.interfaces.Orderable;
import com.PizzaPoint.menu.drink.Drink;
import com.PizzaPoint.menu.pizza.Pizza;
import com.PizzaPoint.menu.pizza.topping.ToppingOption;
import com.PizzaPoint.util.PriceCalculator;

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
                receipt.append(pizza.getName());
                receipt.append("\nSize: ").append(pizza.getSize()).append(" $").append(pizza.getBasePrice()).append("\n");
                receipt.append("Crust: ").append(pizza.getCrust());
                if (pizza.getCrust().getExtraCost() > 0) {
                    receipt.append(String.format(" (+$%.2f)", pizza.getCrust().getExtraCost()));
                }
                receipt.append("Base Sauce: ").append(pizza.getSauce()).append("\n");
                receipt.append("Cheese type: ").append(pizza.getCheese()).append("\n");
                Map<ToppingOption, Integer> toppings = pizza.getToppingsMap();
                if (toppings.isEmpty()) {
                    receipt.append("Toppings: None\n");
                } else {
                    receipt.append("Toppings: ");
                    PizzaSize size = pizza.getSize();
                    double multiplier = size != null ? size.getToppingMultiplier() : 1.0;
                    toppings.forEach((topping, count) -> {
                        double adjustedPrice = topping.getPrice() * multiplier;
                        receipt.append(topping.getName())
                                .append(" ($").append(String.format("%.2f", adjustedPrice)).append(" extra)");
                        if (count > 1) receipt.append(" x").append(count);
                        receipt.append(", ");
                    });

                    // Remove last comma
                    receipt.setLength(receipt.length() - 2);
                    receipt.append("\n");
                }

                receipt.append("Subtotal: $").append(String.format("%.2f", pizza.calculatePrice()  )).append("\n\n");
            }
            else if (item instanceof Drink drink) {
                receipt.append(drink.getName());
                receipt.append(drink.getSize());
                receipt.append(drink.calculatePrice());
            }
            // Later: handle drinks, desserts, etc.
        }
        double total = PriceCalculator.calculateTotal(items);
        receipt.append("------------------\n");
        receipt.append("Total: $").append(String.format("%.2f", total)).append("\n");

        return receipt.toString();
    }
    public void saveToFile(String filename) {
        String content = generate();
        try (FileWriter writer = new FileWriter("receipts/" + filename)) {
            writer.write(content);
            System.out.println("Receipt saved to " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save receipt: " + e.getMessage());
        }
    }

}
