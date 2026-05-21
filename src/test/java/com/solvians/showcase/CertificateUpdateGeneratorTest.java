package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CertificateUpdateGeneratorTest {

    @Test
    public void callReturnsValidCsvLine() {
        CertificateUpdateGenerator generator = new CertificateUpdateGenerator(new IsinGenerator());
        String line = generator.call();
        assertTrue(line.matches("\\d+,[A-Z]{2}[A-Z0-9]{9}\\d,\\d+\\.\\d{2},\\d+,\\d+\\.\\d{2},\\d+"),
                "unexpected line: " + line);
    }

    @Test
    public void callProducesRandomData() {
        CertificateUpdateGenerator generator = new CertificateUpdateGenerator(new IsinGenerator());
        assertNotEquals(generator.call(), generator.call());
    }
}
