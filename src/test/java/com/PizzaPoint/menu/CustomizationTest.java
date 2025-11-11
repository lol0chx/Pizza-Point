package com.PizzaPoint.menu;
import com.PizzaPoint.core.Customization;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CustomizationTest {


    @Test
    void addOptionForSingleChoice() {
        // arrange
        Customization<String> crustCustomization = new Customization<>(true); // singleChoice = true
        crustCustomization.add("Thin");
        // act
        crustCustomization.add("Thick"); // Should replace previous option
        // assert
        assertFalse(crustCustomization.getAll().containsKey("Thin"));
        assertTrue(crustCustomization.getAll().containsKey("Thick"));
        assertEquals(1, crustCustomization.getAll().size());
    }
    @Test
    void addSameOptionMultipleTimes() {
        // arrange
        Customization<String> customization = new Customization<>();
        // act
        customization.add("Cheese");
        customization.add("Cheese");
        // assert
        assertEquals(2, customization.getCount("Cheese"));
        assertTrue(customization.getAll().containsKey("Cheese"));
    }

    @Test
    void removeOptionWithMultipleCount() {
        // arrange
        Customization<String> customization = new Customization<>();
        customization.add("Pepperoni");
        customization.add("Pepperoni");

        // act
        customization.remove("Pepperoni");
        // assert
        assertEquals(1, customization.getCount("Pepperoni"));

    }
}