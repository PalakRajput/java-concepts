package src.main.com.concept.designpatterns.creational.builder;

public class CarBuilder implements Builder {
    int seats;
    String type;
    String engine;

    @Override
    public Builder setSeats(int seats) {
        this.seats = seats;
        return this;
    }

    @Override
    public Builder setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public Builder setEngine(String engine) {
        this.engine = engine;
        return this;
    }

    /**
     * This method is present in specific builder class because the Builder interface can be implemented by another class with same attributes so the interface doesn't know which object will be returned
     *
     * @return final product
     */
    public Car getResult() {
        return new Car(seats, engine, type);
    }
}
