package com.PizzaPoint.services;

import com.PizzaPoint.core.interfaces.Orderable;

import java.util.List;

public class PriceCalculator {
    // Calculates total price by summing all item prices
    public static double calculateTotal(List<Orderable> items ) {
        return items.stream()
                .mapToDouble(Orderable::calculatePrice)
                .sum();
    }

    // Returns the calculated price for a single item
    public static double calculateItemPrice(Orderable item) {
        return item.calculatePrice();
    }

}
