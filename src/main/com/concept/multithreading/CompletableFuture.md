## CompletableFuture

Introduced in java 8. It implements Future interface.

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CompletableFutureWithFuture {
    public Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    public static void main(String[] args) {
        Future<String> completableFuture = new CompletableFutureWithFuture().calculateAsync();

        String result = completableFuture.get();
        assertEquals("Hello", result);
    }
}
```

> Static methods ```CompletableFuture.runAsync()``` or ```CompletableFuture.supplyAsync()``` allow us to create a
> CompletableFuture instance out of Runnable and Supplier functional types correspondingly.
>
> The Runnable interface is the same old interface used in threads and does not allow to return a value.
>
> The Supplier interface is a generic functional interface with a single method that has no arguments and returns a
> value of a parameterized type.
> 
> ***Join method:*** *It is an instance method of the CompletableFuture class. It is used to return the value when the future is complete or throws an unchecked exception if completed exceptionally.*
>
> ***supplyAsync:*** *This method takes a Supplier as an argument and returns CompletableFuture of expected result data type.*

```java
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello, world!";
        });

        future.thenAccept(result -> System.out.println(result));

        //Chaining of completable future

        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> 10)
                .thenApplyAsync(x -> x * 2)
                .thenApplyAsync(x -> x - 10);

        result.thenAccept(System.out::println);
    }
}
```

| Future                                                                                              | CompletableFuture                                                                                       |
|:----------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------|
| Blocking call to get() method to fetch result.                                                      | Results can be achieved using non-blocking methods such as <br/>`thenAccept()`, `thenApply()`, `join()` |
| It is difficult to chain multiple asynchronous operations together                                  | Provides method such as `thenCompose()`, `allOf()`, `thenCombine()` to chain operations.                |
| We can only check if the computation is successful or not and have to handle exceptions explicitly. | Better exception handling with methods such as `handle()`, `exceptionally()`.                           ||                                                                                                         |

---

```java
import java.util.concurrent.CompletableFuture;

class CompletableFutureDemoWithMultipleTasks {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello from future1.");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Hello from future2.");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "Hello from future3.");

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);
        //allFutures is combining three computations and will complete when all 3 futures complete.
        allFutures.thenRun(() -> {
            System.out.println(future1.join());
            System.out.println(future2.join());
            System.out.println(future3.join());
        });

    }
}
```
