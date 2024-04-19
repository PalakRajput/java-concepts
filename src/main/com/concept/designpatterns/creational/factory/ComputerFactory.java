package src.main.com.concept.designpatterns.creational.factory;

public class ComputerFactory {
    Computer computer = null;
    public Computer getComputer(String type){
        if ("PC".equals(type)) {
            computer = new PC(4, 4);
        } else if ("Server".equals(type)) {
            computer = new Server(8, 8);
        }
        return computer;
    }
}
