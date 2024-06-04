package src.main.com.concept.designpatterns.behavioral.memento;

public class Memento {
    private int previousValue;

    public Memento(int previousValue){
        this.previousValue = previousValue;
    }

    public int getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(int previousValue) {
        this.previousValue = previousValue;
    }
}
