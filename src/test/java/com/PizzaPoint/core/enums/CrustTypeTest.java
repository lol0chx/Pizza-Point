package com.PizzaPoint.core.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrustTypeTest {
    
    @Test
    void valueOf_returnsCorrectEnumValue() {
        // Arrange & Act
        CrustType stuffed = CrustType.valueOf("STUFFED");
        CrustType regular = CrustType.valueOf("REGULAR");

        // Assert
        assertEquals(CrustType.STUFFED, stuffed, "Should return STUFFED enum");
        assertEquals(CrustType.REGULAR, regular, "Should return REGULAR enum");
    }
}
