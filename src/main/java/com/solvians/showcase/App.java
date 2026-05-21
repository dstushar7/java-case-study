package com.solvians.showcase;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
    public App(String threads, String quotes) {

    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            int threads = Integer.parseInt(args[0]);
            int quotes = Integer.parseInt(args[1]);

            ExecutorService executor = Executors.newFixedThreadPool(threads);
            try {
                Callable<String> generator = new CertificateUpdateGenerator(new IsinGenerator());
                List<Future<String>> futures = executor.invokeAll(Collections.nCopies(quotes, generator));
                for (Future<String> future : futures) {
                    System.out.println(future.get());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } finally {
                executor.shutdown();
            }
            return;
        }
        throw new RuntimeException("Expect at least number of threads and number of quotes. But got: " + args);
    }
}
