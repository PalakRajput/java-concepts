package src.main.com.concept.multithreading;

public class EvenOddDemo {
    int count = 1;

    static int N;

    public void printOddNumber() {
        synchronized (this) {
            while (count < N) {
                while (count % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(count + " from " + Thread.currentThread().getName());
                count++;
                notifyAll();
            }
        }
    }

    public void printEvenNumber() {
        synchronized (this) {
            while (count < N) {
                while (count % 2 == 1) {
                    try {
                        wait(); //wait until the number is odd
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(count + " from " + Thread.currentThread().getName()); //If number is even print it
                count++; //increment the count
                notifyAll(); //notify other thread
            }
        }
    }

    public static void main(String[] args) {
        N = 100;
        EvenOddDemo myClass = new EvenOddDemo();
        Thread t1 = new Thread(() -> myClass.printEvenNumber());
        Thread t2 = new Thread(myClass::printOddNumber);
        Thread t3 = new Thread(() -> myClass.printEvenNumber());
        Thread t4 = new Thread(myClass::printOddNumber);
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");
        t4.setName("t4");
        t1.start();
        t3.start();
        t4.start();
        t2.start();

    }
}
