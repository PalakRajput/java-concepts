package src.main.com.concept.designpatterns.behavioral.observer;

public class ObserverImpl implements Observer {
    String username;

    public ObserverImpl(String username) {
        this.username = username;
    }

    @Override
    public void sendEmail(String postId) {
        System.out.println(username + " received an email about new post with id: " + postId);
    }
}
