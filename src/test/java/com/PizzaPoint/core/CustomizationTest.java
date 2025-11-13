package com.PizzaPoint.core;

import com.PizzaPoint.core.enums.CrustType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomizationTest {

    @Test
    void add_incrementsCountForExistingOption() {
        // Arrange
        Customization<CrustType> customization = new Customization<>();
        CrustType crust = CrustType.STUFFED;

        // Act
        customization.add(crust);
        customization.add(crust);
        customization.add(crust);

        // Assert
        assertEquals(3, customization.getAll().get(crust), 
            "Adding same option 3 times should result in count of 3");
    }

    @Test
    void remove_decrementsCountAndRemovesWhenZero() {
        // Arrange
        Customization<CrustType> customization = new Customization<>();
        CrustType crust = CrustType.THIN;
        customization.add(crust);
        customization.add(crust);

        // Act
        customization.remove(crust);  // Count should be 1
        int countAfterFirstRemove = customization.getAll().get(crust);
        customization.remove(crust);  // Count should be 0, key removed

        // Assert
        assertEquals(1, countAfterFirstRemove, "After one remove, count should be 1");
        assertFalse(customization.getAll().containsKey(crust), 
            "After removing all, option should not exist in map");
    }

    @Test
    void singleChoiceMode_replacesInsteadOfAdding() {
        // Arrange
        Customization<CrustType> customization = new Customization<>(true);  // Single choice mode
        CrustType firstChoice = CrustType.THIN;
        CrustType secondChoice = CrustType.THICK;

        // Act
        customization.add(firstChoice);
        customization.add(secondChoice);  // Should replace, not add

        // Assert
        assertFalse(customization.getAll().containsKey(firstChoice), 
            "First choice should be replaced in single-choice mode");
        assertTrue(customization.getAll().containsKey(secondChoice), 
            "Second choice should be present");
        assertEquals(1, customization.getAll().size(), 
            "Single-choice mode should only have one option");
    }
}
