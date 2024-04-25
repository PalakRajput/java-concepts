package src.main.com.concept.designpatterns.behavioral.observer;

public class ClientApp {
    public static void main(String[] args) {
        Observer observer1 = new ObserverImpl("John");
        Observer observer2 = new ObserverImpl("Jane");
        Observer observer3 = new ObserverImpl("Jessica");
        Observer observer4 = new ObserverImpl("James");

        Subject subject = new SubjectImpl();
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.addObserver(observer3);
        subject.addObserver(observer4);

        subject.notifyFollowers("P1234");

        subject.removeObserver(observer3);
        System.out.println("\n*******************************************************\n");
        subject.notifyFollowers("P5678");

    }
}
