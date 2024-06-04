package src.main.com.concept.designpatterns.behavioral.memento;

public class ClientApp {
    public static void main(String[] args) {
        Adder adder = new Adder();
        adder.add(10);
        adder.add(20);
        System.out.println(adder.getResult());
        adder.undo();
        System.out.println(adder.getResult());
    }
}
