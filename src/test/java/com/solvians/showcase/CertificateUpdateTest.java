package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CertificateUpdateTest {

    @Test
    public void csvFormat() {
        CertificateUpdate update = new CertificateUpdate(
                1352122280502L, "DE1234567896", 101.23, 1000, 103.45, 1000);
        assertEquals("1352122280502,DE1234567896,101.23,1000,103.45,1000", update.toString());
    }

    @Test
    public void rejectsInvalidValues() {
        assertThrows(IllegalArgumentException.class,
                () -> new CertificateUpdate(0L, "DE1234567896", 99.99, 1000, 100.00, 1000));
        assertThrows(IllegalArgumentException.class,
                () -> new CertificateUpdate(0L, "DE1234567896", 100.00, 999, 100.00, 1000));
        assertThrows(IllegalArgumentException.class,
                () -> new CertificateUpdate(0L, "DE1234567896", 100.00, 1000, 200.01, 1000));
        assertThrows(IllegalArgumentException.class,
                () -> new CertificateUpdate(0L, "DE1234567896", 100.00, 1000, 100.00, 10001));
        assertThrows(IllegalArgumentException.class,
                () -> new CertificateUpdate(0L, "DE1234567", 100.00, 1000, 100.00, 1000));
    }

    @Test
    public void acceptsBoundaryValues() {
        new CertificateUpdate(0L, "DE1234567896", 100.00, 1000, 200.00, 10000);
        new CertificateUpdate(0L, "DE1234567896", 200.00, 5000, 100.00, 1000);
    }
}
