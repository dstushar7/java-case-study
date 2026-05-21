package com.solvians.showcase.generator;

/**
 * Strategy for producing ISIN-style identifier strings. Other implementations
 * could cover related schemes such as CUSIP or SEDOL.
 */
public interface IsinGenerator {

    /**
     * Returns a newly-generated 12-character identifier.
     */
    String generate();

    /**
     * Computes the check digit for an 11-character identifier body using the
     * Luhn-style algorithm specified in ISO 6166.
     *
     * @param body 11 characters: 2 uppercase letters followed by 9 alphanumeric
     * @return the check digit, 0-9
     * @throws IllegalArgumentException if body is null or not 11 characters
     */
    static int calculateCheckDigit(String body) {
        if (body == null || body.length() != 11) {
            throw new IllegalArgumentException("ISIN body must be 11 characters: " + body);
        }
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < body.length(); i++) {
            char c = body.charAt(i);
            if (c >= '0' && c <= '9') {
                digits.append(c);
            } else {
                digits.append(c - 'A' + 10);
            }
        }

        int sum = 0;
        int len = digits.length();
        for (int i = 0; i < len; i++) {
            int d = digits.charAt(i) - '0';
            if ((len - 1 - i) % 2 == 0) {
                d *= 2;
            }
            sum += d / 10 + d % 10;
        }
        return (10 - sum % 10) % 10;
    }
}
