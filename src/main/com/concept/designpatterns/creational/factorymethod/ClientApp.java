package src.main.com.concept.designpatterns.creational.factorymethod;

/**
 * In Factory design pattern, the creation logic of objects is not exposed to the client.
 * Let's say we have an interface called Computer which has methods like, getMemory(), getCPU(), etc.
 * Now we have two classes PC and Server so instead of creation objects in ClientApp we create the object in factory class and returns it.
 * <p>
 * Create a main class which calls factory class
 * Factory class returns required class instance.
 */
public class ClientApp {
    public static void main(String[] args) {
        //Create factory object
        String type = "PC";
        ComputerFactory computerFactory = null;
        if ("PC".equals(type)) {
            computerFactory = new PCFactoryCreator();
        } else if ("Server".equals(type)) {
            computerFactory = new ServerFactoryCreator();
        }
        Computer computer = computerFactory.getComputer();
        computer.print();
    }
}
