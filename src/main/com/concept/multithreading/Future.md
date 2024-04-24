## Future

This represents a future result of an asynchronous computation.

```java
import java.util.concurrent.*;

public class SquareCalculator {

    private ExecutorService executor
            = Executors.newSingleThreadExecutor();

    public Future<Integer> calculate(Integer input) {
        Callable<Integer> callable = () -> {
            Thread.sleep(1000);
            return input * input;
        };
        return executor.submit(callable);
    }

    public static void main(String[] args) {
        SquareCalculator squareCalculator = new SquareCalculator();
        Future<Integer> future = squareCalculator.calculate(100);

        boolean isFutureTaskCompleted = future.isDone();
        while (!future.isDone()) {
            System.out.println("Task is not yet processed.");
        }

        //This is blocking call which returns the future object value.
        Integer value = future.get();
        //Integer result = future.get(500, TimeUnit.MILLISECONDS);
        //The difference between get(long, TimeUnit) and get() is that the get(long, Timeunit) will throw a TimeoutException if the task doesn’t return before the specified timeout period.
        
        //To cancel the future task
        Future<Integer> future1 = new SquareCalculator().calculate(4);
        boolean canceled = future1.cancel(true);
        boolean isFutureTaskCancelled = future1.isCancelled();
        
        //The below line will throw CancellationException as the future is cancelled above 
        //future1.get();
    }
}
```