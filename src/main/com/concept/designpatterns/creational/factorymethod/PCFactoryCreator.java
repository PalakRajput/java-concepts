package src.main.com.concept.designpatterns.creational.factorymethod;

public class PCFactoryCreator implements ComputerFactory {
    @Override
    public Computer getComputer() {
        return new PC(4, 4);
    }
}
