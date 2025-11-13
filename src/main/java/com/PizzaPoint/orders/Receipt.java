package com.PizzaPoint.orders;

import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.core.interfaces.Orderable;
import com.PizzaPoint.menu.drink.Drink;
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
    private String itemName;
    private final List<String> notes = new ArrayList<>();

    public Receipt (Order order) {
        this.order = order;
    }

    public String generate() {
        int itemNumber = 1;
        StringBuilder receipt = new StringBuilder();
        receipt.append("--------------------\n");
       // Add customer name or just set it to guest
        String customerName = order.getCustomerName();
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("'Date:' yyyy-MM-dd 'Time:' HH-mm-ss"));
        if (customerName != null && !customerName.isBlank()) {
            receipt.append("Date and Time: ").append(dateTime);
            receipt.append("\nCustomer: ").append(customerName).append("\n");
            receipt.append("Order #: ").append(order.getItems().size()).append(" item(s)\n");
            receipt.append("--------------------\n");
        }

        List<Orderable> items = order.getItems();
        for (Orderable item : items) {
            if (item instanceof Pizza pizza) {
                itemName = "pizza";
                receipt.append("\n[").append(itemNumber++).append("] ").append(pizza.getName());
                receipt.append("\nSize: ").append(pizza.getSize()).append(" $").append(pizza.getBasePrice()).append("\n");
                receipt.append("Crust: ").append(pizza.getCrust());
                if (pizza.getCrust().getExtraCost() > 0) {
                    receipt.append(String.format(" (+$%.2f)", pizza.getCrust().getExtraCost()));
                }
                receipt.append("\nBase Sauce: ").append(pizza.getSauce()).append("\n");
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
                                .append(" ($").append(String.format("%.2f", adjustedPrice));
                        if (count > 1) {
                            receipt.append(" x").append(count);
                        }
                        receipt.append(")");
                        receipt.append(", ");
                    });
                    // Remove last comma
                    receipt.setLength(receipt.length() - 2);
                    receipt.append("\n");
                }
                // Display sides if any
                if (!pizza.getSides().isEmpty()) {
                    receipt.append("Sides: ");
                    for (int i = 0; i < pizza.getSides().size(); i++) {
                        receipt.append(pizza.getSides().get(i).getName());
                        if (i < pizza.getSides().size() - 1) {
                            receipt.append(", ");
                        }
                    }
                    receipt.append("\n");
                }
                
                receipt.append(itemName).append(" Total: $").append(String.format("%.2f", pizza.calculatePrice()  )).append("\n-----------------------");
            }
            else if (item instanceof Drink drink) {
                receipt.append("\n[").append(itemNumber++).append("] ").append(drink.getName()).append(" ");
                receipt.append(drink.getSize()).append(" ");
               receipt.append(drink.calculatePrice()).append("\n");
               receipt.append("------------------------------");
               // receipt.append(itemName).append("(s) Total: $").append(String.format("%.2f", drink.calculatePrice()  )).append("\n\n");
                itemName = "Drink";
            }
            // Later: handle drinks, desserts, etc.
        }

        double total = PriceCalculator.calculateTotal(items);
        receipt.append("\n------------------\n");
        receipt.append(" Sub Total: $").append(String.format("%.2f", total)).append("\n");

        if(CheckOutScreen.getTendered() > 0) {
            receipt.append("customer paid Cash: ").append(CheckOutScreen.getTendered());
            receipt.append("\nChange: ").append(CheckOutScreen.getChange());
        }

        if (!notes.isEmpty()) {
            notes.forEach(entry -> receipt.append("- ").append(entry).append("\n"));
        }

        return receipt.toString();
    }

    public void addNote(String note) {
        if (note == null || note.isBlank()) {
            return;
        }
        notes.add(note);
    }

    public void saveToFile(String filename) {
        String content = generate();
        try (FileWriter writer = new FileWriter("receipts/" + filename)) {
            writer.write(content);
            System.out.println("Receipt saved");
        } catch (IOException e) {
            System.err.println("Failed to save receipt: " + e.getMessage());
        }
    }

}
