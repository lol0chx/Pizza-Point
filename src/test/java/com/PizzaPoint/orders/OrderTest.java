package com.PizzaPoint.orders;

import com.PizzaPoint.core.enums.CheeseType;
import com.PizzaPoint.core.enums.CrustType;
import com.PizzaPoint.core.enums.PizzaSize;
import com.PizzaPoint.core.enums.SauceType;
import com.PizzaPoint.menu.pizza.Pizza;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void addItem_addsItemToOrder() {
        // Arrange
        Order order = new Order();
        Pizza pizza = new Pizza(10.99, PizzaSize.SMALL, CrustType.REGULAR, SauceType.MARINARA, CheeseType.MOZZARELLA);

        // Act
        order.addItem(pizza);

        // Assert
        assertEquals(1, order.getItems().size(), "Order should contain 1 item");
        assertTrue(order.getItems().contains(pizza), "Order should contain the pizza");
    }

    @Test
    void removeItem_removesItemFromOrder() {
        // Arrange
        Order order = new Order();
        Pizza pizza = new Pizza(10.99, PizzaSize.SMALL, CrustType.REGULAR, SauceType.MARINARA, CheeseType.MOZZARELLA);
        order.addItem(pizza);

        // Act
        order.removeItem(pizza);

        // Assert
        assertEquals(0, order.getItems().size(), "Order should be empty after removing item");
        assertFalse(order.getItems().contains(pizza), "Order should not contain the pizza");
    }

    @Test
    void setCustomerName_storesCustomerName() {
        // Arrange
        Order order = new Order();
        String customerName = "John Doe";

        // Act
        order.setCustomerName(customerName);

        // Assert
        assertEquals(customerName, order.getCustomerName(), "Customer name should match");
    }
}
