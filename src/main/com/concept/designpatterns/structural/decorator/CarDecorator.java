package src.main.com.concept.designpatterns.structural.decorator;

public class CarDecorator implements Car {
    //Should be accessible to child decorator class
    protected Car car;

    public CarDecorator(Car car){
        this.car = car;
    }

    @Override
    public void assemble() {
        this.car.assemble();
    }
}
