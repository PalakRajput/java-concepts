package src.main.com.concept.multithreading;

public class MultiThreadDemo implements Runnable {

    //Runnable runnable = () -> System.out.println("Hello world");

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new MultiThreadDemo());
        t.start();
        t.join();
        for (int i = 6; i <=10; i++){
            System.out.print(i + " ");
        }
    }

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
        for (int i = 1; i <=5; i++){
            System.out.print(i + " ");
        }
    }
}
