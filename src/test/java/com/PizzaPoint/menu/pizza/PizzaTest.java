package com.PizzaPoint.menu.pizza;

import com.PizzaPoint.core.enums.CheeseType;
import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.core.enums.SauceType;
import com.PizzaPoint.menu.pizza.topping.ToppingOption;
import com.PizzaPoint.menu.pizza.topping.ToppingMenu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PizzaTest {

    @Test
    void calculatePrice_withBasePizzaNoToppings_returnsBasePriceOnly() {
        // Arrange
        PizzaSize size = PizzaSize.SMALL;
        double basePrice = size.getBasePrice();  // 10.99
        Pizza pizza = new Pizza(basePrice, size, CrustType.REGULAR, SauceType.MARINARA, CheeseType.MOZZARELLA);

        // Act
        double actualPrice = pizza.calculatePrice();

        // Assert
        assertEquals(basePrice, actualPrice, 0.01, 
            "Pizza with no toppings should cost only base price");
    }

    @Test
    void calculatePrice_withToppings_appliesSizeMultiplier() {
        // Arrange
        PizzaSize size = PizzaSize.MEDIUM;  // Multiplier = 1.5
        double basePrice = size.getBasePrice();  // 14.99
        Pizza pizza = new Pizza(basePrice, size, CrustType.REGULAR, SauceType.MARINARA, CheeseType.MOZZARELLA);
        
        ToppingOption pepperoni = ToppingMenu.getAllToppings().get("Pepperoni");  // Base price 1.50
        pizza.add(pepperoni);
        pizza.add(pepperoni);  // 2 portions
        
        double expectedToppingCost = pepperoni.getPrice() * size.getToppingMultiplier() * 2;  // 1.50 * 1.5 * 2 = 4.50
        double expectedTotal = basePrice + expectedToppingCost;  // 14.99 + 4.50 = 19.49

        // Act
        double actualPrice = pizza.calculatePrice();

        // Assert
        assertEquals(expectedTotal, actualPrice, 0.01, 
            "Pizza price should include base price plus toppings with size multiplier");
    }

}
