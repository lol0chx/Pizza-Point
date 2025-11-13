package com.PizzaPoint.services;

import com.PizzaPoint.core.enums.CheeseType;
import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.core.enums.SauceType;
import com.PizzaPoint.core.interfaces.Orderable;
import com.PizzaPoint.menu.pizza.Pizza;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    @Test
    void calculateTotal_withMultipleItems_returnsSumOfAllPrices() {
        // Arrange
        List<Orderable> items = new ArrayList<>();
        Pizza pizza1 = new Pizza(10.99, PizzaSize.SMALL, CrustType.REGULAR, SauceType.MARINARA, CheeseType.MOZZARELLA);
        Pizza pizza2 = new Pizza(14.99, PizzaSize.MEDIUM, CrustType.REGULAR, SauceType.MARINARA, CheeseType.MOZZARELLA);
        items.add(pizza1);
        items.add(pizza2);
        double expectedTotal = 10.99 + 14.99;

        // Act
        double actualTotal = PriceCalculator.calculateTotal(items);

        // Assert
        assertEquals(expectedTotal, actualTotal, 0.01, "Total should be sum of all item prices");
    }

    @Test
    void calculateTotal_withEmptyList_returnsZero() {
        // Arrange
        List<Orderable> emptyList = new ArrayList<>();

        // Act
        double total = PriceCalculator.calculateTotal(emptyList);

        // Assert
        assertEquals(0.0, total, 0.01, "Empty list should return 0");
    }
}
