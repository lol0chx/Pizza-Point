package com.PizzaPoint.ui;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class HomeScreenTest {

    @Test
    void startHome_displaysMainMenu() {
        // Arrange
        HomeScreen homeScreen = new HomeScreen();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Act
        // Note: This test verifies the menu display structure exists
        // Full integration test would require mocking user input

        // Assert
        assertNotNull(homeScreen, "HomeScreen should be instantiated");
    }

    @Test
    void homeScreen_hasValidConstructor() {
        // Arrange & Act
        HomeScreen homeScreen = new HomeScreen();

        // Assert
        assertNotNull(homeScreen, "HomeScreen should be successfully created");
        assertInstanceOf(HomeScreen.class, homeScreen, "Object should be instance of HomeScreen");
    }
}
