package src.main.com.concept.designpatterns.creational.builder;

public class Car {
    int seats;
    String type;
    String engine;

    public Car(int seats, String type, String engine) {
        this.seats = seats;
        this.type = type;
        this.engine = engine;
    }

    public int getSeats() {
        return seats;
    }

    public String getType() {
        return type;
    }

    public String getEngine() {
        return engine;
    }
}
