package src.main.com.concept.designpatterns.creational.builder;

public class Director {
    public void constructSportsCar(Builder builder) {
        builder.setType("Sports car").setSeats(2).setEngine("Petrol");
    }


    public void constructSUVCar(Builder builder) {
        builder.setType("SUV car").setSeats(6).setEngine("Diesel");
    }
}
