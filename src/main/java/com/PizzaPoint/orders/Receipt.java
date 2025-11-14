package com.PizzaPoint.orders;

import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.core.interfaces.Orderable;
import com.PizzaPoint.menu.drink.Drink;
import com.PizzaPoint.menu.GarlicKnots;
import com.PizzaPoint.menu.pizza.Pizza;
import com.PizzaPoint.menu.pizza.topping.ToppingOption;
import com.PizzaPoint.ui.CheckOutScreen;
import com.PizzaPoint.services.PriceCalculator;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Receipt {
    private final Order order;
    private final List<String> notes = new ArrayList<>();

    public Receipt (Order order) {
        this.order = order;
    }

    // Generates formatted receipt text with header, items, and footer
    public String generate() {
        return generate(false);
    }
    
    // Generates formatted receipt text with optional thank you message
    public String generate(boolean includeThanks) {
        int itemNumber = 1;
        StringBuilder receipt = new StringBuilder();
        
        // Add customer name or just set it to guest
        String customerName = order.getCustomerName();
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        receipt.append("\n");
        receipt.append("═══════════════════════════════════════\n");
        receipt.append("          🍕 PIZZAPOINT 🍕\n");
        receipt.append("═══════════════════════════════════════\n\n");
        
        if (customerName != null && !customerName.isBlank()) {
            receipt.append("  Customer: ").append(customerName).append("\n");
            receipt.append("  Date: ").append(dateTime).append("\n");
            receipt.append("  Items: ").append(order.getItems().size()).append("\n");
            receipt.append("\n═══════════════════════════════════════\n");
        }

        // Iterate through all items and format each type
        List<Orderable> items = order.getItems();
        for (Orderable item : items) {
            // Format pizza with size, crust, toppings, and sides
            if (item instanceof Pizza pizza) {
                receipt.append("\n  🍕 [").append(itemNumber++).append("] ").append(pizza.getName()).append("\n");
                receipt.append("     Size: ").append(pizza.getSize()).append(" - $").append(String.format("%.2f", pizza.getBasePrice())).append("\n");
                receipt.append("     Crust: ").append(pizza.getCrust());
                if (pizza.getCrust().getExtraCost() > 0) {
                    receipt.append(String.format(" (+$%.2f)", pizza.getCrust().getExtraCost()));
                }
                receipt.append("\n     Sauce: ").append(pizza.getSauce()).append("\n");
                receipt.append("     Cheese: ").append(pizza.getCheese()).append("\n");
                Map<ToppingOption, Integer> toppings = pizza.getToppingsMap();
                if (toppings.isEmpty()) {
                    receipt.append("     Toppings: None\n");
                } else {
                    receipt.append("     Toppings:\n");
                    // Apply size multiplier to topping prices
                    PizzaSize size = pizza.getSize();
                    double multiplier = size != null ? size.getToppingMultiplier() : 1.0;
                    toppings.forEach((topping, count) -> {
                        double adjustedPrice = topping.getPrice() * multiplier;
                        receipt.append("       • ").append(topping.getName())
                                .append(" ($").append(String.format("%.2f", adjustedPrice));
                        if (count > 1) {
                            receipt.append(" x").append(count);
                        }
                        receipt.append(")\n");
                    });
                }
                // Display sides if any
                if (!pizza.getSides().isEmpty()) {
                    receipt.append("     Sides: ");
                    for (int i = 0; i < pizza.getSides().size(); i++) {
                        receipt.append(pizza.getSides().get(i).getName());
                        if (i < pizza.getSides().size() - 1) {
                            receipt.append(", ");
                        }
                    }
                    receipt.append("\n");
                }
                
                receipt.append("     ───────────────────\n");
                receipt.append("     Total: $").append(String.format("%.2f", pizza.calculatePrice())).append("\n");
            }
            else if (item instanceof Drink drink) {
                receipt.append("\n  🥤 [").append(itemNumber++).append("] ").append(drink.getName()).append("\n");
                receipt.append("     Size: ").append(drink.getSize()).append("\n");
                receipt.append("     Total: $").append(String.format("%.2f", drink.calculatePrice())).append("\n");
            }
            else if (item instanceof GarlicKnots garlicKnots) {
                receipt.append("\n  🧄 [").append(itemNumber++).append("] ").append(garlicKnots.getName()).append("\n");
                receipt.append("     Total: $").append(String.format("%.2f", garlicKnots.calculatePrice())).append("\n");
            }
        }

        // Calculate and display total
        double total = PriceCalculator.calculateTotal(items);
        receipt.append("\n═══════════════════════════════════════\n");
        receipt.append("  SUBTOTAL: $").append(String.format("%.2f", total)).append("\n");

        // Add cash payment details if applicable
        if(CheckOutScreen.getTendered() > 0) {
            receipt.append("  Cash Tendered: $").append(String.format("%.2f", CheckOutScreen.getTendered())).append("\n");
            receipt.append("  Change: $").append(String.format("%.2f", CheckOutScreen.getChange())).append("\n");
        }

        if (!notes.isEmpty()) {
            receipt.append("\n");
            notes.forEach(entry -> receipt.append("  ").append(entry).append("\n"));
        }
        
        if (includeThanks) {
            receipt.append("═══════════════════════════════════════\n");
            receipt.append("     Thank you for your order! 🍕\n");
            receipt.append("═══════════════════════════════════════\n");
        } else {
            receipt.append("═══════════════════════════════════════\n");
        }

        return receipt.toString();
    }

    // Adds a note to the receipt (e.g., payment method details)
    public void addNote(String note) {
        if (note == null || note.isBlank()) {
            return;
        }
        notes.add(note);
    }

    // Saves receipt to file in receipts directory
    public void saveToFile(String filename) {
        String content = generate(true); // Include thank you message when saving
        try (FileWriter writer = new FileWriter("receipts/" + filename)) {
            writer.write(content);
            System.out.println("Receipt saved\n\n\n\n\n\n\n\n\n\n\n");
        } catch (IOException e) {
            System.err.println("Failed to save receipt: " + e.getMessage());
        }
    }

}
