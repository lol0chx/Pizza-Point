package com.PizzaPoint.orders;

import com.PizzaPoint.core.interfaces.Orderable;
import com.PizzaPoint.menu.Pizza;

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
