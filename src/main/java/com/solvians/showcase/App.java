package com.solvians.showcase;

/**
 * Hello world!
 */
public class App {
    public App(String threads, String quotes) {

    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            int threads = Integer.parseInt(args[0]);
            int quotes = Integer.parseInt(args[1]);

            CertificateUpdateGenerator generator = new CertificateUpdateGenerator(new IsinGenerator());
            for (int i = 0; i < quotes; i++) {
                System.out.println(generator.call());
            }
            return;
        }
        throw new RuntimeException("Expect at least number of threads and number of quotes. But got: " + args);
    }
}
