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

    public void addItem(Orderable item) {
        items.add(item);
    }

    public void removeItem(Orderable item) {
        items.remove(item);
    }

   //get all items in order
    public List<Orderable> getItems() {
        return items;
    }

    //clear all orders
    public void clear() {
        items.clear();
    }
}
