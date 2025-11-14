package com.PizzaPoint.services;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {

    @Test
    void getIntInput_returnsValidInteger_whenInputIsWithinRange() {
        // Arrange
        String input = "2\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Act
        int result = InputHandler.getIntInput("Enter choice: ", 1, 3);

        // Assert
        assertEquals(2, result, "Should return 2 when input is 2 and range is 1-3");
    }

    @Test
    void getDoubleInput_returnsValidDouble_whenInputIsNumeric() {
        // Arrange
        String input = "15.50\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Act
        double result = InputHandler.getDoubleInput("Enter amount: ");

        // Assert
        assertEquals(15.50, result, 0.01, "Should return 15.50 when input is '15.50'");
    }
}
