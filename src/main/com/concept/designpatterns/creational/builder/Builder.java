package src.main.com.concept.designpatterns.creational.builder;

public interface Builder {
    Builder setSeats(int seats);
    Builder setType(String type);
    Builder setEngine(String engine);

}
