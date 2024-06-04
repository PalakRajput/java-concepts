package src.main.com.concept.designpatterns.behavioral.memento;

public class Adder {
    private int result;
    Memento memento = new Memento(result);

    public void add(int num){
        memento.setPreviousValue(result);
        result += num;
    }

    public int getResult(){
        return result;
    }

    public void undo(){
        result = memento.getPreviousValue();
    }
}
