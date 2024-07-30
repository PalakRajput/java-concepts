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
                System.out.print(count + " ");
                count++;
                notify();
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
                System.out.print(count + " "); //If number is even print it
                count++; //increment the count
                notify(); //notify other thread
            }
        }
    }

    public static void main(String[] args) {
        N = 10;
        EvenOddDemo myClass = new EvenOddDemo();
        Thread t1 = new Thread(() -> myClass.printEvenNumber());
        Thread t2 = new Thread(myClass::printOddNumber);
        t1.start();
        t2.start();
    }
}
