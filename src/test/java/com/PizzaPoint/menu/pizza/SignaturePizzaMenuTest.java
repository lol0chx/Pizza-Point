package com.PizzaPoint.menu.pizza;

import com.PizzaPoint.core.enums.PizzaSize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignaturePizzaMenuTest {

    @Test
    void createMeatLovers_returnsCorrectPizza() {
        // Arrange
        PizzaSize size = PizzaSize.LARGE;

        // Act
        SignaturePizza pizza = SignaturePizzaMenu.createMeatLovers(size);

        // Assert
        assertEquals("Meat Lovers", pizza.getName(), "Pizza name should be Meat Lovers");
        assertEquals(size, pizza.getSize(), "Pizza size should match requested size");
        assertFalse(pizza.getToppingsMap().isEmpty(), "Meat Lovers should have toppings");
    }

    @Test
    void createVeggieSupreme_returnsCorrectPizza() {
        // Arrange
        PizzaSize size = PizzaSize.MEDIUM;

        // Act
        SignaturePizza pizza = SignaturePizzaMenu.createVeggieSupreme(size);

        // Assert
        assertEquals("Veggie Supreme", pizza.getName(), "Pizza name should be Veggie Supreme");
        assertEquals(size, pizza.getSize(), "Pizza size should match requested size");
        assertFalse(pizza.getToppingsMap().isEmpty(), "Veggie Supreme should have toppings");
    }

    @Test
    void getSignaturePizzaNames_returnsAllPizzaNames() {
        // Arrange & Act
        var names = SignaturePizzaMenu.getSignaturePizzaNames();

        // Assert
        assertEquals(5, names.size(), "Should have 5 signature pizzas");
        assertTrue(names.contains("Meat Lovers"), "Should include Meat Lovers");
        assertTrue(names.contains("Veggie Supreme"), "Should include Veggie Supreme");
        assertTrue(names.contains("Hawaiian"), "Should include Hawaiian");
    }
}
