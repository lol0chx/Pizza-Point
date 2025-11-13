package com.PizzaPoint.services;

import com.PizzaPoint.core.interfaces.Orderable;

import java.util.List;

public class PriceCalculator {
    public static double calculateTotal(List<Orderable> items ) {
        return items.stream()
                .mapToDouble(Orderable::calculatePrice)
                .sum();
    }

    public static double calculateItemPrice(Orderable item) {
        return item.calculatePrice();
    }

}
