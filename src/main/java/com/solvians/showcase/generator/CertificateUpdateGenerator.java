package com.solvians.showcase.generator;

import com.solvians.showcase.model.CertificateUpdate;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public final class CertificateUpdateGenerator implements Callable<String> {

    private final IsinGenerator isinGenerator;

    public CertificateUpdateGenerator(IsinGenerator isinGenerator) {
        this.isinGenerator = isinGenerator;
    }

    @Override
    public String call() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        CertificateUpdate update = new CertificateUpdate(
                System.currentTimeMillis(),
                isinGenerator.generate(),
                randomPrice(random),
                random.nextInt(1000, 5001),
                randomPrice(random),
                random.nextInt(1000, 10001));
        return update.toString();
    }

    private static double randomPrice(ThreadLocalRandom random) {
        return 100.00 + random.nextInt(10001) / 100.0;
    }
}
