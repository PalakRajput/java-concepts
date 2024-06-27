package src.main.com.concept.multithreading;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ProducerConsumerExample {

    private static final int QUEUE_CAPACITY = 10;
    private static final int PRODUCER_COUNT = 2;
    private static final int CONSUMER_COUNT = 2;

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

        ExecutorService executorService = Executors.newFixedThreadPool(PRODUCER_COUNT + CONSUMER_COUNT);

        for (int i = 0; i < PRODUCER_COUNT; i++) {
            executorService.execute(new Producer(queue));
        }

        for (int i = 0; i < CONSUMER_COUNT; i++) {
            executorService.execute(new Consumer(queue));
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = produce();
                queue.put(value);
                System.out.println("Produced: " + value);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int produce() {
        return (int) (Math.random() * 100);
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = queue.take();
                System.out.println("Consumed: " + value);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
public class ProducerConsumerImpl {
    final Queue<Integer> queue = new ArrayDeque<>();
    int size = 10;
    Random random = new Random();

    public void produce() {
        while (true) { //for the code to run infinetely
            synchronized (this) {
                while (queue.size() == size) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                System.out.println("Adding data to queue: ");
                queue.add(random.nextInt());
                notifyAll();
            }
        }
    }

    public void consumer() {
        while (true) { //for the code to run infinetely
            synchronized (this) {
                while (queue.isEmpty()) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                System.out.println("Consumed from queue: " + queue.poll());
                this.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumerImpl impl = new ProducerConsumerImpl();
        Thread t1 = new Thread(impl::produce);
        Thread t2 = new Thread(impl::consumer);
        t1.start();
        t2.start();
    }
}
