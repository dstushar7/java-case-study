package com.solvians.showcase;

import com.solvians.showcase.generator.CertificateUpdateGenerator;
import com.solvians.showcase.generator.RandomIsinGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class App {

    private final int threads;
    private final int quotes;

    public App(int threads, int quotes) {
        if (threads <= 0) {
            throw new IllegalArgumentException("threads must be positive: " + threads);
        }
        if (quotes < 0) {
            throw new IllegalArgumentException("quotes must be non-negative: " + quotes);
        }
        this.threads = threads;
        this.quotes = quotes;
    }

    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        try {
            Callable<String> generator = new CertificateUpdateGenerator(new RandomIsinGenerator());
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
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Expect 2 args: <threads> <quotes>. Got: " + Arrays.toString(args));
        }
        int threads = Integer.parseInt(args[0]);
        int quotes = Integer.parseInt(args[1]);
        new App(threads, quotes).run();
    }
}
