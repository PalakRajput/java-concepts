package src.main.com.concept.multithreading;

import java.util.concurrent.*;

public class CallableImpl implements Callable<Integer> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        //service.execute(Runnable); return type is void
//        service.invokeAny()
        Future<Integer> future = service.submit(new CallableImpl());

        try {
            int sum  = future.get();
            System.out.println(sum);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }
}
