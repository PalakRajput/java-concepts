# Executor

The Executor interface has a single execute method to submit Runnable instances for execution.

```java
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class ExecutorDemo {

    public static void main(String[] args) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("Print from executor"));
    }
}
```

# Executor Service

Contains various methods to manage lifecycle of individual tasks or executor.
Provides method to shut down executor service.

```java
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ExecutorServiceDemo {
    public static void main(String[] args) {
        //The Executors helper class contains several methods for the creation of preconfigured thread pool instances.
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //Runnable task
        executorService.submit(() -> System.out.println("Print from executor service"));
        //Callable task
        Callable<Integer> sumOfInt = () -> {
            int result = 0;
            for (int i = 0; i < 10; i++) {
                result += i;
            }
            return result;
        };
        Future<Integer> future = executorService.submit(sumOfInt);
        System.out.println(future.get());
        //Shut down executor service otherwise it will keep on running.
        executorService.shutdown();
    }
}
```

# ThreadPoolExecutor

Under the hood, Executors.newFixedThreadPool(nThreads) calls constructor of ThreadPoolExecutor to create thread pools

## Executors Class' static methods to create thread pools:

# newFixedThreadPool

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class FixedThreadDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        assertEquals(2, executor.getPoolSize());
        //Because the thread pool was created with only 2 threads and we submitted 3 tasks so 1 task will be added to queue.
        assertEquals(1, executor.getQueue().size());
    }
}
```

# newCachedThreadPool

```java
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class CachedThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor =
                (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });
        executor.submit(() -> {
            Thread.sleep(1000);
            return null;
        });

        //Cached thread pool will create more threads if no existing threads are available for performing the submitted task.
        assertEquals(3, executor.getPoolSize());
        assertEquals(0, executor.getQueue().size());
    }
}
```

# newSingleThreadExecutor

Single thread is created and tasks are performed sequentially.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SingleThreadDemo {
    public static void main(String[] args) {
        //The returned single thread executor is immutable so that it can be reconfigured.
        ExecutorService service = Executors.newSingleThreadExecutor();

    }
}
```

# newScheduledThreadPool

1. schedule method allows us to run a task once after a specified delay.
2. scheduleAtFixedRate method allows us to run a task after a specified initial delay and then run it repeatedly with a
   certain period. The period argument is the time measured between the starting times of the tasks, so the execution
   rate is fixed.
3. scheduleWithFixedDelay method is similar to scheduleAtFixedRate in that it repeatedly runs the given task, but the
   specified delay is measured between the end of the previous task and the start of the next. The execution rate may
   vary depending on the time it takes to run any given task.

```java
import java.util.concurrent.*;

class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        CountDownLatch lock = new CountDownLatch(3);
        //Run the task after 500ms and repeat it after every 100ms thrice and then cancel it. 
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            System.out.println("Hello World");
            lock.countDown();
        }, 500, 100, TimeUnit.MILLISECONDS);

        lock.await(1000, TimeUnit.MILLISECONDS);
        future.cancel(true);

    }
}
```