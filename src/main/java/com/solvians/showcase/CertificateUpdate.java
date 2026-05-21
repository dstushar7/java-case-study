package com.solvians.showcase;

import java.util.Locale;

public final class CertificateUpdate {

    private final long timestamp;
    private final String isin;
    private final double bidPrice;
    private final int bidSize;
    private final double askPrice;
    private final int askSize;

    public CertificateUpdate(long timestamp, String isin,
                             double bidPrice, int bidSize,
                             double askPrice, int askSize) {
        if (isin == null || isin.length() != 12) {
            throw new IllegalArgumentException("ISIN must be 12 characters: " + isin);
        }
        if (bidPrice < 100.00 || bidPrice > 200.00) {
            throw new IllegalArgumentException("Bid price out of range: " + bidPrice);
        }
        if (askPrice < 100.00 || askPrice > 200.00) {
            throw new IllegalArgumentException("Ask price out of range: " + askPrice);
        }
        if (bidSize < 1000 || bidSize > 5000) {
            throw new IllegalArgumentException("Bid size out of range: " + bidSize);
        }
        if (askSize < 1000 || askSize > 10000) {
            throw new IllegalArgumentException("Ask size out of range: " + askSize);
        }
        this.timestamp = timestamp;
        this.isin = isin;
        this.bidPrice = bidPrice;
        this.bidSize = bidSize;
        this.askPrice = askPrice;
        this.askSize = askSize;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "%d,%s,%.2f,%d,%.2f,%d",
                timestamp, isin, bidPrice, bidSize, askPrice, askSize);
    }
}
