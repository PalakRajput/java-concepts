package src.main.com.concept.designpatterns.creational.singleton;

public class ThreadSafeSynchronizedMethod {
    private static ThreadSafeSynchronizedMethod instance;

    private ThreadSafeSynchronizedMethod() {

    }

    //Works fine but adding synchronized to a method is costly operation
    public static synchronized ThreadSafeSynchronizedMethod getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSynchronizedMethod();
        }
        return instance;
    }

    /**
     * Double-checked locking is the practice of checking a lazy-initialized object’s state both before and after a synchronized block is entered to determine whether to initialize the object. In early JVM versions, synchronizing entire methods was not performant, which sometimes caused this practice to be used in its place.
     *
     * @return
     */
    public static ThreadSafeSynchronizedMethod getInstanceUsingDoubleLocking() {
        if (instance == null) {
            synchronized (ThreadSafeSynchronizedMethod.class) {
                if (instance == null) {
                    instance = new ThreadSafeSynchronizedMethod();
                }
            }
        }
        return instance;
    }
}
