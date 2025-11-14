package com.PizzaPoint.orders;

import com.PizzaPoint.core.interfaces.Orderable;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Orderable> items = new ArrayList<>();
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Adds a new item to the order
    public void addItem(Orderable item) {
        items.add(item);
    }

    // Removes an item from the order
    public void removeItem(Orderable item) {
        items.remove(item);
    }

    // Returns all items in the order
    public List<Orderable> getItems() {
        return items;
    }

     // Clears all items from the order
    public void clear() {
        items.clear();
    }
}
