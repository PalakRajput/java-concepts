package src.main.com.concept.designpatterns.behavioral.observer;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyFollowers(String postId);
}
