### wait(), notify() and notifyAll()

These methods are used to enable intercommunication of threads. These methods can only be called from synchronized area
otherwise we will get IllegalMonitorStateException.

The Thread which is excepting updation it has to call wait() method and the Thread
which is performing updation it has to call notify() method. After getting notification the
waiting Thread will get those updations.

```java
class ThreadA {
    public static void main(String[] args) throws InterruptedException {
        ThreadB b = new ThreadB();
        b.start();
        synchronized (b) {
            System.out.println("main Thread calling wait() method");//step-1
            b.wait();
            System.out.println("main Thread got notification call");//step-4
            System.out.println(b.total);
        }
    }
}

class ThreadB extends Thread {
    int total = 0;

    public void run() {
        synchronized (this) {
            System.out.println("child thread starts calcuation");//step-2
            for (int i = 0; i <= 100; i++) {
                total = total + i;
            }
            System.out.println("child thread giving notification call");//step-3
            this.notify();
        }
    }
}
```

### yield(), join(), sleep()

yield() is used when we want another thread of same priority to be executed instead of current running thread. If there
are no threads of same priority then the current thread will continue executing.

join() is used when a thread wants to wait for complete execution of another thread. If a Thread t1 executes t2.join()
then t1 should go for waiting state until t2 completes.

sleep() if a Thread don’t want to perform any operation for a particular amount of time then we
should go for sleep() method.

### Synchronized

Synchronized keyword is only applicable for method or code blocks. Only one thread can access the synchronized method or
block at once but other threads can execute the non synchronized methods.

If thread wants to execute the instance synchronized method, then object level lock will be acquired and different
threads with different objects can execute the same method.

If thread wants to execute the static synchronized method, then class level lock will be acquired. At this time no other
threads can execute any other static synchronized methods, but they can access normal static methods, instance methods
or
synchronized instance methods.

```java
class Display {
    public synchronized void wish(String name) {
        for (int i = 0; i < 5; i++) {
            System.out.print("good morning:");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("Exception occurred: {}", e, getMessage(), e);
            }
            System.out.println(name);
        }
    }
}

class MyThread extends Thread {
    Display d;
    String name;

    MyThread(Display d, String name) {
        this.d = d;
        this.name = name;
    }

    public void run() {
        d.wish(name);
    }
}

class SynchronizedDemo {
    public static void main(String[] args) {
        Display d1 = new Display();
        //Since we only have 1 object d1, first t1 will execute and then t2 will execute.
        MyThread t1 = new MyThread(d1, "dhoni");
        MyThread t2 = new MyThread(d1, "yuvaraj");
        t1.start();
        t2.start();

        Display d2 = new Display();
        Display d3 = new Display();
        //Since we have 2 objects d2 and d3, and both threads t3 & t4 are using different objects the output will be irregular.
        MyThread t3 = new MyThread(d2, "dhoni");
        MyThread t4 = new MyThread(d3, "yuvaraj");
        t3.start();
        t4.start();
    }
}
```

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

* Contains various methods to manage lifecycle of individual tasks or executor.
* Provides method to shut down executor service.
* A task can be submitted using execute(), submit(), invokeAny(), invokeAll() methods.
* execute(): es.execute(() -> System.out.println("Hello world")) method is a void method so it doesn't return anything.
* submit() -> ```
  Future<String> future = es.submit(() -> {
  System.out.println("Hello world");
  return "Executed";
  })``` returns a Future object.
* String result = es.invokeAny(listOfCallableTasks) returns the result of any task completed successfully.
* List<Future<String>> results = es.invokeAll(callableTasks) returns the list of
* The executor service won't shutdown automatically. To shutdown the executor service we can use shutdown() or
  shutdownNow().
* shutdown() -> will stop accepting new tasks and shutdown es once the accepted tasks are completed.
* shutdownNow() -> will return the lists of tasks waiting to be processed and tries to shutdown the es.

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

### CountDownLatch

**CountDownLatch:** CountDownLatch is a synchronization aid in Java that allows one or more threads to wait until a set
of
operations, performed by other threads, completes.
**Initialization: new CountDownLatch(5):** This creates a new CountDownLatch with an initial count of 5. The initial
count
represents the number of times the countDown method must be invoked on the latch before it releases awaiting threads.
**Usage:** The coursesReady object is shared among different threads. Each thread, before completing its operation,
calls
countDown on the latch, decrementing the count. Threads waiting for the operations to complete call await, blocking
until the count reaches zero.
Here’s a more detailed explanation of how a CountDownLatch works:

The count is initialized during instantiation, representing the number of times countDown must be called before the
waiting threads are released.
Threads call the countDown method when they complete their tasks, decrementing the count.
Threads waiting for the tasks to complete call the await method. They will be blocked until the count reaches zero.
When the count reaches zero, the waiting threads are released, and the await method returns.

```java
import java.util.concurrent.CountDownLatch;

public class Chef {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch coursesReady = new CountDownLatch(5);

        // Kitchen tasks for each course
        new Thread(new CookingTask("Appetizer", coursesReady)).start();
        new Thread(new CookingTask("Soup", coursesReady)).start();
        new Thread(new CookingTask("Main Course", coursesReady)).start();
        new Thread(new CookingTask("Dessert", coursesReady)).start();
        new Thread(new CookingTask("Coffee", coursesReady)).start();

        // Wait for all courses to be ready
        coursesReady.await();

        System.out.println("All courses are ready! Let's serve the meal.");
    }
}

class CookingTask implements Runnable {
    private final String course;
    private final CountDownLatch latch;

    public CookingTask(String course, CountDownLatch latch) {
        this.course = course;
        this.latch = latch;
    }

    @Override
    public void run() {
        // Simulate cooking time
        try {
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println(course + " is ready!");
            latch.countDown(); // Signal that this course is ready
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

CountDownLatch is suitable when one or more threads need to wait for a fixed number of threads to complete their tasks
before proceeding.
CyclicBarrier is ideal when multiple threads need to synchronize at a predefined point in their execution and then
continue with their tasks.
Semaphore is handy when controlling access to a shared resource with a limited capacity, allowing a specified number of
threads to access the resource simultaneously.