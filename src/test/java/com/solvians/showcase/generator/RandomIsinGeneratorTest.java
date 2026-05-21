package com.solvians.showcase.generator;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomIsinGeneratorTest {

    @Test
    public void generate() {
        String isin = new RandomIsinGenerator().generate(new Random(42L));

        assertEquals(12, isin.length());
        assertTrue(isin.matches("[A-Z]{2}[A-Z0-9]{9}[0-9]"));
        assertEquals(
                IsinGenerator.calculateCheckDigit(isin.substring(0, 11)),
                Character.digit(isin.charAt(11), 10));
    }
}
