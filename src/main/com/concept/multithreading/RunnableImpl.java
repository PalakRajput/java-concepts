package src.main.com.concept.multithreading;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableImpl implements Runnable {

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for(int i=0; i< 10; i++){
            System.out.println(Thread.currentThread().getName() + " Run method called: " + i);
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableImpl());
        thread.start();

        //The main method will keep on running unless the executor service is shut down.


        //Create 5 threads
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<?> future = service.submit(new RunnableImpl());

        //All queued/waiting tasks are completed but no new tasks are submitted.
        service.shutdown();

        //Only a single thread is created
        Executor service1 = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> System.out.println("Hey");
        service1.execute(runnable);

        //Returns list of waiting task and tries to shut down.
//        service1.shutdownNow();


    }
}
