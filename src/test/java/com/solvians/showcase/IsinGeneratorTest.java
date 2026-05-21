package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IsinGeneratorTest {

    @Test
    public void calculateCheckDigit() {
        assertEquals(6, IsinGenerator.calculateCheckDigit("DE123456789"));
    }
}
