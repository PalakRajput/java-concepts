package src.main.com.concept.multithreading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CompletableFutureDemo {
    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static CompletableFuture<String> futureOne() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("futureOne::" + Thread.currentThread().getName());
            sleep(4);
            return "CF1";
        });
    }

    private static CompletableFuture<String> futureTwo() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("futureTwo::" + Thread.currentThread().getName());
            sleep(3);
            return "CF2";
        });
    }

    private static CompletableFuture<String> futureThree() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("futureThree::" + Thread.currentThread().getName());
            sleep(2);
            return "CF3";
        });
    }

    public static void main(String[] args) {
        //allOf
        /*long startTime = System.currentTimeMillis();
        List<CompletableFuture<String>> completableFutures = Arrays.asList(futureOne(), futureTwo(), futureThree());
        CompletableFuture<Void> future = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));
        System.out.println("Hello from Main::" + Thread.currentThread().getName());
        sleep(6);
        CompletableFuture<List<String>> allFutureResults = future
                .thenApply(t -> completableFutures.stream().map(CompletableFuture::join)
                        .collect(Collectors.toList()));
        System.out.println("Result: " + allFutureResults.join());
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken::" + (endTime - startTime) / 1000);*/

        //anyOf
        long startTime = System.currentTimeMillis();
        List<CompletableFuture<String>> completableFutures = Arrays.asList(futureOne(), futureTwo(), futureThree());
        CompletableFuture<Object> future = CompletableFuture.anyOf(completableFutures.toArray(new CompletableFuture[0]));
        System.out.println("Hello from Main::" + Thread.currentThread().getName());
        System.out.println(future.join());
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken::" + (endTime - startTime) / 1000);

    }
}
