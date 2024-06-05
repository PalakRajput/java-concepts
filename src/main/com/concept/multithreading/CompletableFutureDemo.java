package src.main.com.concept.multithreading;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //allOf
//        usageOfAllOf();
//        //anyOf
//        usageOfAnyOf();
//        usageOfThenCompose();
//        usageOfThenApply();
//        usageOfThenAccept();
//        usageOfThenRun();
        //for errors
        usageOfExceptionally();
        usageOfHandle();

    }

    private static void usageOfHandle() throws ExecutionException, InterruptedException {
        Integer age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println("Oops! We have an exception - " + ex.getMessage());
                return "Unknown!";
            }
            return result;
        });

        System.out.println("Maturity : " + maturityFuture.get());
    }

    private static void usageOfExceptionally() throws ExecutionException, InterruptedException {
        Integer age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });

        System.out.println("Maturity : " + maturityFuture.get());
    }

    private static void usageOfAllOf() {
        long startTime = System.currentTimeMillis();
        List<CompletableFuture<String>> completableFutures = Arrays.asList(futureOne(), futureTwo(), futureThree());
        //allOf returns CompletableFuture<Void>
        CompletableFuture<Void> allFuture = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));
        System.out.println("Hello from Main::" + Thread.currentThread().getName());
        sleep(6);
        //This is to get the result of
        CompletableFuture<List<String>> allFutureResults = allFuture
                .thenApply(t -> completableFutures.stream().map(CompletableFuture::join)
                        .collect(Collectors.toList()));
        System.out.println("Result from allOf allFutureResult.join: " + allFutureResults.join());
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken by allOf::" + (endTime - startTime) / 1000);
    }

    private static void usageOfAnyOf() {
        long startTime = System.currentTimeMillis();
        List<CompletableFuture<String>> completableFutures = Arrays.asList(futureOne(), futureTwo(), futureThree());
        CompletableFuture<Object> future = CompletableFuture.anyOf(completableFutures.toArray(new CompletableFuture[0]));
        System.out.println("Hello from Main for anyOf::" + Thread.currentThread().getName());
        System.out.println(future.join());
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken by anyOf::" + (endTime - startTime) / 1000);
    }

    private static void usageOfThenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<String> res = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
            return "cf1 execution done";
        });
        res.thenRun(() -> System.out.println("\nProcessed result from thenRun"));
        System.out.println("After thenRun: " + res.get());
        System.out.println("--------------------------------------------------------------");

    }

    private static void usageOfThenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<String> res = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.print(i + " ");
            }
            return "cf1 execution done for thenAccept";
        });
        res.thenAccept(cf -> System.out.println(cf + " Processed result;"));
        System.out.println("After thenAccept: " + res.get());
        System.out.println("--------------------------------------------------------------");
    }

    private static void usageOfThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> res = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
            return "cf1 execution done for thenApply";
        });
        CompletableFuture<String> stringCompletableFuture = res.thenApply(cf -> cf + "Processed result;");
        System.out.println("After thenApply: " + stringCompletableFuture.get());
        System.out.println("After thenApply original cf: " + res.get());
        System.out.println("--------------------------------------------------------------");
    }

    static CompletableFuture<String> cf1() {
        return CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
            return "cf1 execution done";
        });
    }

    static CompletableFuture<String> cf2(String value) {
        return CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
            System.out.println(value);
            return "cf2 execution done";
        });
    }

    private static void usageOfThenCompose() throws ExecutionException, InterruptedException {

        CompletableFuture<String> cf3 = cf1().thenCompose(val -> cf2(val));
        System.out.println(cf3.get());
    }
}
