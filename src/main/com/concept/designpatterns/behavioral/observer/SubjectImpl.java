package src.main.com.concept.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class SubjectImpl implements Subject {
    List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyFollowers(String postId) {
        for(Observer observer: observers){
            observer.sendEmail(postId);
        }
    }
}
