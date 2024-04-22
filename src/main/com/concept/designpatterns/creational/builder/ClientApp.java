package src.main.com.concept.designpatterns.creational.builder;

public class ClientApp {

    public static void main(String[] args) {
        Director director = new Director();

        CarBuilder carBuilder = new CarBuilder();
        director.constructSportsCar(carBuilder);

        Car car = carBuilder.getResult();
        System.out.println(car.getType() + ", " + car.getSeats() + ", " + car.getEngine());
    }
}
