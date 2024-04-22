package src.main.com.concept.designpatterns.creational.factorymethod;

public class ServerFactoryCreator implements ComputerFactory {

    @Override
    public Computer getComputer() {
        return new Server(8, 8);
    }
}
