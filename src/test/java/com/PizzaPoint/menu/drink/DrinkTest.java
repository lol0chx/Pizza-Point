package com.PizzaPoint.menu.drink;

import com.PizzaPoint.core.enums.DrinkSize;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrinkTest {

    @Test
    void calculatePrice_returnsCorrectSizePrice() {
        // Arrange
        Drink smallDrink = new Drink("Coke", DrinkSize.SMALL);
        Drink largeDrink = new Drink("Pepsi", DrinkSize.LARGE);

        // Act
        double smallPrice = smallDrink.calculatePrice();
        double largePrice = largeDrink.calculatePrice();

        // Assert
        assertEquals(DrinkSize.SMALL.getPrice(), smallPrice, 0.01, "Small drink should match size price");
        assertEquals(DrinkSize.LARGE.getPrice(), largePrice, 0.01, "Large drink should match size price");
    }

    @Test
    void getName_returnsCorrectDrinkName() {
        // Arrange
        Drink drink = new Drink("Sprite", DrinkSize.MEDIUM);

        // Act
        String name = drink.getName();

        // Assert
        assertEquals("Sprite", name, "Drink name should be Sprite");
    }
}
