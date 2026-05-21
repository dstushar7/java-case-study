package com.solvians.showcase.generator;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * IsinGenerator producing uniformly-random ISINs using {@link ThreadLocalRandom}.
 */
public final class RandomIsinGenerator implements IsinGenerator {

    @Override
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
        isin[11] = (char) ('0' + IsinGenerator.calculateCheckDigit(new String(isin, 0, 11)));
        return new String(isin);
    }
}
