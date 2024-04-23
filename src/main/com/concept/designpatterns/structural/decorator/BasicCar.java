package src.main.com.concept.designpatterns.structural.decorator;

public class BasicCar implements Car {

    @Override
    public void assemble() {
        System.out.println("Base car");
    }
}
