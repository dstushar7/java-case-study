package com.solvians.showcase.generator;

public interface IsinGenerator {

    String generate();

    static int calculateCheckDigit(String body) {
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
