package com.solvians.showcase.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IsinGeneratorTest {

    @Test
    public void calculateCheckDigit() {
        assertEquals(6, IsinGenerator.calculateCheckDigit("DE123456789"));
    }

    @Test
    public void calculateCheckDigitRejectsInvalidBody() {
        assertThrows(IllegalArgumentException.class, () -> IsinGenerator.calculateCheckDigit(null));
        assertThrows(IllegalArgumentException.class, () -> IsinGenerator.calculateCheckDigit("DE"));
    }
}
