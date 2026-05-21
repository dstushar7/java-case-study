package com.solvians.showcase;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class IsinGenerator {

    public String generate() {
        return generate(ThreadLocalRandom.current());
    }

    String generate(Random random) {
        char[] isin = new char[12];
        isin[0] = (char) ('A' + random.nextInt(26));
        isin[1] = (char) ('A' + random.nextInt(26));
        for (int i = 2; i < 11; i++) {
            int n = random.nextInt(36);
            isin[i] = (char) (n < 10 ? '0' + n : 'A' + n - 10);
        }
        isin[11] = (char) ('0' + calculateCheckDigit(new String(isin, 0, 11)));
        return new String(isin);
    }

    public static int calculateCheckDigit(String body) {
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
